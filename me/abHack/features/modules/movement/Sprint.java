//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Sprint extends Module
{
    public /* synthetic */ Setting<Mode> mode;
    private static /* synthetic */ Sprint INSTANCE;
    
    public void onUpdate() {
        switch (this.mode.getValue()) {
            case RAGE: {
                if ((!Sprint.mc.gameSettings.keyBindForward.isKeyDown() && !Sprint.mc.gameSettings.keyBindBack.isKeyDown() && !Sprint.mc.gameSettings.keyBindLeft.isKeyDown() && !Sprint.mc.gameSettings.keyBindRight.isKeyDown()) || Sprint.mc.player.isSneaking() || Sprint.mc.player.collidedHorizontally) {
                    break;
                }
                if (Sprint.mc.player.getFoodStats().getFoodLevel() <= 6.0f) {
                    break;
                }
                Sprint.mc.player.setSprinting(true);
                break;
            }
            case LEGIT: {
                if (!Sprint.mc.gameSettings.keyBindForward.isKeyDown() || Sprint.mc.player.isSneaking() || Sprint.mc.player.isHandActive() || Sprint.mc.player.collidedHorizontally || Sprint.mc.player.getFoodStats().getFoodLevel() <= 6.0f) {
                    break;
                }
                if (Sprint.mc.currentScreen != null) {
                    break;
                }
                Sprint.mc.player.setSprinting(true);
                break;
            }
        }
    }
    
    static {
        Sprint.INSTANCE = new Sprint();
    }
    
    @SubscribeEvent
    public void onSprint(final MoveEvent moveEvent) {
        if (moveEvent.getStage() == 1 && this.mode.getValue() == Mode.RAGE && (Sprint.mc.player.movementInput.moveForward != 0.0f || Sprint.mc.player.movementInput.moveStrafe != 0.0f)) {
            moveEvent.setCanceled(true);
        }
    }
    
    public void onDisable() {
        if (!nullCheck()) {
            Sprint.mc.player.setSprinting(false);
        }
    }
    
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    public static Sprint getInstance() {
        if (Sprint.INSTANCE == null) {
            Sprint.INSTANCE = new Sprint();
        }
        return Sprint.INSTANCE;
    }
    
    private void setInstance() {
        Sprint.INSTANCE = this;
    }
    
    public Sprint() {
        super("Sprint", "Modifies sprinting", Module.Category.MOVEMENT, false, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.LEGIT));
        this.setInstance();
    }
    
    public enum Mode
    {
        LEGIT, 
        RAGE;
    }
}
