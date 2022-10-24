//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.gui;

import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class GuiButtonWithImage extends GuiButton
{
    private static final /* synthetic */ ResourceLocation customButtonTextures;
    
    public GuiButtonWithImage(final int n, final int n2, final int n3, final int n4, final int n5, final String s) {
        super(n, n2, n3, n4, n5, s);
    }
    
    static {
        customButtonTextures = new ResourceLocation("accswitcher:textures/gui/custombutton.png");
    }
    
    public void drawButton(final Minecraft minecraft, final int n, final int n2, final float n3) {
        if (this.visible) {
            final FontRenderer fontRenderer = minecraft.fontRenderer;
            minecraft.getTextureManager().bindTexture(GuiButtonWithImage.customButtonTextures);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.hovered = (n >= this.x && n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height);
            final int getHoverState = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.x, this.y, 0, 46 + getHoverState * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + getHoverState * 20, this.width / 2, this.height);
            this.mouseDragged(minecraft, n, n2);
            int packedFGColour = 14737632;
            if (this.packedFGColour != 0) {
                packedFGColour = this.packedFGColour;
            }
            else if (!this.enabled) {
                packedFGColour = 10526880;
            }
            else if (this.hovered) {
                packedFGColour = 16777120;
            }
            this.drawCenteredString(fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, packedFGColour);
        }
    }
}
