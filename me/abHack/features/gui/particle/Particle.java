//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.particle;

import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;
import javax.vecmath.*;
import java.util.concurrent.*;

public class Particle
{
    private final /* synthetic */ int maxAlpha;
    private /* synthetic */ int alpha;
    private /* synthetic */ Vector2f pos;
    private /* synthetic */ Vector2f acceleration;
    private /* synthetic */ Vector2f velocity;
    private /* synthetic */ float size;
    
    public void setVelocity(final Vector2f velocity) {
        this.velocity = velocity;
    }
    
    public Vector2f getVelocity() {
        return this.velocity;
    }
    
    public int getAlpha() {
        return this.alpha;
    }
    
    public void respawn(final ScaledResolution scaledResolution) {
        this.pos = new Vector2f((float)(Math.random() * scaledResolution.getScaledWidth()), (float)(Math.random() * scaledResolution.getScaledHeight()));
    }
    
    public void setAcceleration(final Vector2f acceleration) {
        this.acceleration = acceleration;
    }
    
    public void setAlpha(final int alpha) {
        this.alpha = alpha;
    }
    
    public void render(final int n, final int n2) {
        if (Mouse.isButtonDown(0)) {
            final float a = n - this.pos.getX();
            final float a2 = n2 - this.pos.getY();
            if (Math.abs(a) < 50.0f && Math.abs(a2) < 50.0f) {
                this.acceleration.setX(this.acceleration.getX() + a * 0.0015f);
                this.acceleration.setY(this.acceleration.getY() + a2 * 0.0015f);
            }
        }
        RenderUtil.drawRect(this.pos.x, this.pos.y, this.pos.x + this.size, this.pos.y + this.size, changeAlpha(ColorUtil.toRGBA(ClickGui.getInstance().particlered.getValue(), ClickGui.getInstance().particlegreen.getValue(), ClickGui.getInstance().particleblue.getValue()), this.alpha));
    }
    
    public void update() {
        if (this.alpha < this.maxAlpha) {
            this.alpha += 8;
        }
        if (this.acceleration.getX() > 0.35f) {
            this.acceleration.setX(this.acceleration.getX() * 0.975f);
        }
        else if (this.acceleration.getX() < -0.35f) {
            this.acceleration.setX(this.acceleration.getX() * 0.975f);
        }
        if (this.acceleration.getY() > 0.35f) {
            this.acceleration.setY(this.acceleration.getY() * 0.975f);
        }
        else if (this.acceleration.getY() < -0.35f) {
            this.acceleration.setY(this.acceleration.getY() * 0.975f);
        }
        this.pos.add((Tuple2f)this.acceleration);
        this.pos.add((Tuple2f)this.velocity);
    }
    
    public float getSize() {
        return this.size;
    }
    
    public static int changeAlpha(int n, final int n2) {
        n &= 0xFFFFFF;
        return n2 << 24 | n;
    }
    
    public Vector2f getPos() {
        return this.pos;
    }
    
    public void setPos(final Vector2f pos) {
        this.pos = pos;
    }
    
    public Particle(final Vector2f pos) {
        this.pos = pos;
        this.velocity = new Vector2f(-1.0f + ThreadLocalRandom.current().nextFloat() * 2.0f, -1.0f + ThreadLocalRandom.current().nextFloat() * 2.0f);
        this.acceleration = new Vector2f(0.0f, 0.35f);
        this.alpha = 0;
        this.maxAlpha = ThreadLocalRandom.current().nextInt(32, 192);
        this.size = 0.5f + ThreadLocalRandom.current().nextFloat() * 1.5f;
    }
    
    public Vector2f getAcceleration() {
        return this.acceleration;
    }
    
    public void setSize(final float size) {
        this.size = size;
    }
}
