//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class MoveEvent extends EventStage
{
    private /* synthetic */ MoverType type;
    private /* synthetic */ double y;
    private /* synthetic */ double z;
    private /* synthetic */ double x;
    
    public double getZ() {
        return this.z;
    }
    
    public double getY() {
        return this.y;
    }
    
    public MoveEvent(final int n, final MoverType type, final double x, final double y, final double z) {
        super(n);
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setType(final MoverType type) {
        this.type = type;
    }
    
    public MoverType getType() {
        return this.type;
    }
    
    public double getX() {
        return this.x;
    }
}
