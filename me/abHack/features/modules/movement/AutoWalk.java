//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoWalk extends Module
{
    public AutoWalk() {
        super("AutoWalk", "Automatically walks in a straight line", Module.Category.MOVEMENT, true, false, false);
    }
    
    @SubscribeEvent
    public void onUpdateInput(final InputUpdateEvent inputUpdateEvent) {
        inputUpdateEvent.getMovementInput().moveForward = 1.0f;
    }
}
