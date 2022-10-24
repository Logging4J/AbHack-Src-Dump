//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.abHack.features.modules.misc.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiScreen.class })
public class MixinGuiScreen extends Gui
{
    @Inject(method = { "renderToolTip" }, at = { @At("HEAD") }, cancellable = true)
    public void renderToolTipHook(final ItemStack itemStack, final int n, final int n2, final CallbackInfo callbackInfo) {
        if (ShulkerViewer.getInstance().isOn() && itemStack.getItem() instanceof ItemShulkerBox) {
            ShulkerViewer.getInstance().renderShulkerToolTip(itemStack, n, n2, (String)null);
            callbackInfo.cancel();
        }
    }
}
