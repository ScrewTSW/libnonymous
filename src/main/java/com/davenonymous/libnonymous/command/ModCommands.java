package com.davenonymous.libnonymous.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class ModCommands {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            Commands.literal("libnonymous")
                .then(CommandOpenConfigGUI.register(dispatcher))
        );
    }
}
