package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.block.custom.CableBlock;
import net.minecraft.client.model.MinecartModel;
import net.minecraft.client.renderer.entity.state.MinecartRenderState;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.mclanguageprovider.MinecraftModContainer;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, InfinityTech.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.TITANIUM_ORE);
        blockWithItem(ModBlocks.TITANIUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.TITANIUM_NETHER_ORE);
        blockWithItem(ModBlocks.TITANIUM_END_ORE);
        blockWithItem(ModBlocks.TITANIUM_BLOCK);
        blockWithItem(ModBlocks.TITANIUM_RAW_BLOCK);

        blockWithItem(ModBlocks.STEEL_BLOCK);

        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.LEAD_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.LEAD_NETHER_ORE);
        blockWithItem(ModBlocks.LEAD_END_ORE);
        blockWithItem(ModBlocks.LEAD_BLOCK);
        blockWithItem(ModBlocks.LEAD_RAW_BLOCK);

        blockWithItem(ModBlocks.ALUMINUM_ORE);
        blockWithItem(ModBlocks.ALUMINUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.ALUMINUM_NETHER_ORE);
        blockWithItem(ModBlocks.ALUMINUM_END_ORE);
        blockWithItem(ModBlocks.ALUMINUM_BLOCK);
        blockWithItem(ModBlocks.ALUMINUM_RAW_BLOCK);

        blockWithItem(ModBlocks.NICKEL_ORE);
        blockWithItem(ModBlocks.NICKEL_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.NICKEL_NETHER_ORE);
        blockWithItem(ModBlocks.NICKEL_END_ORE);
        blockWithItem(ModBlocks.NICKEL_BLOCK);
        blockWithItem(ModBlocks.NICKEL_RAW_BLOCK);

        blockWithItem(ModBlocks.PLATINUM_ORE);
        blockWithItem(ModBlocks.PLATINUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.PLATINUM_NETHER_ORE);
        blockWithItem(ModBlocks.PLATINUM_END_ORE);
        blockWithItem(ModBlocks.PLATINUM_BLOCK);
        blockWithItem(ModBlocks.PLATINUM_RAW_BLOCK);

        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.SILVER_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.SILVER_NETHER_ORE);
        blockWithItem(ModBlocks.SILVER_END_ORE);
        blockWithItem(ModBlocks.SILVER_BLOCK);
        blockWithItem(ModBlocks.SILVER_RAW_BLOCK);

        blockWithItem(ModBlocks.TIN_ORE);
        blockWithItem(ModBlocks.TIN_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.TIN_NETHER_ORE);
        blockWithItem(ModBlocks.TIN_END_ORE);
        blockWithItem(ModBlocks.TIN_BLOCK);
        blockWithItem(ModBlocks.TIN_RAW_BLOCK);

        blockWithItem(ModBlocks.URANIUM_ORE);
        blockWithItem(ModBlocks.URANIUM_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.URANIUM_NETHER_ORE);
        blockWithItem(ModBlocks.URANIUM_END_ORE);
        blockWithItem(ModBlocks.URANIUM_BLOCK);
        blockWithItem(ModBlocks.URANIUM_RAW_BLOCK);

        blockWithItem(ModBlocks.ZINC_ORE);
        blockWithItem(ModBlocks.ZINC_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.ZINC_NETHER_ORE);
        blockWithItem(ModBlocks.ZINC_END_ORE);
        blockWithItem(ModBlocks.ZINC_BLOCK);
        blockWithItem(ModBlocks.ZINC_RAW_BLOCK);

        horizontalBlockWithItem(ModBlocks.CREATIVE_BATTERY, true);
        horizontalBlockWithItem(ModBlocks.BATTERY_BLOCK, true);

        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_1);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_2);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_3);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_4);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_5);
        solarPanelBlockWithItem(ModBlocks.SOLAR_PANEL_6);

        cableBlockWithItem(ModBlocks.TIN_CABLE);
        cableBlockWithItem(ModBlocks.COPPER_CABLE);
        cableBlockWithItem(ModBlocks.GOLD_CABLE);

        chestBlockWithItem(ModBlocks.IRON_CHEST);

    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

