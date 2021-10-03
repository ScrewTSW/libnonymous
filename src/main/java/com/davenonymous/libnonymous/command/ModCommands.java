package com.davenonymous.libnonymous.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ModCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("libnonymous")
                .then(CommandOpenConfigGUI.register(dispatcher))
        );
    }
}
