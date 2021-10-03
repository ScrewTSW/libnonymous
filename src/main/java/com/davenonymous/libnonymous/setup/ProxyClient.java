package com.davenonymous.libnonymous.setup;

import com.davenonymous.libnonymous.render.RenderEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;

public class ProxyClient implements IProxy {
    
    private static final Minecraft minecraft = Minecraft.getInstance();
    
    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(RenderEventHandler.class);
    }

    @Override
    public Level getClientWorld() {
        return minecraft.level;
    }

    @Override
    public Player getClientPlayer() {
        return minecraft.player;
    }
}