//    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
//        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);
//
//        getVariantBuilder(block).forAllStates(function);
//    }
//
//    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
//        ConfiguredModel[] models = new ConfiguredModel[1];
//        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(GojiBerryBushBlock.AGE),
//                ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/" + textureName + state.getValue(GojiBerryBushBlock.AGE))).renderType("cutout"));
//
//        return models;
//    }
//
//    public void makeCrop(CropBlock block, String modelName, String textureName) {
//        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
//
//        getVariantBuilder(block).forAllStates(function);
//    }
//
//    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
//        ConfiguredModel[] models = new ConfiguredModel[1];
//        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((RadishCropBlock) block).getAgeProperty()),
//                ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/" + textureName + state.getValue(((RadishCropBlock) block).getAgeProperty()))).renderType("cutout"));
//
//        return models;
//    }
//
//    private void customLamp() {
//        getVariantBuilder(ModBlocks.BISMUTH_LAMP.get()).forAllStates(state -> {
//            if(state.getValue(BismuthLampBlock.CLICKED)) {
//                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_on",
//                        ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/" + "bismuth_lamp_on")))};
//            } else {
//                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_off",
//                        ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/" + "bismuth_lamp_off")))};
//            }
//        });
//
//        simpleBlockItem(ModBlocks.BISMUTH_LAMP.get(), models().cubeAll("bismuth_lamp_on",
//                ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, "block/" + "bismuth_lamp_on")));
//    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("infinity_tech:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("infinity_tech:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void chestBlockWithItem(Holder<? extends Block> block){
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        ModelFile chestCoreTemplate = models()
                .withExistingParent("chest_template", ModelProvider.BLOCK_FOLDER + "/block")
                .texture("texture", getBlockTexture(block))
                .texture("particle", getBlockParticleTexture(block))
                .element()
                .from(1, 0, 1)
                .to(15, 10, 15)
                .allFaces((direction, faceBuilder) -> {
                    switch (direction){
                        case DOWN -> faceBuilder.uvs(7, 4.75F, 10.5F, 8.25F).texture("#texture").end();
                        case UP -> faceBuilder.uvs(3.5F, 4.75F, 7, 8.25F).texture("#texture").end();
                        case NORTH -> faceBuilder.uvs(3.5F, 8.25F, 7, 10.75F).texture("#texture").end();
                        case SOUTH -> faceBuilder.uvs(10.5F, 8.25F, 14, 10.75F).texture("#texture").end();
                        case WEST -> faceBuilder.uvs(7, 8.25F, 10.5F, 10.75F).texture("#texture").end();
                        case EAST -> faceBuilder.uvs(0, 8.25F, 3.5F, 10.75F).texture("#texture").end();
                    }
                })
                .end()

                .element()
                .from(1, 9, 1)
                .to(15, 14, 15)
                .allFaces((direction, faceBuilder) -> {
                    switch (direction){
                        case DOWN -> faceBuilder.uvs(7, 0, 10.5F, 3.5F).texture("#texture").end();
                        case UP -> faceBuilder.uvs(3.5F, 0, 7, 3.5F).texture("#texture").end();
                        case NORTH -> faceBuilder.uvs(3.5F, 3.5F, 7, 4.75F).texture("#texture").end();
                        case SOUTH -> faceBuilder.uvs(10.5F, 3.5F, 14, 4.75F).texture("#texture").end();
                        case WEST -> faceBuilder.uvs(7, 3.5F, 10.5F, 4.75F).texture("#texture").end();
                        case EAST -> faceBuilder.uvs(0, 3.5F, 3.5F, 4.75F).texture("#texture").end();
                    }
                })
                .end()

                .element()
                .from(7, 7, 0)
                .to(9, 11, 1)
                .allFaces((direction, faceBuilder) -> {
                    switch (direction){
                        case DOWN -> faceBuilder.uvs(0, 0.75F, 1.25F, 0.5F).texture("#texture").end();
                        case UP -> faceBuilder.uvs(0, 0.25F, 0.75F, 0.5F).texture("#texture").end();
                        case NORTH -> faceBuilder.uvs(0.25F, 0.25F, 0.75F, 1.25F).texture("#texture").end();
                        case SOUTH -> faceBuilder.uvs(1, 0.25F, 1.5F, 1.25F).texture("#texture").end();
                        case WEST -> faceBuilder.uvs(0.75F, 0.25F, 1, 1.25F).texture("#texture").end();
                        case EAST -> faceBuilder.uvs(0, 0.25F, 0.25F, 1.25F).texture("#texture").end();
                    }
                })
                .end();

        ModelFile chestCore = models().
                getBuilder(blockId.getPath()).parent(chestCoreTemplate).
                texture("particle", getBlockParticleTexture(block));

        getVariantBuilder(block.value()).partialState()
                .modelForState().modelFile(chestCore).addModel();

        itemModels().getBuilder(blockId.getPath()).parent(chestCoreTemplate);

    }

    private void cableBlockWithItem(Holder<? extends Block> block){
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        ModelFile cableCoreTemplate = models().
                withExistingParent("cable_core_template", ModelProvider.BLOCK_FOLDER + "/thin_block").
                element().from(6, 6, 6).to(10, 10, 10).
                face(Direction.DOWN).uvs(0, 7, 4, 11).cullface(Direction.DOWN).texture("#cable").end().
                face(Direction.UP).uvs(0, 7, 4, 11).cullface(Direction.UP).texture("#cable").end().
                face(Direction.NORTH).uvs(0, 7, 4, 11).cullface(Direction.NORTH).texture("#cable").end().
                face(Direction.SOUTH).uvs(0, 7, 4, 11).cullface(Direction.SOUTH).texture("#cable").end().
                face(Direction.WEST).uvs(0, 7, 4, 11).cullface(Direction.WEST).texture("#cable").end().
                face(Direction.EAST).uvs(0, 7, 4, 11).cullface(Direction.EAST).texture("#cable").end().
                end();


        ModelFile cableSideTemplate = models().
                withExistingParent("cable_side_template", ModelProvider.BLOCK_FOLDER + "/thin_block").
                element().from(6, 6, 0).to(10, 10, 6).
                face(Direction.DOWN).uvs(0, 0, 4, 6).texture("#cable").end().
                face(Direction.UP).uvs(0, 0, 4, 6).texture("#cable").end().
                face(Direction.NORTH).uvs(0, 12, 4, 16).texture("#cable").end().
                face(Direction.SOUTH).uvs(0, 12, 4, 16).texture("#cable").end().
                face(Direction.WEST).uvs(5, 7, 11, 11).texture("#cable").end().
                face(Direction.EAST).uvs(5, 7, 11, 11).texture("#cable").end().
                end();

        ModelFile cableCore = models().
                getBuilder(blockId.getPath() + "_core").parent(cableCoreTemplate).
                texture("particle", getBlockTexture(block)).
                texture("cable", getBlockTexture(block));
        ModelFile cableSide = models().
                getBuilder(blockId.getPath() + "_side").parent(cableSideTemplate).
                texture("particle", getBlockTexture(block)).
                texture("cable", getBlockTexture(block));

        getMultipartBuilder(block.value()).part().
                modelFile(cableCore).addModel().end().part().
                modelFile(cableSide).rotationX(270).addModel().condition(CableBlock.UP, true).end().part().
                modelFile(cableSide).rotationX(90).addModel().condition(CableBlock.DOWN, true).end().part().
                modelFile(cableSide).addModel().condition(CableBlock.NORTH, true).end().part().
                modelFile(cableSide).rotationX(180).addModel().condition(CableBlock.SOUTH, true).end().part().
                modelFile(cableSide).rotationY(90).addModel().condition(CableBlock.EAST, true).end().part().
                modelFile(cableSide).rotationY(270).addModel().condition(CableBlock.WEST, true).end();

        itemModels().
                getBuilder(blockId.getPath()).parent(cableCore).
                transforms().
                transform(ItemDisplayContext.GUI).rotation(30, 45, 0).scale(1.5f, 1.5f, 1.5f).end().
                transform(ItemDisplayContext.GROUND).scale(1.01f, 1.01f, 1.01f).end().
                transform(ItemDisplayContext.FIXED).scale(1.01f, 1.01f, 1.01f).end().
                transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).scale(1.01f, 1.01f, 1.01f).end().
                transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).scale(1.01f, 1.01f, 1.01f).end().
                transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).scale(1.01f, 1.01f, 1.01f).end().
                transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).scale(1.01f, 1.01f, 1.01f).end().end();
    }

    private void solarPanelBlockWithItem(Holder<? extends Block> block) {
        ModelFile solarPanelTemplate = models().
                withExistingParent("solar_panel_template", ModelProvider.BLOCK_FOLDER + "/thin_block").
                element().from(0, 0, 0).to(16, 4, 16).
                face(Direction.DOWN).uvs(0, 0, 16, 16).cullface(Direction.DOWN).texture("#side").end().
                face(Direction.UP).uvs(0, 0, 16, 16).texture("#top").end().
                face(Direction.NORTH).uvs(0, 12, 16, 16).cullface(Direction.NORTH).texture("#side").end().
                face(Direction.SOUTH).uvs(0, 12, 16, 16).cullface(Direction.SOUTH).texture("#side").end().
                face(Direction.WEST).uvs(0, 12, 16, 16).cullface(Direction.WEST).texture("#side").end().
                face(Direction.EAST).uvs(0, 12, 16, 16).cullface(Direction.EAST).texture("#side").end().
                end();

        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        ModelFile solarPanel = models().
                getBuilder(blockId.getPath()).parent(solarPanelTemplate).
                texture("particle", "#top").
                texture("top", getBlockTexture(block, "_top")).
                texture("side", getBlockTexture(block, "_side"));

        getVariantBuilder(block.value()).partialState().
                modelForState().modelFile(solarPanel).addModel();

        simpleBlockItem(block.value(), solarPanel);
    }

    private void horizontalBlockWithItem(Holder<? extends Block> block, boolean uniqueBottomTexture) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        ModelFile model = models().
                withExistingParent(blockId.getPath(), ModelProvider.BLOCK_FOLDER + "/cube").
                texture("particle", "#up").
                texture("up", getBlockTexture(block, "_top")).
                texture("down", getBlockTexture(block, uniqueBottomTexture?"_bottom":"_top")).
                texture("north", getBlockTexture(block, "_side")).
                texture("south", getBlockTexture(block, "_side")).
                texture("east", getBlockTexture(block, "_side")).
                texture("west", getBlockTexture(block, "_side"));

        simpleBlockWithItem(block.value(), model);
    }

    private ResourceLocation getBlockParticleTexture(Holder<? extends Block> block){
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "particle/" + blockId.getPath());
    }

    private ResourceLocation getBlockParticleTexture(Holder<? extends Block> block, String pathSuffix){
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                "particle/" + blockId.getPath() + pathSuffix);
    }

    private ResourceLocation getBlockTexture(Holder<? extends Block> block) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + blockId.getPath());
    }

    private ResourceLocation getBlockTexture(Holder<? extends Block> block, String pathSuffix) {
        ResourceLocation blockId = Objects.requireNonNull(block.getKey()).location();

        return ResourceLocation.fromNamespaceAndPath(blockId.getNamespace(),
                ModelProvider.BLOCK_FOLDER + "/" + blockId.getPath() + pathSuffix);
    }
}
