//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

@Cancelable
public class PlayerDamageBlockEvent extends EventStage
{
    public /* synthetic */ BlockPos pos;
    public /* synthetic */ EnumFacing facing;
    
    public PlayerDamageBlockEvent(final int n, final BlockPos pos, final EnumFacing facing) {
        super(n);
        this.pos = pos;
        this.facing = facing;
    }
    
    public final BlockPos getPos() {
        return this.pos;
    }
}
