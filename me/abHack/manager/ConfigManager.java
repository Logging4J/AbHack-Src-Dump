//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.util.*;
import me.abHack.features.*;
import java.util.stream.*;
import me.abHack.*;
import java.nio.file.attribute.*;
import java.nio.file.*;
import com.google.gson.*;
import java.util.*;
import me.abHack.features.setting.*;
import me.abHack.features.modules.*;
import java.io.*;

public class ConfigManager implements Util
{
    public /* synthetic */ ArrayList<Feature> features;
    public /* synthetic */ String config;
    
    public void resetConfig(final boolean b, final String s) {
        final Iterator<Feature> iterator = this.features.iterator();
        while (iterator.hasNext()) {
            iterator.next().reset();
        }
        if (b) {
            this.saveConfig(s);
        }
    }
    
    public ConfigManager() {
        this.features = new ArrayList<Feature>();
        this.config = "ab-Hack/config/";
    }
    
    public void loadConfig(final String s) {
        if (Arrays.stream((Object[])Objects.requireNonNull((T[])new File("ab-Hack").listFiles())).filter(File::isDirectory).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()).contains(new File(String.valueOf(new StringBuilder().append("ab-Hack/").append(s).append("/"))))) {
            this.config = String.valueOf(new StringBuilder().append("ab-Hack/").append(s).append("/"));
        }
        else {
            this.config = "ab-Hack/config/";
        }
        OyVey.friendManager.onLoad();
        for (final Feature feature : this.features) {
            try {
                this.loadSettings(feature);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.saveCurrentConfig();
    }
    
    public void saveSettings(final Feature feature) throws IOException {
        final JsonObject jsonObject = new JsonObject();
        final File file = new File(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature))));
        if (!file.exists()) {
            file.mkdir();
        }
        final Path value = Paths.get(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature)).append(feature.getName()).append(".json")), new String[0]);
        if (!Files.exists(value, new LinkOption[0])) {
            Files.createFile(value, (FileAttribute<?>[])new FileAttribute[0]);
        }
        final String json = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)this.writeSettings(feature));
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(value, new OpenOption[0])));
        bufferedWriter.write(json);
        bufferedWriter.close();
    }
    
    public void saveConfig(final String str) {
        this.config = String.valueOf(new StringBuilder().append("ab-Hack/").append(str).append("/"));
        final File file = new File(this.config);
        if (!file.exists()) {
            file.mkdir();
        }
        OyVey.friendManager.saveFriends();
        for (final Feature feature : this.features) {
            try {
                this.saveSettings(feature);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.saveCurrentConfig();
    }
    
    public boolean configExists(final String str) {
        return Arrays.stream((Object[])Objects.requireNonNull((T[])new File("ab-Hack").listFiles())).filter(File::isDirectory).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()).contains(new File(String.valueOf(new StringBuilder().append("ab-Hack/").append(str).append("/"))));
    }
    
    public String loadCurrentConfig() {
        final File source = new File("ab-Hack/currentconfig.txt");
        String nextLine = "config";
        try {
            if (source.exists()) {
                final Scanner scanner = new Scanner(source);
                while (scanner.hasNextLine()) {
                    nextLine = scanner.nextLine();
                }
                scanner.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return nextLine;
    }
    
    public JsonObject writeSettings(final Feature feature) {
        final JsonObject jsonObject = new JsonObject();
        final JsonParser jsonParser = new JsonParser();
        for (final Setting setting : feature.getSettings()) {
            if (setting.isEnumSetting()) {
                jsonObject.add(setting.getName(), new EnumConverter((Class)((Enum)setting.getValue()).getClass()).doForward((Enum)setting.getValue()));
            }
            else {
                if (setting.isStringSetting()) {
                    setting.setValue((Object)((String)setting.getValue()).replace(" ", "_"));
                }
                try {
                    jsonObject.add(setting.getName(), jsonParser.parse(setting.getValueAsString()));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
    
    public void init() {
        this.features.addAll((Collection<? extends Feature>)OyVey.moduleManager.modules);
        this.features.add(OyVey.friendManager);
        this.loadConfig(this.loadCurrentConfig());
        OyVey.LOGGER.info("Config loaded.");
    }
    
    private static void loadFile(final JsonObject jsonObject, final Feature feature) {
        for (final Map.Entry<String, V> entry : jsonObject.entrySet()) {
            final String name = entry.getKey();
            final JsonElement jsonElement = (JsonElement)entry.getValue();
            if (feature instanceof FriendManager) {
                try {
                    OyVey.friendManager.addFriend(new FriendManager.Friend(jsonElement.getAsString(), UUID.fromString(name)));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else {
                boolean b = false;
                for (final Setting setting : feature.getSettings()) {
                    if (name.equals(setting.getName())) {
                        try {
                            setValueFromJson(feature, setting, jsonElement);
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        b = true;
                    }
                }
                if (b) {}
            }
        }
    }
    
    public static void setValueFromJson(final Feature feature, final Setting setting, final JsonElement jsonElement) {
        final String type = setting.getType();
        switch (type) {
            case "Boolean": {
                setting.setValue((Object)jsonElement.getAsBoolean());
            }
            case "Double": {
                setting.setValue((Object)jsonElement.getAsDouble());
            }
            case "Float": {
                setting.setValue((Object)jsonElement.getAsFloat());
            }
            case "Integer": {
                setting.setValue((Object)jsonElement.getAsInt());
            }
            case "String": {
                setting.setValue((Object)jsonElement.getAsString().replace("_", " "));
            }
            case "Bind": {
                setting.setValue((Object)new Bind.BindConverter().doBackward(jsonElement));
            }
            case "Enum": {
                try {
                    final Enum doBackward = new EnumConverter((Class)((Enum)setting.getValue()).getClass()).doBackward(jsonElement);
                    setting.setValue((doBackward == null) ? setting.getDefaultValue() : doBackward);
                }
                catch (Exception ex) {}
            }
            default: {
                OyVey.LOGGER.error(String.valueOf(new StringBuilder().append("Unknown Setting type for: ").append(feature.getName()).append(" : ").append(setting.getName())));
            }
        }
    }
    
    public void saveCurrentConfig() {
        final File file = new File("ab-Hack/currentconfig.txt");
        try {
            if (file.exists()) {
                final FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(this.config.replaceAll("/", "").replaceAll("ab-Hack", ""));
                fileWriter.close();
            }
            else {
                file.createNewFile();
                final FileWriter fileWriter2 = new FileWriter(file);
                fileWriter2.write(this.config.replaceAll("/", "").replaceAll("ab-Hack", ""));
                fileWriter2.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadSettings(final Feature feature) throws IOException {
        final Path value = Paths.get(String.valueOf(new StringBuilder().append(this.config).append(this.getDirectory(feature)).append(feature.getName()).append(".json")), new String[0]);
        if (!Files.exists(value, new LinkOption[0])) {
            return;
        }
        this.loadPath(value, feature);
    }
    
    public String getDirectory(final Feature feature) {
        String value = "";
        if (feature instanceof Module) {
            value = String.valueOf(new StringBuilder().append(value).append(((Module)feature).getCategory().getName()).append("/"));
        }
        return value;
    }
    
    private void loadPath(final Path path, final Feature feature) throws IOException {
        final InputStream inputStream = Files.newInputStream(path, new OpenOption[0]);
        try {
            loadFile(new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject(), feature);
        }
        catch (IllegalStateException ex) {
            OyVey.LOGGER.error(String.valueOf(new StringBuilder().append("Bad Config File for: ").append(feature.getName()).append(". Resetting...")));
            loadFile(new JsonObject(), feature);
        }
        inputStream.close();
    }
}
