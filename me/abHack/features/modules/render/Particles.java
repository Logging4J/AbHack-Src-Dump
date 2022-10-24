//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;

public class Particles extends Module
{
    public void onUpdate() {
        final int n = (int)(Math.random() * 5.0 + 0.0);
        final int n2 = (int)(Math.random() * 3.0 + 1.0);
        final int n3 = (int)(Math.random() * 5.0 - 1.0);
        final int n4 = (int)(Math.random() * 47.0 + 1.0);
        if (n4 != 1 && n4 != 2 && n4 != 41) {
            Particles.mc.effectRenderer.spawnEffectParticle(n4, Particles.mc.player.posX + 1.5 + -n, Particles.mc.player.posY + n2, Particles.mc.player.posZ + 1.5 + -n3, 0.0, 0.5, 0.0, new int[] { 10 });
        }
    }
    
    public Particles() {
        super("Particles", "Display Particle.", Module.Category.RENDER, false, false, false);
    }
}
