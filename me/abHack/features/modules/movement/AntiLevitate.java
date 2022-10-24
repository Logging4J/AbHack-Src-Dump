//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import net.minecraft.potion.*;
import java.util.*;

public class AntiLevitate extends Module
{
    public AntiLevitate() {
        super("AntiLevitate", "Removes shulker levitation", Module.Category.MOVEMENT, false, false, false);
    }
    
    public void onUpdate() {
        if (AntiLevitate.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionFromResourceLocation("levitation")))) {
            AntiLevitate.mc.player.removeActivePotionEffect(Potion.getPotionFromResourceLocation("levitation"));
        }
    }
}
