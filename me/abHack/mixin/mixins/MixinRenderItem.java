//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.features.modules.render.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderItem.class })
public class MixinRenderItem
{
    @Shadow
    private void renderModel(final IBakedModel bakedModel, final int n, final ItemStack itemStack) {
    }
    
    @Inject(method = { "renderItemModel" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift = At.Shift.BEFORE) })
    private void renderItemModel(final ItemStack itemStack, final IBakedModel bakedModel, final ItemCameraTransforms.TransformType transformType, final boolean b, final CallbackInfo callbackInfo) {
        final RenderItemEvent renderItemEvent = new RenderItemEvent(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
        MinecraftForge.EVENT_BUS.post((Event)renderItemEvent);
        if (ViewModel.getInstance().isEnabled()) {
            if (!b) {
                GlStateManager.scale(renderItemEvent.getMainHandScaleX(), renderItemEvent.getMainHandScaleY(), renderItemEvent.getMainHandScaleZ());
            }
            else {
                GlStateManager.scale(renderItemEvent.getOffHandScaleX(), renderItemEvent.getOffHandScaleY(), renderItemEvent.getOffHandScaleZ());
            }
        }
    }
}
