//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.tileentity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.awt.*;
import java.util.*;
import net.minecraft.item.*;
import me.abHack.util.*;

public class ChestESP extends Module
{
    private final /* synthetic */ Setting<Boolean> shulker;
    private final /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Setting<Boolean> frame;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Boolean> dispenser;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Boolean> chest;
    private final /* synthetic */ Setting<Boolean> hopper;
    private final /* synthetic */ Setting<Boolean> furnace;
    private final /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ Setting<Boolean> cart;
    private final /* synthetic */ Setting<Boolean> echest;
    private final /* synthetic */ Setting<Float> lineWidth;
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        final HashMap<BlockPos, Integer> hashMap = new HashMap<BlockPos, Integer>();
        for (final TileEntity tileEntity : ChestESP.mc.world.loadedTileEntityList) {
            final BlockPos getPos;
            if (((tileEntity instanceof TileEntityChest && this.chest.getValue()) || (tileEntity instanceof TileEntityDispenser && this.dispenser.getValue()) || (tileEntity instanceof TileEntityShulkerBox && this.shulker.getValue()) || (tileEntity instanceof TileEntityEnderChest && this.echest.getValue()) || (tileEntity instanceof TileEntityFurnace && this.furnace.getValue()) || (tileEntity instanceof TileEntityHopper && this.hopper.getValue())) && ChestESP.mc.player.getDistanceSq(getPos = tileEntity.getPos()) <= MathUtil.square(this.range.getValue())) {
                final int tileEntityColor;
                if ((tileEntityColor = this.getTileEntityColor(tileEntity)) == -1) {
                    continue;
                }
                hashMap.put(getPos, tileEntityColor);
            }
        }
        for (final Entity entity : ChestESP.mc.world.loadedEntityList) {
            final BlockPos getPosition;
            if (((entity instanceof EntityItemFrame && this.frame.getValue()) || (entity instanceof EntityMinecartChest && this.cart.getValue())) && ChestESP.mc.player.getDistanceSq(getPosition = entity.getPosition()) <= MathUtil.square(this.range.getValue())) {
                final int entityColor;
                if ((entityColor = this.getEntityColor(entity)) == -1) {
                    continue;
                }
                hashMap.put(getPosition, entityColor);
            }
        }
        for (final Map.Entry<BlockPos, Integer> entry : hashMap.entrySet()) {
            final BlockPos blockPos = entry.getKey();
            final int intValue = entry.getValue();
            RenderUtil.drawBoxESP(blockPos, new Color(intValue), false, new Color(intValue), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
        }
    }
    
    private int getEntityColor(final Entity entity) {
        if (entity instanceof EntityMinecartChest) {
            return Colors.GRAY;
        }
        if (entity instanceof EntityItemFrame && ((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox) {
            return Colors.YELLOW;
        }
        if (entity instanceof EntityItemFrame && !(((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox)) {
            return Colors.ORANGE;
        }
        return -1;
    }
    
    private int getTileEntityColor(final TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityChest) {
            return Colors.ORANGE;
        }
        if (tileEntity instanceof TileEntityShulkerBox) {
            return Colors.PINK;
        }
        if (tileEntity instanceof TileEntityEnderChest) {
            return Colors.PURPLE;
        }
        if (tileEntity instanceof TileEntityFurnace) {
            return Colors.GRAY;
        }
        if (tileEntity instanceof TileEntityHopper) {
            return Colors.GRAY;
        }
        if (tileEntity instanceof TileEntityDispenser) {
            return Colors.GRAY;
        }
        return -1;
    }
    
    public ChestESP() {
        super("ChestESP", "Helps you to see where container blocks are", Module.Category.RENDER, false, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)60.0f, (T)1.0f, (T)140.0f));
        this.chest = (Setting<Boolean>)this.register(new Setting("Chest", (T)true));
        this.dispenser = (Setting<Boolean>)this.register(new Setting("Dispenser", (T)false));
        this.shulker = (Setting<Boolean>)this.register(new Setting("Shulker", (T)true));
        this.echest = (Setting<Boolean>)this.register(new Setting("Ender Chest", (T)true));
        this.furnace = (Setting<Boolean>)this.register(new Setting("Furnace", (T)false));
        this.hopper = (Setting<Boolean>)this.register(new Setting("Hopper", (T)false));
        this.cart = (Setting<Boolean>)this.register(new Setting("Minecart", (T)false));
        this.frame = (Setting<Boolean>)this.register(new Setting("Item Frame", (T)false));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)false));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)125, (T)0, (T)255, p0 -> this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f, p0 -> this.outline.getValue()));
    }
    
    public abstract static class Colors
    {
        public static final /* synthetic */ int GRAY;
        public static final /* synthetic */ int ORANGE;
        public static final /* synthetic */ int YELLOW;
        public static final /* synthetic */ int PINK;
        public static final /* synthetic */ int PURPLE;
        
        static {
            RAINBOW = Integer.MIN_VALUE;
            WHITE = ColorUtil.toRGBA(255, 255, 255, 155);
            BLACK = ColorUtil.toRGBA(0, 0, 0, 155);
            RED = ColorUtil.toRGBA(255, 0, 0, 155);
            GREEN = ColorUtil.toRGBA(0, 255, 0, 155);
            BLUE = ColorUtil.toRGBA(0, 0, 255, 155);
            ORANGE = ColorUtil.toRGBA(255, 128, 0, 100);
            PURPLE = ColorUtil.toRGBA(105, 13, 173, 100);
            GRAY = ColorUtil.toRGBA(169, 169, 169, 155);
            DARK_RED = ColorUtil.toRGBA(64, 0, 0, 155);
            YELLOW = ColorUtil.toRGBA(255, 255, 0, 155);
            PINK = ColorUtil.toRGBA(255, 120, 203, 100);
        }
    }
}
