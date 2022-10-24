//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import me.abHack.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import me.abHack.event.events.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.player.*;
import net.minecraft.item.*;

@Mixin({ PlayerControllerMP.class })
public abstract class MixinPlayerControllerMP
{
    @Shadow
    public abstract void syncCurrentPlayItem();
    
    @Redirect(method = { "onPlayerDamageBlock" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
    public float getPlayerRelativeBlockHardnessHook(final IBlockState blockState, final EntityPlayer entityPlayer, final World world, final BlockPos blockPos) {
        return blockState.getPlayerRelativeBlockHardness(entityPlayer, world, blockPos) * ((TpsSync.getInstance().isOn() && (boolean)TpsSync.getInstance().mining.getValue()) ? (1.0f / OyVey.serverManager.getTpsFactor()) : 1.0f);
    }
    
    @Inject(method = { "clickBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void clickBlockHook(final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event)new BlockEvent(3, blockPos, enumFacing));
    }
    
    @Inject(method = { "onPlayerDamageBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void onPlayerDamageBlockHook(final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event)new BlockEvent(4, blockPos, enumFacing));
    }
    
    @Inject(method = { "onPlayerDamageBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void onPlayerDamageBlockHooktwo(final BlockPos blockPos, final EnumFacing enumFacing, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        MinecraftForge.EVENT_BUS.post((Event)new PlayerDamageBlockEvent(0, blockPos, enumFacing));
    }
    
    @Inject(method = { "processRightClickBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void processRightClickBlock(final EntityPlayerSP entityPlayerSP, final WorldClient worldClient, final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d, final EnumHand enumHand, final CallbackInfoReturnable<EnumActionResult> callbackInfoReturnable) {
        final ProcessRightClickBlockEvent processRightClickBlockEvent = new ProcessRightClickBlockEvent(blockPos, enumHand, Minecraft.instance.player.getHeldItem(enumHand));
        MinecraftForge.EVENT_BUS.post((Event)processRightClickBlockEvent);
        if (processRightClickBlockEvent.isCanceled()) {
            callbackInfoReturnable.cancel();
        }
    }
    
    @Inject(method = { "onStoppedUsingItem" }, at = { @At("HEAD") }, cancellable = true)
    private void onStoppedUsingItem(final EntityPlayer entityPlayer, final CallbackInfo callbackInfo) {
        if (PacketEat.getInstance().isOn() && entityPlayer.getHeldItem(entityPlayer.getActiveHand()).getItem() instanceof ItemFood) {
            this.syncCurrentPlayItem();
            entityPlayer.stopActiveHand();
            callbackInfo.cancel();
        }
    }
}
