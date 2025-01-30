package com.rgerva.infinitytech.datagen;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import com.rgerva.infinitytech.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
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

    List<ItemLike> SMELTABLES = List.of(ModItems.TITANIUM_RAW.get(), ModBlocks.TITANIUM_ORE.get(),
            ModBlocks.TITANIUM_DEEPSLATE_ORE.get(), ModBlocks.TITANIUM_END_ORE.get());

    @Override
    protected void buildRecipes() {
        oreSmelting(this.output, SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 200,"titanium");
        oreBlasting(this.output, SMELTABLES, RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get(), 0.25f, 100,"titanium");

        shapeless(RecipeCategory.MISC, ModItems.TITANIUM_RAW.get(), 9)
                .requires(ModBlocks.TITANIUM_RAW_BLOCK)
                .unlockedBy("has_titanium_raw", has(ModItems.TITANIUM_RAW.get()))
                .save(this.output);

        shapeless(RecipeCategory.MISC, ModItems.TITANIUM_NUGGET.get(), 9)
                .requires(ModItems.TITANIUM_INGOT)
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT.get()))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_INGOT.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TITANIUM_NUGGET.get())
                .unlockedBy("has_titanium_raw", has(ModItems.TITANIUM_RAW))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModBlocks.TITANIUM_RAW_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TITANIUM_RAW.get())
                .unlockedBy("has_titanium_raw", has(ModItems.TITANIUM_RAW))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModBlocks.TITANIUM_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_SWORD.get())
                .pattern(" B ")
                .pattern(" B ")
                .pattern(" S ")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_PICKAXE.get())
                .pattern("BBB")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_SHOVEL.get())
                .pattern(" B ")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_AXE.get())
                .pattern(" BB")
                .pattern(" SB")
                .pattern(" S ")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_HOE.get())
                .pattern(" BB")
                .pattern(" S ")
                .pattern(" S ")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_HELMET.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("   ")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_CHESTPLATE.get())
                .pattern("B B")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_LEGGINGS.get())
                .pattern("BBB")
                .pattern("B B")
                .pattern("B B")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModItems.TITANIUM_BOOTS.get())
                .pattern("BBB")
                .pattern("B B")
                .define('B', ModItems.TITANIUM_INGOT.get())
                .unlockedBy("has_titanium_ingot", has(ModItems.TITANIUM_INGOT))
                .save(this.output);
    }

    protected void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                               float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                               float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, InfinityTech.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
