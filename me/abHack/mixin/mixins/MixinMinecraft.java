//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.crash.*;
import org.spongepowered.asm.mixin.injection.*;
import org.lwjgl.input.*;
import net.minecraftforge.common.*;
import me.abHack.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.*;
import net.minecraft.client.entity.*;
import me.abHack.features.modules.player.*;
import net.minecraft.client.multiplayer.*;

@Mixin({ Minecraft.class })
public abstract class MixinMinecraft
{
    @Inject(method = { "shutdownMinecraftApplet" }, at = { @At("HEAD") })
    private void stopClient(final CallbackInfo callbackInfo) {
        this.unload();
    }
    
    @Redirect(method = { "run" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayCrashReport(Lnet/minecraft/crash/CrashReport;)V"))
    public void displayCrashReport(final Minecraft minecraft, final CrashReport crashReport) {
        this.unload();
    }
    
    @Inject(method = { "runTickKeyboard" }, at = { @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE) })
    private void onKeyboard(final CallbackInfo callbackInfo) {
        int eventKey = 0;
        if (Keyboard.getEventKey() == 0) {
            final int n = Keyboard.getEventCharacter() + '\u0100';
        }
        else {
            eventKey = Keyboard.getEventKey();
        }
        final int n2 = eventKey;
        if (Keyboard.getEventKeyState()) {
            MinecraftForge.EVENT_BUS.post((Event)new KeyEvent(n2));
        }
    }
    
    private void unload() {
        OyVey.LOGGER.info("Initiated client shutdown.");
        OyVey.onUnload();
        OyVey.LOGGER.info("Finished client shutdown.");
    }
    
    @Redirect(method = { "sendClickBlockToController" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;isHandActive()Z"))
    private boolean isHandActiveWrapper(final EntityPlayerSP entityPlayerSP) {
        return !MultiTask.getInstance().isOn() && entityPlayerSP.isHandActive();
    }
    
    @Redirect(method = { "rightClickMouse" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getIsHittingBlock()Z", ordinal = 0))
    private boolean isHittingBlockHook(final PlayerControllerMP playerControllerMP) {
        return !MultiTask.getInstance().isOn() && playerControllerMP.getIsHittingBlock();
    }
}
