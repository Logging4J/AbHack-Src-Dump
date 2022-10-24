//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import java.nio.file.attribute.*;
import me.abHack.*;
import me.abHack.features.modules.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;
import java.io.*;
import java.util.stream.*;
import java.nio.file.*;

public class FileManager extends Feature
{
    private final /* synthetic */ Path notebot;
    private final /* synthetic */ Path base;
    private final /* synthetic */ Path config;
    
    private String[] expandPath(final String s) {
        return s.split(":?\\\\\\\\|\\/");
    }
    
    private void createDirectory(final Path path) {
        try {
            if (!Files.isDirectory(path, new LinkOption[0])) {
                if (Files.exists(path, new LinkOption[0])) {
                    Files.delete(path);
                }
                Files.createDirectories(path, (FileAttribute<?>[])new FileAttribute[0]);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Path getConfig() {
        return this.getBasePath().resolve("config");
    }
    
    public FileManager() {
        this.base = this.getMkDirectory(this.getRoot(), "ab-Hack");
        this.config = this.getMkDirectory(this.base, "config");
        this.notebot = this.getMkDirectory(this.base, "notebot");
        final Iterator<Module.Category> iterator = OyVey.moduleManager.getCategories().iterator();
        while (iterator.hasNext()) {
            this.getMkDirectory(this.config, iterator.next().getName());
        }
    }
    
    public Path getBaseResolve(final String... array) {
        final String[] array2 = this.expandPaths(array).toArray(String[]::new);
        if (array2.length < 1) {
            throw new IllegalArgumentException("missing path");
        }
        return this.lookupPath(this.getBasePath(), array2);
    }
    
    private Path lookupPath(final Path path, final String... more) {
        return Paths.get(path.toString(), more);
    }
    
    public static List<String> readTextFileAllLines(final String s) {
        try {
            return Files.readAllLines(Paths.get(s, new String[0]), StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            System.out.println(String.valueOf(new StringBuilder().append("WARNING: Unable to read file, creating new file: ").append(s)));
            appendTextFile("", s);
            return Collections.emptyList();
        }
    }
    
    private Stream<String> expandPaths(final String... array) {
        return Arrays.stream(array).map((Function<? super String, ?>)this::expandPath).flatMap((Function<? super Object, ? extends Stream<? extends String>>)Arrays::stream);
    }
    
    public Path getMkConfigDirectory(final String... array) {
        return this.getMkDirectory(this.getConfig(), this.expandPaths(array).collect(Collectors.joining(File.separator)));
    }
    
    public Path getMkBaseDirectory(final String... array) {
        return this.getMkDirectory(this.getBasePath(), this.expandPaths(array).collect(Collectors.joining(File.separator)));
    }
    
    public static boolean appendTextFile(final String o, final String s) {
        try {
            final Path value = Paths.get(s, new String[0]);
            Files.write(value, Collections.singletonList(o), StandardCharsets.UTF_8, Files.exists(value, new LinkOption[0]) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        }
        catch (IOException ex) {
            System.out.println(String.valueOf(new StringBuilder().append("WARNING: Unable to write file: ").append(s)));
            return false;
        }
        return true;
    }
    
    public Path getBasePath() {
        return this.base;
    }
    
    public Path getCache() {
        return this.getBasePath().resolve("cache");
    }
    
    private Path getRoot() {
        return Paths.get("", new String[0]);
    }
    
    public Path getNotebot() {
        return this.getBasePath().resolve("notebot");
    }
    
    public Path getMkBaseResolve(final String... array) {
        final Path baseResolve = this.getBaseResolve(array);
        this.createDirectory(baseResolve.getParent());
        return baseResolve;
    }
    
    private Path getMkDirectory(final Path path, final String... array) {
        if (array.length < 1) {
            return path;
        }
        final Path lookupPath = this.lookupPath(path, array);
        this.createDirectory(lookupPath);
        return lookupPath;
    }
}
