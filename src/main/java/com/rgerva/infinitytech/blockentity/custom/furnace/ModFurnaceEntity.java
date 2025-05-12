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
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModFurnaceEntity extends BlockEntity implements MenuProvider, RecipeCraftingHolder {
    private final eFurnaceConfigs furnaceConfig;

    public final ContainerData data;

    private int furnaceBurnTime;
    private int recipesUsed;
    public int cookTime;
    public int totalCookTime;

    private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();
    public RecipeType<? extends AbstractCookingRecipe> recipeType;

    public IItemHandler itemHandler = new ItemStackHandler(3){
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
            case null -> null;
        };
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
        this.furnaceBurnTime = tag.getInt("BurnTime");
        this.cookTime = tag.getInt("CookTime");
        this.totalCookTime = tag.getInt("CookTimeTotal");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("BurnTime", this.furnaceBurnTime);
        tag.putInt("CookTime", this.cookTime);
        tag.putInt("CookTimeTotal", this.totalCookTime);
        super.saveAdditional(tag, registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        setChanged();
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
        this.recipes.addTo(recipeHolder.id().location(), 1);
    }

    @Override
    public @Nullable RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, ModFurnaceEntity furnaceEntity){

    }

    public void drops(){

    }


    public List<RecipeHolder<?>> grantStoredRecipeExperience(ServerLevel level, Vec3 worldPosition) {
        List<RecipeHolder<?>> list = new ArrayList<>();

        return list;
    }

}
