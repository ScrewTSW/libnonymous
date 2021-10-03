package com.davenonymous.libnonymous.base;

import com.davenonymous.libnonymous.utils.IThreeToOneSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

public class BaseNamedContainerProvider implements MenuProvider {
    Component displayName;
    IThreeToOneSupplier<Integer, Inventory, Player, AbstractContainerMenu> supplier;

    public BaseNamedContainerProvider(Component displayName, IThreeToOneSupplier<Integer, Inventory, Player, AbstractContainerMenu> supplier) {
        this.displayName = displayName;
        this.supplier = supplier;
    }

    @Override
    public Component getDisplayName() {
        return this.displayName;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return this.supplier.apply(id, inv, player);
    }
}
