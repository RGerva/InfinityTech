/**
 * Class: ModFurnaceEntity
 * Created by: D56V1OK
 * On: 2025/mai.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.blockentity.custom.furnace;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.gui.menu.ModFurnaceMenu;
import com.rgerva.infinitytech.util.types.eFurnaceConfigs;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static java.lang.Math.clamp;

public class ModFurnaceEntity extends BlockEntity implements MenuProvider, RecipeCraftingHolder, StackedContentsCompatible {
    private final eFurnaceConfigs furnaceConfig;

    public final ContainerData data;

    public static final int INPUT = 0;
    public static final int FUEL = 1;
    public static final int OUTPUT = 2;

    public int furnaceBurnTime;
    public int recipesUsed;
    public int cookTime;
    public int totalCookTime;

    public final Object2IntOpenHashMap<ResourceKey<Recipe<?>>> recipes = new Object2IntOpenHashMap<>();
    public RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckSmelting;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckSmoking;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckBlasting;

    public static final Map<Item, Boolean> HAS_RECIPE = new HashMap<>();
    public static final Map<Item, Boolean> HAS_RECIPE_SMOKING = new HashMap<>();
    public static final Map<Item, Boolean> HAS_RECIPE_BLASTING = new HashMap<>();

    public final int[] provides = new int[Direction.values().length];
    protected final int[] lastProvides = new int[this.provides.length];

    public ItemStackHandler itemHandler = new ItemStackHandler(3){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(Objects.requireNonNull(level).isClientSide){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public ModFurnaceEntity(BlockPos pos, BlockState blockState, eFurnaceConfigs eFurnaceConfigs) {
        super(Objects.requireNonNull(getEntityType(eFurnaceConfigs)), pos, blockState);
        this.furnaceConfig = eFurnaceConfigs;
        this.recipeType = RecipeType.SMELTING;
        recipeCheckSmelting = RecipeManager.createCheck(RecipeType.SMELTING);
        recipeCheckSmoking = RecipeManager.createCheck(RecipeType.SMOKING);
        recipeCheckBlasting = RecipeManager.createCheck(RecipeType.BLASTING);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> furnaceBurnTime;
                    case 1 -> recipesUsed;
                    case 2 -> cookTime;
                    case 3 -> totalCookTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> furnaceBurnTime = value;
                    case 1 -> recipesUsed = value;
                    case 2 -> cookTime = value;
                    case 3 -> totalCookTime = value;
                    case 4 -> {}
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        };

    }

    public eFurnaceConfigs getFurnaceConfig() {
        return furnaceConfig;
    }

    public static BlockEntityType<ModFurnaceEntity> getEntityType(eFurnaceConfigs configs){
        return switch (configs){
            case COPPER -> ModBlockEntities.COPPER_FURNACE_ENTITY.get();
            case IRON -> ModBlockEntities.IRON_FURNACE_ENTITY.get();
            case GOLD -> ModBlockEntities.GOLD_FURNACE_ENTITY.get();
            case null -> null;
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.infinity_tech.furnace_" + getFurnaceConfig().name().toLowerCase());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return new ModFurnaceMenu(i, inventory, this, this.data);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);

        itemHandler.deserializeNBT(registries, tag.getCompound(getFurnaceConfig().name().toLowerCase() + ".inventory"));
        this.furnaceBurnTime = tag.getInt(getFurnaceConfig().name().toLowerCase() + ".burn_time");
        this.cookTime = tag.getInt(getFurnaceConfig().name().toLowerCase() + ".cook_time");
        this.totalCookTime = tag.getInt(getFurnaceConfig().name().toLowerCase() + ".cook_time_total");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.@NotNull Provider registries) {

        tag.put(getFurnaceConfig().name().toLowerCase() + ".inventory", itemHandler.serializeNBT(registries));
        tag.putInt(getFurnaceConfig().name().toLowerCase() + ".burn_time", this.furnaceBurnTime);
        tag.putInt(getFurnaceConfig().name().toLowerCase() + ".cook_time", this.cookTime);
        tag.putInt(getFurnaceConfig().name().toLowerCase() + ".cook_time_total", this.totalCookTime);
        super.saveAdditional(tag, registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        setChanged();
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void onDataPacket(@NotNull Connection net, @NotNull ClientboundBlockEntityDataPacket pkt, HolderLookup.@NotNull Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipeHolder) {
        if(recipeHolder != null){
            ResourceKey<Recipe<?>> key = recipeHolder.id();
            this.recipes.addTo(key, 1);
        }
    }

    @Override
    public @Nullable RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ModFurnaceEntity furnaceEntity){
        boolean iFlag = false;
        boolean wasBurning = furnaceEntity.isBurning();

        if (!Objects.requireNonNull(furnaceEntity.level).isClientSide) {

            if (furnaceEntity.totalCookTime != furnaceEntity.getCookTime()) {
                furnaceEntity.totalCookTime = furnaceEntity.getCookTime();
            }
            if (furnaceEntity.doesNeedUpdateSend()) {
                furnaceEntity.onUpdateSent();
            }
        }

        if (!furnaceEntity.level.isClientSide) {
            if (furnaceEntity.isBurning()) {
                --furnaceEntity.furnaceBurnTime;
            }

            ItemStack itemstack = furnaceEntity.itemHandler.getStackInSlot(FUEL);
            if (furnaceEntity.isBurning() || !itemstack.isEmpty() && !furnaceEntity.itemHandler.getStackInSlot(INPUT).isEmpty()) {
                RecipeHolder<? extends AbstractCookingRecipe> irecipe = null;
                if (!furnaceEntity.itemHandler.getStackInSlot(INPUT).isEmpty()) {
                    irecipe = furnaceEntity.getRecipeNonCached(furnaceEntity.itemHandler.getStackInSlot(INPUT));
                }

                boolean valid = furnaceEntity.canSmelt(irecipe);
                if (!furnaceEntity.isBurning() && valid) {

                    furnaceEntity.furnaceBurnTime = getBurnTime(itemstack, furnaceEntity.recipeType) * furnaceEntity.getCookTime() / 200;
                    furnaceEntity.recipesUsed = furnaceEntity.furnaceBurnTime;

                    if (furnaceEntity.isBurning()) {
                        iFlag = true;
                        if (!itemstack.isEmpty() && isFuel(itemstack)){
                            itemstack.shrink(1);
                            furnaceEntity.itemHandler.insertItem(FUEL, itemstack.getCraftingRemainder(), false);
                        }
                    }
                }
                if (furnaceEntity.isBurning() && valid) {
                    ++furnaceEntity.cookTime;
                    if (furnaceEntity.cookTime >= furnaceEntity.totalCookTime) {
                        furnaceEntity.cookTime = 0;
                        furnaceEntity.totalCookTime = furnaceEntity.getCookTime();
                        furnaceEntity.smeltItem(irecipe);
                        iFlag = true;
                    }
                } else {
                    furnaceEntity.cookTime = 0;
                }
            } else if (!furnaceEntity.isBurning() && furnaceEntity.cookTime > 0) {
                furnaceEntity.cookTime = clamp(furnaceEntity.cookTime - 2, 0, furnaceEntity.totalCookTime);
            }
            if (furnaceEntity.level.getGameTime() % 24 == 0) {

                if (furnaceEntity.cookTime <= 0) {

                    if (furnaceEntity.itemHandler.getStackInSlot(INPUT).isEmpty()) {

                        iFlag = true;
                    } else if (furnaceEntity.itemHandler.getStackInSlot(INPUT).getCount() < furnaceEntity.itemHandler.getStackInSlot(INPUT).getMaxStackSize()) {

                        iFlag = true;
                    }
                    if (furnaceEntity.itemHandler.getStackInSlot(FUEL).isEmpty()) {

                        iFlag = true;
                    } else if (furnaceEntity.itemHandler.getStackInSlot(FUEL).getCount() < furnaceEntity.itemHandler.getStackInSlot(FUEL).getMaxStackSize()) {

                        iFlag = true;
                    }
                }
            }
        }

        if (wasBurning != furnaceEntity.isBurning()) {
            level.setBlock(blockPos, level.getBlockState(furnaceEntity.worldPosition).setValue(BlockStateProperties.LIT, furnaceEntity.isBurning()), 3);
        }

        if (iFlag) {
            furnaceEntity.setChanged();
        }

    }

    protected boolean doesNeedUpdateSend() {
        return !Arrays.equals(this.provides, this.lastProvides);
    }

    public void onUpdateSent() {
        System.arraycopy(this.provides, 0, this.lastProvides, 0, this.provides.length);
        Objects.requireNonNull(this.level).updateNeighborsAt(this.worldPosition, getBlockState().getBlock());
    }

    private static boolean isFuel(ItemStack stack){
        if(!stack.isEmpty()){
            Item item = stack.getItem();
            return item.getDefaultInstance().getBurnTime(null, getFuelValues()) > 0;
        }else{
            return false;
        }
    }

    public static FuelValues getFuelValues(){
        return Objects.requireNonNull(Minecraft.getInstance().level).fuelValues();
    }

    public static int getBurnTime(ItemStack stack, RecipeType<?> recipeType) {
        return EventHooks.getItemBurnTime(stack, stack.getBurnTime(recipeType, getFuelValues()), recipeType, getFuelValues());
    }

    public static boolean isItemFuel(ItemStack stack, RecipeType<?> recipeType) {
        return getBurnTime(stack, recipeType) > 0;
    }

    public int getCookTime(){
        if(this.itemHandler.getStackInSlot(INPUT).getItem() == Items.AIR){
            return totalCookTime;
        }
        return Math.max(1, getSpeed());
    }

    protected int getSpeed(){
        int regular = getFurnaceConfig().getBurnSpeed();
        RecipeHolder<? extends AbstractCookingRecipe> iRecipe = getRecipeNonCached(this.itemHandler.getStackInSlot(INPUT));
        if(iRecipe != null){
            int recipeCockTime = iRecipe.value().cookingTime();
            double div = 200.0 / recipeCockTime;
            double result = regular / div;
            return (int)Math.max(1, result);
        }else{
            return 0;
        }
    }

    private ItemStack getResult(AbstractCookingRecipe iRecipe, ItemStack input){
        ItemStack out = iRecipe.assemble(new SingleRecipeInput(input), Objects.requireNonNull(level).registryAccess());
        out.setCount(out.getCount());
        return out;
    }

    protected boolean canSmelt(@Nullable RecipeHolder<?> iRecipe) {
        ItemStack recipeInput = this.itemHandler.getStackInSlot(INPUT);
        if(!recipeInput.isEmpty() && iRecipe != null){
            ItemStack recipeOutput = getResult((AbstractCookingRecipe) iRecipe.value(), recipeInput);
            if(!recipeOutput.isEmpty()){
                ItemStack output = this.itemHandler.getStackInSlot(OUTPUT);
                if(output.isEmpty()){
                    return true;
                } else if (!ItemStack.isSameItemSameComponents(output, recipeOutput)) {
                    return false;
                }else {
                    return output.getCount() + recipeOutput.getCount() <= Math.min(output.getMaxStackSize(), 64);
                }
            }
        }
        return false;
    }

    protected void smeltItem(@Nullable RecipeHolder<?> iRecipe){
        if(iRecipe != null){
            ItemStack input = this.itemHandler.getStackInSlot(INPUT);
            ItemStack itemRecipe = getResult((AbstractCookingRecipe) iRecipe.value(), input);
            ItemStack output = this.itemHandler.getStackInSlot(OUTPUT);

            if(output.isEmpty()){
                this.itemHandler.insertItem(OUTPUT, itemRecipe.copy(), false);
            } else if (output.is(this.itemHandler.getStackInSlot(OUTPUT).getItem())) {
                this.itemHandler.insertItem(OUTPUT, itemRecipe.copy(), false).grow(1);
            }

            if(!Objects.requireNonNull(level).isClientSide){
                this.setRecipeUsed(iRecipe);
            }

            if (input.getItem() == Blocks.WET_SPONGE.asItem() && !this.itemHandler.getStackInSlot(FUEL).isEmpty() && this.itemHandler.getStackInSlot(FUEL).getItem() == Items.BUCKET) {
                this.itemHandler.insertItem(FUEL, new ItemStack(Items.WATER_BUCKET), false);
            }
            input.shrink(1);
        }
    }

    public void drops(){
        SimpleContainer simpleContainer = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            simpleContainer.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(Objects.requireNonNull(this.level), this.worldPosition, simpleContainer);
    }

    public void unlockRecipes(ServerPlayer player) {
        player.awardRecipes(this.grantStoredRecipeExperience(player.serverLevel(), player.position()));
        this.recipes.clear();
    }

    public List<RecipeHolder<?>> grantStoredRecipeExperience(ServerLevel level, Vec3 worldPosition) {
        List<RecipeHolder<?>> list = new ArrayList<>();
        for(Object2IntMap.Entry<ResourceKey<Recipe<?>>> entry : recipes.object2IntEntrySet()){
            level.recipeAccess().byKey(entry.getKey()).ifPresent((h) -> {
                list.add(h);
                splitAndSpawnExperience(level, worldPosition, entry.getIntValue(), ((AbstractCookingRecipe) h.value()).experience());
            });
        }
        return list;
    }

    private static void splitAndSpawnExperience(ServerLevel level, Vec3 worldPosition, int craftedAmount, float experience) {
        int integer = (int) Math.floor((float) craftedAmount * experience);
        float frac = Mth.frac((float) craftedAmount * experience);
        if(frac != 0.0F && Math.random() < (double) frac){
            integer++;
        }
        ExperienceOrb.award(level, worldPosition, integer);
    }

    protected RecipeHolder<? extends AbstractCookingRecipe> getRecipeNonCached(ItemStack stack){
        if (recipeType == RecipeType.SMOKING){
            return this.recipeCheckSmoking.getRecipeFor(new SingleRecipeInput(stack), (ServerLevel) this.level).orElse(null);
        } else if (recipeType == RecipeType.BLASTING){
            return this.recipeCheckBlasting.getRecipeFor(new SingleRecipeInput(stack), (ServerLevel) this.level).orElse(null);
        } else {
            return this.recipeCheckSmelting.getRecipeFor(new SingleRecipeInput(stack), (ServerLevel) this.level).orElse(null);
        }
    }

    public boolean hasRecipe(ItemStack stack){
        Item item = stack.getItem();
        if (recipeType == RecipeType.SMOKING)
        {
            return HAS_RECIPE_SMOKING.computeIfAbsent(item, (value) -> this.recipeCheckSmoking.getRecipeFor(new SingleRecipeInput(stack), (ServerLevel) this.level).isPresent());
        }
        else if (recipeType == RecipeType.BLASTING)
        {
            return HAS_RECIPE_BLASTING.computeIfAbsent(item, (value) -> this.recipeCheckBlasting.getRecipeFor(new SingleRecipeInput(stack), (ServerLevel) this.level).isPresent());

        }
        return HAS_RECIPE.computeIfAbsent(item, (value) -> this.recipeCheckSmelting.getRecipeFor(new SingleRecipeInput(stack), (ServerLevel) this.level).isPresent());
    }

    @Override
    public void fillStackedContents(@NotNull StackedItemContents stackedItemContents) {
        SimpleContainer simpleContainer = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < this.itemHandler.getSlots(); i++){
            stackedItemContents.accountStack(simpleContainer.getItem(i));
        }
    }

}
