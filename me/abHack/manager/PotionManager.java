//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import net.minecraft.entity.player.*;
import java.util.concurrent.*;
import net.minecraft.client.resources.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.potion.*;
import java.util.*;

public class PotionManager extends Feature
{
    private final /* synthetic */ Map<EntityPlayer, PotionList> potions;
    
    public List<PotionEffect> getOwnPotions() {
        return this.getPlayerPotions((EntityPlayer)PotionManager.mc.player);
    }
    
    public PotionManager() {
        this.potions = new ConcurrentHashMap<EntityPlayer, PotionList>();
    }
    
    public String getColoredPotionString(final PotionEffect potionEffect) {
        return this.getPotionString(potionEffect);
    }
    
    public PotionEffect[] getImportantPotions(final EntityPlayer entityPlayer) {
        final PotionEffect[] array = new PotionEffect[3];
        for (final PotionEffect potionEffect : this.getPlayerPotions(entityPlayer)) {
            final String lowerCase = I18n.format(potionEffect.getPotion().getName(), new Object[0]).toLowerCase();
            switch (lowerCase) {
                case "strength": {
                    array[0] = potionEffect;
                }
                case "weakness": {
                    array[1] = potionEffect;
                }
                case "speed": {
                    array[2] = potionEffect;
                    continue;
                }
            }
        }
        return array;
    }
    
    public String getPotionString(final PotionEffect potionEffect) {
        return String.valueOf(new StringBuilder().append(I18n.format(potionEffect.getPotion().getName(), new Object[0])).append(" ").append(potionEffect.getAmplifier() + 1).append(" ").append(ChatFormatting.WHITE).append(Potion.getPotionDurationString(potionEffect, 1.0f)));
    }
    
    public List<PotionEffect> getPlayerPotions(final EntityPlayer entityPlayer) {
        final PotionList list = this.potions.get(entityPlayer);
        List<PotionEffect> effects = new ArrayList<PotionEffect>();
        if (list != null) {
            effects = list.getEffects();
        }
        return effects;
    }
    
    public static class PotionList
    {
        private final /* synthetic */ List<PotionEffect> effects;
        
        public List<PotionEffect> getEffects() {
            return this.effects;
        }
        
        public PotionList() {
            this.effects = new ArrayList<PotionEffect>();
        }
        
        public void addEffect(final PotionEffect potionEffect) {
            if (potionEffect != null) {
                this.effects.add(potionEffect);
            }
        }
    }
}
