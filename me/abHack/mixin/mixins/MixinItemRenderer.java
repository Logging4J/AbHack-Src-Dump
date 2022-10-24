//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.features.modules.render.*;
import net.minecraft.client.renderer.*;
import me.abHack.util.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.item.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;

@Mixin({ ItemRenderer.class })
public abstract class MixinItemRenderer
{
    private final boolean injection;
    
    public MixinItemRenderer() {
        this.injection = true;
    }
    
    @Inject(method = { "transformSideFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    public void transformSideFirstPerson(final EnumHandSide enumHandSide, final float n, final CallbackInfo callbackInfo) {
        final RenderItemEvent renderItemEvent = new RenderItemEvent(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
        MinecraftForge.EVENT_BUS.post((Event)renderItemEvent);
        if (ViewModel.getInstance().isEnabled()) {
            GlStateManager.translate(((enumHandSide == EnumHandSide.RIGHT) ? 1 : -1) * 0.56f, -0.52f + ((ViewModel.getInstance().isDisabled() || (boolean)ViewModel.getInstance().doBob.getValue()) ? n : 0.0f) * -0.6f, -0.72f);
            if (enumHandSide == EnumHandSide.RIGHT) {
                GlStateManager.translate(renderItemEvent.getMainX(), renderItemEvent.getMainY(), renderItemEvent.getMainZ());
                RenderUtil.rotationHelper((float)renderItemEvent.getMainRotX(), (float)renderItemEvent.getMainRotY(), (float)renderItemEvent.getMainRotZ());
            }
            else {
                GlStateManager.translate(renderItemEvent.getOffX(), renderItemEvent.getOffY(), renderItemEvent.getOffZ());
                RenderUtil.rotationHelper((float)renderItemEvent.getOffRotX(), (float)renderItemEvent.getOffRotY(), (float)renderItemEvent.getOffRotZ());
            }
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "transformEatFirstPerson" }, at = { @At("HEAD") }, cancellable = true)
    private void transformEatFirstPerson(final float n, final EnumHandSide enumHandSide, final ItemStack itemStack, final CallbackInfo callbackInfo) {
        if (ViewModel.getInstance().isEnabled()) {
            if (!(boolean)ViewModel.getInstance().noEatAnimation.getValue()) {
                final float n2 = Minecraft.getMinecraft().player.getItemInUseCount() - n + 1.0f;
                final float n3 = n2 / itemStack.getMaxItemUseDuration();
                if (n3 < 0.8f) {
                    GlStateManager.translate(0.0f, MathHelper.abs(MathHelper.cos(n2 / 4.0f * 3.1415927f) * 0.1f), 0.0f);
                }
                final float n4 = 1.0f - (float)Math.pow(n3, 27.0);
                final int n5 = (enumHandSide == EnumHandSide.RIGHT) ? 1 : -1;
                GlStateManager.translate(n4 * 0.6f * n5 * (double)ViewModel.getInstance().eatX.getValue(), n4 * 0.5f * -(double)ViewModel.getInstance().eatY.getValue(), 0.0);
                GlStateManager.rotate(n5 * n4 * 90.0f, 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(n4 * 10.0f, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(n5 * n4 * 30.0f, 0.0f, 0.0f, 1.0f);
            }
            callbackInfo.cancel();
        }
    }
}
