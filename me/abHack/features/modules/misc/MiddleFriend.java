//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import net.minecraft.entity.*;

public class MiddleFriend extends Module
{
    private /* synthetic */ boolean clicked;
    
    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown(2)) {
            if (!this.clicked && MiddleFriend.mc.currentScreen == null) {
                this.onClick();
            }
            this.clicked = true;
        }
        else {
            this.clicked = false;
        }
    }
    
    private void onClick() {
        final RayTraceResult objectMouseOver = MiddleFriend.mc.objectMouseOver;
        final Entity entityHit;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && (entityHit = objectMouseOver.entityHit) instanceof EntityPlayer) {
            if (OyVey.friendManager.isFriend(entityHit.getName())) {
                OyVey.friendManager.removeFriend(entityHit.getName());
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(entityHit.getName()).append(ChatFormatting.RED).append(" has been unfriended.")));
            }
            else {
                OyVey.friendManager.addFriend(entityHit.getName());
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.AQUA).append(entityHit.getName()).append(ChatFormatting.AQUA).append(" has been friended.")));
            }
        }
        this.clicked = true;
    }
    
    public MiddleFriend() {
        super("MiddleFriend", "Middleclick Friends.", Category.MISC, true, false, false);
        this.clicked = false;
    }
}
