//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import net.minecraft.entity.*;
import me.abHack.util.*;
import net.minecraft.util.*;
import me.abHack.*;

public class Strafe extends Module
{
    private /* synthetic */ double moveSpeed;
    private final /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Boolean> strafeJump;
    private /* synthetic */ int stage;
    private final /* synthetic */ Setting<Boolean> limiter;
    private final /* synthetic */ Setting<Float> speed;
    private /* synthetic */ double lastDist;
    private static /* synthetic */ Strafe INSTANCE;
    private final /* synthetic */ Timer timer;
    
    public static Strafe getInstance() {
        if (Strafe.INSTANCE == null) {
            Strafe.INSTANCE = new Strafe();
        }
        return Strafe.INSTANCE;
    }
    
    public static double getBaseMoveSpeed() {
        double n = 0.272;
        if (Strafe.mc.player.isPotionActive(MobEffects.SPEED)) {
            n *= 1.0 + 0.2 * Objects.requireNonNull(Strafe.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier();
        }
        return n;
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (this.mode.getValue() == Mode.NCP) {
            this.doNCP(moveEvent);
        }
    }
    
    public Strafe() {
        super("Strafe", "AirControl etc.", Module.Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.NCP));
        this.limiter = (Setting<Boolean>)this.register(new Setting("SetGround", (T)true, p0 -> this.mode.getValue() == Mode.NCP));
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (T)2.0f, (T)1.0f, (T)5.0f, p0 -> this.mode.getValue() == Mode.NCP));
        this.timer = new Timer();
        this.strafeJump = (Setting<Boolean>)this.register(new Setting("Jump", (T)false, p0 -> this.mode.getValue() == Mode.INSTANT));
        this.stage = 1;
        Strafe.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting().equals(this.mode) && this.mode.getPlannedValue() == Mode.INSTANT) {
            Strafe.mc.player.motionY = -0.1;
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            this.lastDist = Math.sqrt((Strafe.mc.player.posX - Strafe.mc.player.prevPosX) * (Strafe.mc.player.posX - Strafe.mc.player.prevPosX) + (Strafe.mc.player.posZ - Strafe.mc.player.prevPosZ) * (Strafe.mc.player.posZ - Strafe.mc.player.prevPosZ));
        }
    }
    
    public void onEnable() {
        this.timer.reset();
        this.moveSpeed = getBaseMoveSpeed();
    }
    
    public void onDisable() {
        this.moveSpeed = 0.0;
    }
    
    private void doNCP(final MoveEvent moveEvent) {
        if (this.shouldReturn()) {
            return;
        }
        if (!this.limiter.getValue() && Strafe.mc.player.onGround) {
            this.stage = 2;
        }
        switch (this.stage) {
            case 0: {
                ++this.stage;
                this.lastDist = 0.0;
                break;
            }
            case 2: {
                double motionY = 0.40123128;
                if (Strafe.mc.player.moveForward == 0.0f && Strafe.mc.player.moveStrafing == 0.0f) {
                    break;
                }
                if (!Strafe.mc.player.onGround) {
                    break;
                }
                if (Strafe.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                    motionY += (Objects.requireNonNull(Strafe.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST)).getAmplifier() + 1) * 0.1f;
                }
                moveEvent.setY(Strafe.mc.player.motionY = motionY);
                this.moveSpeed *= this.speed.getValue();
                break;
            }
            case 3: {
                this.moveSpeed = this.lastDist - 0.76 * (this.lastDist - getBaseMoveSpeed());
                break;
            }
            default: {
                if (Strafe.mc.world.getCollisionBoxes((Entity)Strafe.mc.player, Strafe.mc.player.getEntityBoundingBox().offset(0.0, Strafe.mc.player.motionY, 0.0)).size() > 0 || (Strafe.mc.player.collidedVertically && this.stage > 0)) {
                    this.stage = ((Strafe.mc.player.moveForward != 0.0f || Strafe.mc.player.moveStrafing != 0.0f) ? 1 : 0);
                }
                this.moveSpeed = this.lastDist - this.lastDist / 159.0;
                break;
            }
        }
        this.moveSpeed = Math.max(this.moveSpeed, getBaseMoveSpeed());
        double n = Strafe.mc.player.movementInput.moveForward;
        double n2 = Strafe.mc.player.movementInput.moveStrafe;
        final double n3 = Strafe.mc.player.rotationYaw;
        if (n == 0.0 && n2 == 0.0) {
            moveEvent.setX(0.0);
            moveEvent.setZ(0.0);
        }
        else if (n != 0.0 && n2 != 0.0) {
            n *= Math.sin(0.7853981633974483);
            n2 *= Math.cos(0.7853981633974483);
        }
        moveEvent.setX((n * this.moveSpeed * -Math.sin(Math.toRadians(n3)) + n2 * this.moveSpeed * Math.cos(Math.toRadians(n3))) * 0.99);
        moveEvent.setZ((n * this.moveSpeed * Math.cos(Math.toRadians(n3)) - n2 * this.moveSpeed * -Math.sin(Math.toRadians(n3))) * 0.99);
        ++this.stage;
    }
    
    @SubscribeEvent
    public void onMode(final MoveEvent moveEvent) {
        if (!this.shouldReturn() && moveEvent.getStage() == 0 && this.mode.getValue() == Mode.INSTANT && !nullCheck() && !Strafe.mc.player.isSneaking() && !Strafe.mc.player.isInWater() && !Strafe.mc.player.isInLava() && (Strafe.mc.player.movementInput.moveForward != 0.0f || Strafe.mc.player.movementInput.moveStrafe != 0.0f)) {
            if (Strafe.mc.player.onGround && this.strafeJump.getValue()) {
                moveEvent.setY(Strafe.mc.player.motionY = 0.4);
            }
            final MovementInput movementInput = Strafe.mc.player.movementInput;
            float moveForward = movementInput.moveForward;
            float moveStrafe = movementInput.moveStrafe;
            float rotationYaw = Strafe.mc.player.rotationYaw;
            if (moveForward == 0.0 && moveStrafe == 0.0) {
                moveEvent.setX(0.0);
                moveEvent.setZ(0.0);
            }
            else {
                if (moveForward != 0.0) {
                    if (moveStrafe > 0.0) {
                        rotationYaw += ((moveForward > 0.0) ? -45 : 45);
                    }
                    else if (moveStrafe < 0.0) {
                        rotationYaw += ((moveForward > 0.0) ? 45 : -45);
                    }
                    moveStrafe = 0.0f;
                    if (moveForward != 0.0f) {
                        moveForward = ((moveForward > 0.0) ? 1.0f : -1.0f);
                    }
                }
                final float n = (moveStrafe == 0.0f) ? moveStrafe : ((moveStrafe > 0.0) ? 1.0f : -1.0f);
                moveEvent.setX(moveForward * EntityUtil.getMaxSpeed() * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n * EntityUtil.getMaxSpeed() * Math.sin(Math.toRadians(rotationYaw + 90.0f)));
                moveEvent.setZ(moveForward * EntityUtil.getMaxSpeed() * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n * EntityUtil.getMaxSpeed() * Math.cos(Math.toRadians(rotationYaw + 90.0f)));
            }
        }
    }
    
    private boolean shouldReturn() {
        return OyVey.moduleManager.isModuleEnabled("Freecam") || OyVey.moduleManager.isModuleEnabled("ElytraFlight") || OyVey.moduleManager.isModuleEnabled("Flight");
    }
    
    public enum Mode
    {
        INSTANT, 
        NCP;
    }
}
