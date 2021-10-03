package com.davenonymous.libnonymous.setup;

import com.davenonymous.libnonymous.Libnonymous;
import com.davenonymous.libnonymous.particles.BlockProjectionParticleFactory;
import com.davenonymous.libnonymous.particles.BlockProjectionParticleType;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
    }

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<BlockEntityType<?>> event) {

    }

    @SubscribeEvent
    public static void onParticleRegistry(final RegistryEvent.Register<ParticleType<?>> event) {
        IForgeRegistry<ParticleType<?>> registry = event.getRegistry();
        registry.register(new BlockProjectionParticleType(true).setRegistryName(Libnonymous.MODID, "block_projection_particle"));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onParticleFactoryRegistry(final ParticleFactoryRegisterEvent event) {
        minecraft.particleEngine.register(ModObjects.blockProjectionParticleType, new BlockProjectionParticleFactory());
    }
}
