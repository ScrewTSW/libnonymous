package com.davenonymous.libnonymous.gui.config.types;

import com.davenonymous.libnonymous.gui.framework.ColorHelper;
import com.davenonymous.libnonymous.gui.framework.event.MouseClickEvent;
import com.davenonymous.libnonymous.gui.framework.event.MouseEnterEvent;
import com.davenonymous.libnonymous.gui.framework.event.MouseExitEvent;
import com.davenonymous.libnonymous.gui.framework.event.WidgetEventResult;
import com.davenonymous.libnonymous.gui.framework.widgets.WidgetFontAwesome;
import com.davenonymous.libnonymous.gui.framework.widgets.WidgetIntegerSelect;
import net.minecraftforge.common.ForgeConfigSpec;

public class IntegerSettingListEntry extends SettingListEntry {
    WidgetIntegerSelect inputField;

    public IntegerSettingListEntry(String optionKey, String comment, ForgeConfigSpec.ConfigValue value, Object defaultValue, int columnWidth) {
        super(optionKey, comment, value, defaultValue, columnWidth);
    }

    @Override
    public void fillPanel(int entryHeight) {
        int val = (int) value.get();
        this.setSize(columnWidth, entryHeight+18);

        inputField = new WidgetIntegerSelect(0, Integer.MAX_VALUE, val);
        inputField.setDimensions(5, entryHeight-3, columnWidth - 26, 14);
        this.add(inputField);

        WidgetFontAwesome save = new WidgetFontAwesome(SAVE_ICON, WidgetFontAwesome.IconSize.MEDIUM);
        save.setColor(ColorHelper.COLOR_DISABLED);
        save.setPosition(columnWidth - 16, entryHeight-4);
        save.addListener(MouseEnterEvent.class, (event, widget) -> {
            save.setColor(ColorHelper.COLOR_ENABLED);
            return WidgetEventResult.CONTINUE_PROCESSING;
        });
        save.addListener(MouseExitEvent.class, (event, widget) -> {
            save.setColor(ColorHelper.COLOR_DISABLED);
            return WidgetEventResult.CONTINUE_PROCESSING;
        });
        save.addListener(MouseClickEvent.class, (event, widget) -> {
            int inputValue = inputField.getValue();
            value.set(inputValue);
            value.save();
            updateDefaultIconState();
            return WidgetEventResult.CONTINUE_PROCESSING;
        });
        this.add(save);
    }

    @Override
    public void setValueInInputField(Object defaultValue) {
        if(defaultValue == null) {
            inputField.setValue(0);
        } else {
            inputField.setValue((Integer) defaultValue);
        }
    }
}
