//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items.buttons;

import me.abHack.features.modules.*;
import java.util.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;
import me.abHack.features.modules.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import me.abHack.features.gui.components.*;
import me.abHack.features.gui.*;
import me.abHack.features.gui.components.items.*;

public class ModuleButton extends Button
{
    private /* synthetic */ List<Item> items;
    private /* synthetic */ boolean subOpen;
    private /* synthetic */ int logs;
    private final /* synthetic */ Module module;
    
    public Module getModule() {
        return this.module;
    }
    
    public ModuleButton(final Module module) {
        super(module.getName());
        this.items = new ArrayList<Item>();
        this.module = module;
        this.logs = 0;
        this.initSettings();
    }
    
    public void onKeyTyped(final char c, final int n) {
        super.onKeyTyped(c, n);
        if (!this.items.isEmpty() && this.subOpen) {
            for (final Item item : this.items) {
                if (item.isHidden()) {
                    continue;
                }
                item.onKeyTyped(c, n);
            }
        }
    }
    
    public void toggle() {
        this.module.toggle();
    }
    
    public boolean getState() {
        return this.module.isEnabled();
    }
    
    public void initSettings() {
        final ArrayList<Item> items = new ArrayList<Item>();
        if (!this.module.getSettings().isEmpty()) {
            for (final Setting setting : this.module.getSettings()) {
                if (setting.getValue() instanceof Boolean && !setting.getName().equals("Enabled")) {
                    items.add((Item)new BooleanButton((Setting)setting));
                }
                if (setting.getValue() instanceof Bind && !setting.getName().equalsIgnoreCase("Keybind") && !this.module.getName().equalsIgnoreCase("Hud")) {
                    items.add((Item)new BindButton((Setting)setting));
                }
                if ((setting.getValue() instanceof String || setting.getValue() instanceof Character) && !setting.getName().equalsIgnoreCase("displayName")) {
                    items.add((Item)new StringButton(setting));
                }
                if (setting.isNumberSetting() && setting.hasRestriction()) {
                    items.add((Item)new Slider(setting));
                }
                else {
                    if (!setting.isEnumSetting()) {
                        continue;
                    }
                    items.add((Item)new EnumButton((Setting)setting));
                }
            }
        }
        items.add((Item)new BindButton(this.module.getSettingByName("Keybind")));
        this.items = items;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (!this.items.isEmpty()) {
            if (n3 == 1 && this.isHovering(n, n2)) {
                this.subOpen = !this.subOpen;
                ModuleButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            if (this.subOpen) {
                for (final Item item : this.items) {
                    if (item.isHidden()) {
                        continue;
                    }
                    item.mouseClicked(n, n2, n3);
                }
            }
        }
    }
    
    public int getHeight() {
        if (this.subOpen) {
            int n = 14;
            for (final Item item : this.items) {
                if (item.isHidden()) {
                    continue;
                }
                n += item.getHeight() + 1;
            }
            return n + 2;
        }
        return 14;
    }
    
    public static void drawCompleteImage(final float n, final float n2, final int n3, final int n4) {
        GL11.glPushMatrix();
        GL11.glTranslatef(n, n2, 0.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex3f(0.0f, 0.0f, 0.0f);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex3f(0.0f, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex3f((float)n3, (float)n4, 0.0f);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex3f((float)n3, 0.0f, 0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public static float fa(float n) {
        if ((n %= 360.0f) >= 180.0f) {
            n -= 360.0f;
        }
        if (n < -180.0f) {
            n += 360.0f;
        }
        return n;
    }
    
    public static void drawModalRect(final int n, final int n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8, final float n9, final float n10) {
        Gui.drawScaledCustomSizeModalRect(n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        super.drawScreen(n, n2, n3);
        if (!this.items.isEmpty()) {
            if (HUD.getInstance().magenDavid.getValue()) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/logs.png"));
                GlStateManager.translate(this.getX() + this.getWidth() - 6.7f, this.getY() + 7.7f - 0.3f, 0.0f);
                GlStateManager.rotate(fa((float)this.logs), 0.0f, 0.0f, 1.0f);
                drawModalRect(-5, -5, 0.0f, 0.0f, 10, 10, 10, 10, 10.0f, 10.0f);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
            if (this.subOpen) {
                float n4 = 1.0f;
                this.logs += 5;
                for (final Item item : this.items) {
                    ++Component.counter1[0];
                    if (!item.isHidden()) {
                        item.setLocation(this.x + 1.0f, this.y + (n4 += 15.0f));
                        item.setHeight(15);
                        item.setWidth(this.width - 9);
                        item.drawScreen(n, n2, n3);
                    }
                    item.update();
                }
            }
            else {
                this.logs = 0;
            }
        }
        if (this.isHovering(n, n2)) {
            final DescriptionDisplay descriptionDisplay = OyVeyGui.getInstance().getDescriptionDisplay();
            descriptionDisplay.setDescription(this.module.getDescription());
            descriptionDisplay.setLocation((float)(n + 2), (float)(n2 + 1));
            descriptionDisplay.setDraw(true);
        }
    }
}
