//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.misc.*;
import net.minecraft.block.properties.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { BlockLiquid.class }, priority = 1782)
public class MixinBlockLiquid
{
    @Inject(method = { "canCollideCheck" }, at = { @At("HEAD") }, cancellable = true)
    public void canCollideCheck(final IBlockState blockState, final boolean b, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        callbackInfoReturnable.setReturnValue((Object)((Interact.getInstance().isOn() && (boolean)Interact.getInstance().liquid.getValue()) || (b && (int)blockState.getValue((IProperty)BlockLiquid.LEVEL) == 0)));
    }
}
