package com.davenonymous.libnonymous.setup;

import com.davenonymous.libnonymous.command.ModCommands;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;

public class ForgeEventHandlers {
    @SubscribeEvent
    public void serverLoad(FMLServerStartingEvent event) {
        ModCommands.register(event.getServer().getCommands().getDispatcher());
    }
}
