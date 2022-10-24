//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ Render.class })
abstract class MixinRenderer<T extends Entity>
{
    @Shadow
    protected boolean renderOutlines;
    @Shadow
    @Final
    protected RenderManager renderManager;
    
    @Shadow
    protected abstract boolean bindEntityTexture(final T p0);
    
    @Shadow
    protected abstract int getTeamColor(final T p0);
}
