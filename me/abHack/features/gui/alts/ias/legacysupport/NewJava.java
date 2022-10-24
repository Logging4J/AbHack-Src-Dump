//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.legacysupport;

import java.time.*;
import java.time.format.*;

public class NewJava implements ILegacyCompat
{
    public int[] getDate() {
        return new int[] { LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getYear() };
    }
    
    public String getFormattedDate() {
        return LocalDateTime.now().withDayOfMonth(this.getDate()[1]).withMonth(this.getDate()[0]).withYear(this.getDate()[2]).toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    }
}
