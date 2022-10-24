//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.passive.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.movement.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ AbstractHorse.class })
public class MixinAbstractHorse
{
    @Inject(method = { "isHorseSaddled" }, at = { @At("HEAD") }, cancellable = true)
    public void isHorseSaddled(final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (EntityControl.INSTANCE.isEnabled()) {
            callbackInfoReturnable.setReturnValue((Object)true);
        }
    }
}
