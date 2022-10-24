//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.entity.*;
import javax.annotation.*;
import net.minecraft.util.math.*;
import com.google.common.base.*;
import me.abHack.features.modules.misc.*;
import java.util.*;
import me.abHack.features.modules.render.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityRenderer.class })
public class MixinEntityRenderer
{
    @Redirect(method = { "getMouseOver" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List<Entity> getEntitiesInAABBexcludingHook(final WorldClient worldClient, @Nullable final Entity entity, final AxisAlignedBB axisAlignedBB, @Nullable final Predicate<? super Entity> predicate) {
        if (NoEntityTrace.getINSTANCE().isOn() && NoEntityTrace.getINSTANCE().noTrace) {
            return new ArrayList<Entity>();
        }
        return (List<Entity>)worldClient.getEntitiesInAABBexcluding(entity, axisAlignedBB, (Predicate)predicate);
    }
    
    @ModifyVariable(method = { "orientCamera" }, ordinal = 3, at = @At(value = "STORE", ordinal = 0), require = 1)
    public double changeCameraDistanceHook(final double n) {
        return (double)((CameraClip.getInstance().isEnabled() && (boolean)CameraClip.getInstance().extend.getValue()) ? CameraClip.getInstance().distance.getValue() : n);
    }
    
    @ModifyVariable(method = { "orientCamera" }, ordinal = 7, at = @At(value = "STORE", ordinal = 0), require = 1)
    public double orientCameraHook(final double n) {
        return (double)((CameraClip.getInstance().isEnabled() && (boolean)CameraClip.getInstance().extend.getValue()) ? CameraClip.getInstance().distance.getValue() : ((CameraClip.getInstance().isEnabled() && !(boolean)CameraClip.getInstance().extend.getValue()) ? 4.0 : n));
    }
    
    @Inject(method = { "hurtCameraEffect" }, at = { @At("HEAD") }, cancellable = true)
    public void hurtCameraEffect(final float n, final CallbackInfo callbackInfo) {
        final NoRenderEvent noRenderEvent = new NoRenderEvent(1);
        MinecraftForge.EVENT_BUS.post((Event)noRenderEvent);
        if (noRenderEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }
}
