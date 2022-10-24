//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import java.util.*;
import com.mojang.realmsclient.gui.*;
import java.util.regex.*;

public class TextUtil
{
    public static String coloredString(final String s, final Color color) {
        String s2 = s;
        switch (color) {
            case AQUA: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.AQUA).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case WHITE: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case BLACK: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.BLACK).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case DARK_BLUE: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.DARK_BLUE).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case DARK_GREEN: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.DARK_GREEN).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case DARK_AQUA: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.DARK_AQUA).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case DARK_RED: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case DARK_PURPLE: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.DARK_PURPLE).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case GOLD: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.GOLD).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case DARK_GRAY: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.DARK_GRAY).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case GRAY: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case BLUE: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.BLUE).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case RED: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case GREEN: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case LIGHT_PURPLE: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.LIGHT_PURPLE).append(s2).append(ChatFormatting.RESET));
                break;
            }
            case YELLOW: {
                s2 = String.valueOf(new StringBuilder().append(ChatFormatting.YELLOW).append(s2).append(ChatFormatting.RESET));
                break;
            }
        }
        return s2;
    }
    
    static {
        BLACK = String.valueOf(ChatFormatting.BLACK);
        DARK_BLUE = String.valueOf(ChatFormatting.DARK_BLUE);
        DARK_GREEN = String.valueOf(ChatFormatting.DARK_GREEN);
        DARK_AQUA = String.valueOf(ChatFormatting.DARK_AQUA);
        DARK_RED = String.valueOf(ChatFormatting.DARK_RED);
        DARK_PURPLE = String.valueOf(ChatFormatting.DARK_PURPLE);
        GOLD = String.valueOf(ChatFormatting.GOLD);
        GRAY = String.valueOf(ChatFormatting.GRAY);
        DARK_GRAY = String.valueOf(ChatFormatting.DARK_GRAY);
        BLUE = String.valueOf(ChatFormatting.BLUE);
        GREEN = String.valueOf(ChatFormatting.GREEN);
        AQUA = String.valueOf(ChatFormatting.AQUA);
        RED = String.valueOf(ChatFormatting.RED);
        LIGHT_PURPLE = String.valueOf(ChatFormatting.LIGHT_PURPLE);
        YELLOW = String.valueOf(ChatFormatting.YELLOW);
        WHITE = String.valueOf(ChatFormatting.WHITE);
        OBFUSCATED = String.valueOf(ChatFormatting.OBFUSCATED);
        BOLD = String.valueOf(ChatFormatting.BOLD);
        STRIKE = String.valueOf(ChatFormatting.STRIKETHROUGH);
        UNDERLINE = String.valueOf(ChatFormatting.UNDERLINE);
        ITALIC = String.valueOf(ChatFormatting.ITALIC);
        RESET = String.valueOf(ChatFormatting.RESET);
        rand = new Random();
    }
    
    public static String cropMaxLengthMessage(final String s, final int n) {
        String substring = "";
        if (s.length() >= 256 - n) {
            substring = s.substring(0, 256 - n);
        }
        return substring;
    }
    
    public static String stripColor(final String input) {
        if (input != null) {
            return Pattern.compile("(?i)§[0-9A-FK-OR]").matcher(input).replaceAll("");
        }
        return "";
    }
    
    public enum Color
    {
        BLACK, 
        DARK_AQUA, 
        DARK_PURPLE, 
        GRAY, 
        NONE, 
        YELLOW, 
        DARK_GRAY, 
        AQUA, 
        WHITE, 
        DARK_BLUE, 
        RED, 
        BLUE, 
        DARK_RED, 
        DARK_GREEN, 
        GREEN, 
        GOLD, 
        LIGHT_PURPLE;
    }
}
