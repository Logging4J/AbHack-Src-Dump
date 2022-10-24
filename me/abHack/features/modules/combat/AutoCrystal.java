//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import java.util.concurrent.atomic.*;
import net.minecraft.entity.player.*;
import me.abHack.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import java.util.concurrent.*;
import org.lwjgl.input.*;
import net.minecraft.network.*;
import io.netty.util.internal.*;
import com.mojang.authlib.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import java.awt.*;
import me.abHack.util.*;
import net.minecraft.item.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import java.util.*;

public class AutoCrystal extends Module
{
    public /* synthetic */ Setting<Boolean> fakeSwing;
    private final /* synthetic */ Timer manualTimer;
    private /* synthetic */ float yaw;
    public /* synthetic */ Setting<Boolean> hyperSync;
    public /* synthetic */ Setting<Boolean> sync;
    public /* synthetic */ Setting<Integer> facePlaceSpeed;
    public /* synthetic */ Setting<AutoSwitch> autoSwitch;
    private final /* synthetic */ Timer predictTimer;
    private final /* synthetic */ Setting<Float> slabHeight;
    private /* synthetic */ boolean mainHand;
    public /* synthetic */ Setting<Boolean> sound;
    public /* synthetic */ Setting<Integer> breakDelay;
    public /* synthetic */ Setting<Boolean> predictFriendDmg;
    public final /* synthetic */ Timer threadTimer;
    private /* synthetic */ int minDmgCount;
    public final /* synthetic */ Setting<Boolean> removeAfterAttack;
    private /* synthetic */ AxisAlignedBB renderBB;
    private /* synthetic */ boolean switching;
    public /* synthetic */ Setting<Boolean> syncySync;
    public /* synthetic */ Setting<Boolean> resetBreakTimer;
    public /* synthetic */ Setting<Boolean> doublePop;
    public /* synthetic */ Setting<Boolean> extraSelfCalc;
    private final /* synthetic */ Setting<Integer> oAlpha;
    private final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Integer> threadDelay;
    private /* synthetic */ float pitch;
    public /* synthetic */ Setting<Boolean> holdFaceBreak;
    private final /* synthetic */ Setting<Float> accel;
    public static /* synthetic */ Set<BlockPos> brokenPos;
    public /* synthetic */ Setting<Boolean> justRender;
    public /* synthetic */ Setting<Boolean> suicide;
    public /* synthetic */ Setting<Boolean> predictRotate;
    public /* synthetic */ Setting<Float> predictOffset;
    public final /* synthetic */ Setting<Boolean> attackOppositeHand;
    private final /* synthetic */ Setting<Boolean> boxrainbow;
    private /* synthetic */ BlockPos webPos;
    public /* synthetic */ Setting<PredictTimer> instantTimer;
    public /* synthetic */ Setting<Boolean> actualSlowBreak;
    public /* synthetic */ Setting<Boolean> box;
    private /* synthetic */ BlockPos lastPos;
    private static /* synthetic */ AutoCrystal instance;
    public /* synthetic */ Setting<Integer> predictDelay;
    public /* synthetic */ Setting<Boolean> place;
    public /* synthetic */ Setting<Integer> damageSyncTime;
    private final /* synthetic */ Timer breakTimer;
    private /* synthetic */ double currentDamage;
    private /* synthetic */ BlockPos placePos;
    public /* synthetic */ Setting<Float> placeRange;
    public /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Timer renderTimer;
    private /* synthetic */ boolean silentSwitch;
    public /* synthetic */ Setting<Boolean> placeSwing;
    public /* synthetic */ Setting<DamageSync> damageSync;
    private final /* synthetic */ Setting<Float> duration;
    public /* synthetic */ Setting<Float> maxSelfBreak;
    public /* synthetic */ Setting<Boolean> syncCount;
    public /* synthetic */ Setting<Target> targetMode;
    public /* synthetic */ Setting<Boolean> antiSurround;
    public /* synthetic */ Setting<Rotate> rotate;
    public /* synthetic */ Setting<Float> range;
    public /* synthetic */ Setting<Logic> logic;
    public /* synthetic */ Setting<Integer> manualBreak;
    public /* synthetic */ Setting<Boolean> wasteMinDmgCount;
    public /* synthetic */ Setting<Float> breakRange;
    public /* synthetic */ Setting<Float> soundPlayer;
    public /* synthetic */ Setting<Boolean> enormousSync;
    public /* synthetic */ Setting<Boolean> brownZombie;
    private /* synthetic */ BlockPos renderPos;
    private /* synthetic */ double lastDamage;
    public /* synthetic */ Setting<Integer> packets;
    private /* synthetic */ float timePassed;
    public /* synthetic */ Setting<Boolean> syncedFeetPlace;
    public /* synthetic */ Setting<Boolean> renderExtrapolation;
    private final /* synthetic */ Setting<Boolean> scaleFactor;
    public /* synthetic */ Setting<Boolean> fullSync;
    private final /* synthetic */ Setting<Integer> bGreen;
    public /* synthetic */ Setting<Boolean> outline;
    public /* synthetic */ Setting<Boolean> slowFaceBreak;
    private /* synthetic */ double renderDamage;
    public /* synthetic */ Setting<Boolean> mineSwitch;
    public /* synthetic */ Setting<Integer> minArmor;
    private final /* synthetic */ AtomicBoolean threadOngoing;
    private final /* synthetic */ Setting<Integer> eventMode;
    public /* synthetic */ Setting<Boolean> text;
    private /* synthetic */ int crystalCount;
    private /* synthetic */ boolean foundDoublePop;
    public final /* synthetic */ Setting<Boolean> antiBlock;
    private final /* synthetic */ Setting<Integer> bAlpha;
    public /* synthetic */ Setting<Switch> switchMode;
    private /* synthetic */ int rotationPacketsSpoofed;
    public /* synthetic */ boolean rotating;
    public /* synthetic */ Setting<Boolean> noCount;
    private final /* synthetic */ List<RenderPos> positions;
    private final /* synthetic */ Timer syncTimer;
    private /* synthetic */ boolean posConfirmed;
    public /* synthetic */ Setting<Float> breaktrace;
    public static /* synthetic */ Set<BlockPos> placedPos;
    public /* synthetic */ Setting<Float> dropOff;
    public /* synthetic */ Setting<Integer> confirm;
    public /* synthetic */ Setting<Boolean> exactHand;
    public /* synthetic */ Setting<Boolean> holdFacePlace;
    public /* synthetic */ Setting<Boolean> oneDot15;
    public /* synthetic */ Setting<Float> soundRange;
    public /* synthetic */ Setting<Integer> wasteAmount;
    public /* synthetic */ Setting<Float> minDamage;
    public /* synthetic */ Setting<Integer> placeDelay;
    private /* synthetic */ EntityPlayer currentSyncTarget;
    private final /* synthetic */ Setting<Settings> setting;
    public /* synthetic */ Setting<Integer> rotations;
    public /* synthetic */ Setting<Boolean> predictPos;
    public /* synthetic */ Setting<Boolean> superSafe;
    private /* synthetic */ ScheduledExecutorService executor;
    public /* synthetic */ Setting<Boolean> syncThreadBool;
    private final /* synthetic */ Timer syncroTimer;
    public /* synthetic */ Setting<Boolean> antiNaked;
    private final /* synthetic */ Setting<Boolean> outlinerainbow;
    private final /* synthetic */ AtomicBoolean shouldInterrupt;
    private /* synthetic */ Map<Entity, Float> crystalMap;
    public /* synthetic */ Setting<Boolean> gigaSync;
    public /* synthetic */ Setting<Float> minMinDmg;
    private final /* synthetic */ Setting<Boolean> onlyplaced;
    public /* synthetic */ Setting<Boolean> rotateFirst;
    private final /* synthetic */ Setting<Integer> oGreen;
    private final /* synthetic */ Setting<Boolean> fadeFactor;
    private /* synthetic */ BlockPos lastRenderPos;
    private final /* synthetic */ Setting<Integer> oRed;
    public /* synthetic */ Setting<Boolean> limitFacePlace;
    public /* synthetic */ Setting<Raytrace> raytrace;
    public /* synthetic */ Setting<Boolean> antiCommit;
    public /* synthetic */ Setting<Float> popDamage;
    private final /* synthetic */ Setting<Integer> oBlue;
    public /* synthetic */ Setting<Boolean> lethalSwitch;
    public /* synthetic */ Setting<Boolean> predictCalc;
    public static /* synthetic */ EntityPlayer target;
    public /* synthetic */ Setting<Integer> popTime;
    private final /* synthetic */ Map<EntityPlayer, Timer> totemPops;
    private final /* synthetic */ Setting<Integer> bBlue;
    public /* synthetic */ Setting<Boolean> calcEvenIfNoDamage;
    private /* synthetic */ PlaceInfo placeInfo;
    public /* synthetic */ Setting<Boolean> manualMinDmg;
    private final /* synthetic */ Queue<CPacketUseEntity> packetUseEntities;
    public /* synthetic */ Setting<Boolean> webAttack;
    private /* synthetic */ BlockPos syncedPlayerPos;
    private final /* synthetic */ Timer placeTimer;
    private final /* synthetic */ Setting<Integer> bRed;
    public /* synthetic */ Setting<ThreadMode> threadMode;
    private /* synthetic */ BlockPos syncedCrystalPos;
    public /* synthetic */ Setting<Float> maxSelfPlace;
    private /* synthetic */ boolean addTolowDmg;
    private /* synthetic */ boolean offHand;
    private /* synthetic */ Queue<Entity> attackList;
    public static /* synthetic */ Set<BlockPos> lowDmgPos;
    public /* synthetic */ Setting<Integer> syncThreads;
    public /* synthetic */ Setting<Integer> predictTicks;
    public /* synthetic */ Setting<Float> facePlace;
    private /* synthetic */ Entity efficientTarget;
    public /* synthetic */ Setting<Boolean> doublePopOnDamage;
    public /* synthetic */ Setting<Boolean> breakSwing;
    public /* synthetic */ Setting<Boolean> instant;
    private final /* synthetic */ Setting<Float> moveSpeed;
    public /* synthetic */ Setting<Double> popHealth;
    private final /* synthetic */ Timer switchTimer;
    public /* synthetic */ Setting<RenderMode> renderMode;
    public /* synthetic */ Setting<Float> placetrace;
    public /* synthetic */ Setting<Boolean> explode;
    private final /* synthetic */ Setting<Integer> max;
    public /* synthetic */ Setting<Boolean> holySync;
    public /* synthetic */ Setting<Boolean> manual;
    private /* synthetic */ boolean didRotation;
    private final /* synthetic */ Setting<Integer> switchCooldown;
    public /* synthetic */ Setting<AntiFriendPop> antiFriendPop;
    public /* synthetic */ Setting<Boolean> soundConfirm;
    public /* synthetic */ Setting<Boolean> fullCalc;
    private final /* synthetic */ Setting<Boolean> slabFactor;
    private /* synthetic */ Thread thread;
    
