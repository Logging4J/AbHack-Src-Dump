//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.iasencrypt;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;

public final class EncryptionTools
{
    private static final /* synthetic */ Base64.Decoder decoder;
    private static final /* synthetic */ MessageDigest sha512;
    private static final /* synthetic */ KeyGenerator keyGen;
    private static final /* synthetic */ Base64.Encoder encoder;
    
    public static String encode(final String s) {
        try {
            final byte[] bytes = s.getBytes("UTF-8");
            final Cipher instance = Cipher.getInstance("AES");
            instance.init(1, getSecretKey());
            return new String(EncryptionTools.encoder.encode(instance.doFinal(bytes)));
        }
        catch (BadPaddingException cause) {
            throw new RuntimeException("The password does not match", cause);
        }
        catch (IOException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            final Object cause2;
            throw new RuntimeException((Throwable)cause2);
        }
    }
    
    public static String decodeOld(final String src) {
        try {
            return new String(EncryptionTools.decoder.decode(src), "UTF-8");
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static String generatePassword() {
        EncryptionTools.keyGen.init(256);
        return new String(EncryptionTools.encoder.encode(EncryptionTools.keyGen.generateKey().getEncoded()));
    }
    
    private static SecretKeySpec getSecretKey() {
        try {
            return new SecretKeySpec(Arrays.copyOf(EncryptionTools.sha512.digest(String.valueOf(new StringBuilder().append("${secretSalt}").append(Standards.getPassword()).append("${secretSalt}")).getBytes("UTF-8")), 16), "AES");
        }
        catch (UnsupportedEncodingException cause) {
            throw new RuntimeException(cause);
        }
    }
    
    private static KeyGenerator getAESGenerator() {
        try {
            return KeyGenerator.getInstance("AES");
        }
        catch (NoSuchAlgorithmException cause) {
            throw new RuntimeException(cause);
        }
    }
    
    public static String decode(final String src) {
        try {
            final byte[] decode = EncryptionTools.decoder.decode(src);
            final Cipher instance = Cipher.getInstance("AES");
            instance.init(2, getSecretKey());
            return new String(instance.doFinal(decode), "UTF-8");
        }
        catch (BadPaddingException cause) {
            throw new RuntimeException("The password does not match", cause);
        }
        catch (IOException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            final Object cause2;
            throw new RuntimeException((Throwable)cause2);
        }
    }
    
    static {
        secretSalt = "${secretSalt}";
        DEFAULT_ENCODING = "UTF-8";
        encoder = Base64.getEncoder();
        decoder = Base64.getDecoder();
        sha512 = getSha512Hasher();
        keyGen = getAESGenerator();
    }
    
    private static MessageDigest getSha512Hasher() {
        try {
            return MessageDigest.getInstance("SHA-512");
        }
        catch (NoSuchAlgorithmException cause) {
            throw new RuntimeException(cause);
        }
    }
}
