//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import java.awt.datatransfer.*;
import java.awt.*;
import javax.swing.*;

public class DisplayUtil
{
    public static void Display() {
        new Frame().setVisible(false);
        throw new NoStackTraceThrowable("Verification was unsuccessful!");
    }
    
    public static class Frame extends JFrame
    {
        public static void copyToClipboard() {
            final StringSelection stringSelection = new StringSelection(SystemUtil.getSystemInfo());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
        }
        
        public Frame() {
            this.setTitle("Verification failed.");
            this.setDefaultCloseOperation(2);
            this.setLocationRelativeTo(null);
            copyToClipboard();
            JOptionPane.showMessageDialog(this, String.valueOf(new StringBuilder().append("Sorry, you are not on the HWID list.\nHWID: ").append(SystemUtil.getSystemInfo()).append("\n(Copied to clipboard.)")), "Could not verify your HWID successfully.", -1, UIManager.getIcon("OptionPane.errorIcon"));
        }
    }
}
