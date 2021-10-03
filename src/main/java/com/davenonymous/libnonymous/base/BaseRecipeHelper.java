package com.davenonymous.libnonymous.base;

import com.davenonymous.libnonymous.utils.RecipeData;
import com.davenonymous.libnonymous.utils.RecipeHelper;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseRecipeHelper<T extends RecipeData> {
    RecipeType<T> recipeType;

    public BaseRecipeHelper(RecipeType<T> type) {
        this.recipeType = type;
    }

    public boolean hasRecipes(RecipeManager manager) {
        Map<ResourceLocation, Recipe<?>> recipes = getRecipes(manager);
        return recipes != null && recipes.size() > 0;
    }

    public int getRecipeCount(RecipeManager manager) {
        Map<ResourceLocation, Recipe<?>> recipes = getRecipes(manager);
        return recipes != null ? recipes.size() : 0;
    }

    public T getRecipe(RecipeManager manager, ResourceLocation id) {
        Map<ResourceLocation, Recipe<?>> recipes = getRecipes(manager);
        if(recipes == null) {
            return null;
        }

        return (T) recipes.getOrDefault(id, null);
    }

    public Stream<T> getRecipeStream(RecipeManager manager) {
        return getRecipes(manager).values().stream().map(r -> (T)r);
    }

    public Map<ResourceLocation, Recipe<?>> getRecipes(RecipeManager manager) {
        return RecipeHelper.getRecipes(manager, recipeType);
    }

    public List<T> getRecipesList(RecipeManager manager) {
        return getRecipeStream(manager).collect(Collectors.toList());
    }

    public T getRandomRecipe(RecipeManager manager, Random rand) {
        Map<ResourceLocation, Recipe<?>> recipes = getRecipes(manager);
        if(recipes == null || recipes.size() == 0) {
            return null;
        }
        Set<ResourceLocation> ids = recipes.keySet();
        ResourceLocation randomId = (ResourceLocation) ids.toArray()[rand.nextInt(ids.size())];
        return (T) recipes.get(randomId);

    }

}
