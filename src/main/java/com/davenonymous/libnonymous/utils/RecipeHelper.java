package com.davenonymous.libnonymous.utils;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.Map;

public class RecipeHelper {
    private static final Field recipesField;

    static {
        recipesField = ObfuscationReflectionHelper.findField(RecipeManager.class, "recipes");
        recipesField.setAccessible(true);
    }

    public static Map<ResourceLocation, Recipe<?>> getRecipes(RecipeManager manager, RecipeType<?> type) {
        Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipeTypeMap = null;
        try {
            recipeTypeMap = (Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>>) recipesField.get(manager);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return recipeTypeMap.get(type);
    }

    public static boolean registerRecipe(RecipeManager manager, RecipeType<?> type, Recipe recipe) {
        Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipeTypeMap = null;
        try {
            recipeTypeMap = (Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>>) recipesField.get(manager);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(recipeTypeMap == null) {
            return false;
        }

        Map<ResourceLocation, Recipe<?>> recipes = recipeTypeMap.get(type);
        if(recipes == null) {
            return false;
        }

        recipes.put(recipe.getId(), recipe);
        return true;
    }

    public static boolean removeRecipe(RecipeManager manager, RecipeType<?> type, String recipeId) {
        Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipeTypeMap = null;
        try {
            recipesField.setAccessible(true);
            recipeTypeMap = (Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>>) recipesField.get(manager);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if(recipeTypeMap == null) {
            return false;
        }

        Map<ResourceLocation, Recipe<?>> recipes = recipeTypeMap.get(type);
        if(recipes == null) {
            return false;
        }

        recipes.remove(ResourceLocation.tryParse(recipeId));
        return true;
    }

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType (ResourceLocation id) {
        final RecipeType<T> type = new RecipeType<T>() {
            @Override
            public String toString () {
                return id.toString();
            }
        };

        return Registry.register(Registry.RECIPE_TYPE,  id, type);
    }
}
