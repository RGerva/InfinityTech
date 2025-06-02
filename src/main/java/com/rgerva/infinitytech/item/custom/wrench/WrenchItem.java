package com.rgerva.infinitytech.item.custom.wrench;

import com.rgerva.infinitytech.util.ModTags;
import com.rgerva.infinitytech.util.WrenchConfigurable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
    }

    public static boolean isHoldingWrench(Player player){
        for (ItemStack stack : player.getHandSlots()){
            if(isWrench(stack)){
                return true;
            }
        }
        return false;
    }

    private static boolean isWrench(ItemStack stack) {
        return stack.is(ModTags.Items.WRENCH_TAG);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if(level.isClientSide){
            return InteractionResult.SUCCESS;
        }

        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        BlockState state = level.getBlockState(blockPos);
        Block block = state.getBlock();

        if(!(block instanceof WrenchConfigurable wrenchConfigurableBlock)) {
            if(player instanceof ServerPlayer serverPlayer){
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(
                        Component.literal("Can't be configured").withStyle(ChatFormatting.RED)
                ));
            }
            return InteractionResult.SUCCESS;
        }

        ItemStack itemStack = context.getItemInHand();
        Direction currentFace = Direction.DOWN;

        return wrenchConfigurableBlock.onUseWrench(context, currentFace, player != null && player.isShiftKeyDown());
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.wrench.info").
                    withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.infinity_tech.shift_details").withStyle(ChatFormatting.YELLOW));
        }
    }

}
