package com.rgerva.infinitytech.item.custom;

import com.rgerva.infinitytech.block.custom.BatteryBlock;
import com.rgerva.infinitytech.block.custom.CableBlock;
import com.rgerva.infinitytech.block.custom.SolarPanelBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState blockState = level.getBlockState(pos);

        if(!level.isClientSide && player != null){
            Block block = blockState.getBlock();
            if(block instanceof CableBlock || block instanceof SolarPanelBlock || block instanceof BatteryBlock){
                if(player.isShiftKeyDown()){
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    ItemStack itemStack = new ItemStack(block.asItem());
                    ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                    level.addFreshEntity(itemEntity);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.wrench.info").
                    withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }

}
