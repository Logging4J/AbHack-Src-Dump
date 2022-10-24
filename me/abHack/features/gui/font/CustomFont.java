//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.font;

import net.minecraft.client.renderer.texture.*;
import java.awt.*;
import java.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class CustomFont extends CFont
{
    protected /* synthetic */ CFont.CharData[] boldChars;
    private final /* synthetic */ int[] colorCode;
    protected /* synthetic */ CFont.CharData[] boldItalicChars;
    protected /* synthetic */ CFont.CharData[] italicChars;
    protected /* synthetic */ DynamicTexture texItalic;
    protected /* synthetic */ DynamicTexture texItalicBold;
    protected /* synthetic */ DynamicTexture texBold;
    
    public float drawStringWithShadow(final String s, final double n, final double n2, final int n3) {
        return Math.max(this.drawString(s, n + 1.0, n2 + 1.0, n3, true), this.drawString(s, n, n2, n3, false));
    }
    
    public CustomFont(final Font font, final boolean b, final boolean b2) {
        super(font, b, b2);
        this.colorCode = new int[32];
        this.boldChars = new CFont.CharData[256];
        this.italicChars = new CFont.CharData[256];
        this.boldItalicChars = new CFont.CharData[256];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n, n2, n3, false);
    }
    
    private void setupMinecraftColorcodes() {
        for (int i = 0; i < 32; ++i) {
            final int n = (i >> 3 & 0x1) * 85;
            int n2 = (i >> 2 & 0x1) * 170 + n;
            int n3 = (i >> 1 & 0x1) * 170 + n;
            int n4 = (i >> 0 & 0x1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.colorCode[i] = ((n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | (n4 & 0xFF));
        }
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }
    
    public float drawCenteredString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n - this.getStringWidth(s) / 2, n2, n3);
    }
    
    public float drawCenteredStringWithShadow(final String s, final float n, final float n2, final int n3) {
        return this.drawStringWithShadow(s, n - this.getStringWidth(s) / 2, n2, n3);
    }
    
    public List<String> wrapWords(final String s, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.getStringWidth(s) > n) {
            final String[] split = s.split(" ");
            String str = "";
            char c = '\uffff';
            for (final String str2 : split) {
                for (int j = 0; j < str2.toCharArray().length; ++j) {
                    if (str2.toCharArray()[j] == '§' && j < str2.toCharArray().length - 1) {
                        c = str2.toCharArray()[j + 1];
                    }
                }
                if (this.getStringWidth(String.valueOf(new StringBuilder().append(str).append(str2).append(" "))) < n) {
                    str = String.valueOf(new StringBuilder().append(str).append(str2).append(" "));
                }
                else {
                    list.add(str);
                    str = String.valueOf(new StringBuilder().append("§").append(c).append(str2).append(" "));
                }
            }
            if (str.length() > 0) {
                if (this.getStringWidth(str) < n) {
                    list.add(String.valueOf(new StringBuilder().append("§").append(c).append(str).append(" ")));
                }
                else {
                    final Iterator<String> iterator = this.formatString(str, n).iterator();
                    while (iterator.hasNext()) {
                        list.add(iterator.next());
                    }
                }
            }
        }
        else {
            list.add(s);
        }
        return list;
    }
    
    public float drawString(final String s, double n, double n2, int n3, final boolean b) {
        --n;
        n2 -= 2.0;
        if (s == null) {
            return 0.0f;
        }
        if (n3 == 553648127) {
            n3 = 16777215;
        }
        if ((n3 & 0xFC000000) == 0x0) {
            n3 |= 0xFF000000;
        }
        if (b) {
            n3 = ((n3 & 0xFCFCFC) >> 2 | (n3 & 0xFF000000));
        }
        CFont.CharData[] array = this.charData;
        final float n4 = (n3 >> 24 & 0xFF) / 255.0f;
        int n5 = 0;
        int n6 = 0;
        boolean b2 = false;
        boolean b3 = false;
        final boolean b4 = true;
        n *= 2.0;
        n2 *= 2.0;
        if (b4) {
            GL11.glPushMatrix();
            GlStateManager.scale(0.5, 0.5, 0.5);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n4);
            final int length = s.length();
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(this.tex.getGlTextureId());
            GL11.glBindTexture(3553, this.tex.getGlTextureId());
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '§' && i < length) {
                    int index = 21;
                    try {
                        index = "0123456789abcdefklmnor".indexOf(s.charAt(i + 1));
                    }
                    catch (Exception ex) {}
                    if (index < 16) {
                        n5 = 0;
                        n6 = 0;
                        b3 = false;
                        b2 = false;
                        GlStateManager.bindTexture(this.tex.getGlTextureId());
                        array = this.charData;
                        if (index < 0 || index > 15) {
                            index = 15;
                        }
                        if (b) {
                            index += 16;
                        }
                        final int n7 = this.colorCode[index];
                        GlStateManager.color((n7 >> 16 & 0xFF) / 255.0f, (n7 >> 8 & 0xFF) / 255.0f, (n7 & 0xFF) / 255.0f, n4);
                    }
                    else if (index != 16) {
                        if (index == 17) {
                            n5 = 1;
                            if (n6 != 0) {
                                GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                                array = this.boldItalicChars;
                            }
                            else {
                                GlStateManager.bindTexture(this.texBold.getGlTextureId());
                                array = this.boldChars;
                            }
                        }
                        else if (index == 18) {
                            b2 = true;
                        }
                        else if (index == 19) {
                            b3 = true;
                        }
                        else if (index == 20) {
                            n6 = 1;
                            if (n5 != 0) {
                                GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                                array = this.boldItalicChars;
                            }
                            else {
                                GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                                array = this.italicChars;
                            }
                        }
                        else if (index == 21) {
                            n5 = 0;
                            n6 = 0;
                            b3 = false;
                            b2 = false;
                            GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n4);
                            GlStateManager.bindTexture(this.tex.getGlTextureId());
                            array = this.charData;
                        }
                    }
                    ++i;
                }
                else if (char1 < array.length && char1 >= '\0') {
                    GL11.glBegin(4);
                    this.drawChar(array, char1, (float)n, (float)n2);
                    GL11.glEnd();
                    if (b2) {
                        this.drawLine(n, n2 + array[char1].height / 2, n + array[char1].width - 8.0, n2 + array[char1].height / 2, 1.0f);
                    }
                    if (b3) {
                        this.drawLine(n, n2 + array[char1].height - 2.0, n + array[char1].width - 8.0, n2 + array[char1].height - 2.0, 1.0f);
                    }
                    n += array[char1].width - 8 + this.charOffset;
                }
            }
            GL11.glHint(3155, 4352);
            GL11.glPopMatrix();
        }
        return (float)n / 2.0f;
    }
    
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        this.setupBoldItalicIDs();
    }
    
    public int getStringWidth(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        CFont.CharData[] array = this.charData;
        int n2 = 0;
        int n3 = 0;
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '§' && i < length) {
                final int index = "0123456789abcdefklmnor".indexOf(char1);
                if (index < 16) {
                    n2 = 0;
                    n3 = 0;
                }
                else if (index == 17) {
                    n2 = 1;
                    if (n3 != 0) {
                        array = this.boldItalicChars;
                    }
                    else {
                        array = this.boldChars;
                    }
                }
                else if (index == 20) {
                    n3 = 1;
                    if (n2 != 0) {
                        array = this.boldItalicChars;
                    }
                    else {
                        array = this.italicChars;
                    }
                }
                else if (index == 21) {
                    n2 = 0;
                    n3 = 0;
                    array = this.charData;
                }
                ++i;
            }
            else if (char1 < array.length && char1 >= '\0') {
                n += array[char1].width - 8 + this.charOffset;
            }
        }
        return n / 2;
    }
    
    public List<String> formatString(final String s, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        String s2 = "";
        char c = '\uffff';
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c2 = charArray[i];
            if (c2 == '§' && i < charArray.length - 1) {
                c = charArray[i + 1];
            }
            if (this.getStringWidth(String.valueOf(new StringBuilder().append(s2).append(c2))) < n) {
                s2 = String.valueOf(new StringBuilder().append(s2).append(c2));
            }
            else {
                list.add(s2);
                s2 = String.valueOf(new StringBuilder().append("§").append(c).append(c2));
            }
        }
        if (s2.length() > 0) {
            list.add(s2);
        }
        return list;
    }
    
    private void drawLine(final double n, final double n2, final double n3, final double n4, final float n5) {
        GL11.glDisable(3553);
        GL11.glLineWidth(n5);
        GL11.glBegin(1);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
}
