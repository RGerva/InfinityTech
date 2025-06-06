package com.rgerva.infinitytech.blockentity.custom.chest.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.block.custom.chest.ModChestBlock;
import com.rgerva.infinitytech.blockentity.custom.chest.model.ModChestModel;
import com.rgerva.infinitytech.blockentity.custom.chest.model.ModelItem;
import com.rgerva.infinitytech.network.interfaces.IChestPackageUpdate;
import com.rgerva.infinitytech.util.types.eChestConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModChestRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {

    private final ModChestModel model;
    private final BlockEntityRenderDispatcher renderer;
    private static final List<ModelItem> MODEL_ITEMS = Arrays.asList(
            new ModelItem(new Vector3f(0.3F, 0.45F, 0.3F), 3.0F),
            new ModelItem(new Vector3f(0.7F, 0.45F, 0.3F), 3.0F),
            new ModelItem(new Vector3f(0.3F, 0.45F, 0.7F), 3.0F),
            new ModelItem(new Vector3f(0.7F, 0.45F, 0.7F), 3.0F),
            new ModelItem(new Vector3f(0.3F, 0.1F, 0.3F), 3.0F),
            new ModelItem(new Vector3f(0.7F, 0.1F, 0.3F), 3.0F),
            new ModelItem(new Vector3f(0.3F, 0.1F, 0.7F), 3.0F),
            new ModelItem(new Vector3f(0.7F, 0.1F, 0.7F), 3.0F),
            new ModelItem(new Vector3f(0.5F, 0.32F, 0.5F), 3.0F)
    );

    public ModChestRenderer(BlockEntityRendererProvider.Context context) {
        this.renderer = context.getBlockEntityRenderDispatcher();
        this.model = new ModChestModel(context.bakeLayer(new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "chest"), "main")));
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        Level level = blockEntity.getLevel();
        boolean useTileEntityBlockState = level != null;

        BlockState blockState = useTileEntityBlockState ? blockEntity.getBlockState() : ModBlocks.CHEST_IRON.get().defaultBlockState().setValue(ModChestBlock.FACING, Direction.SOUTH);
        Block block = blockState.getBlock();

        eChestConfigs configs = Objects.requireNonNull(ModChestBlock.getTypeFromBlock(block));

        if (block instanceof ModChestBlock) {
            poseStack.pushPose();

            float f = blockState.getValue(ModChestBlock.FACING).toYRot();

            poseStack.translate(0.5D, 0.5D, 0.5D);
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.translate(-0.5D, -0.5D, -0.5D);

            float openness = blockEntity.getOpenNess(partialTick);
            openness = 1.0F - openness;
            openness = 1.0F - openness * openness * openness;


            Material material = new Material(Sheets.CHEST_SHEET, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/chest_" + configs.name().toLowerCase()));
            VertexConsumer vertexConsumer = material.buffer(bufferSource, RenderType::entityCutout);
            this.render(poseStack, vertexConsumer, this.model, openness, packedLight, packedOverlay);

            poseStack.popPose();

            if (blockEntity instanceof IChestPackageUpdate iChest && Vec3.atCenterOf(blockEntity.getBlockPos()).closerThan(this.renderer.camera.getPosition(), 128d)) {
                float rotation = (float) (360D * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) - partialTick;

                for (int j = 0; j < MODEL_ITEMS.size() - 1; j++) {
                    renderItem(poseStack, bufferSource, iChest.getTopItems().get(j), MODEL_ITEMS.get(j), rotation, packedLight);
                }
            }
        }
    }

    private void render(PoseStack poseStack, VertexConsumer buffer, ModChestModel model, float openness, int packedLight, int packedOverlay) {
        model.setupAnim(openness);
        model.renderToBuffer(poseStack, buffer, packedLight, packedOverlay);
    }

    public static void renderItem(PoseStack matrices, MultiBufferSource buffer, ItemStack item, ModelItem modelItem, float rotation, int light) {
        // if no stack, skip
        if (item.isEmpty()) return;

        // start rendering
        matrices.pushPose();
        Vector3f center = modelItem.getCenter();
        matrices.translate(center.x(), center.y(), center.z());

        matrices.mulPose(Axis.YP.rotationDegrees(rotation));

        // scale
        float scale = modelItem.getSizeScaled();
        matrices.scale(scale, scale, scale);

        // render the actual item
        Minecraft.getInstance().getItemRenderer().renderStatic(item, ItemDisplayContext.NONE, light, OverlayTexture.NO_OVERLAY, matrices, buffer, null, 0);

        matrices.popPose();
    }

    @Override
    public AABB getRenderBoundingBox(T blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return AABB.encapsulatingFullBlocks(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
    }
}
