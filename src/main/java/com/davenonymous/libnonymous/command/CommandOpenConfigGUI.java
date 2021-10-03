package com.davenonymous.libnonymous.command;

import com.davenonymous.libnonymous.network.Networking;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.command.EnumArgument;
import net.minecraftforge.server.command.ModIdArgument;

public class CommandOpenConfigGUI implements Command<CommandSourceStack> {
    private static final CommandOpenConfigGUI CMD = new CommandOpenConfigGUI();

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("config")
            .requires(cs -> cs.hasPermission(0))
            .then(
                Commands.argument("modid", ModIdArgument.modIdArgument())
                    .then(
                        Commands.argument("mode", EnumArgument.enumArgument(Mode.class))
                            .executes(CMD)
                    )
                    .executes(context -> {
                        final String modId = context.getArgument("modid", String.class);
                        ServerPlayer player = context.getSource().getPlayerOrException();
                        Networking.openConfigGui(player, modId, Mode.BY_SPEC);
                        return 0;
                    })
            )
            .executes(context -> {
                ServerPlayer player = context.getSource().getPlayerOrException();
                Networking.openConfigGui(player);
                return 0;
            });
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        final String modId = context.getArgument("modid", String.class);
        Mode mode = context.getArgument("mode", Mode.class);

        ServerPlayer player = context.getSource().getPlayerOrException();
        Networking.openConfigGui(player, modId, mode);
        return 0;
    }

    public enum Mode {
        NATIVE,
        BY_SPEC
    }
}
