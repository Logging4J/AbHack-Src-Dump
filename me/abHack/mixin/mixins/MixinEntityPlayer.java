//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import com.mojang.authlib.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.player.*;
import net.minecraft.entity.*;
import me.abHack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityPlayer.class })
public abstract class MixinEntityPlayer extends EntityLivingBase
{
    public MixinEntityPlayer(final World world, final GameProfile gameProfile) {
        super(world);
    }
    
    @Inject(method = { "getCooldownPeriod" }, at = { @At("HEAD") }, cancellable = true)
    private void getCooldownPeriodHook(final CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (TpsSync.getInstance().isOn() && (boolean)TpsSync.getInstance().attack.getValue()) {
            callbackInfoReturnable.setReturnValue((Object)(float)(1.0 / this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).getBaseValue() * 20.0 * OyVey.serverManager.getTpsFactor()));
        }
    }
}
