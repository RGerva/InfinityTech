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
import com.rgerva.infinitytech.network.interfaces.ModSyncPackages;
import com.rgerva.infinitytech.util.types.eFurnaceConfigs;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static java.lang.Math.clamp;

public class ModFurnaceEntity extends BlockEntity implements MenuProvider, ModSyncPackages, RecipeCraftingHolder {
    private final eFurnaceConfigs furnaceConfig;
    public static final int INPUT = 0;
    public static final int FUEL = 1;
    public static final int OUTPUT = 2;

    protected final ContainerData data;
    public final Object2IntOpenHashMap<ResourceKey<Recipe<?>>> recipes = new Object2IntOpenHashMap<>();
    public static final Map<Item, Boolean> HAS_RECIPE = new HashMap<>();
    public RecipeType<? extends AbstractCookingRecipe> recipeType;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckSmelting;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckSmoking;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> recipeCheckBlasting;

    public int furnaceBurnTime;
    public int cookTime;
    public int totalCookTime;

    public final ItemStackHandler itemHandler = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!Objects.requireNonNull(level).isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(),getBlockState(), 3);
            }
        }
    };

    public ModFurnaceEntity(BlockPos pos, BlockState blockState, eFurnaceConfigs eFurnaceConfigs) {
        super(Objects.requireNonNull(getEntityType(eFurnaceConfigs)), pos, blockState);
        this.furnaceConfig = eFurnaceConfigs;
        recipeCheckSmelting = RecipeManager.createCheck(RecipeType.SMELTING);
        recipeCheckSmoking = RecipeManager.createCheck(RecipeType.SMOKING);
        recipeCheckBlasting = RecipeManager.createCheck(RecipeType.BLASTING);
        recipeType = RecipeType.SMELTING;

        this.data = new ContainerData() {

            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> ModFurnaceEntity.this.cookTime;
                    case 1 -> ModFurnaceEntity.this.totalCookTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i){
                    case 0 -> ModFurnaceEntity.this.cookTime = i1;
                    case 1 -> ModFurnaceEntity.this.totalCookTime = i1;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public eFurnaceConfigs getFurnaceConfig() {
        return furnaceConfig;
    }

    public static BlockEntityType<ModFurnaceEntity> getEntityType(eFurnaceConfigs configs){
        return switch (configs){
            case COPPER -> ModBlockEntities.COPPER_FURNACE_ENTITY.get();
            case null -> null;
        };
    }

    @Override
    public BlockEntity getInterfaceSyncBlockEntity() {
        return this;
    }

    @Override
    public int getInterfaceSyncEnergy() {
        return 0;
    }

    @Override
    public int getInterfaceSyncCapacity() {
        return 0;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.infinity_tech.furnace_" + getFurnaceConfig().name().toLowerCase());
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ModFurnaceMenu(i, inventory, this, this.data);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        itemHandler.deserializeNBT(registries, tag.getCompound(getFurnaceConfig().name().toLowerCase() + ".inventory"));
        cookTime = tag.getInt(getFurnaceConfig().name().toLowerCase() + ".cookTime");
        totalCookTime = tag.getInt(getFurnaceConfig().name().toLowerCase() + ".totalCookTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put(getFurnaceConfig().name().toLowerCase() + ".inventory", itemHandler.serializeNBT(registries));
        tag.putInt(getFurnaceConfig().name().toLowerCase() + ".cookTime", cookTime);
        tag.putInt(getFurnaceConfig().name().toLowerCase() + ".totalCookTime", totalCookTime);

        super.saveAdditional(tag, registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
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

    public boolean isBurning(){
        return furnaceBurnTime > 0;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ModFurnaceEntity furnaceEntity){
        if(level.isClientSide()){
            if(furnaceEntity.totalCookTime != furnaceEntity.getCookTime()){
                furnaceEntity.totalCookTime = furnaceEntity.getCookTime();
            }
            if(furnaceEntity.isBurning()){
                --furnaceEntity.furnaceBurnTime;
            }
            furnaceEntity.checkRecipeType();

            ItemStack itemStack = furnaceEntity.itemHandler.getStackInSlot(FUEL);
            if(furnaceEntity.isBurning() || !itemStack.isEmpty() && !furnaceEntity.itemHandler.getStackInSlot(INPUT).isEmpty()){
                RecipeHolder<? extends AbstractCookingRecipe> iRecipe = null;
                if(!furnaceEntity.itemHandler.getStackInSlot(INPUT).isEmpty()){
                    iRecipe = furnaceEntity.getRecipeNonCached(furnaceEntity.itemHandler.getStackInSlot(INPUT));
                }

                boolean isValid = furnaceEntity.canSmelt(iRecipe);
                if(furnaceEntity.isBurning() && isValid){
                    furnaceEntity.cookTime++;
                    if(furnaceEntity.cookTime >= furnaceEntity.totalCookTime){
                        furnaceEntity.cookTime = 0;
                        furnaceEntity.totalCookTime = furnaceEntity.getCookTime();
                        furnaceEntity.smelt(iRecipe);
                    }
                }else {
                    furnaceEntity.cookTime = 0;
                }
            } else if (!furnaceEntity.isBurning() && furnaceEntity.cookTime > 0) {
                furnaceEntity.cookTime = clamp(furnaceEntity.cookTime - 2, 0, furnaceEntity.totalCookTime);
            }
        }

        if(furnaceEntity.isBurning()){
            level.setBlock(blockPos, level.getBlockState(furnaceEntity.worldPosition).setValue(BlockStateProperties.LIT, furnaceEntity.isBurning()), 3);
        }

        furnaceEntity.setChanged();
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    protected void smelt(@Nullable RecipeHolder<?> iRecipe){
        if(iRecipe != null && this.canSmelt(iRecipe)){
            ItemStack itemStack = this.itemHandler.getStackInSlot(INPUT);
            ItemStack itemStack1 = iRecipe.value().assemble(null, RegistryAccess.EMPTY);
            ItemStack itemStack2 = this.itemHandler.getStackInSlot(OUTPUT);
            if(itemStack2.isEmpty()){
                this.itemHandler.setStackInSlot(OUTPUT, itemStack1.copy());
            } else if (itemStack2.getItem() == itemStack1.getItem()) {
                itemStack2.grow(itemStack1.getCount());
            }

            if(!this.level.isClientSide){
                this.setRecipeUsed(iRecipe);
            }

            if(itemStack.getItem() == Blocks.WET_SPONGE.asItem() && !this.itemHandler.getStackInSlot(FUEL).isEmpty() && this.itemHandler.getStackInSlot(FUEL).getItem() == Items.BUCKET){
                this.itemHandler.setStackInSlot(FUEL, new ItemStack(Items.WATER_BUCKET));
            }

            itemStack.shrink(1);
        }
    }

    protected boolean canSmelt(@Nullable RecipeHolder<?> iRecipe){
        if(!this.itemHandler.getStackInSlot(0).isEmpty() && iRecipe != null){
            ItemStack recipeOutput = iRecipe.value().assemble(null, RegistryAccess.EMPTY);
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

    protected void checkRecipeType() {

    }

    public int getCookTime(){
        if(itemHandler.getStackInSlot(INPUT).getItem() == Items.AIR){
            return totalCookTime;
        }
        return Math.max(1, getSpeed());
    }

    protected int getSpeed(){
        int regular = this.getFurnaceConfig().getBurnSpeed();
        RecipeHolder<? extends AbstractCookingRecipe> recipeHolder = getRecipeNonCached(this.itemHandler.getStackInSlot(INPUT));
        if(recipeHolder != null){
            int recipeCookTime = recipeHolder.value().cookingTime();
            double div = 200.0 / recipeCookTime;
            double result = regular / div;
            return (int) Math.max(1, result);
        }else{
            return 0;
        }
    }

    private RecipeHolder<? extends AbstractCookingRecipe> getRecipeNonCached(ItemStack stackInSlot) {
        if(recipeType == RecipeType.SMOKING){
            return this.recipeCheckSmoking.getRecipeFor(new SingleRecipeInput(stackInSlot), (ServerLevel) this.level).orElse(null);
        } else if (recipeType == RecipeType.BLASTING) {
            return this.recipeCheckBlasting.getRecipeFor(new SingleRecipeInput(stackInSlot), (ServerLevel) this.level).orElse(null);
        }else {
            return this.recipeCheckSmelting.getRecipeFor(new SingleRecipeInput(stackInSlot), (ServerLevel) this.level).orElse(null);
        }
    }


    public List<RecipeHolder<?>> grantStoredRecipeExperience(ServerLevel level, Vec3 worldPosition) {
        List<RecipeHolder<?>> list = new ArrayList<>();

        for (Object2IntMap.Entry<ResourceKey<Recipe<?>>> entry : recipes.object2IntEntrySet()){
            level.recipeAccess().byKey(entry.getKey()).ifPresent((h) -> {
                list.add(h);
                splitAndSpawnExperience(level, worldPosition, entry.getIntValue(), ((AbstractCookingRecipe) h.value()).experience());
            });
        }
        return list;
    }

    private static void splitAndSpawnExperience(ServerLevel level, Vec3 worldPosition, int craftedAmount, float experience) {
        int expAmount = (int) Math.floor((float) craftedAmount * experience);
        float frac = Mth.frac((float) craftedAmount * experience);
        if(frac != 0.0F && Math.random() < (double) frac){
            ++expAmount;
        }
        ExperienceOrb.award(level, worldPosition, expAmount);
    }

    public boolean hasRecipe(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return HAS_RECIPE.computeIfAbsent(item, (value) -> this.recipeCheckSmelting.getRecipeFor(new SingleRecipeInput(itemStack), (ServerLevel) this.level).isPresent());
    }

}
