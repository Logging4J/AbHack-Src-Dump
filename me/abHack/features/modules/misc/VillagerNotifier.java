//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import me.abHack.features.command.*;
import java.util.*;

public class VillagerNotifier extends Module
{
    private final /* synthetic */ Set<Entity> entities;
    private static /* synthetic */ VillagerNotifier instance;
    
    @Override
    public void onUpdate() {
        for (final Entity entity : VillagerNotifier.mc.world.loadedEntityList) {
            if (entity instanceof EntityVillager) {
                if (this.entities.contains(entity)) {
                    continue;
                }
                Command.sendMessage(String.valueOf(new StringBuilder().append("Villager Detected at: X:").append((int)entity.posX).append(" X: ").append((int)entity.posY).append(" Z:").append((int)entity.posZ)));
                this.entities.add(entity);
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.entities.clear();
    }
    
    public VillagerNotifier() {
        super("VillagerNotifier", "Notifies you when a Villager is discovered", Category.MISC, true, false, false);
        this.entities = new HashSet<Entity>();
        VillagerNotifier.instance = this;
    }
}
