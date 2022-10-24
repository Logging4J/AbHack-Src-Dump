//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { Entity.class }, priority = 998)
public class MixinEntity
{
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void addVelocityHook(final Entity entity, final double n, final double n2, final double n3) {
        final PushEvent pushEvent = new PushEvent(entity, n, n2, n3, true);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (!pushEvent.isCanceled()) {
            entity.motionX += pushEvent.x;
            entity.motionY += pushEvent.y;
            entity.motionZ += pushEvent.z;
            entity.isAirBorne = pushEvent.airbone;
        }
    }
}
