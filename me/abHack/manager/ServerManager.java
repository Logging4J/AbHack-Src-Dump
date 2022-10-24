//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import me.abHack.util.*;
import java.text.*;
import me.abHack.features.modules.client.*;
import net.minecraft.client.network.*;
import java.util.*;

public class ServerManager extends Feature
{
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ float[] tpsCounts;
    private final /* synthetic */ DecimalFormat format;
    private /* synthetic */ String serverBrand;
    private /* synthetic */ long lastUpdate;
    private /* synthetic */ float TPS;
    
    public long serverRespondingTime() {
        return this.timer.getPassedTimeMs();
    }
    
    public boolean isServerNotResponding() {
        return this.timer.passedMs((int)HUD.getInstance().lagTime.getValue());
    }
    
    public void onPacketReceived() {
        this.timer.reset();
    }
    
    public int getPing() {
        if (fullNullCheck()) {
            return 0;
        }
        try {
            return Objects.requireNonNull(ServerManager.mc.getConnection()).getPlayerInfo(ServerManager.mc.getConnection().getGameProfile().getId()).getResponseTime();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public void setServerBrand(final String serverBrand) {
        this.serverBrand = serverBrand;
    }
    
    public float getTPS() {
        return this.TPS;
    }
    
    public float getTpsFactor() {
        return 20.0f / this.TPS;
    }
    
    public ServerManager() {
        this.tpsCounts = new float[10];
        this.format = new DecimalFormat("##.00#");
        this.timer = new Timer();
        this.TPS = 20.0f;
        this.lastUpdate = -1L;
        this.serverBrand = "";
    }
    
    public void reset() {
        Arrays.fill(this.tpsCounts, 20.0f);
        this.TPS = 20.0f;
    }
    
    public void update() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.lastUpdate == -1L) {
            this.lastUpdate = currentTimeMillis;
            return;
        }
        float n = (currentTimeMillis - this.lastUpdate) / 20.0f;
        if (n == 0.0f) {
            n = 50.0f;
        }
        float n2;
        if ((n2 = 1000.0f / n) > 20.0f) {
            n2 = 20.0f;
        }
        System.arraycopy(this.tpsCounts, 0, this.tpsCounts, 1, this.tpsCounts.length - 1);
        this.tpsCounts[0] = n2;
        double n3 = 0.0;
        final float[] tpsCounts = this.tpsCounts;
        for (int length = tpsCounts.length, i = 0; i < length; ++i) {
            n3 += tpsCounts[i];
        }
        double number;
        if ((number = n3 / this.tpsCounts.length) > 20.0) {
            number = 20.0;
        }
        this.TPS = Float.parseFloat(this.format.format(number));
        this.lastUpdate = currentTimeMillis;
    }
    
    public String getServerBrand() {
        return this.serverBrand;
    }
}
