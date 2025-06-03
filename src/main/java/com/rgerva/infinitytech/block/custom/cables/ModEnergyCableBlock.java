package com.rgerva.infinitytech.block.custom.cables;

import com.rgerva.infinitytech.blockentity.ModBlockEntities;
import com.rgerva.infinitytech.blockentity.custom.cables.ModCableEntity;
import com.rgerva.infinitytech.blockentity.custom.cables.ModEnergyCableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import org.jetbrains.annotations.Nullable;

public class ModEnergyCableBlock extends ModCableBlock{
    public ModEnergyCableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canConnectTo(Level world, BlockPos pos, Direction facing) {
        return world.getCapability(Capabilities.EnergyStorage.BLOCK, pos.relative(facing), facing.getOpposite()) != null;
    }

    @Override
    public boolean isCable(LevelAccessor world, BlockPos pos, Direction facing) {
        BlockState state = world.getBlockState(pos.relative(facing));
        return state.getBlock().equals(this);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ModEnergyCableEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult onCableSideActivated(ItemStack itemStack, BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, Direction direction) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        //TODO
        return super.onCableSideActivated(itemStack, state, worldIn, pos, player, handIn, hit, direction);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.TEST_CABLE.get(), ModCableEntity::tick);
    }
}
