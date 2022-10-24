//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.tools;

import net.minecraftforge.fml.relauncher.*;
import me.abHack.features.gui.alts.tools.alt.*;
import java.net.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import net.minecraft.client.*;

@SideOnly(Side.CLIENT)
public class SkinTools
{
    public static final /* synthetic */ File cachedir;
    private static final /* synthetic */ File skinOut;
    
    public static void cacheSkins() {
        if (!SkinTools.cachedir.exists() && !SkinTools.cachedir.mkdirs()) {
            System.out.println("Skin cache directory creation failed.");
        }
        for (final AccountData accountData : AltDatabase.getInstance().getAlts()) {
            final File file = new File(SkinTools.cachedir, String.valueOf(new StringBuilder().append(accountData.alias).append(".png")));
            try {
                final InputStream openStream = new URL(String.format("https://skins.minecraft.net/MinecraftSkins/%s.png", accountData.alias)).openStream();
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                final byte[] array = new byte[2048];
                int read;
                while ((read = openStream.read(array)) != -1) {
                    fileOutputStream.write(array, 0, read);
                }
                openStream.close();
                fileOutputStream.close();
            }
            catch (IOException ex) {
                try {
                    final InputStream openStream2 = new URL("https://skins.minecraft.net/MinecraftSkins/direwolf20.png").openStream();
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    final FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    final byte[] array2 = new byte[2048];
                    int read2;
                    while ((read2 = openStream2.read(array2)) != -1) {
                        fileOutputStream2.write(array2, 0, read2);
                    }
                    openStream2.close();
                    fileOutputStream2.close();
                }
                catch (IOException ex2) {}
            }
        }
    }
    
    public static void buildSkin(final String str) {
        BufferedImage read;
        try {
            read = ImageIO.read(new File(SkinTools.cachedir, String.valueOf(new StringBuilder().append(str).append(".png"))));
        }
        catch (IOException ex2) {
            if (SkinTools.skinOut.exists()) {
                SkinTools.skinOut.delete();
            }
            return;
        }
        final BufferedImage im = new BufferedImage(16, 32, 2);
        if (read.getHeight() == 64) {
            final int[] rgb = read.getRGB(8, 8, 8, 8, null, 0, 8);
            final int[] rgb2 = read.getRGB(20, 20, 8, 12, null, 0, 8);
            final int[] rgb3 = read.getRGB(44, 20, 4, 12, null, 0, 4);
            final int[] rgb4 = read.getRGB(36, 52, 4, 12, null, 0, 4);
            final int[] rgb5 = read.getRGB(4, 20, 4, 12, null, 0, 4);
            final int[] rgb6 = read.getRGB(20, 52, 4, 12, null, 0, 4);
            final int[] rgb7 = read.getRGB(40, 8, 8, 8, null, 0, 8);
            final int[] rgb8 = read.getRGB(20, 36, 8, 12, null, 0, 8);
            final int[] rgb9 = read.getRGB(44, 36, 4, 12, null, 0, 4);
            final int[] rgb10 = read.getRGB(52, 52, 4, 12, null, 0, 4);
            final int[] rgb11 = read.getRGB(4, 36, 4, 12, null, 0, 4);
            final int[] rgb12 = read.getRGB(4, 52, 4, 12, null, 0, 4);
            for (int i = 0; i < rgb7.length; ++i) {
                if (rgb7[i] == 0) {
                    rgb7[i] = rgb[i];
                }
            }
            for (int j = 0; j < rgb8.length; ++j) {
                if (rgb8[j] == 0) {
                    rgb8[j] = rgb2[j];
                }
            }
            for (int k = 0; k < rgb9.length; ++k) {
                if (rgb9[k] == 0) {
                    rgb9[k] = rgb3[k];
                }
            }
            for (int l = 0; l < rgb10.length; ++l) {
                if (rgb10[l] == 0) {
                    rgb10[l] = rgb4[l];
                }
            }
            for (int n = 0; n < rgb11.length; ++n) {
                if (rgb11[n] == 0) {
                    rgb11[n] = rgb5[n];
                }
            }
            for (int n2 = 0; n2 < rgb12.length; ++n2) {
                if (rgb12[n2] == 0) {
                    rgb12[n2] = rgb6[n2];
                }
            }
            im.setRGB(4, 0, 8, 8, rgb7, 0, 8);
            im.setRGB(4, 8, 8, 12, rgb8, 0, 8);
            im.setRGB(0, 8, 4, 12, rgb9, 0, 4);
            im.setRGB(12, 8, 4, 12, rgb10, 0, 4);
            im.setRGB(4, 20, 4, 12, rgb11, 0, 4);
            im.setRGB(8, 20, 4, 12, rgb12, 0, 4);
        }
        else {
            final int[] rgb13 = read.getRGB(8, 8, 8, 8, null, 0, 8);
            final int[] rgb14 = read.getRGB(20, 20, 8, 12, null, 0, 8);
            final int[] rgb15 = read.getRGB(44, 20, 4, 12, null, 0, 4);
            final int[] rgb16 = read.getRGB(4, 20, 4, 12, null, 0, 4);
            final int[] rgb17 = read.getRGB(40, 8, 8, 8, null, 0, 8);
            for (int n3 = 0; n3 < rgb17.length; ++n3) {
                if (rgb17[n3] == 0) {
                    rgb17[n3] = rgb13[n3];
                }
            }
            im.setRGB(4, 0, 8, 8, rgb17, 0, 8);
            im.setRGB(4, 8, 8, 12, rgb14, 0, 8);
            im.setRGB(0, 8, 4, 12, rgb15, 0, 4);
            im.setRGB(12, 8, 4, 12, rgb15, 0, 4);
            im.setRGB(4, 20, 4, 12, rgb16, 0, 4);
            im.setRGB(8, 20, 4, 12, rgb16, 0, 4);
        }
        try {
            ImageIO.write(im, "png", SkinTools.skinOut);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void javDrawSkin(final int n, final int n2, final int n3, final int n4) {
        if (!SkinTools.skinOut.exists()) {
            return;
        }
        new SkinRender(Minecraft.getMinecraft().getTextureManager(), SkinTools.skinOut).drawImage(n, n2, n3, n4);
    }
    
    static {
        cachedir = new File(Minecraft.getMinecraft().gameDir, "cachedImages/skins/");
        skinOut = new File(SkinTools.cachedir, "temp.png");
    }
}
