//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import me.abHack.event.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.util.*;
import net.minecraft.client.settings.*;
import me.abHack.features.modules.movement.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { MovementInputFromOptions.class }, priority = 10001)
public class MixinMovementInputFromOptions extends MovementInput implements MixinInterface
{
    @Redirect(method = { "updatePlayerMoveState" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
    public boolean isKeyPressed(final KeyBinding keyBinding) {
        final int getKeyCode = keyBinding.getKeyCode();
        if (getKeyCode <= 0) {
            return keyBinding.isKeyDown();
        }
        if (getKeyCode >= 256) {
            return keyBinding.isKeyDown();
        }
        if (!PlayerTweaks.getInstance().isOn()) {
            return keyBinding.isKeyDown();
        }
        if (!(boolean)PlayerTweaks.getInstance().guiMove.getValue()) {
            return keyBinding.isKeyDown();
        }
        if (Minecraft.getMinecraft().currentScreen == null) {
            return keyBinding.isKeyDown();
        }
        if (Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            return keyBinding.isKeyDown();
        }
        return Keyboard.isKeyDown(getKeyCode);
    }
}
