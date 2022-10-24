//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

public class Timer
{
    private /* synthetic */ long time;
    
    public boolean passedNS(final long n) {
        return System.nanoTime() - this.time >= n;
    }
    
    public boolean passedMs(final long n) {
        return this.getMs(System.nanoTime() - this.time) >= n;
    }
    
    public long getMs(final long n) {
        return n / 1000000L;
    }
    
    public void setMs(final long n) {
        this.time = System.nanoTime() - n * 1000000L;
    }
    
    public Timer reset() {
        this.time = System.nanoTime();
        return this;
    }
    
    public boolean sleep(final long n) {
        if (System.nanoTime() / 1000000L - n >= n) {
            this.reset();
            return true;
        }
        return false;
    }
    
    public Timer() {
        this.time = -1L;
    }
    
    public boolean passedDs(final double n) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(n * 100.0);
    }
    
    public boolean passedS(final double n) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(n * 1000.0);
    }
    
    public boolean passedDms(final double n) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(n * 10.0);
    }
    
    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }
    
    public boolean hasReached(final long n, final boolean b) {
        if (b) {
            this.reset();
        }
        final long currentTimeMillis = System.currentTimeMillis();
        this.getClass();
        return currentTimeMillis + 1L >= n;
    }
    
    public boolean passedM(final double n) {
        return this.getMs(System.nanoTime() - this.time) >= (long)(n * 1000.0 * 60.0);
    }
    
    public boolean hasReached(final long n) {
        final long currentTimeMillis = System.currentTimeMillis();
        this.getClass();
        return currentTimeMillis + 1L >= n;
    }
}
