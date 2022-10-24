//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.model.*;
import net.minecraft.inventory.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { LayerBipedArmor.class }, priority = 1898)
public class MixinLayerBipedArmor
{
    @Inject(method = { "setModelSlotVisible" }, at = { @At("HEAD") }, cancellable = true)
    protected void setModelSlotVisible(final ModelBiped modelBiped, final EntityEquipmentSlot entityEquipmentSlot, final CallbackInfo callbackInfo) {
        final NoRenderEvent noRenderEvent = new NoRenderEvent(0);
        MinecraftForge.EVENT_BUS.post((Event)noRenderEvent);
        if (noRenderEvent.isCanceled()) {
            callbackInfo.cancel();
            switch (entityEquipmentSlot) {
                case HEAD: {
                    modelBiped.bipedHead.showModel = false;
                    modelBiped.bipedHeadwear.showModel = false;
                }
                case CHEST: {
                    modelBiped.bipedBody.showModel = false;
                    modelBiped.bipedRightArm.showModel = false;
                    modelBiped.bipedLeftArm.showModel = false;
                }
                case LEGS: {
                    modelBiped.bipedBody.showModel = false;
                    modelBiped.bipedRightLeg.showModel = false;
                    modelBiped.bipedLeftLeg.showModel = false;
                }
                case FEET: {
                    modelBiped.bipedRightLeg.showModel = false;
                    modelBiped.bipedLeftLeg.showModel = false;
                    break;
                }
            }
        }
    }
}
