//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util;

import java.io.*;
import java.util.regex.*;

public final class VersionNumber implements Comparable<VersionNumber>, Serializable
{
    private static final long serialVersionUID = 1L;
    public static final VersionNumber NONE;
    private static final Pattern PATTERN;
    private final long value;
    private final String suffix;
    
    private VersionNumber() {
        this.value = 0L;
        this.suffix = "";
    }
    
    private VersionNumber(final short[] array) {
        this(array, null);
    }
    
    private VersionNumber(final short[] array, final String s) {
        this.value = pack(array);
        this.suffix = ((s != null) ? s : "");
    }
    
    private VersionNumber(final short n, final short n2, final short n3, final short n4) {
        this(n, n2, n3, n4, null);
    }
    
    private VersionNumber(final short n, final short n2, final short n3, final short n4, final String s) {
        this.value = pack(n, n2, n3, n4);
        this.suffix = ((s != null) ? s : "");
    }
    
    @Override
    public String toString() {
        final short[] unpack = unpack(this.value);
        return String.format("%d.%d%3$s%4$s%5$s", unpack[0], unpack[1], ((this.value & 0x7FFFFFFFL) > 0L) ? String.format(".%d", unpack[2]) : "", ((this.value & 0x7FFFL) > 0L) ? String.format(".%d", unpack[3]) : "", this.suffix);
    }
    
    @Override
    public int compareTo(final VersionNumber versionNumber) {
        if (versionNumber == null) {
            return 1;
        }
        final long n = this.value - versionNumber.value;
        return (n > 0L) ? 1 : ((n < 0L) ? -1 : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof VersionNumber && ((VersionNumber)o).value == this.value;
    }
    
    @Override
    public int hashCode() {
        return (int)(this.value >> 32) ^ (int)(this.value & 0xFFFFFFFFL);
    }
    
    private static long pack(final short... array) {
        return (long)array[0] << 48 | (long)array[1] << 32 | (long)(array[2] << 16) | (long)array[3];
    }
    
    private static short[] unpack(final long n) {
        return new short[] { (short)(n >> 48), (short)(n >> 32 & 0x7FFFL), (short)(n >> 16 & 0x7FFFL), (short)(n & 0x7FFFL) };
    }
    
    public static VersionNumber parse(final String s) {
        return parse(s, VersionNumber.NONE);
    }
    
    public static VersionNumber parse(final String s, final String s2) {
        return parse(s, parse(s2));
    }
    
    private static VersionNumber parse(final String input, final VersionNumber versionNumber) {
        if (input == null) {
            return versionNumber;
        }
        final Matcher matcher = VersionNumber.PATTERN.matcher(input);
        if (!matcher.matches()) {
            return versionNumber;
        }
        final short[] array = new short[4];
        for (int i = 0; i < 4; ++i) {
            final String group = matcher.group(i + 1);
            if (group != null) {
                final int int1 = Integer.parseInt(group);
                if (int1 > 32767) {
                    throw new IllegalArgumentException("Version parts cannot exceed 32767, found " + int1);
                }
                array[i] = (short)int1;
            }
        }
        return new VersionNumber(array, matcher.group(5));
    }
    
    static {
        NONE = new VersionNumber();
        PATTERN = Pattern.compile("^(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5})(?:\\.(\\d{1,5}))?)?)?(-[a-zA-Z0-9_\\-]+)?$");
    }
}
