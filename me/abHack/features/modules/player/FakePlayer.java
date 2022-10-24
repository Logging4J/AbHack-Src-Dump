//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import net.minecraft.client.entity.*;
import me.abHack.features.setting.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;

public class FakePlayer extends Module
{
    private static /* synthetic */ FakePlayer INSTANCE;
    private /* synthetic */ EntityOtherPlayerMP otherPlayer;
    public /* synthetic */ Setting<Boolean> hollow;
    
    private void setInstance() {
        FakePlayer.INSTANCE = this;
    }
    
    public FakePlayer() {
        super("FakePlayer", "fake player", Module.Category.PLAYER, false, false, false);
        this.hollow = (Setting<Boolean>)this.register(new Setting("Move", (T)false));
    }
    
    public void onTick() {
        if (this.otherPlayer != null) {
            final Random random = new Random();
            this.otherPlayer.moveForward = FakePlayer.mc.player.moveForward + random.nextInt(5) / 10.0f;
            this.otherPlayer.moveStrafing = FakePlayer.mc.player.moveStrafing + random.nextInt(5) / 10.0f;
            if (this.hollow.getValue()) {
                this.travel(this.otherPlayer.moveStrafing, this.otherPlayer.moveVertical, this.otherPlayer.moveForward);
            }
        }
    }
    
    static {
        FakePlayer.INSTANCE = new FakePlayer();
    }
    
    public void onEnable() {
        if (FakePlayer.mc.world == null || FakePlayer.mc.player == null) {
            this.toggle();
            return;
        }
        if (this.otherPlayer == null) {
            this.otherPlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.randomUUID(), "Steve_ab"));
            this.otherPlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
            this.otherPlayer.inventory.copyInventory(FakePlayer.mc.player.inventory);
        }
        FakePlayer.mc.world.spawnEntity((Entity)this.otherPlayer);
    }
    
    public void travel(final float n, final float n2, final float n3) {
        final double posY = this.otherPlayer.posY;
        float n4 = 0.8f;
        float n5 = 0.02f;
        float n6 = (float)EnchantmentHelper.getDepthStriderModifier((EntityLivingBase)this.otherPlayer);
        if (n6 > 3.0f) {
            n6 = 3.0f;
        }
        if (!this.otherPlayer.onGround) {
            n6 *= 0.5f;
        }
        if (n6 > 0.0f) {
            n4 += (0.54600006f - n4) * n6 / 3.0f;
            n5 += (this.otherPlayer.getAIMoveSpeed() - n5) * n6 / 4.0f;
        }
        this.otherPlayer.moveRelative(n, n2, n3, n5);
        this.otherPlayer.move(MoverType.SELF, this.otherPlayer.motionX, this.otherPlayer.motionY, this.otherPlayer.motionZ);
        final EntityOtherPlayerMP otherPlayer = this.otherPlayer;
        otherPlayer.motionX *= n4;
        final EntityOtherPlayerMP otherPlayer2 = this.otherPlayer;
        otherPlayer2.motionY *= 0.800000011920929;
        final EntityOtherPlayerMP otherPlayer3 = this.otherPlayer;
        otherPlayer3.motionZ *= n4;
        if (!this.otherPlayer.hasNoGravity()) {
            final EntityOtherPlayerMP otherPlayer4 = this.otherPlayer;
            otherPlayer4.motionY -= 0.02;
        }
        if (this.otherPlayer.collidedHorizontally && this.otherPlayer.isOffsetPositionInLiquid(this.otherPlayer.motionX, this.otherPlayer.motionY + 0.6000000238418579 - this.otherPlayer.posY + posY, this.otherPlayer.motionZ)) {
            this.otherPlayer.motionY = 0.30000001192092896;
        }
    }
    
    public static FakePlayer getInstance() {
        if (FakePlayer.INSTANCE == null) {
            FakePlayer.INSTANCE = new FakePlayer();
        }
        return FakePlayer.INSTANCE;
    }
    
    public void onDisable() {
        if (this.otherPlayer != null) {
            FakePlayer.mc.world.removeEntity((Entity)this.otherPlayer);
            this.otherPlayer = null;
        }
    }
}
