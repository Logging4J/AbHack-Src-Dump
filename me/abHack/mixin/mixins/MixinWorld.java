//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.world.chunk.*;
import java.util.*;
import com.google.common.base.*;
import net.minecraft.entity.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ World.class })
public class MixinWorld
{
    @Redirect(method = { "getEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/Chunk;getEntitiesOfTypeWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/List;Lcom/google/common/base/Predicate;)V"))
    public <T extends Entity> void getEntitiesOfTypeWithinAABBHook(final Chunk chunk, final Class<? extends T> clazz, final AxisAlignedBB axisAlignedBB, final List<T> list, final Predicate<? super T> predicate) {
        try {
            chunk.getEntitiesOfTypeWithinAABB((Class)clazz, axisAlignedBB, (List)list, (Predicate)predicate);
        }
        catch (Exception ex) {}
    }
    
    @Redirect(method = { "handleMaterialAcceleration" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isPushedByWater()Z"))
    public boolean isPushedbyWaterHook(final Entity entity) {
        final PushEvent pushEvent = new PushEvent(2, entity);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        return entity.isPushedByWater() && !pushEvent.isCanceled();
    }
    
    @Inject(method = { "checkLightFor" }, at = { @At("HEAD") }, cancellable = true)
    private void checkLightForHook(final EnumSkyBlock enumSkyBlock, final BlockPos blockPos, final CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (NoRender.getInstance().isOn() && (boolean)NoRender.getInstance().skyLightUpdate.getValue() && enumSkyBlock == EnumSkyBlock.SKY) {
            callbackInfoReturnable.setReturnValue((Object)true);
            callbackInfoReturnable.cancel();
        }
    }
}
