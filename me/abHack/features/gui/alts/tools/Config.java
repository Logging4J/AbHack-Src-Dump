//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.tools;

import me.abHack.features.gui.alts.tools.alt.*;
import me.abHack.features.gui.alts.iasencrypt.*;
import java.nio.file.attribute.*;
import java.nio.file.*;
import java.io.*;
import net.minecraft.client.*;
import java.util.*;

public class Config implements Serializable
{
    private static /* synthetic */ Config instance;
    private final /* synthetic */ ArrayList<Pair<String, Object>> field_218893_c;
    
    static {
        configFileName = ".iasx";
    }
    
    public static void load() {
        loadFromOld();
        readFromFile();
    }
    
    public static Config getInstance() {
        return Config.instance;
    }
    
    public void setKey(final String s, final AltDatabase altDatabase) {
        this.setKey(new Pair<String, Object>(s, altDatabase));
    }
    
    private static void saveToFile() {
        try {
            final Path path = new File(Standards.IASFOLDER, ".iasx").toPath();
            final DosFileAttributes dosFileAttributes = Files.readAttributes(path, DosFileAttributes.class, new LinkOption[0]);
            final DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class, new LinkOption[0]);
            if (dosFileAttributes.isHidden()) {
                dosFileAttributeView.setHidden(false);
            }
        }
        catch (NoSuchFileException ex4) {}
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(Standards.IASFOLDER, ".iasx")));
            objectOutputStream.writeObject(Config.instance);
            objectOutputStream.close();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        try {
            final Path path2 = new File(Standards.IASFOLDER, ".iasx").toPath();
            final DosFileAttributes dosFileAttributes2 = Files.readAttributes(path2, DosFileAttributes.class, new LinkOption[0]);
            final DosFileAttributeView dosFileAttributeView2 = Files.getFileAttributeView(path2, DosFileAttributeView.class, new LinkOption[0]);
            if (!dosFileAttributes2.isHidden()) {
                dosFileAttributeView2.setHidden(true);
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    public static void save() {
        saveToFile();
    }
    
    private static void readFromFile() {
        final File file = new File(Standards.IASFOLDER, ".iasx");
        if (file.exists()) {
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                Config.instance = (Config)objectInputStream.readObject();
                objectInputStream.close();
            }
            catch (IOException | ClassNotFoundException ex) {
                final Throwable t;
                t.printStackTrace();
                Config.instance = new Config();
                file.delete();
            }
        }
        if (Config.instance == null) {
            Config.instance = new Config();
        }
    }
    
    private static void loadFromOld() {
        final File file = new File(Minecraft.getMinecraft().gameDir, "user.cfg");
        if (file.exists()) {
            try {
                final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                Config.instance = (Config)objectInputStream.readObject();
                objectInputStream.close();
                file.delete();
                System.out.println("Loaded data from old file");
            }
            catch (IOException | ClassNotFoundException ex) {
                final Throwable t;
                t.printStackTrace();
                file.delete();
            }
        }
    }
    
    public Object getKey(final String anObject) {
        for (final Pair<String, Object> pair : this.field_218893_c) {
            if (!pair.getValue1().equals(anObject)) {
                continue;
            }
            return pair.getValue2();
        }
        return null;
    }
    
    private Config() {
        this.field_218893_c = new ArrayList<Pair<String, Object>>();
        Config.instance = this;
    }
    
    public void setKey(final Pair<String, Object> e) {
        if (this.getKey(e.getValue1()) != null) {
            this.removeKey(e.getValue1());
        }
        this.field_218893_c.add(e);
        save();
    }
    
    private void removeKey(final String anObject) {
        for (int i = 0; i < this.field_218893_c.size(); ++i) {
            if (this.field_218893_c.get(i).getValue1().equals(anObject)) {
                this.field_218893_c.remove(i);
            }
        }
    }
}
