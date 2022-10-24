//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import me.abHack.*;
import me.abHack.features.modules.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.setting.*;
import com.google.gson.*;
import me.abHack.manager.*;
import me.abHack.features.*;
import java.util.*;

public class ModuleCommand extends Command
{
    public ModuleCommand() {
        super("module", new String[] { "<module>", "<set/reset>", "<setting>", "<value>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("Modules: ");
            for (final Module.Category category : OyVey.moduleManager.getCategories()) {
                String str = String.valueOf(new StringBuilder().append(category.getName()).append(": "));
                for (final Module module : OyVey.moduleManager.getModulesByCategory(category)) {
                    str = String.valueOf(new StringBuilder().append(str).append(module.isEnabled() ? ChatFormatting.GREEN : ChatFormatting.RED).append(module.getName()).append(ChatFormatting.WHITE).append(", "));
                }
                sendMessage(str);
            }
            return;
        }
        final Module moduleByDisplayName = OyVey.moduleManager.getModuleByDisplayName(array[0]);
        if (moduleByDisplayName == null) {
            final Module moduleByName = OyVey.moduleManager.getModuleByName(array[0]);
            if (moduleByName == null) {
                sendMessage("This module doesnt exist.");
                return;
            }
            sendMessage(String.valueOf(new StringBuilder().append(" This is the original name of the module. Its current name is: ").append(moduleByName.getDisplayName())));
        }
        else {
            if (array.length == 2) {
                sendMessage(String.valueOf(new StringBuilder().append(moduleByDisplayName.getDisplayName()).append(" : ").append(moduleByDisplayName.getDescription())));
                for (final Setting<Object> setting : moduleByDisplayName.getSettings()) {
                    sendMessage(String.valueOf(new StringBuilder().append(setting.getName()).append(" : ").append(setting.getValue()).append(", ").append(setting.getDescription())));
                }
                return;
            }
            if (array.length == 3) {
                if (array[1].equalsIgnoreCase("set")) {
                    sendMessage("Please specify a setting.");
                }
                else if (array[1].equalsIgnoreCase("reset")) {
                    for (final Setting<Object> setting2 : moduleByDisplayName.getSettings()) {
                        setting2.setValue(setting2.getDefaultValue());
                    }
                }
                else {
                    sendMessage("This command doesnt exist.");
                }
                return;
            }
            if (array.length == 4) {
                sendMessage("Please specify a value.");
                return;
            }
            final Setting settingByName;
            if (array.length == 5 && (settingByName = moduleByDisplayName.getSettingByName(array[2])) != null) {
                final JsonParser jsonParser = new JsonParser();
                if (settingByName.getType().equalsIgnoreCase("String")) {
                    settingByName.setValue(array[3]);
                    sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_GRAY).append(moduleByDisplayName.getName()).append(" ").append(settingByName.getName()).append(" has been set to ").append(array[3]).append(".")));
                    return;
                }
                try {
                    if (settingByName.getName().equalsIgnoreCase("Enabled")) {
                        if (array[3].equalsIgnoreCase("true")) {
                            moduleByDisplayName.enable();
                        }
                        if (array[3].equalsIgnoreCase("false")) {
                            moduleByDisplayName.disable();
                        }
                    }
                    ConfigManager.setValueFromJson(moduleByDisplayName, settingByName, jsonParser.parse(array[3]));
                }
                catch (Exception ex) {
                    sendMessage(String.valueOf(new StringBuilder().append("Bad Value! This setting requires a: ").append(settingByName.getType()).append(" value.")));
                    return;
                }
                sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append(moduleByDisplayName.getName()).append(" ").append(settingByName.getName()).append(" has been set to ").append(array[3]).append(".")));
            }
        }
    }
}
