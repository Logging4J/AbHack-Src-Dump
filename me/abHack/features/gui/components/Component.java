//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components;

import me.abHack.features.*;
import me.abHack.features.gui.components.items.*;
import net.minecraft.client.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;
import me.abHack.*;
import me.abHack.features.gui.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.abHack.features.gui.components.items.buttons.*;

public class Component extends Feature
{
    private /* synthetic */ boolean open;
    private /* synthetic */ int height;
    private /* synthetic */ int y2;
    private /* synthetic */ int angle;
    private /* synthetic */ boolean hidden;
    private /* synthetic */ int x2;
    private /* synthetic */ int y;
    private final /* synthetic */ int barHeight;
    private final /* synthetic */ ArrayList<Item> items;
    private final /* synthetic */ Minecraft minecraft;
    private /* synthetic */ int startcolor;
    public /* synthetic */ boolean drag;
    private /* synthetic */ int x;
    private /* synthetic */ int width;
    public static /* synthetic */ int[] counter1;
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drag(n, n2);
        Component.counter1 = new int[] { 1 };
        float n5 = 0.0f;
        if (this.open) {
            final float n4 = this.getTotalItemHeight() - 2.0f;
        }
        else {
            n5 = 0.0f;
        }
        final float n6 = n5;
        if (ClickGui.getInstance().rainbowg.getValue()) {
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.startcolor = ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB();
                ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB();
            }
        }
        else {
            this.startcolor = ColorUtil.toRGBA(ClickGui.getInstance().g_red.getValue(), ClickGui.getInstance().g_green.getValue(), ClickGui.getInstance().g_blue.getValue(), ClickGui.getInstance().g_alpha.getValue());
        }
        final int rgba = ColorUtil.toRGBA(ClickGui.getInstance().g_red1.getValue(), ClickGui.getInstance().g_green1.getValue(), ClickGui.getInstance().g_blue1.getValue(), ClickGui.getInstance().g_alpha1.getValue());
        RenderUtil.drawRect((float)this.x, (float)this.y, (float)(this.x + this.width), (float)(this.y + this.height - 5), ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue(), 255));
        RenderUtil.drawGradientSideways(this.x - 1, this.y, this.x + this.width + 1, this.y + this.barHeight - 2.0f, this.startcolor, rgba);
        if (this.open) {
            RenderUtil.drawGradientSideways(this.x - 1, this.y + 13.2f, this.x + this.width + 1, this.y + n6 + 19.0f, this.startcolor, rgba);
            RenderUtil.drawRect((float)this.x, this.y + 13.2f, (float)(this.x + this.width), this.y + this.height + n6, ColorUtil.toRGBA(0, 0, 0, ClickGui.getInstance().alphaBox.getValue()));
        }
        OyVey.textManager.drawStringWithShadow(this.getName(), this.x + 3.0f, this.y - 4.0f - OyVeyGui.getClickGui().getTextOffset(), -1);
        if (!this.open) {
            if (this.angle > 0) {
                this.angle -= 6;
            }
        }
        else if (this.angle < 180) {
            this.angle += 6;
        }
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        glColor(new Color(255, 255, 255, 255));
        this.minecraft.getTextureManager().bindTexture(new ResourceLocation("textures/arrow.png"));
        GlStateManager.translate((float)(this.getX() + this.getWidth() - 7), this.getY() + 6 - 0.3f, 0.0f);
        GlStateManager.rotate(calculateRotation((float)this.angle), 0.0f, 0.0f, 1.0f);
        drawModalRect(-5, -5, 0.0f, 0.0f, 10, 10, 10, 10, 10.0f, 10.0f);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        if (this.open) {
            RenderUtil.drawRect((float)this.x, this.y + 12.5f, (float)(this.x + this.width), this.y + this.height + n6, 1996488704);
            if (ClickGui.getInstance().outline.getValue()) {
                GlStateManager.disableTexture2D();
                GlStateManager.enableBlend();
                GlStateManager.disableAlpha();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.shadeModel(7425);
                GL11.glBegin(2);
                GL11.glColor4f(ClickGui.getInstance().red.getValue() / 255.0f, ClickGui.getInstance().green.getValue() / 255.0f, ClickGui.getInstance().blue.getValue() / 255.0f, 255.0f);
                GL11.glVertex3f((float)this.x, this.y - 0.5f, 0.0f);
                GL11.glVertex3f((float)(this.x + this.width), this.y - 0.5f, 0.0f);
                GL11.glVertex3f((float)(this.x + this.width), this.y + this.height + n6, 0.0f);
                GL11.glVertex3f((float)this.x, this.y + this.height + n6, 0.0f);
                GL11.glEnd();
                GlStateManager.shadeModel(7424);
                GlStateManager.disableBlend();
                GlStateManager.enableAlpha();
                GlStateManager.enableTexture2D();
            }
        }
        if (this.open) {
            float n7 = this.getY() + this.getHeight() - 3.0f;
            for (final Item item : this.getItems()) {
                ++Component.counter1[0];
                if (item.isHidden()) {
                    continue;
                }
                item.setLocation(this.x + 2.0f, n7);
                item.setWidth(this.getWidth() - 4);
                item.drawScreen(n, n2, n3);
                n7 += item.getHeight() + 1.5f;
            }
        }
    }
    
    public static void drawModalRect(final int n, final int n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8, final float n9, final float n10) {
        Gui.drawScaledCustomSizeModalRect(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    private void drag(final int n, final int n2) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + n;
        this.y = this.y2 + n2;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.x2 = this.x - n;
            this.y2 = this.y - n2;
            OyVeyGui.getClickGui().getComponents().forEach(component -> {
                if (component.drag) {
                    component.drag = false;
                }
                return;
            });
            this.drag = true;
            return;
        }
        if (n3 == 1 && this.isHovering(n, n2)) {
            this.open = !this.open;
            Component.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseClicked(n, n2, n3));
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void addButton(final Button e) {
        this.items.add(e);
    }
    
    public static float calculateRotation(float n) {
        if ((n %= 360.0f) >= 180.0f) {
            n -= 360.0f;
        }
        if (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    public final ArrayList<Item> getItems() {
        return this.items;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setupItems() {
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    private float getTotalItemHeight() {
        float n = 0.0f;
        final Iterator<Item> iterator = this.getItems().iterator();
        while (iterator.hasNext()) {
            n += iterator.next().getHeight() + 1.5f;
        }
        return n;
    }
    
    private boolean isHovering(final int n, final int n2) {
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
    }
    
    public Component(final String s, final int x, final int y, final boolean open) {
        super(s);
        this.minecraft = Minecraft.getMinecraft();
        this.items = new ArrayList<Item>();
        this.hidden = false;
        this.x = x;
        this.y = y;
        this.width = 88;
        this.height = 18;
        this.barHeight = 15;
        this.angle = 180;
        this.open = open;
        this.setupItems();
    }
    
    static {
        Component.counter1 = new int[] { 1 };
    }
    
    public int getY() {
        return this.y;
    }
    
    public void onKeyTyped(final char c, final int n) {
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.onKeyTyped(c, n));
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        if (n3 == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseReleased(n, n2, n3));
    }
    
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
    }
}
