package com.besson.tutorial.datagen;

import com.besson.tutorial.TutorialMod;
import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.item.ModItems;
import com.besson.tutorial.tag.ModItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipesProvider(PackOutput pOutput) {
        super(pOutput);
    }

    public static final List<ItemLike> ICE_ETHER = List.of(ModItems.RAW_ICE_ETHER.get(), ModBlocks.ICE_ETHER_ORE.get());

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER.get(), 0.25F, 200, "ice_ether");
        oreBlasting(pWriter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER.get(), 0.25F, 100, "ice_ether");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ICE_ETHER.get())
                .unlockedBy(getHasName(ModItems.ICE_ETHER.get()), has(ModItems.ICE_ETHER.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ICE_ETHER.get(), 9)
                .requires(ModBlocks.ICE_ETHER_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ICE_ETHER_BLOCK.get()), has(ModBlocks.ICE_ETHER_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.SUGAR, 3)
                .pattern("###")
                .define('#', ModItemTags.SUGAR_TAG)
                .unlockedBy(getHasName(Items.BEETROOT), has(Items.BEETROOT))
                .save(pWriter, TutorialMod.MOD_ID + ":" + "sugar_from_beetroot");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_ORE.get())
                .requires(ModItems.RAW_ICE_ETHER.get())
                .requires(Blocks.STONE)
                .unlockedBy(getHasName(ModItems.RAW_ICE_ETHER.get()), has(ModItems.RAW_ICE_ETHER.get()))
                .unlockedBy(getHasName(Blocks.STONE), has(Blocks.STONE))
                .save(pWriter);
    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                    pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, TutorialMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
