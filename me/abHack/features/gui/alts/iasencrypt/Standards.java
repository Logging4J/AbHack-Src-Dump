//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.iasencrypt;

import me.abHack.features.gui.alts.tools.*;
import java.io.*;
import java.nio.file.attribute.*;
import java.nio.file.*;
import net.minecraft.client.*;
import me.abHack.features.gui.alts.tools.alt.*;
import java.util.*;
import me.abHack.features.gui.alts.ias.account.*;

public final class Standards
{
    public static /* synthetic */ File IASFOLDER;
    
    private static Config getConfigV3() {
        final File file = new File(Standards.IASFOLDER, ".ias");
        Config config = null;
        if (file.exists()) {
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                config = (Config)objectInputStream.readObject();
                objectInputStream.close();
            }
            catch (IOException | ClassNotFoundException ex) {
                final Throwable t;
                t.printStackTrace();
            }
            file.delete();
        }
        return config;
    }
    
    public static void importAccounts() {
        processData(getConfigV3());
        processData(getConfigV2());
        processData(getConfigV1(), false);
    }
    
    public static String getPassword() {
        final File file = new File(Standards.IASFOLDER, ".iasp");
        if (file.exists()) {
            String s;
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                s = (String)objectInputStream.readObject();
                objectInputStream.close();
            }
            catch (IOException | ClassNotFoundException ex2) {
                final Object cause;
                throw new RuntimeException((Throwable)cause);
            }
            return s;
        }
        final String generatePassword = EncryptionTools.generatePassword();
        try {
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(generatePassword);
            objectOutputStream.close();
        }
        catch (IOException cause2) {
            throw new RuntimeException(cause2);
        }
        try {
            final Path path = file.toPath();
            final DosFileAttributes dosFileAttributes = Files.readAttributes(path, DosFileAttributes.class, new LinkOption[0]);
            final DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class, new LinkOption[0]);
            if (!dosFileAttributes.isHidden()) {
                dosFileAttributeView.setHidden(true);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return generatePassword;
    }
    
    static {
        cfgn = ".iasx";
        pwdn = ".iasp";
        Standards.IASFOLDER = Minecraft.getMinecraft().gameDir;
    }
    
    private static Config getConfigV1() {
        final File file = new File(Minecraft.getMinecraft().gameDir, "user.cfg");
        Config config = null;
        if (file.exists()) {
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                config = (Config)objectInputStream.readObject();
                objectInputStream.close();
            }
            catch (IOException | ClassNotFoundException ex) {
                final Throwable t;
                t.printStackTrace();
            }
            file.delete();
        }
        return config;
    }
    
    private static Config getConfigV2() {
        final File file = new File(Minecraft.getMinecraft().gameDir, ".ias");
        Config config = null;
        if (file.exists()) {
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                config = (Config)objectInputStream.readObject();
                objectInputStream.close();
            }
            catch (IOException | ClassNotFoundException ex) {
                final Throwable t;
                t.printStackTrace();
            }
            file.delete();
        }
        return config;
    }
    
    private static void processData(final Config config, final boolean b) {
        if (config != null) {
            final Iterator<AccountData> iterator = ((AltDatabase)config.getKey("altaccounts")).getAlts().iterator();
            while (iterator.hasNext()) {
                final ExtendedAccountData convertData = convertData(iterator.next(), b);
                if (hasData((AccountData)convertData)) {
                    continue;
                }
                AltDatabase.getInstance().getAlts().add((AccountData)convertData);
            }
        }
    }
    
    private static boolean hasData(final AccountData accountData) {
        final Iterator<AccountData> iterator = AltDatabase.getInstance().getAlts().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().equalsBasic(accountData)) {
                continue;
            }
            return true;
        }
        return false;
    }
    
    private static void processData(final Config config) {
        processData(config, true);
    }
    
    private static ExtendedAccountData convertData(final AccountData accountData, final boolean b) {
        if (b) {
            if (accountData instanceof ExtendedAccountData) {
                return new ExtendedAccountData(EncryptionTools.decodeOld(accountData.user), EncryptionTools.decodeOld(accountData.pass), accountData.alias, ((ExtendedAccountData)accountData).useCount, ((ExtendedAccountData)accountData).lastused, ((ExtendedAccountData)accountData).premium);
            }
            return new ExtendedAccountData(EncryptionTools.decodeOld(accountData.user), EncryptionTools.decodeOld(accountData.pass), accountData.alias);
        }
        else {
            if (accountData instanceof ExtendedAccountData) {
                return new ExtendedAccountData(accountData.user, accountData.pass, accountData.alias, ((ExtendedAccountData)accountData).useCount, ((ExtendedAccountData)accountData).lastused, ((ExtendedAccountData)accountData).premium);
            }
            return new ExtendedAccountData(accountData.user, accountData.pass, accountData.alias);
        }
    }
    
    public static void updateFolder() {
        final String upperCase = System.getProperty("os.name").toUpperCase();
        String s;
        if (upperCase.contains("WIN")) {
            s = System.getenv("AppData");
        }
        else {
            s = System.getProperty("user.home");
            if (upperCase.contains("MAC")) {
                s = String.valueOf(new StringBuilder().append(s).append("/Library/Application Support"));
            }
        }
        Standards.IASFOLDER = new File(s);
    }
}
