//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import java.nio.*;
import javax.imageio.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;

public class IconUtil
{
    static {
        INSTANCE = new IconUtil();
    }
    
    public ByteBuffer readImageToBuffer(final InputStream input) throws IOException {
        final BufferedImage read = ImageIO.read(input);
        final int[] rgb = read.getRGB(0, 0, read.getWidth(), read.getHeight(), null, 0, read.getWidth());
        final ByteBuffer allocate = ByteBuffer.allocate(4 * rgb.length);
        Arrays.stream(rgb).map(n -> n << 8 | (n >> 24 & 0xFF)).forEach(allocate::putInt);
        allocate.flip();
        return allocate;
    }
}
