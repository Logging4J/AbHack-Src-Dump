//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib;

final class Item
{
    int index;
    int type;
    int intVal;
    long longVal;
    String strVal1;
    String strVal2;
    String strVal3;
    int hashCode;
    Item next;
    
    Item() {
    }
    
    Item(final int index) {
        this.index = index;
    }
    
    Item(final int index, final Item item) {
        this.index = index;
        this.type = item.type;
        this.intVal = item.intVal;
        this.longVal = item.longVal;
        this.strVal1 = item.strVal1;
        this.strVal2 = item.strVal2;
        this.strVal3 = item.strVal3;
        this.hashCode = item.hashCode;
    }
    
    void set(final int intVal) {
        this.type = 3;
        this.intVal = intVal;
        this.hashCode = (Integer.MAX_VALUE & this.type + intVal);
    }
    
    void set(final long longVal) {
        this.type = 5;
        this.longVal = longVal;
        this.hashCode = (Integer.MAX_VALUE & this.type + (int)longVal);
    }
    
    void set(final float n) {
        this.type = 4;
        this.intVal = Float.floatToRawIntBits(n);
        this.hashCode = (Integer.MAX_VALUE & this.type + (int)n);
    }
    
    void set(final double n) {
        this.type = 6;
        this.longVal = Double.doubleToRawLongBits(n);
        this.hashCode = (Integer.MAX_VALUE & this.type + (int)n);
    }
    
    void set(final int type, final String strVal1, final String strVal2, final String strVal3) {
        this.type = type;
        this.strVal1 = strVal1;
        this.strVal2 = strVal2;
        this.strVal3 = strVal3;
        switch (type) {
            case 7: {
                this.intVal = 0;
            }
            case 1:
            case 8:
            case 16:
            case 30: {
                this.hashCode = (Integer.MAX_VALUE & type + strVal1.hashCode());
            }
            case 12: {
                this.hashCode = (Integer.MAX_VALUE & type + strVal1.hashCode() * strVal2.hashCode());
            }
            default: {
                this.hashCode = (Integer.MAX_VALUE & type + strVal1.hashCode() * strVal2.hashCode() * strVal3.hashCode());
            }
        }
    }
    
    void set(final String strVal1, final String strVal2, final int n) {
        this.type = 18;
        this.longVal = n;
        this.strVal1 = strVal1;
        this.strVal2 = strVal2;
        this.hashCode = (Integer.MAX_VALUE & 18 + n * this.strVal1.hashCode() * this.strVal2.hashCode());
    }
    
    void set(final int intVal, final int hashCode) {
        this.type = 33;
        this.intVal = intVal;
        this.hashCode = hashCode;
    }
    
    boolean isEqualTo(final Item item) {
        switch (this.type) {
            case 1:
            case 7:
            case 8:
            case 16:
            case 30: {
                return item.strVal1.equals(this.strVal1);
            }
            case 5:
            case 6:
            case 32: {
                return item.longVal == this.longVal;
            }
            case 3:
            case 4: {
                return item.intVal == this.intVal;
            }
            case 31: {
                return item.intVal == this.intVal && item.strVal1.equals(this.strVal1);
            }
            case 12: {
                return item.strVal1.equals(this.strVal1) && item.strVal2.equals(this.strVal2);
            }
            case 18: {
                return item.longVal == this.longVal && item.strVal1.equals(this.strVal1) && item.strVal2.equals(this.strVal2);
            }
            default: {
                return item.strVal1.equals(this.strVal1) && item.strVal2.equals(this.strVal2) && item.strVal3.equals(this.strVal3);
            }
        }
    }
}
