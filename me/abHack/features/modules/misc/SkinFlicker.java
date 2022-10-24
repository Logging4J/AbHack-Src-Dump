//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import java.util.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;

public class SkinFlicker extends Module
{
    private final /* synthetic */ Random r;
    private final /* synthetic */ int len;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Integer> slowness;
    private static final /* synthetic */ EnumPlayerModelParts[] PARTS_HORIZONTAL;
    private static final /* synthetic */ EnumPlayerModelParts[] PARTS_VERTICAL;
    
    public SkinFlicker() {
        super("SkinFlicker", "Dynamic skin.", Category.MISC, false, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.HORIZONTAL));
        this.slowness = (Setting<Integer>)this.register(new Setting("Slowness", (T)2, (T)1, (T)10));
        this.r = new Random();
        this.len = EnumPlayerModelParts.values().length;
    }
    
    @Override
    public void onTick() {
        if (SkinFlicker.mc.player == null) {
            return;
        }
        switch (this.mode.getValue()) {
            case RANDOM: {
                if (SkinFlicker.mc.player.ticksExisted % this.slowness.getValue() != 0) {
                    return;
                }
                SkinFlicker.mc.gameSettings.switchModelPartEnabled(EnumPlayerModelParts.values()[this.r.nextInt(this.len)]);
                break;
            }
            case VERTICAL:
            case HORIZONTAL: {
                int n = SkinFlicker.mc.player.ticksExisted / this.slowness.getValue() % (SkinFlicker.PARTS_HORIZONTAL.length * 2);
                boolean b = false;
                if (n >= SkinFlicker.PARTS_HORIZONTAL.length) {
                    b = true;
                    n -= SkinFlicker.PARTS_HORIZONTAL.length;
                }
                SkinFlicker.mc.gameSettings.setModelPartEnabled(this.mode.getValue().equals("VERTICAL") ? SkinFlicker.PARTS_VERTICAL[n] : SkinFlicker.PARTS_HORIZONTAL[n], b);
                break;
            }
        }
    }
    
    static {
        PARTS_HORIZONTAL = new EnumPlayerModelParts[] { EnumPlayerModelParts.LEFT_SLEEVE, EnumPlayerModelParts.JACKET, EnumPlayerModelParts.HAT, EnumPlayerModelParts.LEFT_PANTS_LEG, EnumPlayerModelParts.RIGHT_PANTS_LEG, EnumPlayerModelParts.RIGHT_SLEEVE };
        PARTS_VERTICAL = new EnumPlayerModelParts[] { EnumPlayerModelParts.HAT, EnumPlayerModelParts.JACKET, EnumPlayerModelParts.LEFT_SLEEVE, EnumPlayerModelParts.RIGHT_SLEEVE, EnumPlayerModelParts.LEFT_PANTS_LEG, EnumPlayerModelParts.RIGHT_PANTS_LEG };
    }
    
    public enum Mode
    {
        VERTICAL, 
        HORIZONTAL, 
        RANDOM;
    }
}
