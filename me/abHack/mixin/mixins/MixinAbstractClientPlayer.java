//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.util.*;
import me.abHack.features.modules.client.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ AbstractClientPlayer.class })
public abstract class MixinAbstractClientPlayer
{
    @Inject(method = { "getLocationCape" }, at = { @At("HEAD") }, cancellable = true)
    public void getLocationCape(final CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
        if (Capes.getInstance().isEnabled()) {
            callbackInfoReturnable.setReturnValue((Object)Capes.getInstance().Cape());
        }
    }
}
