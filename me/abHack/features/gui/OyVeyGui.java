//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui;

import me.abHack.features.gui.components.*;
import me.abHack.features.gui.particle.*;
import me.abHack.features.modules.client.*;
import net.minecraft.client.gui.*;
import me.abHack.features.modules.*;
import me.abHack.features.gui.components.items.*;
import org.lwjgl.input.*;
import me.abHack.*;
import me.abHack.features.gui.components.items.buttons.*;
import me.abHack.features.*;
import java.util.*;
import java.util.function.*;
import java.io.*;

public class OyVeyGui extends GuiScreen
{
    private final /* synthetic */ ArrayList<Snow> _snowList;
    private static /* synthetic */ OyVeyGui INSTANCE;
    private static final /* synthetic */ DescriptionDisplay descriptionDisplay;
    private final /* synthetic */ ArrayList<Component> components;
    public /* synthetic */ ParticleSystem particleSystem;
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        this.components.forEach(component -> component.mouseReleased(n, n2, n3));
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        final ClickGui instance = ClickGui.getInstance();
        OyVeyGui.descriptionDisplay.setDraw(false);
        this.checkMouseWheel();
        this.drawDefaultBackground();
        this.components.forEach(component -> component.drawScreen(n, n2, n3));
        if (OyVeyGui.descriptionDisplay.shouldDraw() && instance.moduleDescription.getValue()) {
            OyVeyGui.descriptionDisplay.drawScreen(n, n2, n3);
        }
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        if (!this._snowList.isEmpty() && ClickGui.getInstance().snowing.getValue()) {
            this._snowList.forEach(snow -> snow.Update(scaledResolution));
        }
        if (this.particleSystem != null && ClickGui.getInstance().particles.getValue()) {
            this.particleSystem.render(n, n2);
        }
        else {
            this.particleSystem = new ParticleSystem(new ScaledResolution(this.mc));
        }
    }
    
    public void updateModule(final Module module) {
        final Iterator<Component> iterator = this.components.iterator();
        while (iterator.hasNext()) {
            for (final Item item : iterator.next().getItems()) {
                if (!(item instanceof ModuleButton)) {
                    continue;
                }
                final ModuleButton moduleButton = (ModuleButton)item;
                final Module module2 = moduleButton.getModule();
                if (module == null) {
                    continue;
                }
                if (!module.equals(module2)) {
                    continue;
                }
                moduleButton.initSettings();
            }
        }
    }
    
    public void checkMouseWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        }
        else if (dWheel > 0) {
            this.components.forEach(component2 -> component2.setY(component2.getY() + 10));
        }
    }
    
    public int getTextOffset() {
        return -6;
    }
    
    private void setInstance() {
        OyVeyGui.INSTANCE = this;
    }
    
    public DescriptionDisplay getDescriptionDisplay() {
        return OyVeyGui.descriptionDisplay;
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    static {
        OyVeyGui.INSTANCE = new OyVeyGui();
        descriptionDisplay = new DescriptionDisplay("", 0.0f, 0.0f);
    }
    
    public static OyVeyGui getInstance() {
        if (OyVeyGui.INSTANCE == null) {
            OyVeyGui.INSTANCE = new OyVeyGui();
        }
        return OyVeyGui.INSTANCE;
    }
    
    private void load() {
        int n = -84;
        final Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 3; ++j) {
                this._snowList.add(new Snow(25 * i, j * -50, random.nextInt(3) + 1, random.nextInt(2) + 1));
            }
        }
        for (final Module.Category category : OyVey.moduleManager.getCategories()) {
            final ArrayList<Component> components = this.components;
            final String name = category.getName();
            n += 90;
            components.add(new Component(name, n, 4, true) {
                public void setupItems() {
                    OyVeyGui$1.counter1 = new int[] { 1 };
                    OyVey.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton((Button)new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(component -> component.getItems().sort(Comparator.comparing((Function<? super E, ? extends Comparable>)Feature::getName)));
    }
    
    public static OyVeyGui getClickGui() {
        return getInstance();
    }
    
    public void keyTyped(final char c, final int n) throws IOException {
        super.keyTyped(c, n);
        this.components.forEach(component -> component.onKeyTyped(c, n));
    }
    
    public OyVeyGui() {
        this._snowList = new ArrayList<Snow>();
        this.components = new ArrayList<Component>();
        this.setInstance();
        this.load();
    }
    
    public Component getComponentByName(final String anotherString) {
        for (final Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return component;
        }
        return null;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        this.components.forEach(component -> component.mouseClicked(n, n2, n3));
    }
    
    public void updateScreen() {
        if (this.particleSystem != null) {
            this.particleSystem.update();
        }
    }
    
    public final ArrayList<Component> getComponents() {
        return this.components;
    }
}
