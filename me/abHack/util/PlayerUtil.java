//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import com.google.common.collect.*;
import com.google.gson.*;
import java.net.*;
import java.nio.charset.*;
import javax.net.ssl.*;
import net.minecraft.advancements.*;
import net.minecraft.util.math.*;
import org.apache.commons.io.*;
import java.io.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.network.*;
import java.util.*;
import me.abHack.features.command.*;
import com.mojang.util.*;

public class PlayerUtil implements Util
{
    private static final /* synthetic */ JsonParser PARSER;
    
    public static List<String> getHistoryOfNames(final UUID uuid) {
        try {
            final JsonArray asJsonArray = getResources(new URL(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(getIdNoHyphens(uuid)).append("/names"))), "GET").getAsJsonArray();
            final ArrayList arrayList = Lists.newArrayList();
            final Iterator iterator = asJsonArray.iterator();
            while (iterator.hasNext()) {
                final JsonObject asJsonObject = iterator.next().getAsJsonObject();
                arrayList.add(String.valueOf(new StringBuilder().append(asJsonObject.get("name").getAsString()).append("\u00c2§8").append(new Date(asJsonObject.has("changedToAt") ? asJsonObject.get("changedToAt").getAsLong() : 0L))));
            }
            Collections.sort(arrayList);
            return (List<String>)arrayList;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getNameFromUUID(final String s) {
        try {
            final lookUpName target = new lookUpName(s);
            final Thread thread = new Thread(target);
            thread.start();
            thread.join();
            return target.getName();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String requestIDs(final String s) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://api.mojang.com/profiles/minecraft").openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            final OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            final String convertStreamToString = convertStreamToString(bufferedInputStream);
            bufferedInputStream.close();
            httpURLConnection.disconnect();
            return convertStreamToString;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getNameFromUUID(final UUID uuid) {
        try {
            final lookUpName target = new lookUpName(uuid);
            final Thread thread = new Thread(target);
            thread.start();
            thread.join();
            return target.getName();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static JsonElement getResources(final URL url, final String requestMethod, final JsonElement jsonElement) throws Exception {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpsURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            if (jsonElement != null) {
                final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(AdvancementManager.GSON.toJson(jsonElement));
                dataOutputStream.close();
            }
            final Scanner scanner = new Scanner(httpURLConnection.getInputStream());
            final StringBuilder obj = new StringBuilder();
            while (scanner.hasNextLine()) {
                obj.append(scanner.nextLine());
                obj.append('\n');
            }
            scanner.close();
            return PlayerUtil.PARSER.parse(String.valueOf(obj));
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
    
    public static String convertStreamToString(final InputStream source) {
        final Scanner useDelimiter = new Scanner(source).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "/";
    }
    
    public static String getIdNoHyphens(final UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }
    
    static {
        PARSER = new JsonParser();
    }
    
    private static JsonElement getResources(final URL url, final String s) throws Exception {
        return getResources(url, s, null);
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static UUID getUUIDFromName(final String s) {
        try {
            final lookUpUUID target = new lookUpUUID(s);
            final Thread thread = new Thread(target);
            thread.start();
            thread.join();
            return target.getUUID();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static class lookUpName implements Runnable
    {
        private volatile /* synthetic */ String name;
        private final /* synthetic */ String uuid;
        private final /* synthetic */ UUID uuidID;
        
        public String getName() {
            return this.name;
        }
        
        public String lookUpName() {
            EntityPlayer getPlayerEntityByUUID = null;
            if (Util.mc.world != null) {
                getPlayerEntityByUUID = Util.mc.world.getPlayerEntityByUUID(this.uuidID);
            }
            if (getPlayerEntityByUUID == null) {
                final String value = String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(this.uuid.replace("-", "")).append("/names"));
                try {
                    final String string = IOUtils.toString(new URL(value));
                    if (string.contains(",")) {
                        final List<String> list = Arrays.asList(string.split(","));
                        Collections.reverse(list);
                        return list.get(1).replace("{\"name\":\"", "").replace("\"", "");
                    }
                    return string.replace("[{\"name\":\"", "").replace("\"}]", "");
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
            return getPlayerEntityByUUID.getName();
        }
        
        public lookUpName(final UUID uuidID) {
            this.uuidID = uuidID;
            this.uuid = uuidID.toString();
        }
        
        public lookUpName(final String s) {
            this.uuid = s;
            this.uuidID = UUID.fromString(s);
        }
        
        @Override
        public void run() {
            this.name = this.lookUpName();
        }
    }
    
    public static class lookUpUUID implements Runnable
    {
        private volatile /* synthetic */ UUID uuid;
        private final /* synthetic */ String name;
        
        public lookUpUUID(final String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            NetworkPlayerInfo networkPlayerInfo2;
            try {
                networkPlayerInfo2 = new ArrayList<NetworkPlayerInfo>(Objects.requireNonNull(Util.mc.getConnection()).getPlayerInfoMap()).stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(this.name)).findFirst().orElse(null);
                assert networkPlayerInfo2 != null;
                this.uuid = networkPlayerInfo2.getGameProfile().getId();
            }
            catch (Exception ex2) {
                networkPlayerInfo2 = null;
            }
            if (networkPlayerInfo2 == null) {
                Command.sendMessage("Player isn't online. Looking up UUID..");
                final String requestIDs = PlayerUtil.requestIDs(String.valueOf(new StringBuilder().append("[\"").append(this.name).append("\"]")));
                if (requestIDs == null || requestIDs.isEmpty()) {
                    Command.sendMessage("Couldn't find player ID. Are you connected to the internet? (0)");
                }
                else {
                    final JsonElement parse = new JsonParser().parse(requestIDs);
                    if (parse.getAsJsonArray().size() == 0) {
                        Command.sendMessage("Couldn't find player ID. (1)");
                    }
                    else {
                        try {
                            this.uuid = UUIDTypeAdapter.fromString(parse.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString());
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            Command.sendMessage("Couldn't find player ID. (2)");
                        }
                    }
                }
            }
        }
        
        public UUID getUUID() {
            return this.uuid;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
