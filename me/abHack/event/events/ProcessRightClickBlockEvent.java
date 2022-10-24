//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

@Cancelable
public class ProcessRightClickBlockEvent extends EventStage
{
    public /* synthetic */ ItemStack stack;
    public /* synthetic */ BlockPos pos;
    public /* synthetic */ EnumHand hand;
    
    public ProcessRightClickBlockEvent(final BlockPos pos, final EnumHand hand, final ItemStack stack) {
        this.pos = pos;
        this.hand = hand;
        this.stack = stack;
    }
}
