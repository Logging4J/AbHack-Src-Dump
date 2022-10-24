//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.account;

import me.abHack.features.gui.alts.tools.alt.*;
import me.abHack.features.gui.alts.ias.enums.*;
import java.util.*;
import me.abHack.features.gui.alts.ias.tools.*;

public class ExtendedAccountData extends AccountData
{
    public /* synthetic */ int useCount;
    public /* synthetic */ int[] lastused;
    public /* synthetic */ EnumBool premium;
    
    public ExtendedAccountData(final String s, final String s2, final String s3, final int useCount, final int[] lastused, final EnumBool premium) {
        super(s, s2, s3);
        this.useCount = useCount;
        this.lastused = lastused;
        this.premium = premium;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final ExtendedAccountData extendedAccountData = (ExtendedAccountData)o;
        return Arrays.equals(this.lastused, extendedAccountData.lastused) && this.premium == extendedAccountData.premium && this.useCount == extendedAccountData.useCount && this.user.equals(extendedAccountData.user) && this.pass.equals(extendedAccountData.pass);
    }
    
    public ExtendedAccountData(final String s, final String s2, final String s3) {
        super(s, s2, s3);
        this.useCount = 0;
        this.lastused = JavaTools.getJavaCompat().getDate();
        this.premium = EnumBool.UNKNOWN;
    }
}
