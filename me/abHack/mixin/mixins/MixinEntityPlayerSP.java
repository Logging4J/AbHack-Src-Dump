//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import net.minecraft.world.*;
import net.minecraft.client.network.*;
import net.minecraft.stats.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.movement.*;
import me.abHack.util.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;
import me.abHack.event.events.*;

@Mixin(value = { EntityPlayerSP.class }, priority = 9998)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer
{
    public MixinEntityPlayerSP(final Minecraft minecraft, final World world, final NetHandlerPlayClient netHandlerPlayClient, final StatisticsManager statisticsManager, final RecipeBook recipeBook) {
        super(world, netHandlerPlayClient.getGameProfile());
    }
    
    @Inject(method = { "sendChatMessage" }, at = { @At("HEAD") }, cancellable = true)
    public void sendChatMessage(final String s, final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new ChatEvent(s));
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") })
    private void preMotion(final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new UpdateWalkingPlayerEvent(0));
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    private void postMotion(final CallbackInfo callbackInfo) {
        MinecraftForge.EVENT_BUS.post((Event)new UpdateWalkingPlayerEvent(1));
    }
    
    @Inject(method = { "pushOutOfBlocks" }, at = { @At("HEAD") }, cancellable = true)
    private void pushOutOfBlocksHook(final double n, final double n2, final double n3, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        final PushEvent pushEvent = new PushEvent(1);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (pushEvent.isCanceled()) {
            callbackInfoReturnable.setReturnValue((Object)false);
        }
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal = 2))
    public void onLivingUpdate(final EntityPlayerSP entityPlayerSP, final boolean b) {
        Label_0100: {
            if (Sprint.getInstance().isOn()) {
                if (Sprint.getInstance().mode.getValue() == Sprint.Mode.RAGE) {
                    if (Util.mc.player.moveForward == 0.0f) {
                        if (Util.mc.player.moveStrafing == 0.0f) {
                            break Label_0100;
                        }
                    }
                }
                else if (Util.mc.player.movementInput.moveStrafe == 0.0f) {
                    break Label_0100;
                }
            }
            else if (Util.mc.player.movementInput.moveStrafe == 0.0f) {
                break Label_0100;
            }
            entityPlayerSP.setSprinting(true);
            return;
        }
        entityPlayerSP.setSprinting(b);
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(final AbstractClientPlayer abstractClientPlayer, final MoverType moverType, final double n, final double n2, final double n3) {
        final MoveEvent moveEvent = new MoveEvent(0, moverType, n, n2, n3);
        MinecraftForge.EVENT_BUS.post((Event)moveEvent);
        if (!moveEvent.isCanceled()) {
            super.move(moveEvent.getType(), moveEvent.getX(), moveEvent.getY(), moveEvent.getZ());
        }
    }
}
