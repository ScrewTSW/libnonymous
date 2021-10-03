package com.davenonymous.libnonymous.network;

import com.davenonymous.libnonymous.base.BasePacket;
import com.davenonymous.libnonymous.gui.framework.WidgetSlot;
import com.davenonymous.libnonymous.serialization.Sync;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class PacketEnabledSlots extends BasePacket {
    @Sync
    boolean[] enabledSlots;

    public PacketEnabledSlots(List<Slot> slots) {
        super();
        this.enabledSlots = new boolean[slots.size()];

        int index = 0;
        for(Slot slot : slots) {
            this.enabledSlots[index] = slot.isActive();
            index++;
        }
    }

    public PacketEnabledSlots(boolean[] enabledSlots) {
        super();
        this.enabledSlots = enabledSlots;
    }

    public PacketEnabledSlots(FriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public void doWork(Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer serverPlayer = ctx.get().getSender();
        int index = 0;
        for(Slot slot : serverPlayer.containerMenu.slots) {
            if(slot instanceof WidgetSlot) {
                if(index >= this.enabledSlots.length) {
                    break;
                }

                ((WidgetSlot) slot).setEnabled(this.enabledSlots[index]);
            }

            index++;
        }
    }
}