    public AutoCrystal() {
        super("AutoCrystal", "Best CA on the market", Category.COMBAT, true, false, false);
        this.threadTimer = new Timer();
        this.setting = (Setting<Settings>)this.register(new Setting("Settings", (T)Settings.PLACE));
        this.attackOppositeHand = (Setting<Boolean>)this.register(new Setting("OppositeHand", (T)false, p0 -> this.setting.getValue() == Settings.DEV));
        this.removeAfterAttack = (Setting<Boolean>)this.register(new Setting("AttackRemove", (T)false, p0 -> this.setting.getValue() == Settings.DEV));
        this.antiBlock = (Setting<Boolean>)this.register(new Setting("AntiFeetPlace", (T)false, p0 -> this.setting.getValue() == Settings.DEV));
        this.switchCooldown = (Setting<Integer>)this.register(new Setting("Switch Cooldown", (T)500, (T)0, (T)1000, p0 -> this.setting.getValue() == Settings.MISC));
        this.eventMode = (Setting<Integer>)this.register(new Setting("Updates", (T)3, (T)1, (T)3, p0 -> this.setting.getValue() == Settings.DEV));
        this.switchTimer = new Timer();
        this.manualTimer = new Timer();
        this.breakTimer = new Timer();
        this.placeTimer = new Timer();
        this.syncTimer = new Timer();
        this.predictTimer = new Timer();
        this.positions = new ArrayList<RenderPos>();
        this.renderTimer = new Timer();
        this.shouldInterrupt = new AtomicBoolean(false);
        this.syncroTimer = new Timer();
        this.totemPops = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.packetUseEntities = new LinkedList<CPacketUseEntity>();
        this.threadOngoing = new AtomicBoolean(false);
        this.raytrace = (Setting<Raytrace>)this.register(new Setting("Walls", (T)Raytrace.NONE, p0 -> this.setting.getValue() == Settings.MISC));
        this.place = (Setting<Boolean>)this.register(new Setting("Place", (T)true, p0 -> this.setting.getValue() == Settings.PLACE));
        this.placeDelay = (Setting<Integer>)this.register(new Setting("PlaceDelay", (T)0, (T)0, (T)500, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.placeRange = (Setting<Float>)this.register(new Setting("PlaceRange", (T)6.0f, (T)0.0f, (T)6.0f, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.minDamage = (Setting<Float>)this.register(new Setting("MinDamage", (T)5.0f, (T)0.1f, (T)20.0f, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.maxSelfPlace = (Setting<Float>)this.register(new Setting("MaxSelfPlace", (T)6.0f, (T)0.1f, (T)36.0f, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.wasteAmount = (Setting<Integer>)this.register(new Setting("WasteAmount", (T)2, (T)1, (T)5, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.wasteMinDmgCount = (Setting<Boolean>)this.register(new Setting("CountMinDmg", (T)true, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.facePlace = (Setting<Float>)this.register(new Setting("FacePlace", (T)15.0f, (T)0.1f, (T)20.0f, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.placetrace = (Setting<Float>)this.register(new Setting("PlaceWalls", (T)3.5f, (T)0.0f, (T)10.0f, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue() && this.raytrace.getValue() != Raytrace.NONE && this.raytrace.getValue() != Raytrace.BREAK));
        this.antiSurround = (Setting<Boolean>)this.register(new Setting("AntiSurround", (T)true, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.limitFacePlace = (Setting<Boolean>)this.register(new Setting("LimitFacePlace", (T)true, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.oneDot15 = (Setting<Boolean>)this.register(new Setting("1.15", (T)false, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.doublePop = (Setting<Boolean>)this.register(new Setting("AntiTotem", (T)false, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue()));
        this.popHealth = (Setting<Double>)this.register(new Setting("PopHealth", (T)1.0, (T)0.0, (T)3.0, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue() && this.doublePop.getValue()));
        this.popDamage = (Setting<Float>)this.register(new Setting("PopDamage", (T)4.0f, (T)0.0f, (T)6.0f, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue() && this.doublePop.getValue()));
        this.popTime = (Setting<Integer>)this.register(new Setting("PopTime", (T)500, (T)0, (T)1000, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue() && this.doublePop.getValue()));
        this.minMinDmg = (Setting<Float>)this.register(new Setting("MinMinDmg", (T)0.0f, (T)0.0f, (T)3.0f, p0 -> this.setting.getValue() == Settings.DEV && this.place.getValue()));
        this.explode = (Setting<Boolean>)this.register(new Setting("Break", (T)true, p0 -> this.setting.getValue() == Settings.BREAK));
        this.switchMode = (Setting<Switch>)this.register(new Setting("Attack", (T)Switch.BREAKSLOT, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue()));
        this.breakDelay = (Setting<Integer>)this.register(new Setting("BreakDelay", (T)0, (T)0, (T)500, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue()));
        this.breakRange = (Setting<Float>)this.register(new Setting("BreakRange", (T)6.0f, (T)0.0f, (T)6.0f, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue()));
        this.packets = (Setting<Integer>)this.register(new Setting("Packets", (T)1, (T)1, (T)6, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue()));
        this.maxSelfBreak = (Setting<Float>)this.register(new Setting("MaxSelfBreak", (T)6.0f, (T)0.1f, (T)36.0f, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue()));
        this.breaktrace = (Setting<Float>)this.register(new Setting("BreakWalls", (T)3.5f, (T)0.0f, (T)10.0f, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.raytrace.getValue() != Raytrace.NONE && this.raytrace.getValue() != Raytrace.PLACE));
        this.instant = (Setting<Boolean>)this.register(new Setting("Predict", (T)true, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.place.getValue()));
        this.instantTimer = (Setting<PredictTimer>)this.register(new Setting("PredictTimer", (T)PredictTimer.NONE, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.place.getValue() && this.instant.getValue()));
        this.predictDelay = (Setting<Integer>)this.register(new Setting("PredictDelay", (T)12, (T)0, (T)500, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.place.getValue() && this.instant.getValue() && this.instantTimer.getValue() == PredictTimer.PREDICT));
        this.resetBreakTimer = (Setting<Boolean>)this.register(new Setting("ResetBreakTimer", (T)true, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.place.getValue() && this.instant.getValue()));
        this.predictCalc = (Setting<Boolean>)this.register(new Setting("PredictCalc", (T)true, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.place.getValue() && this.instant.getValue()));
        this.superSafe = (Setting<Boolean>)this.register(new Setting("SuperSafe", (T)true, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.place.getValue() && this.instant.getValue()));
        this.antiCommit = (Setting<Boolean>)this.register(new Setting("AntiOverCommit", (T)true, p0 -> this.setting.getValue() == Settings.BREAK && this.explode.getValue() && this.place.getValue() && this.instant.getValue()));
        this.manual = (Setting<Boolean>)this.register(new Setting("Manual", (T)true, p0 -> this.setting.getValue() == Settings.BREAK));
        this.manualMinDmg = (Setting<Boolean>)this.register(new Setting("ManMinDmg", (T)true, p0 -> this.setting.getValue() == Settings.BREAK && this.manual.getValue()));
        this.manualBreak = (Setting<Integer>)this.register(new Setting("ManualDelay", (T)500, (T)0, (T)500, p0 -> this.setting.getValue() == Settings.BREAK && this.manual.getValue()));
        this.sync = (Setting<Boolean>)this.register(new Setting("Sync", (T)true, p0 -> this.setting.getValue() == Settings.BREAK && (this.explode.getValue() || this.manual.getValue())));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true, p0 -> this.setting.getValue() == Settings.RENDER));
        this.justRender = (Setting<Boolean>)this.register(new Setting("JustRender", (T)false, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue()));
        this.fakeSwing = (Setting<Boolean>)this.register(new Setting("FakeSwing", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.justRender.getValue()));
        this.renderMode = (Setting<RenderMode>)this.register(new Setting("Mode", (T)RenderMode.GLIDE, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue()));
        this.fadeFactor = (Setting<Boolean>)this.register(new Setting("Fade", (T)true, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.FADE && this.render.getValue()));
        this.scaleFactor = (Setting<Boolean>)this.register(new Setting("Shrink", (T)false, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.FADE && this.render.getValue()));
        this.slabFactor = (Setting<Boolean>)this.register(new Setting("Slab", (T)false, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.FADE && this.render.getValue()));
        this.onlyplaced = (Setting<Boolean>)this.register(new Setting("OnlyPlaced", (T)false, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.FADE && this.render.getValue()));
        this.duration = (Setting<Float>)this.register(new Setting("Duration", (T)1500.0f, (T)0.0f, (T)5000.0f, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.FADE && this.render.getValue()));
        this.max = (Setting<Integer>)this.register(new Setting("MaxPositions", (T)15, (T)1, (T)30, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.FADE && this.render.getValue()));
        this.slabHeight = (Setting<Float>)this.register(new Setting("SlabDepth", (T)1.0f, (T)0.1f, (T)1.0f, p0 -> this.setting.getValue() == Settings.RENDER && (this.renderMode.getValue() == RenderMode.STATIC || this.renderMode.getValue() == RenderMode.GLIDE) && this.render.getValue()));
        this.moveSpeed = (Setting<Float>)this.register(new Setting("Speed", (T)2000.0f, (T)1.0f, (T)2000.0f, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.GLIDE && this.render.getValue()));
        this.accel = (Setting<Float>)this.register(new Setting("Deceleration", (T)0.1f, (T)0.0f, (T)1.0f, p0 -> this.setting.getValue() == Settings.RENDER && this.renderMode.getValue() == RenderMode.GLIDE && this.render.getValue()));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)true, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue()));
        this.bRed = (Setting<Integer>)this.register(new Setting("BoxRed", (T)150, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.bGreen = (Setting<Integer>)this.register(new Setting("BoxGreen", (T)0, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.bBlue = (Setting<Integer>)this.register(new Setting("BoxBlue", (T)150, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.bAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)40, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.boxrainbow = (Setting<Boolean>)this.register(new Setting("Box Rainbow", (T)false, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue()));
        this.oRed = (Setting<Integer>)this.register(new Setting("OutlineRed", (T)255, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.oGreen = (Setting<Integer>)this.register(new Setting("OutlineGreen", (T)50, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.oBlue = (Setting<Integer>)this.register(new Setting("OutlineBlue", (T)255, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.oAlpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", (T)255, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.5f, (T)0.1f, (T)5.0f, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.outlinerainbow = (Setting<Boolean>)this.register(new Setting("Outline Rainbow", (T)false, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.text = (Setting<Boolean>)this.register(new Setting("Text", (T)true, p0 -> this.setting.getValue() == Settings.RENDER && this.render.getValue()));
        this.holdFacePlace = (Setting<Boolean>)this.register(new Setting("HoldFacePlace", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.holdFaceBreak = (Setting<Boolean>)this.register(new Setting("HoldSlowBreak", (T)false, p0 -> this.setting.getValue() == Settings.MISC && this.holdFacePlace.getValue()));
        this.slowFaceBreak = (Setting<Boolean>)this.register(new Setting("SlowFaceBreak", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.actualSlowBreak = (Setting<Boolean>)this.register(new Setting("ActuallySlow", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.facePlaceSpeed = (Setting<Integer>)this.register(new Setting("FaceSpeed", (T)500, (T)0, (T)500, p0 -> this.setting.getValue() == Settings.MISC));
        this.antiNaked = (Setting<Boolean>)this.register(new Setting("AntiNaked", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)12.0f, (T)0.1f, (T)20.0f, p0 -> this.setting.getValue() == Settings.MISC));
        this.targetMode = (Setting<Target>)this.register(new Setting("Target", (T)Target.CLOSEST, p0 -> this.setting.getValue() == Settings.MISC));
        this.webAttack = (Setting<Boolean>)this.register(new Setting("WebAttack", (T)true, p0 -> this.setting.getValue() == Settings.MISC && this.targetMode.getValue() != Target.DAMAGE));
        this.doublePopOnDamage = (Setting<Boolean>)this.register(new Setting("DamagePop", (T)false, p0 -> this.setting.getValue() == Settings.PLACE && this.place.getValue() && this.doublePop.getValue() && this.targetMode.getValue() == Target.DAMAGE));
        this.minArmor = (Setting<Integer>)this.register(new Setting("MinArmor", (T)20, (T)0, (T)125, p0 -> this.setting.getValue() == Settings.MISC));
        this.autoSwitch = (Setting<AutoSwitch>)this.register(new Setting("Switch", (T)AutoSwitch.SILENT, p0 -> this.setting.getValue() == Settings.MISC));
        this.lethalSwitch = (Setting<Boolean>)this.register(new Setting("LethalSwitch", (T)false, p0 -> this.setting.getValue() == Settings.MISC && this.autoSwitch.getValue() != AutoSwitch.NONE));
        this.mineSwitch = (Setting<Boolean>)this.register(new Setting("MineSwitch", (T)true, p0 -> this.setting.getValue() == Settings.MISC && this.autoSwitch.getValue() != AutoSwitch.NONE));
        this.rotate = (Setting<Rotate>)this.register(new Setting("Rotate", (T)Rotate.OFF, p0 -> this.setting.getValue() == Settings.MISC));
        this.rotateFirst = (Setting<Boolean>)this.register(new Setting("FirstRotation", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.rotate.getValue() != Rotate.OFF && this.eventMode.getValue() == 2));
        this.suicide = (Setting<Boolean>)this.register(new Setting("Suicide", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.fullCalc = (Setting<Boolean>)this.register(new Setting("ExtraCalc", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.sound = (Setting<Boolean>)this.register(new Setting("Sound", (T)true, p0 -> this.setting.getValue() == Settings.MISC));
        this.soundRange = (Setting<Float>)this.register(new Setting("SoundRange", (T)12.0f, (T)0.0f, (T)12.0f, p0 -> this.setting.getValue() == Settings.MISC));
        this.soundPlayer = (Setting<Float>)this.register(new Setting("SoundPlayer", (T)6.0f, (T)0.0f, (T)12.0f, p0 -> this.setting.getValue() == Settings.MISC));
        this.soundConfirm = (Setting<Boolean>)this.register(new Setting("SoundConfirm", (T)true, p0 -> this.setting.getValue() == Settings.MISC));
        this.extraSelfCalc = (Setting<Boolean>)this.register(new Setting("MinSelfDmg", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.antiFriendPop = (Setting<AntiFriendPop>)this.register(new Setting("FriendPop", (T)AntiFriendPop.NONE, p0 -> this.setting.getValue() == Settings.MISC));
        this.noCount = (Setting<Boolean>)this.register(new Setting("AntiCount", (T)false, p0 -> this.setting.getValue() == Settings.MISC && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK)));
        this.calcEvenIfNoDamage = (Setting<Boolean>)this.register(new Setting("BigFriendCalc", (T)false, p0 -> this.setting.getValue() == Settings.MISC && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK) && this.targetMode.getValue() != Target.DAMAGE));
        this.predictFriendDmg = (Setting<Boolean>)this.register(new Setting("PredictFriend", (T)false, p0 -> this.setting.getValue() == Settings.MISC && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK) && this.instant.getValue()));
        this.breakSwing = (Setting<Boolean>)this.register(new Setting("BreakSwing", (T)false, p0 -> this.setting.getValue() == Settings.DEV));
        this.placeSwing = (Setting<Boolean>)this.register(new Setting("PlaceSwing", (T)false, p0 -> this.setting.getValue() == Settings.DEV));
        this.exactHand = (Setting<Boolean>)this.register(new Setting("ExactHand", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.placeSwing.getValue()));
        this.logic = (Setting<Logic>)this.register(new Setting("Logic", (T)Logic.BREAKPLACE, p0 -> this.setting.getValue() == Settings.DEV));
        this.damageSync = (Setting<DamageSync>)this.register(new Setting("DamageSync", (T)DamageSync.NONE, p0 -> this.setting.getValue() == Settings.DEV));
        this.damageSyncTime = (Setting<Integer>)this.register(new Setting("SyncDelay", (T)500, (T)0, (T)500, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE));
        this.dropOff = (Setting<Float>)this.register(new Setting("DropOff", (T)5.0f, (T)0.0f, (T)10.0f, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() == DamageSync.BREAK));
        this.confirm = (Setting<Integer>)this.register(new Setting("Confirm", (T)250, (T)0, (T)1000, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE));
        this.syncedFeetPlace = (Setting<Boolean>)this.register(new Setting("FeetSync", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE));
        this.fullSync = (Setting<Boolean>)this.register(new Setting("FullSync", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue()));
        this.syncCount = (Setting<Boolean>)this.register(new Setting("SyncCount", (T)true, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue()));
        this.hyperSync = (Setting<Boolean>)this.register(new Setting("HyperSync", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue()));
        this.gigaSync = (Setting<Boolean>)this.register(new Setting("GigaSync", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue()));
        this.syncySync = (Setting<Boolean>)this.register(new Setting("SyncySync", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue()));
        this.enormousSync = (Setting<Boolean>)this.register(new Setting("EnormousSync", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue()));
        this.holySync = (Setting<Boolean>)this.register(new Setting("UnbelievableSync", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue()));
        this.threadMode = (Setting<ThreadMode>)this.register(new Setting("Thread", (T)ThreadMode.NONE, p0 -> this.setting.getValue() == Settings.DEV));
        this.threadDelay = (Setting<Integer>)this.register(new Setting("ThreadDelay", (T)50, (T)1, (T)1000, p0 -> this.setting.getValue() == Settings.DEV && this.threadMode.getValue() != ThreadMode.NONE));
        this.syncThreadBool = (Setting<Boolean>)this.register(new Setting("ThreadSync", (T)true, p0 -> this.setting.getValue() == Settings.DEV && this.threadMode.getValue() != ThreadMode.NONE));
        this.syncThreads = (Setting<Integer>)this.register(new Setting("SyncThreads", (T)1000, (T)1, (T)10000, p0 -> this.setting.getValue() == Settings.DEV && this.threadMode.getValue() != ThreadMode.NONE && this.syncThreadBool.getValue()));
        this.predictPos = (Setting<Boolean>)this.register(new Setting("PredictPos", (T)false, p0 -> this.setting.getValue() == Settings.DEV));
        this.renderExtrapolation = (Setting<Boolean>)this.register(new Setting("RenderExtrapolation", (T)false, p0 -> this.setting.getValue() == Settings.DEV && this.predictPos.getValue()));
        this.predictTicks = (Setting<Integer>)this.register(new Setting("ExtrapolationTicks", (T)2, (T)1, (T)20, p0 -> this.setting.getValue() == Settings.DEV && this.predictPos.getValue()));
        this.rotations = (Setting<Integer>)this.register(new Setting("Spoofs", (T)1, (T)1, (T)20, p0 -> this.setting.getValue() == Settings.DEV));
        this.predictRotate = (Setting<Boolean>)this.register(new Setting("PredictRotate", (T)false, p0 -> this.setting.getValue() == Settings.DEV));
        this.predictOffset = (Setting<Float>)this.register(new Setting("PredictOffset", (T)0.0f, (T)0.0f, (T)4.0f, p0 -> this.setting.getValue() == Settings.DEV));
        this.brownZombie = (Setting<Boolean>)this.register(new Setting("BrownZombieMode", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.rotating = false;
        this.attackList = new ConcurrentLinkedQueue<Entity>();
        this.crystalMap = new HashMap<Entity, Float>();
        this.efficientTarget = null;
        this.currentDamage = 0.0;
        this.renderDamage = 0.0;
        this.lastDamage = 0.0;
        this.didRotation = false;
        this.switching = false;
        this.placePos = null;
        this.renderPos = null;
        this.mainHand = false;
        this.offHand = false;
        this.crystalCount = 0;
        this.minDmgCount = 0;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.webPos = null;
        this.lastPos = null;
        this.posConfirmed = false;
        this.foundDoublePop = false;
        this.rotationPacketsSpoofed = 0;
        AutoCrystal.instance = this;
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.switching) {
            return "§aSwitch";
        }
        if (AutoCrystal.target != null) {
            return AutoCrystal.target.getName();
        }
        return null;
    }
    
    private void switchToSlot(final int currentItem) {
        AutoCrystal.mc.player.inventory.currentItem = currentItem;
        AutoCrystal.mc.playerController.updateController();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH, receiveCanceled = true)
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (!this.justRender.getValue() && this.switchTimer.passedMs(this.switchCooldown.getValue()) && this.explode.getValue() && this.instant.getValue() && receive.getPacket() instanceof SPacketSpawnObject && (this.syncedCrystalPos == null || !this.syncedFeetPlace.getValue() || this.damageSync.getValue() == DamageSync.NONE)) {
            final SPacketSpawnObject sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket();
            final BlockPos blockPos;
            if (sPacketSpawnObject.getType() == 51 && AutoCrystal.mc.player.getDistanceSq(blockPos = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ())) + this.predictOffset.getValue() <= MathUtil.square(this.breakRange.getValue()) && (this.instantTimer.getValue() == PredictTimer.NONE || (this.instantTimer.getValue() == PredictTimer.BREAK && this.breakTimer.passedMs(this.breakDelay.getValue())) || (this.instantTimer.getValue() == PredictTimer.PREDICT && this.predictTimer.passedMs(this.predictDelay.getValue())))) {
                if (this.predictSlowBreak(blockPos.down())) {
                    return;
                }
                if (this.predictFriendDmg.getValue() && (this.antiFriendPop.getValue() == AntiFriendPop.BREAK || this.antiFriendPop.getValue() == AntiFriendPop.ALL) && this.isRightThread()) {
                    for (final EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
                        if (entityPlayer != null && !AutoCrystal.mc.player.equals((Object)entityPlayer) && entityPlayer.getDistanceSq(blockPos) <= MathUtil.square(this.range.getValue() + this.placeRange.getValue()) && OyVey.friendManager.isFriend(entityPlayer)) {
                            if (DamageUtil.calculateDamage(blockPos, (Entity)entityPlayer) <= EntityUtil.getHealth((Entity)entityPlayer) + 0.5) {
                                continue;
                            }
                            return;
                        }
                    }
                }
                if (AutoCrystal.placedPos.contains(blockPos.down())) {
                    Label_0623: {
                        if (this.isRightThread() && this.superSafe.getValue()) {
                            if (!DamageUtil.canTakeDamage(this.suicide.getValue())) {
                                break Label_0623;
                            }
                            final float calculateDamage;
                            if ((calculateDamage = DamageUtil.calculateDamage(blockPos, (Entity)AutoCrystal.mc.player)) - 0.5 <= EntityUtil.getHealth((Entity)AutoCrystal.mc.player)) {
                                if (calculateDamage <= this.maxSelfBreak.getValue()) {
                                    break Label_0623;
                                }
                            }
                        }
                        else if (!this.superSafe.getValue()) {
                            break Label_0623;
                        }
                        return;
                    }
                    this.attackCrystalPredict(sPacketSpawnObject.getEntityID(), blockPos);
                }
                else if (this.predictCalc.getValue() && this.isRightThread()) {
                    float calculateDamage2 = -1.0f;
                    if (DamageUtil.canTakeDamage(this.suicide.getValue())) {
                        calculateDamage2 = DamageUtil.calculateDamage(blockPos, (Entity)AutoCrystal.mc.player);
                    }
                    if (calculateDamage2 + 0.5 < EntityUtil.getHealth((Entity)AutoCrystal.mc.player) && calculateDamage2 <= this.maxSelfBreak.getValue()) {
                        for (final EntityPlayer entityPlayer2 : AutoCrystal.mc.world.playerEntities) {
                            if (entityPlayer2.getDistanceSq(blockPos) <= MathUtil.square(this.range.getValue()) && EntityUtil.isValid((Entity)entityPlayer2, this.range.getValue() + this.breakRange.getValue()) && (!this.antiNaked.getValue() || !DamageUtil.isNaked(entityPlayer2))) {
                                final float calculateDamage3;
                                if ((calculateDamage3 = DamageUtil.calculateDamage(blockPos, (Entity)entityPlayer2)) <= calculateDamage2 && (calculateDamage3 <= this.minDamage.getValue() || DamageUtil.canTakeDamage(this.suicide.getValue())) && calculateDamage3 <= EntityUtil.getHealth((Entity)entityPlayer2)) {
                                    continue;
                                }
                                if (this.predictRotate.getValue() && this.eventMode.getValue() != 2 && (this.rotate.getValue() == Rotate.BREAK || this.rotate.getValue() == Rotate.ALL)) {
                                    this.rotateToPos(blockPos);
                                }
                                this.attackCrystalPredict(sPacketSpawnObject.getEntityID(), blockPos);
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if (!this.soundConfirm.getValue() && receive.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion sPacketExplosion = (SPacketExplosion)receive.getPacket();
            this.removePos(new BlockPos(sPacketExplosion.getX(), sPacketExplosion.getY(), sPacketExplosion.getZ()).down());
        }
        else if (receive.getPacket() instanceof SPacketDestroyEntities) {
            final int[] getEntityIDs = ((SPacketDestroyEntities)receive.getPacket()).getEntityIDs();
            for (int length = getEntityIDs.length, i = 0; i < length; ++i) {
                final Entity getEntityByID = AutoCrystal.mc.world.getEntityByID(getEntityIDs[i]);
                if (getEntityByID instanceof EntityEnderCrystal) {
                    AutoCrystal.brokenPos.remove(new BlockPos(getEntityByID.getPositionVector()).down());
                    AutoCrystal.placedPos.remove(new BlockPos(getEntityByID.getPositionVector()).down());
                }
            }
        }
        else if (receive.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)AutoCrystal.mc.world) instanceof EntityPlayer) {
                this.totemPops.put((EntityPlayer)sPacketEntityStatus.getEntity((World)AutoCrystal.mc.world), new Timer().reset());
            }
        }
        else {
            final SPacketSoundEffect sPacketSoundEffect;
            if (receive.getPacket() instanceof SPacketSoundEffect && (sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket()).getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                final BlockPos blockPos2 = new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ());
                if (this.soundConfirm.getValue()) {
                    this.removePos(blockPos2);
                }
                if (this.threadMode.getValue() == ThreadMode.SOUND && this.isRightThread() && AutoCrystal.mc.player != null && AutoCrystal.mc.player.getDistanceSq(blockPos2) < MathUtil.square(this.soundPlayer.getValue())) {
                    this.handlePool(true);
                }
            }
        }
    }
    
    private void calculateDamage(final EntityPlayer entityPlayer) {
        if (entityPlayer == null && this.targetMode.getValue() != Target.DAMAGE && !this.fullCalc.getValue()) {
            return;
        }
        float n = 0.5f;
        EntityPlayer target = null;
        BlockPos blockPos = null;
        float n2 = 0.0f;
        this.foundDoublePop = false;
        BlockPos blockPos2 = null;
        IBlockState getBlockState = null;
        final BlockPos blockPos3;
        final Block getBlock;
        if (this.webAttack.getValue() && entityPlayer != null && (getBlock = AutoCrystal.mc.world.getBlockState(blockPos3 = new BlockPos(entityPlayer.getPositionVector())).getBlock()) == Blocks.WEB) {
            blockPos2 = blockPos3;
            getBlockState = AutoCrystal.mc.world.getBlockState(blockPos3);
            AutoCrystal.mc.world.setBlockToAir(blockPos3);
        }
        for (final BlockPos blockPos4 : BlockUtil.possiblePlacePositions(this.placeRange.getValue(), this.antiSurround.getValue(), this.oneDot15.getValue())) {
            if (!BlockUtil.rayTracePlaceCheck(blockPos4, (this.raytrace.getValue() == Raytrace.PLACE || this.raytrace.getValue() == Raytrace.FULL) && AutoCrystal.mc.player.getDistanceSq(blockPos4) > MathUtil.square(this.placetrace.getValue()), 1.0f)) {
                continue;
            }
            float calculateDamage = -1.0f;
            if (DamageUtil.canTakeDamage(this.suicide.getValue())) {
                calculateDamage = DamageUtil.calculateDamage(blockPos4, (Entity)AutoCrystal.mc.player);
            }
            if (calculateDamage + 0.5 >= EntityUtil.getHealth((Entity)AutoCrystal.mc.player)) {
                continue;
            }
            if (calculateDamage > this.maxSelfPlace.getValue()) {
                continue;
            }
            if (entityPlayer != null) {
                final float calculateDamage2 = DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer);
                if (this.calcEvenIfNoDamage.getValue() && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.PLACE)) {
                    boolean b = false;
                    for (final EntityPlayer entityPlayer2 : AutoCrystal.mc.world.playerEntities) {
                        if (entityPlayer2 != null && !AutoCrystal.mc.player.equals((Object)entityPlayer2) && entityPlayer2.getDistanceSq(blockPos4) <= MathUtil.square(this.range.getValue() + this.placeRange.getValue()) && OyVey.friendManager.isFriend(entityPlayer2)) {
                            if (DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer2) <= EntityUtil.getHealth((Entity)entityPlayer2) + 0.5) {
                                continue;
                            }
                            b = true;
                            break;
                        }
                    }
                    if (b) {
                        continue;
                    }
                }
                if (this.isDoublePoppable(entityPlayer, calculateDamage2) && (blockPos == null || entityPlayer.getDistanceSq(blockPos4) < entityPlayer.getDistanceSq(blockPos))) {
                    target = entityPlayer;
                    n = calculateDamage2;
                    blockPos = blockPos4;
                    this.foundDoublePop = true;
                }
                else {
                    if (this.foundDoublePop || (calculateDamage2 <= n && (!this.extraSelfCalc.getValue() || calculateDamage2 < n || calculateDamage >= n2))) {
                        continue;
                    }
                    if (calculateDamage2 <= calculateDamage && (calculateDamage2 <= this.minDamage.getValue() || DamageUtil.canTakeDamage(this.suicide.getValue())) && calculateDamage2 <= EntityUtil.getHealth((Entity)entityPlayer)) {
                        continue;
                    }
                    n = calculateDamage2;
                    target = entityPlayer;
                    blockPos = blockPos4;
                    n2 = calculateDamage;
                }
            }
            else {
                final float n3 = n;
                final EntityPlayer entityPlayer3 = target;
                final BlockPos blockPos5 = blockPos;
                final float n4 = n2;
                for (final EntityPlayer entityPlayer4 : AutoCrystal.mc.world.playerEntities) {
                    if (EntityUtil.isValid((Entity)entityPlayer4, this.placeRange.getValue() + this.range.getValue())) {
                        if (this.antiNaked.getValue() && DamageUtil.isNaked(entityPlayer4)) {
                            continue;
                        }
                        final float calculateDamage3 = DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer4);
                        if (this.doublePopOnDamage.getValue() && this.isDoublePoppable(entityPlayer4, calculateDamage3) && (blockPos == null || entityPlayer4.getDistanceSq(blockPos4) < entityPlayer4.getDistanceSq(blockPos))) {
                            target = entityPlayer4;
                            n = calculateDamage3;
                            blockPos = blockPos4;
                            n2 = calculateDamage;
                            this.foundDoublePop = true;
                            if (this.antiFriendPop.getValue() != AntiFriendPop.BREAK && this.antiFriendPop.getValue() != AntiFriendPop.PLACE) {
                                continue;
                            }
                            break;
                        }
                        else {
                            if (this.foundDoublePop || (calculateDamage3 <= n && (!this.extraSelfCalc.getValue() || calculateDamage3 < n || calculateDamage >= n2))) {
                                continue;
                            }
                            if (calculateDamage3 <= calculateDamage && (calculateDamage3 <= this.minDamage.getValue() || DamageUtil.canTakeDamage(this.suicide.getValue())) && calculateDamage3 <= EntityUtil.getHealth((Entity)entityPlayer4)) {
                                continue;
                            }
                            n = calculateDamage3;
                            target = entityPlayer4;
                            blockPos = blockPos4;
                            n2 = calculateDamage;
                        }
                    }
                    else {
                        if ((this.antiFriendPop.getValue() != AntiFriendPop.ALL && this.antiFriendPop.getValue() != AntiFriendPop.PLACE) || entityPlayer4 == null || entityPlayer4.getDistanceSq(blockPos4) > MathUtil.square(this.range.getValue() + this.placeRange.getValue()) || !OyVey.friendManager.isFriend(entityPlayer4)) {
                            continue;
                        }
                        if (DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer4) <= EntityUtil.getHealth((Entity)entityPlayer4) + 0.5) {
                            continue;
                        }
                        n = n3;
                        target = entityPlayer3;
                        blockPos = blockPos5;
                        n2 = n4;
                        break;
                    }
                }
            }
        }
        if (blockPos2 != null) {
            AutoCrystal.mc.world.setBlockState(blockPos2, getBlockState);
            this.webPos = blockPos;
        }
        AutoCrystal.target = target;
        this.currentDamage = n;
        this.placePos = blockPos;
    }
    
    private ScheduledExecutorService getExecutor() {
        final ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        singleThreadScheduledExecutor.scheduleAtFixedRate(RAutoCrystal.getInstance(this), 0L, this.threadDelay.getValue(), TimeUnit.MILLISECONDS);
        return singleThreadScheduledExecutor;
    }
    
    private boolean shouldSlowBreak(final boolean b) {
        return (b && this.manual.getValue() && this.manualMinDmg.getValue() && Mouse.isButtonDown(1) && (!Mouse.isButtonDown(0) || !this.holdFacePlace.getValue())) || (this.holdFacePlace.getValue() && this.holdFaceBreak.getValue() && Mouse.isButtonDown(0) && !this.breakTimer.passedMs(this.facePlaceSpeed.getValue())) || (this.slowFaceBreak.getValue() && !this.breakTimer.passedMs(this.facePlaceSpeed.getValue()));
    }
    
    private void postProcessBreak() {
        while (!this.packetUseEntities.isEmpty()) {
            AutoCrystal.mc.player.connection.sendPacket((Packet)this.packetUseEntities.poll());
            if (this.breakSwing.getValue()) {
                AutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            this.breakTimer.reset();
        }
    }
    
    private void placeCrystal() {
        int intValue = this.wasteAmount.getValue();
        if (this.placeTimer.passedMs(this.placeDelay.getValue()) && this.place.getValue() && (this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC || (this.switchMode.getValue() == Switch.BREAKSLOT && this.switching))) {
            if ((this.offHand || this.mainHand || (this.switchMode.getValue() != Switch.ALWAYS && !this.switching)) && this.crystalCount >= intValue && (!this.antiSurround.getValue() || this.lastPos == null || !this.lastPos.equals((Object)this.placePos))) {
                return;
            }
            this.calculateDamage(this.getTarget(this.targetMode.getValue() == Target.UNSAFE));
            if (AutoCrystal.target != null && this.placePos != null) {
                if (!this.offHand && !this.mainHand && this.autoSwitch.getValue() != AutoSwitch.NONE && (this.currentDamage > this.minDamage.getValue() || (this.lethalSwitch.getValue() && EntityUtil.getHealth((Entity)AutoCrystal.target) <= this.facePlace.getValue())) && !this.switchItem()) {
                    return;
                }
                if (this.currentDamage < this.minDamage.getValue() && this.limitFacePlace.getValue()) {
                    intValue = 1;
                }
                if (this.currentDamage >= this.minMinDmg.getValue() && (this.offHand || this.mainHand || this.autoSwitch.getValue() != AutoSwitch.NONE) && (this.crystalCount < intValue || (this.antiSurround.getValue() && this.lastPos != null && this.lastPos.equals((Object)this.placePos))) && (this.currentDamage > this.minDamage.getValue() || this.minDmgCount < intValue) && this.currentDamage >= 1.0 && (DamageUtil.isArmorLow(AutoCrystal.target, this.minArmor.getValue()) || EntityUtil.getHealth((Entity)AutoCrystal.target) <= this.facePlace.getValue() || this.currentDamage > this.minDamage.getValue() || this.shouldHoldFacePlace())) {
                    final float n = (this.damageSync.getValue() == DamageSync.BREAK) ? (this.dropOff.getValue() - 5.0f) : 0.0f;
                    boolean b = false;
                    if (this.syncedFeetPlace.getValue() && this.placePos.equals((Object)this.lastPos) && this.isEligableForFeetSync(AutoCrystal.target, this.placePos) && !this.syncTimer.passedMs(this.damageSyncTime.getValue()) && AutoCrystal.target.equals((Object)this.currentSyncTarget) && AutoCrystal.target.getPosition().equals((Object)this.syncedPlayerPos) && this.damageSync.getValue() != DamageSync.NONE) {
                        this.syncedCrystalPos = this.placePos;
                        this.lastDamage = this.currentDamage;
                        if (this.fullSync.getValue()) {
                            this.lastDamage = 100.0;
                        }
                        b = true;
                    }
                    if (b || this.currentDamage - n > this.lastDamage || this.syncTimer.passedMs(this.damageSyncTime.getValue()) || this.damageSync.getValue() == DamageSync.NONE) {
                        if (!b && this.damageSync.getValue() != DamageSync.BREAK) {
                            this.lastDamage = this.currentDamage;
                        }
                        this.renderPos = this.placePos;
                        this.renderDamage = this.currentDamage;
                        if (this.switchItem()) {
                            this.currentSyncTarget = AutoCrystal.target;
                            this.syncedPlayerPos = AutoCrystal.target.getPosition();
                            if (this.foundDoublePop) {
                                this.totemPops.put(AutoCrystal.target, new Timer().reset());
                            }
                            this.rotateToPos(this.placePos);
                            if (this.addTolowDmg || (this.actualSlowBreak.getValue() && this.currentDamage < this.minDamage.getValue())) {
                                AutoCrystal.lowDmgPos.add(this.placePos);
                            }
                            AutoCrystal.placedPos.add(this.placePos);
                            if (!this.justRender.getValue()) {
                                if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE && this.rotateFirst.getValue() && this.rotate.getValue() != Rotate.OFF) {
                                    this.placeInfo = new PlaceInfo(this.placePos, this.offHand, this.placeSwing.getValue(), this.exactHand.getValue(), this.silentSwitch);
                                }
                                else {
                                    BlockUtil.placeCrystalOnBlock(this.placePos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.placeSwing.getValue(), this.exactHand.getValue(), this.silentSwitch);
                                }
                            }
                            this.lastPos = this.placePos;
                            this.placeTimer.reset();
                            this.posConfirmed = false;
                            if (this.syncTimer.passedMs(this.damageSyncTime.getValue())) {
                                this.syncedCrystalPos = null;
                                this.syncTimer.reset();
                            }
                        }
                    }
                }
            }
            else {
                this.renderPos = null;
            }
        }
    }
    
    private void removePos(final BlockPos blockPos) {
        if (this.damageSync.getValue() == DamageSync.PLACE) {
            if (AutoCrystal.placedPos.remove(blockPos)) {
                this.posConfirmed = true;
            }
        }
        else if (this.damageSync.getValue() == DamageSync.BREAK && AutoCrystal.brokenPos.remove(blockPos)) {
            this.posConfirmed = true;
        }
    }
    
    @Override
    public void onToggle() {
        AutoCrystal.brokenPos.clear();
        AutoCrystal.placedPos.clear();
        this.totemPops.clear();
        this.rotating = false;
    }
    
    static {
        AutoCrystal.target = null;
        AutoCrystal.lowDmgPos = (Set<BlockPos>)new ConcurrentSet();
        AutoCrystal.placedPos = new HashSet<BlockPos>();
        AutoCrystal.brokenPos = new HashSet<BlockPos>();
    }
    
    private void processMultiThreading() {
        if (this.isOff()) {
            return;
        }
        if (this.threadMode.getValue() == ThreadMode.WHILE) {
            this.handleWhile();
        }
        else if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.handlePool(false);
        }
    }
    
    public void doAutoCrystal() {
        if (this.brownZombie.getValue()) {
            return;
        }
        if (this.check()) {
            switch (this.logic.getValue()) {
                case PLACEBREAK: {
                    this.placeCrystal();
                    this.breakCrystal();
                    break;
                }
                case BREAKPLACE: {
                    this.breakCrystal();
                    this.placeCrystal();
                    break;
                }
            }
            this.manualBreaker();
        }
    }
    
    private void postProcessPlace() {
        if (this.placeInfo != null) {
            this.placeInfo.runPlace();
            this.placeTimer.reset();
            this.placeInfo = null;
        }
    }
    
    private void rotateTo(final Entity entity) {
        switch (this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case BREAK:
            case ALL: {
                final float[] calcAngle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), entity.getPositionVector());
                if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE) {
                    OyVey.rotationManager.setPlayerRotations(calcAngle[0], calcAngle[1]);
                    break;
                }
                this.yaw = calcAngle[0];
                this.pitch = calcAngle[1];
                this.rotating = true;
                break;
            }
        }
    }
    
    private boolean isDoublePoppable(final EntityPlayer entityPlayer, final float n) {
        final float health;
        if (this.doublePop.getValue() && (health = EntityUtil.getHealth((Entity)entityPlayer)) <= this.popHealth.getValue() && n > health + 0.5 && n <= this.popDamage.getValue()) {
            final Timer timer = this.totemPops.get(entityPlayer);
            return timer == null || timer.passedMs(this.popTime.getValue());
        }
        return false;
    }
    
    private EntityPlayer getTarget(final boolean b) {
        if (this.targetMode.getValue() == Target.DAMAGE) {
            return null;
        }
        Object o = null;
        for (final EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer, this.placeRange.getValue() + this.range.getValue()) && (!this.antiNaked.getValue() || !DamageUtil.isNaked(entityPlayer))) {
                if (b && EntityUtil.isSafe((Entity)entityPlayer)) {
                    continue;
                }
                if (this.minArmor.getValue() > 0 && DamageUtil.isArmorLow(entityPlayer, this.minArmor.getValue())) {
                    o = entityPlayer;
                    break;
                }
                if (o == null) {
                    o = entityPlayer;
                }
                else {
                    if (AutoCrystal.mc.player.getDistanceSq((Entity)entityPlayer) >= AutoCrystal.mc.player.getDistanceSq((Entity)o)) {
                        continue;
                    }
                    o = entityPlayer;
                }
            }
        }
        if (b && o == null) {
            return this.getTarget(false);
        }
        if (this.predictPos.getValue() && o != null) {
            final EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP((World)AutoCrystal.mc.world, new GameProfile((((EntityPlayer)o).getUniqueID() == null) ? UUID.fromString("8af022c8-b926-41a0-8b79-2b544ff00fcf") : ((EntityPlayer)o).getUniqueID(), ((EntityPlayer)o).getName()));
            final Vec3d extrapolatePlayerPosition = MathUtil.extrapolatePlayerPosition((EntityPlayer)o, this.predictTicks.getValue());
            entityOtherPlayerMP.copyLocationAndAnglesFrom((Entity)o);
            entityOtherPlayerMP.posX = extrapolatePlayerPosition.x;
            entityOtherPlayerMP.posY = extrapolatePlayerPosition.y;
            entityOtherPlayerMP.posZ = extrapolatePlayerPosition.z;
            entityOtherPlayerMP.setHealth(EntityUtil.getHealth((Entity)o));
            entityOtherPlayerMP.inventory.copyInventory(((EntityPlayer)o).inventory);
            o = entityOtherPlayerMP;
        }
        return (EntityPlayer)o;
    }
    
    public static AutoCrystal getInstance() {
        if (AutoCrystal.instance == null) {
            AutoCrystal.instance = new AutoCrystal();
        }
        return AutoCrystal.instance;
    }
    
    private void attackCrystalPredict(final int entityId, final BlockPos blockPos) {
        if (this.predictRotate.getValue() && (this.eventMode.getValue() != 2 || this.threadMode.getValue() != ThreadMode.NONE) && (this.rotate.getValue() == Rotate.BREAK || this.rotate.getValue() == Rotate.ALL)) {
            this.rotateToPos(blockPos);
        }
        final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
        cPacketUseEntity.entityId = entityId;
        cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
        AutoCrystal.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
        if (this.breakSwing.getValue()) {
            AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        }
        if (this.resetBreakTimer.getValue()) {
            this.breakTimer.reset();
        }
        this.predictTimer.reset();
    }
    
    private void handlePool(final boolean b) {
        if (b || this.executor == null || this.executor.isTerminated() || this.executor.isShutdown() || (this.syncroTimer.passedMs(this.syncThreads.getValue()) && this.syncThreadBool.getValue())) {
            if (this.executor != null) {
                this.executor.shutdown();
            }
            this.executor = this.getExecutor();
            this.syncroTimer.reset();
        }
    }
    
    private boolean switchItem() {
        if (this.offHand || this.mainHand) {
            return true;
        }
        switch (this.autoSwitch.getValue()) {
            case NONE: {
                return false;
            }
            case ALWAYS: {
                if (!this.doSwitch()) {
                    break;
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean isEligableForFeetSync(final EntityPlayer entityPlayer, final BlockPos blockPos) {
        if (this.holySync.getValue()) {
            final BlockPos blockPos2 = new BlockPos(entityPlayer.getPositionVector());
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                if (enumFacing != EnumFacing.DOWN && enumFacing != EnumFacing.UP && blockPos.equals((Object)blockPos2.down().offset(enumFacing))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    private boolean isValid(final Entity entity) {
        return entity != null && AutoCrystal.mc.player.getDistanceSq(entity) <= MathUtil.square(this.breakRange.getValue()) && (this.raytrace.getValue() == Raytrace.NONE || this.raytrace.getValue() == Raytrace.PLACE || AutoCrystal.mc.player.canEntityBeSeen(entity) || (!AutoCrystal.mc.player.canEntityBeSeen(entity) && AutoCrystal.mc.player.getDistanceSq(entity) <= MathUtil.square(this.breaktrace.getValue())));
    }
    
    private boolean shouldHoldFacePlace() {
        this.addTolowDmg = false;
        if (this.holdFacePlace.getValue() && Mouse.isButtonDown(0)) {
            this.addTolowDmg = true;
            return true;
        }
        return false;
    }
    
    @Override
    public void onEnable() {
        if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.processMultiThreading();
        }
    }
    
    private void rotateToPos(final BlockPos blockPos) {
        switch (this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case PLACE:
            case ALL: {
                final float[] calcAngle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() - 0.5f), (double)(blockPos.getZ() + 0.5f)));
                if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE) {
                    OyVey.rotationManager.setPlayerRotations(calcAngle[0], calcAngle[1]);
                    break;
                }
                this.yaw = calcAngle[0];
                this.pitch = calcAngle[1];
                this.rotating = true;
                break;
            }
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 1) {
            this.postProcessing();
        }
        if (updateWalkingPlayerEvent.getStage() != 0) {
            return;
        }
        if (this.eventMode.getValue() == 2) {
            this.doAutoCrystal();
        }
    }
    
    @Override
    public void onTick() {
        if (this.threadMode.getValue() == ThreadMode.NONE && this.eventMode.getValue() == 3) {
            this.doAutoCrystal();
        }
    }
    
    private boolean check() {
        if (fullNullCheck()) {
            return false;
        }
        if (this.syncTimer.passedMs(this.damageSyncTime.getValue())) {
            this.currentSyncTarget = null;
            this.syncedCrystalPos = null;
            this.syncedPlayerPos = null;
        }
        else if (this.syncySync.getValue() && this.syncedCrystalPos != null) {
            this.posConfirmed = true;
        }
        this.foundDoublePop = false;
        if (this.renderTimer.passedMs(500L)) {
            this.renderPos = null;
            this.renderTimer.reset();
        }
        this.mainHand = (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL);
        if (this.autoSwitch.getValue() == AutoSwitch.SILENT && InventoryUtil.getItemHotbar(Items.END_CRYSTAL) != -1) {
            this.mainHand = true;
            this.silentSwitch = true;
        }
        else {
            this.silentSwitch = false;
        }
        this.offHand = (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL);
        this.currentDamage = 0.0;
        this.placePos = null;
        if (!this.offHand && !this.mainHand) {
            this.placeInfo = null;
            this.packetUseEntities.clear();
        }
        if (this.offHand || this.mainHand) {
            this.switching = false;
        }
        if ((!this.offHand && !this.mainHand && this.switchMode.getValue() == Switch.BREAKSLOT && !this.switching) || !DamageUtil.canBreakWeakness((EntityPlayer)AutoCrystal.mc.player) || !this.switchTimer.passedMs(this.switchCooldown.getValue())) {
            this.renderPos = null;
            AutoCrystal.target = null;
            this.rotating = false;
            return false;
        }
        if (this.mineSwitch.getValue() && Mouse.isButtonDown(0) && (this.switching || this.autoSwitch.getValue() == AutoSwitch.ALWAYS) && Mouse.isButtonDown(1) && AutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
            this.switchItem();
        }
        this.mapCrystals();
        if (!this.posConfirmed && this.damageSync.getValue() != DamageSync.NONE && this.syncTimer.passedMs(this.confirm.getValue())) {
            this.syncTimer.setMs(this.damageSyncTime.getValue() + 1);
        }
        return true;
    }
    
    private void mapCrystals() {
        this.efficientTarget = null;
        if (this.packets.getValue() != 1) {
            this.attackList = new ConcurrentLinkedQueue<Entity>();
            this.crystalMap = new HashMap<Entity, Float>();
        }
        this.crystalCount = 0;
        this.minDmgCount = 0;
        Entity efficientTarget = null;
        float n = 0.5f;
        for (final Entity entity : AutoCrystal.mc.world.loadedEntityList) {
            if (!entity.isDead && entity instanceof EntityEnderCrystal) {
                if (!this.isValid(entity)) {
                    continue;
                }
                if (this.syncedFeetPlace.getValue() && entity.getPosition().down().equals((Object)this.syncedCrystalPos) && this.damageSync.getValue() != DamageSync.NONE) {
                    ++this.minDmgCount;
                    ++this.crystalCount;
                    if (this.syncCount.getValue()) {
                        this.minDmgCount = this.wasteAmount.getValue() + 1;
                        this.crystalCount = this.wasteAmount.getValue() + 1;
                    }
                    if (!this.hyperSync.getValue()) {
                        continue;
                    }
                    efficientTarget = null;
                    break;
                }
                else {
                    boolean b = false;
                    boolean b2 = false;
                    float calculateDamage = -1.0f;
                    if (DamageUtil.canTakeDamage(this.suicide.getValue())) {
                        calculateDamage = DamageUtil.calculateDamage(entity, (Entity)AutoCrystal.mc.player);
                    }
                    if (calculateDamage + 0.5 < EntityUtil.getHealth((Entity)AutoCrystal.mc.player) && calculateDamage <= this.maxSelfBreak.getValue()) {
                        final Entity entity2 = efficientTarget;
                        final float n2 = n;
                        for (final EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
                            if (entityPlayer.getDistanceSq(entity) > MathUtil.square(this.range.getValue())) {
                                continue;
                            }
                            if (EntityUtil.isValid((Entity)entityPlayer, this.range.getValue() + this.breakRange.getValue())) {
                                if (this.antiNaked.getValue() && DamageUtil.isNaked(entityPlayer)) {
                                    continue;
                                }
                                final float calculateDamage2;
                                if ((calculateDamage2 = DamageUtil.calculateDamage(entity, (Entity)entityPlayer)) <= calculateDamage && (calculateDamage2 <= this.minDamage.getValue() || DamageUtil.canTakeDamage(this.suicide.getValue())) && calculateDamage2 <= EntityUtil.getHealth((Entity)entityPlayer)) {
                                    continue;
                                }
                                if (calculateDamage2 > n) {
                                    n = calculateDamage2;
                                    efficientTarget = entity;
                                }
                                if (this.packets.getValue() == 1) {
                                    if (calculateDamage2 >= this.minDamage.getValue() || !this.wasteMinDmgCount.getValue()) {
                                        b = true;
                                    }
                                    b2 = true;
                                }
                                else {
                                    if (this.crystalMap.get(entity) != null && this.crystalMap.get(entity) >= calculateDamage2) {
                                        continue;
                                    }
                                    this.crystalMap.put(entity, calculateDamage2);
                                }
                            }
                            else {
                                if ((this.antiFriendPop.getValue() != AntiFriendPop.BREAK && this.antiFriendPop.getValue() != AntiFriendPop.ALL) || !OyVey.friendManager.isFriend(entityPlayer.getName())) {
                                    continue;
                                }
                                if (DamageUtil.calculateDamage(entity, (Entity)entityPlayer) <= EntityUtil.getHealth((Entity)entityPlayer) + 0.5) {
                                    continue;
                                }
                                efficientTarget = entity2;
                                n = n2;
                                this.crystalMap.remove(entity);
                                if (!this.noCount.getValue()) {
                                    break;
                                }
                                b = false;
                                b2 = false;
                                break;
                            }
                        }
                    }
                    if (!b2) {
                        continue;
                    }
                    ++this.minDmgCount;
                    if (!b) {
                        continue;
                    }
                    ++this.crystalCount;
                }
            }
        }
        if (this.damageSync.getValue() == DamageSync.BREAK && (n > this.lastDamage || this.syncTimer.passedMs(this.damageSyncTime.getValue()) || this.damageSync.getValue() == DamageSync.NONE)) {
            this.lastDamage = n;
        }
        if (this.enormousSync.getValue() && this.syncedFeetPlace.getValue() && this.damageSync.getValue() != DamageSync.NONE && this.syncedCrystalPos != null) {
            if (this.syncCount.getValue()) {
                this.minDmgCount = this.wasteAmount.getValue() + 1;
                this.crystalCount = this.wasteAmount.getValue() + 1;
            }
            return;
        }
        if (this.webAttack.getValue() && this.webPos != null) {
            if (AutoCrystal.mc.player.getDistanceSq(this.webPos.up()) > MathUtil.square(this.breakRange.getValue())) {
                this.webPos = null;
            }
            else {
                for (final Entity efficientTarget2 : AutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(this.webPos.up()))) {
                    if (!(efficientTarget2 instanceof EntityEnderCrystal)) {
                        continue;
                    }
                    this.attackList.add(efficientTarget2);
                    this.efficientTarget = efficientTarget2;
                    this.webPos = null;
                    this.lastDamage = 0.5;
                    return;
                }
            }
        }
        if (this.shouldSlowBreak(true) && n < this.minDamage.getValue() && (AutoCrystal.target == null || EntityUtil.getHealth((Entity)AutoCrystal.target) > this.facePlace.getValue() || (!this.breakTimer.passedMs(this.facePlaceSpeed.getValue()) && this.slowFaceBreak.getValue() && Mouse.isButtonDown(0) && this.holdFacePlace.getValue() && this.holdFaceBreak.getValue()))) {
            this.efficientTarget = null;
            return;
        }
        if (this.packets.getValue() == 1) {
            this.efficientTarget = efficientTarget;
        }
        else {
            this.crystalMap = MathUtil.sortByValue(this.crystalMap, true);
            for (final Map.Entry<Entity, Float> entry : this.crystalMap.entrySet()) {
                final Entity entity3 = entry.getKey();
                if (entry.getValue() >= this.minDamage.getValue() || !this.wasteMinDmgCount.getValue()) {
                    ++this.crystalCount;
                }
                this.attackList.add(entity3);
                ++this.minDmgCount;
            }
        }
    }
    
    private void breakCrystal() {
        InventoryUtil.findHotbarBlock(ItemSword.class);
        final int currentItem = AutoCrystal.mc.player.inventory.currentItem;
        if (this.explode.getValue() && this.breakTimer.passedMs(this.breakDelay.getValue()) && (this.switchMode.getValue() == Switch.ALWAYS || this.mainHand || this.offHand)) {
            if (this.packets.getValue() == 1 && this.efficientTarget != null) {
                if (this.justRender.getValue()) {
                    this.doFakeSwing();
                    return;
                }
                if (this.syncedFeetPlace.getValue() && this.gigaSync.getValue() && this.syncedCrystalPos != null && this.damageSync.getValue() != DamageSync.NONE) {
                    return;
                }
                this.rotateTo(this.efficientTarget);
                this.attackEntity(this.efficientTarget);
                this.breakTimer.reset();
            }
            else if (!this.attackList.isEmpty()) {
                if (this.justRender.getValue()) {
                    this.doFakeSwing();
                    return;
                }
                if (this.syncedFeetPlace.getValue() && this.gigaSync.getValue() && this.syncedCrystalPos != null && this.damageSync.getValue() != DamageSync.NONE) {
                    return;
                }
                for (int i = 0; i < this.packets.getValue(); ++i) {
                    final Entity entity = this.attackList.poll();
                    if (entity != null) {
                        this.rotateTo(entity);
                        this.attackEntity(entity);
                    }
                }
                this.breakTimer.reset();
            }
        }
    }
    
    private boolean predictSlowBreak(final BlockPos blockPos) {
        return this.antiCommit.getValue() && AutoCrystal.lowDmgPos.remove(blockPos) && this.shouldSlowBreak(false);
    }
    
    private void manualBreaker() {
        if (this.rotate.getValue() != Rotate.OFF && this.eventMode.getValue() != 2 && this.rotating) {
            if (this.didRotation) {
                AutoCrystal.mc.player.rotationPitch += (float)4.0E-4;
                this.didRotation = false;
            }
            else {
                AutoCrystal.mc.player.rotationPitch -= (float)4.0E-4;
                this.didRotation = true;
            }
        }
        final RayTraceResult objectMouseOver;
        if ((this.offHand || this.mainHand) && this.manual.getValue() && this.manualTimer.passedMs(this.manualBreak.getValue()) && Mouse.isButtonDown(1) && AutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.GOLDEN_APPLE && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.BOW && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.EXPERIENCE_BOTTLE && (objectMouseOver = AutoCrystal.mc.objectMouseOver) != null) {
            switch (objectMouseOver.typeOfHit) {
                case ENTITY: {
                    final Entity entityHit = objectMouseOver.entityHit;
                    if (!(entityHit instanceof EntityEnderCrystal)) {
                        break;
                    }
                    EntityUtil.attackEntity(entityHit, this.sync.getValue(), this.breakSwing.getValue());
                    this.manualTimer.reset();
                    break;
                }
                case BLOCK: {
                    for (final Entity entity : AutoCrystal.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(AutoCrystal.mc.objectMouseOver.getBlockPos().up()))) {
                        if (!(entity instanceof EntityEnderCrystal)) {
                            continue;
                        }
                        EntityUtil.attackEntity(entity, this.sync.getValue(), this.breakSwing.getValue());
                        this.manualTimer.reset();
                    }
                    break;
                }
            }
        }
    }
    
    private boolean isRightThread() {
        return AutoCrystal.mc.isCallingFromMinecraftThread() || (!OyVey.eventManager.ticksOngoing() && !this.threadOngoing.get());
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && clientEvent.getSetting().getFeature() != null && clientEvent.getSetting().getFeature().equals(this) && this.isEnabled() && (clientEvent.getSetting().equals(this.threadDelay) || clientEvent.getSetting().equals(this.threadMode))) {
            if (this.executor != null) {
                this.executor.shutdown();
            }
            if (this.thread != null) {
                this.shouldInterrupt.set(true);
            }
        }
    }
    
    private void attackEntity(final Entity entity) {
        if (entity != null) {
            if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE && this.rotateFirst.getValue() && this.rotate.getValue() != Rotate.OFF) {
                this.packetUseEntities.add(new CPacketUseEntity(entity));
            }
            else {
                EntityUtil.attackEntity(entity, this.sync.getValue(), this.breakSwing.getValue());
                AutoCrystal.brokenPos.add(new BlockPos(entity.getPositionVector()).down());
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.threadMode.getValue() == ThreadMode.NONE && this.eventMode.getValue() == 1) {
            this.doAutoCrystal();
        }
    }
    
    private void doFakeSwing() {
        if (this.fakeSwing.getValue()) {
            EntityUtil.swingArmNoPacket(EnumHand.MAIN_HAND, (EntityLivingBase)AutoCrystal.mc.player);
        }
    }
    
    @Override
    public void onDisable() {
        if (this.thread != null) {
            this.shouldInterrupt.set(true);
        }
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }
    
    public void postTick() {
        if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.processMultiThreading();
        }
    }
    
    private boolean doSwitch() {
        if (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            this.mainHand = false;
        }
        else {
            InventoryUtil.switchToHotbarSlot(ItemEndCrystal.class, false);
            this.mainHand = true;
        }
        this.switching = false;
        return true;
    }
    
    private void handleWhile() {
        if (this.thread == null || this.thread.isInterrupted() || !this.thread.isAlive() || (this.syncroTimer.passedMs(this.syncThreads.getValue()) && this.syncThreadBool.getValue())) {
            if (this.thread == null) {
                this.thread = new Thread(RAutoCrystal.getInstance(this));
            }
            else if (this.syncroTimer.passedMs(this.syncThreads.getValue()) && !this.shouldInterrupt.get() && this.syncThreadBool.getValue()) {
                this.shouldInterrupt.set(true);
                this.syncroTimer.reset();
                return;
            }
            if (this.thread != null && (this.thread.isInterrupted() || !this.thread.isAlive())) {
                this.thread = new Thread(RAutoCrystal.getInstance(this));
            }
            if (this.thread != null && this.thread.getState() == Thread.State.NEW) {
                try {
                    this.thread.start();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.syncroTimer.reset();
            }
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!this.render.getValue()) {
            return;
        }
        final Color color = new Color(this.bRed.getValue(), this.bGreen.getValue(), this.bBlue.getValue(), this.bAlpha.getValue());
        final Color color2 = new Color(this.oRed.getValue(), this.oGreen.getValue(), this.oBlue.getValue(), this.oAlpha.getValue());
        if ((this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC) && this.renderPos != null && (this.box.getValue() || this.outline.getValue())) {
            if (this.renderMode.getValue() == RenderMode.FADE) {
                this.positions.removeIf(renderPos -> renderPos.getPos().equals((Object)this.renderPos));
                this.positions.add(new RenderPos(this.renderPos, 0.0f));
            }
            if (this.renderMode.getValue() == RenderMode.STATIC) {
                RenderUtil.drawSexyBoxOyVeyIsRetardedFuckYouESP(new AxisAlignedBB(this.renderPos), color, color2, this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), 1.0f, 1.0f, this.slabHeight.getValue());
            }
            if (this.renderMode.getValue() == RenderMode.GLIDE) {
                if (this.lastRenderPos == null || AutoCrystal.mc.player.getDistance(this.renderBB.minX, this.renderBB.minY, this.renderBB.minZ) > this.range.getValue()) {
                    this.lastRenderPos = this.renderPos;
                    this.renderBB = new AxisAlignedBB(this.renderPos);
                    this.timePassed = 0.0f;
                }
                if (!this.lastRenderPos.equals((Object)this.renderPos)) {
                    this.lastRenderPos = this.renderPos;
                    this.timePassed = 0.0f;
                }
                final double n = this.renderPos.getX() - this.renderBB.minX;
                final double n2 = this.renderPos.getY() - this.renderBB.minY;
                final double n3 = this.renderPos.getZ() - this.renderBB.minZ;
                float n4 = this.timePassed / this.moveSpeed.getValue() * this.accel.getValue();
                if (n4 > 1.0f) {
                    n4 = 1.0f;
                }
                this.renderBB = this.renderBB.offset(n * n4, n2 * n4, n3 * n4);
                RenderUtil.drawSexyBoxOyVeyIsRetardedFuckYouESP(this.renderBB, color, color2, this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), 1.0f, 1.0f, this.slabHeight.getValue());
                if (this.text.getValue()) {
                    RenderUtil.drawTextz(this.renderBB.offset(0.0, 1.0f - this.slabHeight.getValue() / 2.0f - 0.4, 0.0), String.valueOf(new StringBuilder().append((Math.floor(this.renderDamage) == this.renderDamage) ? Integer.valueOf((int)this.renderDamage) : String.format("%.1f", this.renderDamage)).append("")));
                }
                if (this.renderBB.equals((Object)new AxisAlignedBB(this.renderPos))) {
                    this.timePassed = 0.0f;
                }
                else {
                    this.timePassed += 50.0f;
                }
            }
        }
        if (this.renderMode.getValue() == RenderMode.FADE) {
            final float n5;
            final Color color3;
            final Color color4;
            this.positions.forEach(renderPos3 -> {
                n5 = (this.duration.getValue() - renderPos3.getRenderTime()) / this.duration.getValue();
                RenderUtil.drawSexyBoxOyVeyIsRetardedFuckYouESP(new AxisAlignedBB(renderPos3.getPos()), color3, color4, this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), ((boolean)this.fadeFactor.getValue()) ? n5 : 1.0f, ((boolean)this.scaleFactor.getValue()) ? n5 : 1.0f, ((boolean)this.slabFactor.getValue()) ? n5 : 1.0f);
                renderPos3.setRenderTime(renderPos3.getRenderTime() + 50.0f);
                return;
            });
            this.positions.removeIf(renderPos2 -> renderPos2.getRenderTime() >= this.duration.getValue() || AutoCrystal.mc.world.isAirBlock(renderPos2.getPos()) || !AutoCrystal.mc.world.isAirBlock(renderPos2.getPos().offset(EnumFacing.UP)));
            if (this.positions.size() > this.max.getValue()) {
                this.positions.remove(0);
            }
        }
        if ((this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC) && this.renderPos != null && this.text.getValue() && this.renderMode.getValue() != RenderMode.GLIDE) {
            RenderUtil.drawTextz(new AxisAlignedBB(this.renderPos).offset(0.0, (this.renderMode.getValue() != RenderMode.FADE) ? (1.0f - this.slabHeight.getValue() / 2.0f - 0.4) : 0.1, 0.0), String.valueOf(new StringBuilder().append((Math.floor(this.renderDamage) == this.renderDamage) ? Integer.valueOf((int)this.renderDamage) : String.format("%.1f", this.renderDamage)).append("")));
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0 && this.rotate.getValue() != Rotate.OFF && this.rotating && this.eventMode.getValue() != 2 && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
        BlockPos getPosition = null;
        final CPacketUseEntity cPacketUseEntity;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world) instanceof EntityEnderCrystal) {
            getPosition = cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world).getPosition();
            if (this.removeAfterAttack.getValue()) {
                Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world)).setDead();
                AutoCrystal.mc.world.removeEntityFromWorld(cPacketUseEntity.entityId);
            }
        }
        final CPacketUseEntity cPacketUseEntity2;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity2 = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity2.getEntityFromWorld((World)AutoCrystal.mc.world) instanceof EntityEnderCrystal) {
            final EntityEnderCrystal entityEnderCrystal = (EntityEnderCrystal)cPacketUseEntity2.getEntityFromWorld((World)AutoCrystal.mc.world);
            if (this.antiBlock.getValue() && EntityUtil.isCrystalAtFeet(entityEnderCrystal, this.range.getValue()) && getPosition != null) {
                this.rotateToPos(getPosition);
                BlockUtil.placeCrystalOnBlock(this.placePos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.placeSwing.getValue(), this.exactHand.getValue(), this.silentSwitch);
            }
        }
    }
    
    private void postProcessing() {
        if (this.threadMode.getValue() != ThreadMode.NONE || this.eventMode.getValue() != 2 || this.rotate.getValue() == Rotate.OFF || !this.rotateFirst.getValue()) {
            return;
        }
        switch (this.logic.getValue()) {
            case BREAKPLACE: {
                this.postProcessBreak();
                this.postProcessPlace();
                break;
            }
            case PLACEBREAK: {
                this.postProcessPlace();
                this.postProcessBreak();
                break;
            }
        }
    }
    
    private static class RAutoCrystal implements Runnable
    {
        private /* synthetic */ AutoCrystal autoCrystal;
        private static /* synthetic */ RAutoCrystal instance;
        
        @Override
        public void run() {
            if (this.autoCrystal.threadMode.getValue() == ThreadMode.WHILE) {
                while (this.autoCrystal.isOn() && this.autoCrystal.threadMode.getValue() == ThreadMode.WHILE) {
                    while (OyVey.eventManager.ticksOngoing()) {}
                    if (this.autoCrystal.shouldInterrupt.get()) {
                        this.autoCrystal.shouldInterrupt.set(false);
                        this.autoCrystal.syncroTimer.reset();
                        this.autoCrystal.thread.interrupt();
                        break;
                    }
                    this.autoCrystal.threadOngoing.set(true);
                    OyVey.safetyManager.doSafetyCheck();
                    this.autoCrystal.doAutoCrystal();
                    this.autoCrystal.threadOngoing.set(false);
                    try {
                        Thread.sleep(this.autoCrystal.threadDelay.getValue());
                    }
                    catch (InterruptedException ex) {
                        this.autoCrystal.thread.interrupt();
                        ex.printStackTrace();
                    }
                }
            }
            else if (this.autoCrystal.threadMode.getValue() != ThreadMode.NONE && this.autoCrystal.isOn()) {
                while (OyVey.eventManager.ticksOngoing()) {}
                this.autoCrystal.threadOngoing.set(true);
                OyVey.safetyManager.doSafetyCheck();
                this.autoCrystal.doAutoCrystal();
                this.autoCrystal.threadOngoing.set(false);
            }
        }
        
        public static RAutoCrystal getInstance(final AutoCrystal autoCrystal) {
            if (RAutoCrystal.instance == null) {
                RAutoCrystal.instance = new RAutoCrystal();
                RAutoCrystal.instance.autoCrystal = autoCrystal;
            }
            return RAutoCrystal.instance;
        }
    }
    
    public enum ThreadMode
    {
        WHILE, 
        POOL, 
        NONE, 
        SOUND;
    }
    
    public enum Rotate
    {
        PLACE, 
        BREAK, 
        OFF, 
        ALL;
    }
    
    public enum RenderMode
    {
        STATIC, 
        GLIDE, 
        FADE;
    }
    
    private class RenderPos
    {
        private /* synthetic */ float renderTime;
        private /* synthetic */ BlockPos renderPos;
        
        public BlockPos getPos() {
            return this.renderPos;
        }
        
        public float getRenderTime() {
            return this.renderTime;
        }
        
        public void setRenderTime(final float renderTime) {
            this.renderTime = renderTime;
        }
        
        public void setPos(final BlockPos renderPos) {
            this.renderPos = renderPos;
        }
        
        public RenderPos(final BlockPos renderPos, final float renderTime) {
            this.renderPos = renderPos;
            this.renderTime = renderTime;
        }
    }
    
    public enum Target
    {
        CLOSEST, 
        DAMAGE, 
        UNSAFE;
    }
    
    public enum AntiFriendPop
    {
        ALL, 
        NONE, 
        PLACE, 
        BREAK;
    }
    
    public static class PlaceInfo
    {
        private final /* synthetic */ boolean exactHand;
        private final /* synthetic */ BlockPos pos;
        private final /* synthetic */ boolean placeSwing;
        private final /* synthetic */ boolean silent;
        private final /* synthetic */ boolean offhand;
        
        public void runPlace() {
            BlockUtil.placeCrystalOnBlock(this.pos, this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.placeSwing, this.exactHand, this.silent);
        }
        
        public PlaceInfo(final BlockPos pos, final boolean offhand, final boolean placeSwing, final boolean exactHand, final boolean silent) {
            this.pos = pos;
            this.offhand = offhand;
            this.placeSwing = placeSwing;
            this.exactHand = exactHand;
            this.silent = silent;
        }
    }
    
    public enum AutoSwitch
    {
        ALWAYS, 
        NONE, 
        SILENT;
    }
    
    public enum Logic
    {
        PLACEBREAK, 
        BREAKPLACE;
    }
    
    public enum PredictTimer
    {
        PREDICT, 
        BREAK, 
        NONE;
    }
    
    public enum Switch
    {
        ALWAYS, 
        BREAKSLOT, 
        CALC;
    }
    
    public enum Settings
    {
        BREAK, 
        MISC, 
        PLACE, 
        RENDER, 
        DEV;
    }
    
    public enum DamageSync
    {
        BREAK, 
        NONE, 
        PLACE;
    }
    
    public enum Raytrace
    {
        PLACE, 
        BREAK, 
        FULL, 
        NONE;
    }
}
