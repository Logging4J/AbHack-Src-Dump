//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import me.abHack.features.modules.*;
import java.util.stream.*;
import org.lwjgl.input.*;
import me.abHack.features.gui.*;
import java.util.*;
import me.abHack.features.modules.client.*;
import me.abHack.features.modules.combat.*;
import me.abHack.features.modules.misc.*;
import me.abHack.features.modules.render.*;
import me.abHack.features.modules.player.*;
import me.abHack.features.modules.movement.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import java.util.function.*;
import java.util.concurrent.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.*;

public class ModuleManager extends Feature
{
    public /* synthetic */ List<Module> sortedModules;
    public /* synthetic */ ArrayList<Module> modules;
    public /* synthetic */ List<String> sortedModulesABC;
    
    public void sortModules(final boolean b) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (b ? -1 : 1))).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
    }
    
    public boolean isModuleEnabled(final String s) {
        final Module moduleByName = this.getModuleByName(s);
        return moduleByName != null && moduleByName.isOn();
    }
    
    public void onUnloadPost() {
        final Iterator<Module> iterator = this.modules.iterator();
        while (iterator.hasNext()) {
            iterator.next().enabled.setValue((Object)false);
        }
    }
    
    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }
    
    public ArrayList<Module> getModulesByCategory(final Module.Category category) {
        final ArrayList<Module> list = new ArrayList<Module>();
        final ArrayList<Module> list2;
        this.modules.forEach(e -> {
            if (e.getCategory() == category) {
                list2.add(e);
            }
            return;
        });
        return list;
    }
    
    public void disableModule(final Class<Module> clazz) {
        final Module moduleByClass = this.getModuleByClass(clazz);
        if (moduleByClass != null) {
            moduleByClass.disable();
        }
    }
    
    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }
    
    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }
    
    public void onKeyPressed(final int n) {
        if (n == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof OyVeyGui) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == n) {
                module.toggle();
            }
        });
    }
    
    public void sortModulesABC() {
        this.sortedModulesABC = new ArrayList<String>(this.getEnabledModulesName());
        this.sortedModulesABC.sort(String.CASE_INSENSITIVE_ORDER);
    }
    
    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }
    
    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }
    
    public void disableModule(final String s) {
        final Module moduleByName = this.getModuleByName(s);
        if (moduleByName != null) {
            moduleByName.disable();
        }
    }
    
    public void enableModule(final String s) {
        final Module moduleByName = this.getModuleByName(s);
        if (moduleByName != null) {
            moduleByName.enable();
        }
    }
    
    public void enableModule(final Class<Module> clazz) {
        final Module moduleByClass = this.getModuleByClass(clazz);
        if (moduleByClass != null) {
            moduleByClass.enable();
        }
    }
    
    public ArrayList<String> getEnabledModulesName() {
        final ArrayList<String> list = new ArrayList<String>();
        for (final Module module : this.modules) {
            if (module.isEnabled()) {
                if (!module.isDrawn()) {
                    continue;
                }
                list.add(module.getFullArrayString());
            }
        }
        return list;
    }
    
    public boolean isModuleEnabled(final Class<Module> clazz) {
        final Module moduleByClass = this.getModuleByClass(clazz);
        return moduleByClass != null && moduleByClass.isOn();
    }
    
    public ModuleManager() {
        this.modules = new ArrayList<Module>();
        this.sortedModules = new ArrayList<Module>();
        this.sortedModulesABC = new ArrayList<String>();
    }
    
    public void init() {
        this.modules.add((Module)new ClickGui());
        this.modules.add((Module)new FontMod());
        this.modules.add((Module)new HUD());
        this.modules.add((Module)new Capes());
        this.modules.add((Module)new WaterMark());
        this.modules.add((Module)new Offhand());
        this.modules.add((Module)new Surround());
        this.modules.add((Module)new AutoTrap());
        this.modules.add((Module)new AutoCity());
        this.modules.add((Module)new AutoCev());
        this.modules.add((Module)new AutoTotem());
        this.modules.add((Module)new AutoWeb());
        this.modules.add((Module)new AntiBurrow());
        this.modules.add((Module)new AntiPiston());
        this.modules.add((Module)new AntiCity());
        this.modules.add((Module)new AutoCrystal());
        this.modules.add((Module)new HFAura());
        this.modules.add((Module)new BedAura());
        this.modules.add((Module)new Killaura());
        this.modules.add((Module)new Criticals());
        this.modules.add((Module)new HoleFiller());
        this.modules.add((Module)new AutoArmor());
        this.modules.add((Module)new Selftrap());
        this.modules.add((Module)new FastBow());
        this.modules.add((Module)new Flatten());
        this.modules.add((Module)new BowAim());
        this.modules.add((Module)new Quiver());
        this.modules.add((Module)new AntiCev());
        this.modules.add((Module)new TrapHead());
        this.modules.add((Module)new TrapSelf());
        this.modules.add((Module)new PistonCrystal());
        this.modules.add((Module)new Timer());
        this.modules.add((Module)new ShulkerViewer());
        this.modules.add((Module)new Interact());
        this.modules.add((Module)new MiddleFriend());
        this.modules.add((Module)new PopCounter());
        this.modules.add((Module)new OffhandCrash());
        this.modules.add((Module)new Message());
        this.modules.add((Module)new AntiPackets());
        this.modules.add((Module)new NoEntityTrace());
        this.modules.add((Module)new ChatSuffix());
        this.modules.add((Module)new AutoRespawn());
        this.modules.add((Module)new BowGod());
        this.modules.add((Module)new AntiWeb());
        this.modules.add((Module)new ChestStealer());
        this.modules.add((Module)new CrashSeries());
        this.modules.add((Module)new NoRotate());
        this.modules.add((Module)new Gamemode());
        this.modules.add((Module)new AntiCrash());
        this.modules.add((Module)new Spammer());
        this.modules.add((Module)new NoteBot());
        this.modules.add((Module)new SkinFlicker());
        this.modules.add((Module)new AutoReconnect());
        this.modules.add((Module)new VillagerNotifier());
        this.modules.add((Module)new BlockHighlight());
        this.modules.add((Module)new BreakingESP());
        this.modules.add((Module)new HoleESP());
        this.modules.add((Module)new Skeleton());
        this.modules.add((Module)new Trajectories());
        this.modules.add((Module)new NoRender());
        this.modules.add((Module)new NameTags());
        this.modules.add((Module)new Particles());
        this.modules.add((Module)new ESP());
        this.modules.add((Module)new ItemPhysics());
        this.modules.add((Module)new Fullbright());
        this.modules.add((Module)new CameraClip());
        this.modules.add((Module)new LogoutSpots());
        this.modules.add((Module)new PopChams());
        this.modules.add((Module)new BreadCrumbs());
        this.modules.add((Module)new SkyColor());
        this.modules.add((Module)new ViewModel());
        this.modules.add((Module)new ChestESP());
        this.modules.add((Module)new Chams());
        this.modules.add((Module)new PortalESP());
        this.modules.add((Module)new BurrowESP());
        this.modules.add((Module)new CityRender());
        this.modules.add((Module)new Target());
        this.modules.add((Module)new Ranges());
        this.modules.add((Module)new ExplosionChams());
        this.modules.add((Module)new CrystalScale());
        this.modules.add((Module)new Replenish());
        this.modules.add((Module)new FakePlayer());
        this.modules.add((Module)new TpsSync());
        this.modules.add((Module)new MultiTask());
        this.modules.add((Module)new Speedmine());
        this.modules.add((Module)new FastPlace());
        this.modules.add((Module)new InstantMine());
        this.modules.add((Module)new Phase());
        this.modules.add((Module)new Reach());
        this.modules.add((Module)new PortalGodMode());
        this.modules.add((Module)new AntiHunger());
        this.modules.add((Module)new XCarry());
        this.modules.add((Module)new Anti32k());
        this.modules.add((Module)new AutoXP());
        this.modules.add((Module)new AutoEat());
        this.modules.add((Module)new Blink());
        this.modules.add((Module)new Burrow());
        this.modules.add((Module)new PacketEat());
        this.modules.add((Module)new TickShift());
        this.modules.add((Module)new Swing());
        this.modules.add((Module)new tp());
        this.modules.add((Module)new BlockTweaks());
        this.modules.add((Module)new AntiContainer());
        this.modules.add((Module)new AntiShulkerBox());
        this.modules.add((Module)new AutoDupe());
        this.modules.add((Module)new AutoBuilder());
        this.modules.add((Module)new Strafe());
        this.modules.add((Module)new Step());
        this.modules.add((Module)new Flight());
        this.modules.add((Module)new Scaffold());
        this.modules.add((Module)new ReverseStep());
        this.modules.add((Module)new AntiLevitate());
        this.modules.add((Module)new AutoWalk());
        this.modules.add((Module)new BoatFly());
        this.modules.add((Module)new ElytraFlight());
        this.modules.add((Module)new EntityControl());
        this.modules.add((Module)new NoFall());
        this.modules.add((Module)new PlayerTweaks());
        this.modules.add((Module)new Sprint());
        this.modules.add((Module)new HoleTP());
        this.modules.add((Module)new AirJump());
    }
    
    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(MinecraftForge.EVENT_BUS::register);
        this.modules.forEach(Module::onLoad);
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(render3DEvent));
    }
    
    public <T extends Module> T getModuleByClass(final Class<T> clazz) {
        for (final Module module : this.modules) {
            if (!clazz.isInstance(module)) {
                continue;
            }
            return (T)module;
        }
        return null;
    }
    
    public Module getModuleByName(final String anotherString) {
        for (final Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(render2DEvent));
    }
    
    public ArrayList<Module> getEnabledModules() {
        final ArrayList<Module> list = new ArrayList<Module>();
        for (final Module e : this.modules) {
            if (!e.isEnabled()) {
                continue;
            }
            list.add(e);
        }
        return list;
    }
    
    public void onUnload() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }
    
    public Module getModuleByDisplayName(final String anotherString) {
        for (final Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    private class Animation extends Thread
    {
        /* synthetic */ ScheduledExecutorService service;
        
        public Animation() {
            super("Animation");
            this.service = Executors.newSingleThreadScheduledExecutor();
        }
        
        @Override
        public void start() {
            System.out.println("Starting animation thread.");
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
        
        @Override
        public void run() {
            if (HUD.getInstance().renderingMode.getValue() == HUD.RenderingMode.Length) {
                for (final Module module : ModuleManager.this.sortedModules) {
                    module.offset = ModuleManager.this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(module.getDisplayName()).append(ChatFormatting.GRAY).append((module.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [").append(ChatFormatting.WHITE).append(module.getDisplayInfo()).append(ChatFormatting.GRAY).append("]")) : ""))) / 1.0f;
                    module.vOffset = ModuleManager.this.renderer.getFontHeight() / 1.0f;
                    if (module.isEnabled()) {}
                    if (module.isDisabled()) {
                        continue;
                    }
                }
            }
            else {
                final Iterator<String> iterator2 = ModuleManager.this.sortedModulesABC.iterator();
                while (iterator2.hasNext()) {
                    final Module moduleByName = OyVey.moduleManager.getModuleByName(iterator2.next());
                    moduleByName.offset = ModuleManager.this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(moduleByName.getDisplayName()).append(ChatFormatting.GRAY).append((moduleByName.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [").append(ChatFormatting.WHITE).append(moduleByName.getDisplayInfo()).append(ChatFormatting.GRAY).append("]")) : ""))) / 1.0f;
                    moduleByName.vOffset = ModuleManager.this.renderer.getFontHeight() / 1.0f;
                    if (moduleByName.isEnabled()) {}
                    if (moduleByName.isDisabled()) {
                        continue;
                    }
                }
            }
        }
    }
}
