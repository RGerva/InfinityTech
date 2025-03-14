package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    protected ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "My Recipes";
        }
    }

    List<ItemLike> SMELLABLE_TITANIUM =
            List.of(ModItems.TITANIUM_RAW.get(),
                    ModBlocks.TITANIUM_ORE.get(),
                    ModBlocks.TITANIUM_DEEPSLATE_ORE.get(),
                    ModBlocks.TITANIUM_END_ORE.get());

    List<ItemLike> SMELLABLE_LEAD =
            List.of(ModItems.LEAD_RAW.get(),
                    ModBlocks.LEAD_ORE.get(),
                    ModBlocks.LEAD_DEEPSLATE_ORE.get(),
                    ModBlocks.LEAD_NETHER_ORE.get(),
                    ModBlocks.LEAD_END_ORE.get());

    List<ItemLike> SMELLABLE_ALUMINUM =
            List.of(ModItems.ALUMINUM_RAW.get(),
                    ModBlocks.ALUMINUM_ORE.get(),
                    ModBlocks.ALUMINUM_DEEPSLATE_ORE.get(),
                    ModBlocks.ALUMINUM_NETHER_ORE.get(),
                    ModBlocks.ALUMINUM_END_ORE.get());

    List<ItemLike> SMELLABLE_PLATINUM =
            List.of(ModItems.PLATINUM_RAW.get(),
                    ModBlocks.PLATINUM_ORE.get(),
                    ModBlocks.PLATINUM_DEEPSLATE_ORE.get(),
                    ModBlocks.PLATINUM_NETHER_ORE.get(),
                    ModBlocks.PLATINUM_END_ORE.get());

    List<ItemLike> SMELLABLE_NICKEL =
            List.of(ModItems.NICKEL_RAW.get(),
                    ModBlocks.NICKEL_ORE.get(),
                    ModBlocks.NICKEL_DEEPSLATE_ORE.get(),
                    ModBlocks.NICKEL_NETHER_ORE.get(),
                    ModBlocks.NICKEL_END_ORE.get());

    List<ItemLike> SMELLABLE_SILVER =
            List.of(ModItems.SILVER_RAW.get(),
                    ModBlocks.SILVER_ORE.get(),
                    ModBlocks.SILVER_DEEPSLATE_ORE.get(),
                    ModBlocks.SILVER_NETHER_ORE.get(),
                    ModBlocks.SILVER_END_ORE.get());

    List<ItemLike> SMELLABLE_TIN =
            List.of(ModItems.TIN_RAW.get(),
                    ModBlocks.TIN_ORE.get(),
                    ModBlocks.TIN_DEEPSLATE_ORE.get(),
                    ModBlocks.TIN_NETHER_ORE.get(),
                    ModBlocks.TIN_END_ORE.get());

    List<ItemLike> SMELLABLE_URANIUM =
            List.of(ModItems.URANIUM_RAW.get(),
                    ModBlocks.URANIUM_ORE.get(),
                    ModBlocks.URANIUM_DEEPSLATE_ORE.get(),
                    ModBlocks.URANIUM_NETHER_ORE.get(),
                    ModBlocks.URANIUM_END_ORE.get());

    List<ItemLike> SMELLABLE_ZINC =
            List.of(ModItems.ZINC_RAW.get(),
                    ModBlocks.ZINC_ORE.get(),
                    ModBlocks.ZINC_DEEPSLATE_ORE.get(),
                    ModBlocks.ZINC_NETHER_ORE.get(),
                    ModBlocks.ZINC_END_ORE.get());

    List<ItemLike> TITANIUM_TOOLS =
            List.of(ModItems.TITANIUM_SWORD,
                    ModItems.TITANIUM_PICKAXE,
                    ModItems.TITANIUM_SHOVEL,
                    ModItems.TITANIUM_AXE,
                    ModItems.TITANIUM_HOE);

    List<ItemLike> TITANIUM_ARMOR =
            List.of(ModItems.TITANIUM_HELMET,
                    ModItems.TITANIUM_CHESTPLATE,
                    ModItems.TITANIUM_LEGGINGS,
                    ModItems.TITANIUM_BOOTS);

    @Override
    protected void buildRecipes() {

        //================
        //TITANIUM RECIPES
        //================

        oreCook(this.output, SMELLABLE_TITANIUM, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), "titanium");
        customNuggetRecipe(ModItems.TITANIUM_NUGGET, ModItems.TITANIUM_INGOT, "titanium");
        customBlockToIngotRecipe(ModBlocks.TITANIUM_BLOCK, ModItems.TITANIUM_INGOT, "titanium");
        customDefaultToolRecipe(ModItems.TITANIUM_INGOT, TITANIUM_TOOLS, null);
        customDefaultArmorRecipe(ModItems.TITANIUM_INGOT, TITANIUM_ARMOR);

        //================
        //  STEEL RECIPES
        //================

        oreCook(this.output, List.of(Items.IRON_INGOT), RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), "steel");
        customNuggetRecipe(ModItems.STEEL_NUGGET, ModItems.STEEL_INGOT, "steel");
        customBlockToIngotRecipe(ModBlocks.STEEL_BLOCK, ModItems.STEEL_INGOT, "steel");

        //================
        //  LEAD RECIPES
        //================

        oreCook(this.output, SMELLABLE_LEAD, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), "lead");
        customNuggetRecipe(ModItems.LEAD_NUGGET, ModItems.LEAD_INGOT, "lead");
        customBlockToIngotRecipe(ModBlocks.LEAD_BLOCK, ModItems.LEAD_INGOT, "lead");

        //====================
        //  ALUMINUM RECIPES
        //====================

        oreCook(this.output, SMELLABLE_ALUMINUM, RecipeCategory.MISC, ModItems.ALUMINUM_INGOT.get(), "aluminum");
        customNuggetRecipe(ModItems.ALUMINUM_NUGGET, ModItems.ALUMINUM_INGOT, "aluminum");
        customBlockToIngotRecipe(ModBlocks.ALUMINUM_BLOCK, ModItems.ALUMINUM_INGOT, "aluminum");

        //==================
        //  NICKEL RECIPES
        //==================

        oreCook(this.output, SMELLABLE_NICKEL, RecipeCategory.MISC, ModItems.NICKEL_INGOT.get(), "nickel");
        customNuggetRecipe(ModItems.NICKEL_NUGGET, ModItems.NICKEL_INGOT, "nickel");
        customBlockToIngotRecipe(ModBlocks.NICKEL_BLOCK, ModItems.NICKEL_INGOT, "nickel");

        //====================
        //  PLATINUM RECIPES
        //====================

        oreCook(this.output, SMELLABLE_PLATINUM, RecipeCategory.MISC, ModItems.PLATINUM_INGOT.get(), "platinum");
        customNuggetRecipe(ModItems.PLATINUM_NUGGET, ModItems.PLATINUM_INGOT, "platinum");
        customBlockToIngotRecipe(ModBlocks.PLATINUM_BLOCK, ModItems.PLATINUM_INGOT, "platinum");

        //==================
        //  SILVER RECIPES
        //==================

        oreCook(this.output, SMELLABLE_SILVER, RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), "silver");
        customNuggetRecipe(ModItems.SILVER_NUGGET, ModItems.SILVER_INGOT, "silver");
        customBlockToIngotRecipe(ModBlocks.SILVER_BLOCK, ModItems.SILVER_INGOT, "silver");

        //===============
        //  TIN RECIPES
        //===============

        oreCook(this.output, SMELLABLE_TIN, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), "tin");
        customNuggetRecipe(ModItems.TIN_NUGGET, ModItems.TIN_INGOT, "tin");
        customBlockToIngotRecipe(ModBlocks.TIN_BLOCK, ModItems.TIN_INGOT, "tin");

        //===================
        //  URANIUM RECIPES
        //===================

        oreCook(this.output, SMELLABLE_URANIUM, RecipeCategory.MISC, ModItems.URANIUM_INGOT.get(), "uranium");
        customNuggetRecipe(ModItems.URANIUM_NUGGET, ModItems.URANIUM_INGOT, "uranium");
        customBlockToIngotRecipe(ModBlocks.URANIUM_BLOCK, ModItems.URANIUM_INGOT, "uranium");

        //================
        //  ZINC RECIPES
        //================

        oreCook(this.output, SMELLABLE_ZINC, RecipeCategory.MISC, ModItems.ZINC_INGOT.get(), "zinc");
        customNuggetRecipe(ModItems.ZINC_NUGGET, ModItems.ZINC_INGOT, "zinc");
        customBlockToIngotRecipe(ModBlocks.ZINC_BLOCK, ModItems.ZINC_INGOT, "zinc");

        //==================
        //  CUSTOM RECIPES
        //==================

        customChestRecipe();

        //WRENCH
        this.shaped(RecipeCategory.TOOLS, ModItems.WRENCH.get())
                .pattern(" I ")
                .pattern(" NI")
                .pattern("N  ")
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .group(getItemName(ModItems.WRENCH))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(this.output);

    }

    protected void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                0.25f, 200, pGroup, "_from_smelting");
    }

    protected void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                0.25f, 100, pGroup, "_from_blasting");
    }

    protected void oreCook(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                0.25f, 200, pGroup, "_from_smelting");

        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                0.25f, 100, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, InfinityTech.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected void customChestRecipe() {

        //CHEST COPPER
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHEST_COPPER)
                .define('C', Tags.Items.INGOTS_COPPER)
                .define('W', Tags.Items.CHESTS_WOODEN)
                .pattern("CCC")
                .pattern("CWC")
                .pattern("CCC")
                .group(getItemName(ModBlocks.CHEST_COPPER))
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(ModBlocks.CHEST_COPPER))));

        //CHEST IRON
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHEST_IRON)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('C', ModBlocks.CHEST_COPPER)
                .pattern("III")
                .pattern("ICI")
                .pattern("III")
                .group(getItemName(ModBlocks.CHEST_IRON))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(ModBlocks.CHEST_COPPER))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(ModBlocks.CHEST_IRON))));

        //CHEST GOLD
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHEST_GOLD)
                .define('I', Tags.Items.INGOTS_GOLD)
                .define('G', ModBlocks.CHEST_IRON)
                .pattern("III")
                .pattern("IGI")
                .pattern("III")
                .group(getItemName(ModBlocks.CHEST_GOLD))
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(ModBlocks.CHEST_IRON))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(ModBlocks.CHEST_GOLD))));

        //CHEST DIAMOND
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHEST_DIAMOND)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('I', ModBlocks.CHEST_GOLD)
                .pattern("DDD")
                .pattern("DID")
                .pattern("DDD")
                .group(getItemName(ModBlocks.CHEST_DIAMOND))
                .unlockedBy(getHasName(Items.DIAMOND), has(ModBlocks.CHEST_GOLD))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(ModBlocks.CHEST_DIAMOND))));

        //CHEST OBSIDIAN
        this.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHEST_OBSIDIAN)
                .define('B', Tags.Items.OBSIDIANS)
                .define('C', ModBlocks.CHEST_DIAMOND)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .pattern("BBB")
                .pattern("DCD")
                .pattern("BBB")
                .group(getItemName(ModBlocks.CHEST_OBSIDIAN))
                .unlockedBy(getHasName(Items.OBSIDIAN), has(ModBlocks.CHEST_DIAMOND))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(ModBlocks.CHEST_OBSIDIAN))));

        //CHEST NETHERITE
        netheriteSmithing(ModBlocks.CHEST_OBSIDIAN.asItem(), RecipeCategory.DECORATIONS, ModBlocks.CHEST_NETHERITE.asItem());

    }

    /**
     * Custom method to generate Nuggets from Ingot Recipe.
     *
     * @param pInput  is main item for recipe
     * @param pOutput is the output from recipe
     * @param pGroup  is group name
     */
    protected void customNuggetRecipe(ItemLike pInput, ItemLike pOutput, String pGroup) {
        this.shapeless(RecipeCategory.MISC, pInput, 9)
                .requires(pOutput)
                .group(pGroup.concat("_nugget"))
                .unlockedBy(getHasName(pOutput), has(pOutput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput))));

        this.shaped(RecipeCategory.MISC, pOutput)
                .define('#', pInput)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(pGroup)
                .unlockedBy(getHasName(pOutput), has(pOutput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput).concat("_from_").concat(getSimpleRecipeName(pInput)))));
    }

    /**
     * Custom method to generate Ingots and Blocks Recipe.
     *
     * @param pInput  is main item for recipe
     * @param pOutput is the output from recipe
     * @param pGroup  is group name
     */
    protected void customBlockToIngotRecipe(ItemLike pInput, ItemLike pOutput, String pGroup) {
        this.shapeless(RecipeCategory.MISC, pOutput, 9)
                .requires(pInput)
                .group(pGroup)
                .unlockedBy(getHasName(pOutput), has(pOutput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput).concat("_from_").concat(getSimpleRecipeName(pInput)))));

        this.shaped(RecipeCategory.BUILDING_BLOCKS, pInput)
                .define('#', pOutput)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(pGroup)
                .unlockedBy(getHasName(pOutput), has(pOutput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pInput))));
    }

    /**
     * Custom method to generate Tools Recipe.
     *
     * @param pInput    is main item for recipe
     * @param pOutput   is the output from recipe
     * @param pOptional is optional input for hand Item
     */
    protected void customDefaultToolRecipe(ItemLike pInput, List<ItemLike> pOutput, @Nullable ItemLike pOptional) throws ArrayIndexOutOfBoundsException {
        if (pOutput.size() > 5) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array: %s is Out of Bounds", pOutput.getClass().getName()));
        }

        this.shaped(RecipeCategory.MISC, pOutput.getFirst())
                .pattern(" B ")
                .pattern(" B ")
                .pattern(" S ")
                .define('B', pInput)
                .define('S', Optional.ofNullable(pOptional).orElse(Items.STICK))
                .group(getHasName(pOutput.getFirst()))
                .unlockedBy(getHasName(pInput), has(pInput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.getFirst()))));

        this.shaped(RecipeCategory.MISC, pOutput.get(1))
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', pInput)
                .define('S', Optional.ofNullable(pOptional).orElse(Items.STICK))
                .group(getHasName(pOutput.get(1)))
                .unlockedBy(getHasName(pInput), has(pInput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.get(1)))));

        this.shaped(RecipeCategory.MISC, pOutput.get(2))
                .pattern(" B ")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', pInput)
                .define('S', Optional.ofNullable(pOptional).orElse(Items.STICK))
                .group(getHasName(pOutput.get(2)))
                .unlockedBy(getHasName(pInput), has(pInput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.get(2)))));

        this.shaped(RecipeCategory.MISC, pOutput.get(3))
                .pattern(" BB")
                .pattern(" SB")
                .pattern(" S ")
                .define('B', pInput)
                .define('S', Optional.ofNullable(pOptional).orElse(Items.STICK))
                .group(getHasName(pOutput.get(3)))
                .unlockedBy(getHasName(pInput), has(pInput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.get(3)))));

        this.shaped(RecipeCategory.MISC, pOutput.getLast())
                .pattern(" BB")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', pInput)
                .define('S', Optional.ofNullable(pOptional).orElse(Items.STICK))
                .group(getHasName(pOutput.getLast()))
                .unlockedBy(getHasName(pInput), has(pInput))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.getLast()))));
    }

    /**
     * Custom method to generate Armor Recipe.
     *
     * @param pInput  is main item for recipe
     * @param pOutput is the output from recipe
     */
    protected void customDefaultArmorRecipe(ItemLike pInput, List<ItemLike> pOutput) {
        if (pOutput.size() > 4) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array: %s is Out of Bounds", pOutput.getClass().getName()));
        }

        this.shaped(RecipeCategory.MISC, pOutput.getFirst())
                .pattern("BBB")
                .pattern("B B")
                .pattern("   ")
                .define('B', pInput)
                .unlockedBy(getHasName(pInput), has(pInput))
                .group(getHasName(pOutput.getFirst()))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.getFirst()))));

        this.shaped(RecipeCategory.MISC, pOutput.get(1))
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', pInput)
                .unlockedBy(getHasName(pInput), has(pInput))
                .group(getHasName(pOutput.get(1)))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.get(1)))));

        this.shaped(RecipeCategory.MISC, pOutput.get(2))
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .define('B', pInput)
                .unlockedBy(getHasName(pInput), has(pInput))
                .group(getHasName(pOutput.get(2)))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.get(2)))));

        this.shaped(RecipeCategory.MISC, pOutput.getLast())
                .pattern("BBB")
                .pattern("B B")
                .define('B', pInput)
                .unlockedBy(getHasName(pInput), has(pInput))
                .group(getHasName(pOutput.getLast()))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(InfinityTech.MOD_ID, getSimpleRecipeName(pOutput.getLast()))));
    }
}
