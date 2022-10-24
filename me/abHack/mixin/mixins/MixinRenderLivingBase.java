//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import me.abHack.event.events.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.render.*;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderLivingBase.class })
public abstract class MixinRenderLivingBase<T extends EntityLivingBase> extends Render<T>
{
    public MixinRenderLivingBase(final RenderManager renderManager, final ModelBase modelBase, final float n) {
        super(renderManager);
    }
    
    @Redirect(method = { "renderModel" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void renderModelHook(final ModelBase modelBase, final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        boolean b = false;
        if (Skeleton.getInstance().isEnabled() || ESP.getInstance().isEnabled()) {
            final RenderEntityModelEvent renderEntityModelEvent = new RenderEntityModelEvent(0, modelBase, entity, n, n2, n3, n4, n5, n6);
            if (Skeleton.getInstance().isEnabled()) {
                Skeleton.getInstance().onRenderModel(renderEntityModelEvent);
            }
            if (ESP.getInstance().isEnabled()) {
                ESP.getInstance().onRenderModel(renderEntityModelEvent);
                if (renderEntityModelEvent.isCanceled()) {
                    b = true;
                }
            }
        }
        if (!b) {
            modelBase.render(entity, n, n2, n3, n4, n5, n6);
        }
    }
    
    @Inject(method = { "doRender" }, at = { @At("HEAD") })
    public void doRenderPre(final T t, final double n, final double n2, final double n3, final float n4, final float n5, final CallbackInfo callbackInfo) {
        if (Chams.getInstance().isEnabled() && t != null) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1100000.0f);
        }
    }
    
    @Inject(method = { "doRender" }, at = { @At("RETURN") })
    public void doRenderPost(final T t, final double n, final double n2, final double n3, final float n4, final float n5, final CallbackInfo callbackInfo) {
        if (Chams.getInstance().isEnabled() && t != null) {
            GL11.glPolygonOffset(1.0f, 1000000.0f);
            GL11.glDisable(32823);
        }
    }
}
