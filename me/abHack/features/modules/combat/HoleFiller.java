//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import me.zero.alpine.listener.*;
import net.minecraft.entity.*;
import me.abHack.event.events.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import me.abHack.util.*;
import net.minecraft.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.*;

public class HoleFiller extends Module
{
    private final /* synthetic */ Setting<Integer> red;
    private /* synthetic */ EntityPlayer closestTarget;
    private static /* synthetic */ double pitch;
    private static /* synthetic */ double yaw;
    private static /* synthetic */ boolean isSpoofingAngles;
    private final /* synthetic */ Setting<Integer> blue;
    private /* synthetic */ boolean caOn;
    private final /* synthetic */ Setting<Boolean> rainbow;
    private static /* synthetic */ boolean togglePitch;
    private final /* synthetic */ Setting<Boolean> smart;
    private static /* synthetic */ HoleFiller INSTANCE;
    private final /* synthetic */ Setting<Double> range;
    private /* synthetic */ BlockPos render;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> outlineAlpha;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Double> smartRange;
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.render != null) {
            RenderUtil.drawBoxESP(this.render, ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.outlineAlpha.getValue()), 3.5f, true, true, this.alpha.getValue());
        }
    }
    
    private static void setYawAndPitch(final float n, final float n2) {
        HoleFiller.yaw = n;
        HoleFiller.pitch = n2;
        HoleFiller.isSpoofingAngles = true;
    }
    
    public HoleFiller() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "HoleFiller"
        //     3: ldc             "Fills holes around you."
        //     5: getstatic       me/abHack/features/modules/Module$Category.COMBAT:Lme/abHack/features/modules/Module$Category;
        //     8: iconst_1       
        //     9: iconst_0       
        //    10: iconst_1       
        //    11: invokespecial   me/abHack/features/modules/Module.<init>:(Ljava/lang/String;Ljava/lang/String;Lme/abHack/features/modules/Module$Category;ZZZ)V
        //    14: aload_0        
        //    15: aload_0        
        //    16: new             Lme/abHack/features/setting/Setting;
        //    19: dup            
        //    20: ldc             "Range"
        //    22: ldc2_w          4.5
        //    25: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //    28: ldc2_w          0.1
        //    31: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //    34: ldc2_w          6.0
        //    37: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //    40: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
        //    43: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //    46: putfield        me/abHack/features/modules/combat/HoleFiller.range:Lme/abHack/features/setting/Setting;
        //    49: aload_0        
        //    50: aload_0        
        //    51: new             Lme/abHack/features/setting/Setting;
        //    54: dup            
        //    55: ldc             "Smart"
        //    57: iconst_1       
        //    58: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    61: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;)V
        //    64: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //    67: putfield        me/abHack/features/modules/combat/HoleFiller.smart:Lme/abHack/features/setting/Setting;
        //    70: aload_0        
        //    71: aload_0        
        //    72: new             Lme/abHack/features/setting/Setting;
        //    75: dup            
        //    76: ldc             "Smart Range"
        //    78: ldc2_w          3.0
        //    81: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //    84: ldc2_w          0.1
        //    87: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //    90: ldc2_w          6.0
        //    93: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //    96: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
        //    99: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   102: putfield        me/abHack/features/modules/combat/HoleFiller.smartRange:Lme/abHack/features/setting/Setting;
        //   105: aload_0        
        //   106: aload_0        
        //   107: new             Lme/abHack/features/setting/Setting;
        //   110: dup            
        //   111: ldc             "Announce Usage"
        //   113: iconst_0       
        //   114: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   117: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;)V
        //   120: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   123: putfield        me/abHack/features/modules/combat/HoleFiller.announceUsage:Lme/abHack/features/setting/Setting;
        //   126: aload_0        
        //   127: aload_0        
        //   128: new             Lme/abHack/features/setting/Setting;
        //   131: dup            
        //   132: ldc             "Rainbow"
        //   134: iconst_0       
        //   135: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   138: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;)V
        //   141: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   144: putfield        me/abHack/features/modules/combat/HoleFiller.rainbow:Lme/abHack/features/setting/Setting;
        //   147: aload_0        
        //   148: aload_0        
        //   149: new             Lme/abHack/features/setting/Setting;
        //   152: dup            
        //   153: ldc             "Red"
        //   155: iconst_0       
        //   156: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   159: iconst_0       
        //   160: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   163: sipush          255
        //   166: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   169: aload_0        
        //   170: invokedynamic   BootstrapMethod #0, test:(Lme/abHack/features/modules/combat/HoleFiller;)Ljava/util/function/Predicate;
        //   175: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/Predicate;)V
        //   178: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   181: putfield        me/abHack/features/modules/combat/HoleFiller.red:Lme/abHack/features/setting/Setting;
        //   184: aload_0        
        //   185: aload_0        
        //   186: new             Lme/abHack/features/setting/Setting;
        //   189: dup            
        //   190: ldc             "Green"
        //   192: sipush          255
        //   195: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   198: iconst_0       
        //   199: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   202: sipush          255
        //   205: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   208: aload_0        
        //   209: invokedynamic   BootstrapMethod #1, test:(Lme/abHack/features/modules/combat/HoleFiller;)Ljava/util/function/Predicate;
        //   214: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/Predicate;)V
        //   217: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   220: putfield        me/abHack/features/modules/combat/HoleFiller.green:Lme/abHack/features/setting/Setting;
        //   223: aload_0        
        //   224: aload_0        
        //   225: new             Lme/abHack/features/setting/Setting;
        //   228: dup            
        //   229: ldc             "Blue"
        //   231: iconst_0       
        //   232: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   235: iconst_0       
        //   236: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   239: sipush          255
        //   242: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   245: aload_0        
        //   246: invokedynamic   BootstrapMethod #2, test:(Lme/abHack/features/modules/combat/HoleFiller;)Ljava/util/function/Predicate;
        //   251: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/Predicate;)V
        //   254: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   257: putfield        me/abHack/features/modules/combat/HoleFiller.blue:Lme/abHack/features/setting/Setting;
        //   260: aload_0        
        //   261: aload_0        
        //   262: new             Lme/abHack/features/setting/Setting;
        //   265: dup            
        //   266: ldc             "Alpha"
        //   268: iconst_0       
        //   269: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   272: iconst_0       
        //   273: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   276: sipush          255
        //   279: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   282: aload_0        
        //   283: invokedynamic   BootstrapMethod #3, test:(Lme/abHack/features/modules/combat/HoleFiller;)Ljava/util/function/Predicate;
        //   288: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/Predicate;)V
        //   291: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   294: putfield        me/abHack/features/modules/combat/HoleFiller.alpha:Lme/abHack/features/setting/Setting;
        //   297: aload_0        
        //   298: aload_0        
        //   299: new             Lme/abHack/features/setting/Setting;
        //   302: dup            
        //   303: ldc             "OL-Alpha"
        //   305: iconst_0       
        //   306: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   309: iconst_0       
        //   310: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   313: sipush          255
        //   316: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   319: aload_0        
        //   320: invokedynamic   BootstrapMethod #4, test:(Lme/abHack/features/modules/combat/HoleFiller;)Ljava/util/function/Predicate;
        //   325: invokespecial   me/abHack/features/setting/Setting.<init>:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/Predicate;)V
        //   328: invokevirtual   me/abHack/features/modules/combat/HoleFiller.register:(Lme/abHack/features/setting/Setting;)Lme/abHack/features/setting/Setting;
        //   331: putfield        me/abHack/features/modules/combat/HoleFiller.outlineAlpha:Lme/abHack/features/setting/Setting;
        //   334: aload_0        
        //   335: ldc2_w          -1
        //   338: putfield        me/abHack/features/modules/combat/HoleFiller.systemTime:J
        //   341: aload_0        
        //   342: iconst_0       
        //   343: putfield        me/abHack/features/modules/combat/HoleFiller.switchCooldown:Z
        //   346: aload_0        
        //   347: iconst_0       
        //   348: putfield        me/abHack/features/modules/combat/HoleFiller.isAttacking:Z
        //   351: aload_0        
        //   352: invokespecial   me/abHack/features/modules/combat/HoleFiller.setInstance:()V
        //   355: iconst_1       
        //   356: anewarray       Lnet/minecraft/network/Packet;
        //   359: astore_1       
        //   360: aload_0        
        //   361: new             Lme/zero/alpine/listener/Listener;
        //   364: dup            
        //   365: aload_1        
        //   366: invokedynamic   BootstrapMethod #5, invoke:([Lnet/minecraft/network/Packet;)Lme/zero/alpine/listener/EventHook;
        //   371: iconst_0       
        //   372: anewarray       Ljava/util/function/Predicate;
        //   375: invokespecial   me/zero/alpine/listener/Listener.<init>:(Lme/zero/alpine/listener/EventHook;[Ljava/util/function/Predicate;)V
        //   378: putfield        me/abHack/features/modules/combat/HoleFiller.packetListener:Lme/zero/alpine/listener/Listener;
        //   381: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Thread.java:833)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void lookAtPacket(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double[] calculateLook = EntityUtil.calculateLookAt(n, n2, n3, entityPlayer);
        setYawAndPitch((float)calculateLook[0], (float)calculateLook[1]);
    }
    
    @Override
    public void onDisable() {
        this.closestTarget = null;
        this.render = null;
        resetRotation();
        super.onDisable();
    }
    
    public BlockPos getClosestTargetPos() {
        if (this.closestTarget != null) {
            return new BlockPos(Math.floor(this.closestTarget.posX), Math.floor(this.closestTarget.posY), Math.floor(this.closestTarget.posZ));
        }
        return null;
    }
    
    @Override
    public void onUpdate() {
        if (HoleFiller.mc.world == null) {
            return;
        }
        if (this.smart.getValue()) {
            this.findClosestTarget();
        }
        final List<BlockPos> crystalBlocks = this.findCrystalBlocks();
        BlockPos render = null;
        final int n;
        int currentItem = n = ((HoleFiller.mc.player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN)) ? HoleFiller.mc.player.inventory.currentItem : -1);
        if (currentItem == -1) {
            for (int i = 0; i < 9; ++i) {
                if (HoleFiller.mc.player.inventory.getStackInSlot(i).getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN)) {
                    currentItem = i;
                    break;
                }
            }
        }
        if (currentItem == -1) {
            return;
        }
        for (final BlockPos blockPos : crystalBlocks) {
            if (!HoleFiller.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos)).isEmpty()) {
                continue;
            }
            if (this.smart.getValue() && this.isInRange(blockPos)) {
                render = blockPos;
            }
            else {
                render = blockPos;
            }
        }
        this.render = render;
        if (render != null && HoleFiller.mc.player.onGround) {
            final int currentItem2 = HoleFiller.mc.player.inventory.currentItem;
            if (HoleFiller.mc.player.inventory.currentItem != currentItem) {
                HoleFiller.mc.player.inventory.currentItem = currentItem;
            }
            this.lookAtPacket(render.getX() + 0.5, render.getY() - 0.5, render.getZ() + 0.5, (EntityPlayer)HoleFiller.mc.player);
            BlockUtil.placeBlockScaffold(this.render);
            HoleFiller.mc.player.swingArm(EnumHand.MAIN_HAND);
            HoleFiller.mc.player.inventory.currentItem = currentItem2;
            resetRotation();
        }
    }
    
    public List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : getY; n6 < (b2 ? (getY + n) : ((float)(getY + n2))); ++n6) {
                    final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    private boolean isInRange(final BlockPos blockPos) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)this.getSphere(getPlayerPos(), this.range.getValue().floatValue(), this.range.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::IsHole).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return create.contains((Object)blockPos);
    }
    
    private void setInstance() {
        HoleFiller.INSTANCE = this;
    }
    
    private static void resetRotation() {
        if (HoleFiller.isSpoofingAngles) {
            HoleFiller.yaw = HoleFiller.mc.player.rotationYaw;
            HoleFiller.pitch = HoleFiller.mc.player.rotationPitch;
            HoleFiller.isSpoofingAngles = false;
        }
    }
    
    static {
        HoleFiller.INSTANCE = new HoleFiller();
        HoleFiller.togglePitch = false;
    }
    
    public static HoleFiller getInstance() {
        if (HoleFiller.INSTANCE == null) {
            HoleFiller.INSTANCE = new HoleFiller();
        }
        return HoleFiller.INSTANCE;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final Packet packet = send.getPacket();
        if (packet instanceof CPacketPlayer && HoleFiller.isSpoofingAngles) {
            ((CPacketPlayer)packet).yaw = (float)HoleFiller.yaw;
            ((CPacketPlayer)packet).pitch = (float)HoleFiller.pitch;
        }
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(HoleFiller.mc.player.posX), Math.floor(HoleFiller.mc.player.posY), Math.floor(HoleFiller.mc.player.posZ));
    }
    
    public boolean IsHole(final BlockPos blockPos) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 0, 0);
        final BlockPos add3 = blockPos.add(0, 0, -1);
        final BlockPos add4 = blockPos.add(1, 0, 0);
        final BlockPos add5 = blockPos.add(-1, 0, 0);
        final BlockPos add6 = blockPos.add(0, 0, 1);
        final BlockPos add7 = blockPos.add(0, 2, 0);
        final BlockPos add = blockPos.add(0.5, 0.5, 0.5);
        final BlockPos add8 = blockPos.add(0, -1, 0);
        return HoleFiller.mc.world.getBlockState(add).getBlock() == Blocks.AIR && HoleFiller.mc.world.getBlockState(add2).getBlock() == Blocks.AIR && HoleFiller.mc.world.getBlockState(add7).getBlock() == Blocks.AIR && (HoleFiller.mc.world.getBlockState(add3).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add3).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(add4).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add4).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(add5).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add5).getBlock() == Blocks.BEDROCK) && (HoleFiller.mc.world.getBlockState(add6).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add6).getBlock() == Blocks.BEDROCK) && HoleFiller.mc.world.getBlockState(add).getBlock() == Blocks.AIR && (HoleFiller.mc.world.getBlockState(add8).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(add8).getBlock() == Blocks.BEDROCK);
    }
    
    private List<BlockPos> findCrystalBlocks() {
        final NonNullList create = NonNullList.create();
        if (this.smart.getValue() && this.closestTarget != null) {
            create.addAll((Collection)this.getSphere(this.getClosestTargetPos(), this.smartRange.getValue().floatValue(), this.range.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::IsHole).filter((Predicate<? super Object>)this::isInRange).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        }
        else if (!this.smart.getValue()) {
            create.addAll((Collection)this.getSphere(getPlayerPos(), this.range.getValue().floatValue(), this.range.getValue().intValue(), false, true, 0).stream().filter((Predicate<? super Object>)this::IsHole).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        }
        return (List<BlockPos>)create;
    }
    
    private void findClosestTarget() {
        final List playerEntities = HoleFiller.mc.world.playerEntities;
        this.closestTarget = null;
        for (final EntityPlayer entityPlayer : playerEntities) {
            if (entityPlayer != HoleFiller.mc.player && !OyVey.friendManager.isFriend(entityPlayer.getName()) && EntityUtil.isLiving((Entity)entityPlayer)) {
                if (entityPlayer.getHealth() <= 0.0f) {
                    continue;
                }
                if (this.closestTarget == null) {
                    this.closestTarget = entityPlayer;
                }
                else {
                    if (HoleFiller.mc.player.getDistance((Entity)entityPlayer) >= HoleFiller.mc.player.getDistance((Entity)this.closestTarget)) {
                        continue;
                    }
                    this.closestTarget = entityPlayer;
                }
            }
        }
    }
    
    private double getDistanceToBlockPos(final BlockPos blockPos, final BlockPos blockPos2) {
        final double n = blockPos.getX() - blockPos2.getX();
        final double n2 = blockPos.getY() - blockPos2.getY();
        final double n3 = blockPos.getZ() - blockPos2.getZ();
        return Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    @Override
    public void onEnable() {
        if (OyVey.moduleManager.isModuleEnabled("AutoCrystal") || OyVey.moduleManager.isModuleEnabled("OyVeyCrystal")) {
            this.caOn = true;
        }
        super.onEnable();
    }
}
