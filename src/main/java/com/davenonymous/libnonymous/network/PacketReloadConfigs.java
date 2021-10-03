package com.davenonymous.libnonymous.network;

import com.davenonymous.libnonymous.base.BasePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketReloadConfigs extends BasePacket {

    public PacketReloadConfigs() {
        super();
    }

    public PacketReloadConfigs(FriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public void doWork(Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().getSender().server.reload();
    }
}
