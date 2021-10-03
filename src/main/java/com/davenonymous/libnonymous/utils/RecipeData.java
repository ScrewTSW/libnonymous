package com.davenonymous.libnonymous.utils;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

/*
This class is basically taken as is from
https://github.com/Darkhax-Minecraft/BotanyPots/blob/6aa8b1dbeb/src/main/java/net/darkhax/botanypots/RecipeData.java

Thanks @Darkhax
 */
public abstract class RecipeData implements Recipe<Container> {
    public RecipeData() {
        if (this.getSerializer() == null) {
            throw new IllegalStateException("No serializer found for " + this.getClass().getName());
        }

        if (this.getType() == null) {
            throw new IllegalStateException("No recipe type found for " + this.getClass().getName());
        }
    }

    @Override
    public boolean matches (Container inv, Level worldIn) {
        // Not used
        return false;
    }

    @Override
    public ItemStack assemble (Container inv) {
        // Not used
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions (int width, int height) {
        // Not used
        return false;
    }

    @Override
    public ItemStack getResultItem () {
        // Not used
        return ItemStack.EMPTY;
    }
}
