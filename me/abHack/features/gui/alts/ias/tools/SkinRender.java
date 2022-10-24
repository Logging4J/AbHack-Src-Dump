//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.tools;

import net.minecraft.util.*;
import net.minecraft.client.renderer.texture.*;
import javax.imageio.*;
import java.io.*;
import net.minecraft.client.gui.*;

public class SkinRender
{
    private final /* synthetic */ File file;
    private /* synthetic */ ResourceLocation resourceLocation;
    private /* synthetic */ DynamicTexture previewTexture;
    private final /* synthetic */ TextureManager textureManager;
    
    public SkinRender(final TextureManager textureManager, final File file) {
        this.textureManager = textureManager;
        this.file = file;
    }
    
    private boolean loadPreview() {
        try {
            this.previewTexture = new DynamicTexture(ImageIO.read(this.file));
            this.resourceLocation = this.textureManager.getDynamicTextureLocation("ias", this.previewTexture);
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void drawImage(final int n, final int n2, final int n3, final int n4) {
        final boolean loadPreview;
        if (this.previewTexture == null && !(loadPreview = this.loadPreview())) {
            System.out.println("Failure to load preview.");
            return;
        }
        this.previewTexture.updateDynamicTexture();
        this.textureManager.bindTexture(this.resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(n, n2, 0.0f, 0.0f, n3, n4, 64.0f, 128.0f);
    }
}
