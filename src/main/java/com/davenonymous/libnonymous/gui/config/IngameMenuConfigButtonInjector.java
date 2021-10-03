package com.davenonymous.libnonymous.gui.config;

import com.davenonymous.libnonymous.network.PacketOpenConfigGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class IngameMenuConfigButtonInjector {
    
    private static final Minecraft minecraft = Minecraft.getInstance();
    
    @SubscribeEvent
    public static void onInitGui(GuiScreenEvent.InitGuiEvent event) {
        if(!(event.getGui() instanceof OptionsScreen)) {
            return;
        }

        List<AbstractWidget> widgets = event.getWidgetList();
        if(widgets.size() == 0) {
            return;
        }

        AbstractWidget lastWidget = widgets.get(widgets.size()-1);
        int buttonX = lastWidget.x + lastWidget.getWidth() + 5;
        int buttonY = lastWidget.y;

        event.addWidget(new ConfigButton(buttonX, buttonY));
    }

    private static class ConfigButton extends Button {

        public ConfigButton(int x, int y) {
            super(x, y, 50, 20, new TextComponent(I18n.get("libnonymous.config.gui.label.mods")), ConfigButton::onPress);
        }

        public static void onPress(Button button) {
            PacketOpenConfigGuiHandler.openConfigGuiForAll(minecraft.screen);
        }
    }
}
