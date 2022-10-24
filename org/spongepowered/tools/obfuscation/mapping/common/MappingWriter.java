//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mapping.common;

import org.spongepowered.tools.obfuscation.mapping.*;
import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.tools.*;
import java.io.*;

public abstract class MappingWriter implements IMappingWriter
{
    private final Messager messager;
    private final Filer filer;
    
    public MappingWriter(final Messager messager, final Filer filer) {
        this.messager = messager;
        this.filer = filer;
    }
    
    protected PrintWriter openFileWriter(final String pathname, final String s) throws IOException {
        if (pathname.matches("^.*[\\\\/:].*$")) {
            final File file = new File(pathname);
            file.getParentFile().mkdirs();
            this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + s + " to " + file.getAbsolutePath());
            return new PrintWriter(file);
        }
        final FileObject resource = this.filer.createResource(StandardLocation.CLASS_OUTPUT, "", pathname, new Element[0]);
        this.messager.printMessage(Diagnostic.Kind.NOTE, "Writing " + s + " to " + new File(resource.toUri()).getAbsolutePath());
        return new PrintWriter(resource.openWriter());
    }
}
