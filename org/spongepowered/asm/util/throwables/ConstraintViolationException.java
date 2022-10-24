//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util.throwables;

import org.spongepowered.asm.util.*;

public class ConstraintViolationException extends Exception
{
    private static final String MISSING_VALUE = "UNRESOLVED";
    private static final long serialVersionUID = 1L;
    private final ConstraintParser.Constraint constraint;
    private final String badValue;
    
    public ConstraintViolationException(final ConstraintParser.Constraint constraint) {
        this.constraint = constraint;
        this.badValue = "UNRESOLVED";
    }
    
    public ConstraintViolationException(final ConstraintParser.Constraint constraint, final int i) {
        this.constraint = constraint;
        this.badValue = String.valueOf(i);
    }
    
    public ConstraintViolationException(final String message, final ConstraintParser.Constraint constraint) {
        super(message);
        this.constraint = constraint;
        this.badValue = "UNRESOLVED";
    }
    
    public ConstraintViolationException(final String message, final ConstraintParser.Constraint constraint, final int i) {
        super(message);
        this.constraint = constraint;
        this.badValue = String.valueOf(i);
    }
    
    public ConstraintViolationException(final Throwable cause, final ConstraintParser.Constraint constraint) {
        super(cause);
        this.constraint = constraint;
        this.badValue = "UNRESOLVED";
    }
    
    public ConstraintViolationException(final Throwable cause, final ConstraintParser.Constraint constraint, final int i) {
        super(cause);
        this.constraint = constraint;
        this.badValue = String.valueOf(i);
    }
    
    public ConstraintViolationException(final String message, final Throwable cause, final ConstraintParser.Constraint constraint) {
        super(message, cause);
        this.constraint = constraint;
        this.badValue = "UNRESOLVED";
    }
    
    public ConstraintViolationException(final String message, final Throwable cause, final ConstraintParser.Constraint constraint, final int i) {
        super(message, cause);
        this.constraint = constraint;
        this.badValue = String.valueOf(i);
    }
    
    public ConstraintParser.Constraint getConstraint() {
        return this.constraint;
    }
    
    public String getBadValue() {
        return this.badValue;
    }
}
