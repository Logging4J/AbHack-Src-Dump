//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.tools.alt;

import net.minecraft.client.*;
import me.abHack.features.gui.alts.iasencrypt.*;
import me.abHack.features.gui.alts.ias.account.*;
import com.mojang.util.*;
import net.minecraft.util.*;
import me.abHack.features.gui.alts.*;
import me.abHack.features.gui.alts.ias.config.*;
import java.util.*;
import com.mojang.authlib.yggdrasil.*;
import com.mojang.authlib.*;

public class AltManager
{
    private static /* synthetic */ AltManager manager;
    private final /* synthetic */ UserAuthentication auth;
    
    public Throwable setUser(final String s, final String anObject) {
        Object o = null;
        if (!Minecraft.getMinecraft().getSession().getUsername().equals(EncryptionTools.decode(s)) || Minecraft.getMinecraft().getSession().getToken().equals("0")) {
            if (!Minecraft.getMinecraft().getSession().getToken().equals("0")) {
                for (final AccountData accountData : AltDatabase.getInstance().getAlts()) {
                    if (accountData.alias.equals(Minecraft.getMinecraft().getSession().getUsername())) {
                        if (!accountData.user.equals(s)) {
                            continue;
                        }
                        return (Throwable)new AlreadyLoggedInException();
                    }
                }
            }
            this.auth.logOut();
            this.auth.setUsername(EncryptionTools.decode(s));
            this.auth.setPassword(EncryptionTools.decode(anObject));
            try {
                this.auth.logIn();
                final Session session = new Session(this.auth.getSelectedProfile().getName(), UUIDTypeAdapter.fromUUID(this.auth.getSelectedProfile().getId()), this.auth.getAuthenticatedToken(), this.auth.getUserType().getName());
                MR.setSession(session);
                for (int i = 0; i < AltDatabase.getInstance().getAlts().size(); ++i) {
                    final AccountData accountData2 = AltDatabase.getInstance().getAlts().get(i);
                    if (accountData2.user.equals(s)) {
                        if (accountData2.pass.equals(anObject)) {
                            accountData2.alias = session.getUsername();
                        }
                    }
                }
            }
            catch (Exception ex) {
                o = ex;
            }
        }
        else if (!ConfigValues.ENABLERELOG) {
            o = new AlreadyLoggedInException();
        }
        return (Throwable)o;
    }
    
    public void setUserOffline(final String s) {
        this.auth.logOut();
        final Session session = new Session(s, s, "0", "legacy");
        try {
            MR.setSession(session);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private AltManager() {
        final YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Minecraft.getMinecraft().getProxy(), UUID.randomUUID().toString());
        this.auth = yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
        yggdrasilAuthenticationService.createMinecraftSessionService();
    }
    
    public static AltManager getInstance() {
        if (AltManager.manager == null) {
            AltManager.manager = new AltManager();
        }
        return AltManager.manager;
    }
}
