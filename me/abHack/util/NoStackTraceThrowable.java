//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

public class NoStackTraceThrowable extends RuntimeException
{
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
    
    public NoStackTraceThrowable(final String message) {
        super(message);
        this.setStackTrace(new StackTraceElement[0]);
    }
}
