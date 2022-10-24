//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import me.abHack.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.server.*;
import java.util.*;

public class ExplosionChams extends Module
{
    public final /* synthetic */ Setting<Integer> green;
    public /* synthetic */ int aliveTicks;
    public /* synthetic */ double speed;
    public /* synthetic */ BlockPos crystalPos;
    public /* synthetic */ Map<EntityEnderCrystal, BlockPos> explodedCrystals;
    public final /* synthetic */ Setting<Integer> red;
    public final /* synthetic */ Setting<Float> increase;
    public final /* synthetic */ Setting<Integer> riseSpeed;
    public final /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Timer timer;
    public final /* synthetic */ Setting<Boolean> rainbow;
    public final /* synthetic */ Setting<Integer> blue;
    
    public void onEnable() {
        this.explodedCrystals.clear();
    }
    
    public ExplosionChams() {
        super("ExplosionChams", "Draws a cham when a crystal explodes", Module.Category.RENDER, true, false, false);
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)30, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)167, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)150, (T)0, (T)255));
        this.increase = (Setting<Float>)this.register(new Setting("Increase Size", (T)0.5f, (T)0.1f, (T)5.0f));
        this.riseSpeed = (Setting<Integer>)this.register(new Setting("Rise Time", (T)5, (T)1, (T)50));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true));
        this.explodedCrystals = new HashMap<EntityEnderCrystal, BlockPos>();
        this.crystalPos = null;
        this.aliveTicks = 0;
        this.speed = 0.0;
        this.timer = new Timer();
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        ++this.aliveTicks;
        if (this.timer.passedMs(5L)) {
            this.speed += 0.5;
            this.timer.reset();
        }
        if (this.speed > this.riseSpeed.getValue()) {
            this.speed = 0.0;
            this.explodedCrystals.clear();
        }
    }
    
    @SubscribeEvent
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!this.explodedCrystals.isEmpty()) {
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.7f, (float)this.crystalPos.getZ(), 0.6f + this.increase.getValue(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.6f, (float)this.crystalPos.getZ(), 0.5f + this.increase.getValue(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.5f, (float)this.crystalPos.getZ(), 0.4f + this.increase.getValue(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.4f, (float)this.crystalPos.getZ(), 0.3f + this.increase.getValue(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.3f, (float)this.crystalPos.getZ(), 0.2f + this.increase.getValue(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.2f, (float)this.crystalPos.getZ(), 0.1f + this.increase.getValue(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.1f, (float)this.crystalPos.getZ(), 0.0f + this.increase.getValue(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        try {
            for (final Entity entity : ExplosionChams.mc.world.loadedEntityList) {
                if (entity instanceof EntityEnderCrystal && receive.getPacket() instanceof SPacketExplosion) {
                    this.crystalPos = new BlockPos(entity.posX, entity.posY, entity.posZ);
                    this.explodedCrystals.put((EntityEnderCrystal)entity, this.crystalPos);
                }
            }
        }
        catch (Exception ex) {}
    }
}
