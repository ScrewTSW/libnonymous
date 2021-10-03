package com.davenonymous.libnonymous.network;

import com.davenonymous.libnonymous.base.BasePacket;
import com.davenonymous.libnonymous.serialization.Sync;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import org.lwjgl.glfw.GLFW;

import java.util.function.Supplier;

public class PacketClipboard extends BasePacket {
    @Sync
    protected String clipboardContent;

    public PacketClipboard(String clipboardContent) {
        super();
        this.clipboardContent = clipboardContent;
    }

    public PacketClipboard(FriendlyByteBuf buf) {
        super(buf);
    }

    @Override
    public void doWork(Supplier<NetworkEvent.Context> ctx) {
        GLFW.glfwSetClipboardString(Minecraft.getInstance().getWindow().getWindow(), this.clipboardContent);
    }
}
