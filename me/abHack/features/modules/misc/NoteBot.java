//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import java.util.concurrent.atomic.*;
import me.abHack.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.net.*;
import java.nio.channels.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.block.material.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import java.util.*;
import me.abHack.*;
import java.io.*;
import java.util.zip.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import me.abHack.util.*;
import net.minecraft.network.play.client.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;

public class NoteBot extends Module
{
    private final /* synthetic */ Setting<String> loadFileSet;
    private final /* synthetic */ File file;
    private /* synthetic */ int tuneStage;
    private /* synthetic */ BlockPos nextPos;
    private /* synthetic */ BlockPos currentPos;
    private /* synthetic */ IRegister[] registers;
    private final /* synthetic */ Setting<Boolean> tune;
    private /* synthetic */ boolean tuned;
    private final /* synthetic */ Setting<Boolean> downloadSongs;
    private final /* synthetic */ List<SoundEntry> soundEntries;
    private /* synthetic */ int index;
    private /* synthetic */ int soundIndex;
    private /* synthetic */ Map<Sound, BlockPos[]> soundPositions;
    private final /* synthetic */ List<BlockPos> posList;
    private final /* synthetic */ Map<Sound, Byte> soundBytes;
    private /* synthetic */ int tuneIndex;
    private /* synthetic */ Map<BlockPos, AtomicInteger> posPitch;
    private /* synthetic */ BlockPos endPos;
    private final /* synthetic */ Setting<Boolean> active;
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.downloadSongs.getValue()) {
            this.downloadSongs();
            Command.sendMessage("Songs downloaded");
            this.downloadSongs.setValue(false);
        }
        if (updateWalkingPlayerEvent.getStage() == 0) {
            if (this.tune.getValue()) {
                this.tunePre();
            }
            else if (this.active.getValue()) {
                this.noteBotPre();
            }
        }
        else if (this.tune.getValue()) {
            this.tunePost();
        }
        else if (this.active.getValue()) {
            this.noteBotPost();
        }
    }
    
    @Override
    public void onEnable() {
        if (nullCheck()) {
            this.disable();
            return;
        }
        this.soundEntries.clear();
        this.getNoteBlocks();
        this.soundIndex = 0;
        this.index = 0;
        this.resetTuning();
    }
    
    private BlockPos getRegisterPos(final SoundRegister soundRegister) {
        final SoundEntry soundEntry2 = this.soundEntries.stream().filter(soundEntry -> soundEntry.getRegister().equals(soundRegister)).findFirst().orElse(null);
        if (soundEntry2 == null) {
            return null;
        }
        return soundEntry2.getPos();
    }
    
    private BlockPos getAtomicBlockPos(final BlockPos blockPos) {
        for (final Map.Entry<BlockPos, AtomicInteger> entry : this.posPitch.entrySet()) {
            final BlockPos blockPos2 = entry.getKey();
            final AtomicInteger atomicInteger = entry.getValue();
            if (blockPos2 != null && !blockPos2.equals((Object)blockPos) && atomicInteger.intValue() > 0) {
                return blockPos2;
            }
        }
        return null;
    }
    
    public static Sound getSoundFromBlockState(final IBlockState blockState) {
        if (blockState.getBlock() == Blocks.CLAY) {
            return Sound.CLAY;
        }
        if (blockState.getBlock() == Blocks.GOLD_BLOCK) {
            return Sound.GOLD;
        }
        if (blockState.getBlock() == Blocks.WOOL) {
            return Sound.WOOL;
        }
        if (blockState.getBlock() == Blocks.PACKED_ICE) {
            return Sound.ICE;
        }
        if (blockState.getBlock() == Blocks.BONE_BLOCK) {
            return Sound.BONE;
        }
        if (blockState.getMaterial() == Material.ROCK) {
            return Sound.ROCK;
        }
        if (blockState.getMaterial() == Material.SAND) {
            return Sound.SAND;
        }
        if (blockState.getMaterial() == Material.GLASS) {
            return Sound.GLASS;
        }
        return (blockState.getMaterial() == Material.WOOD) ? Sound.WOOD : Sound.NONE;
    }
    
    private void tunePost() {
        if (this.tuneStage == 0 && this.currentPos != null) {
            final EnumFacing facing = BlockUtil.getFacing(this.currentPos);
            NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.currentPos, facing));
            NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentPos, facing));
        }
        else if (this.currentPos != null) {
            this.posPitch.get(this.currentPos).decrementAndGet();
            NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.currentPos, BlockUtil.getFacing(this.currentPos), EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        }
    }
    
    private void resetTuning() {
        if (NoteBot.mc.world == null || NoteBot.mc.player == null) {
            this.disable();
            return;
        }
        this.tuned = true;
        this.soundPositions = setUpSoundMap();
        this.posPitch = new LinkedHashMap<BlockPos, AtomicInteger>();
        this.soundPositions.values().forEach(a -> Arrays.asList(a).forEach(endPos -> {
            if (endPos != null) {
                this.endPos = endPos;
                this.posPitch.put(endPos, new AtomicInteger(-1));
            }
        }));
        this.tuneStage = 0;
        this.tuneIndex = 0;
    }
    
    private static IRegister[] createRegister(final File file) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] b = new byte[fileInputStream.available()];
        fileInputStream.read(b);
        final ArrayList<SimpleRegister> list = new ArrayList<SimpleRegister>();
        boolean b2 = true;
        final byte[] array = b;
        final int length = array.length;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == 64) {
                b2 = false;
                break;
            }
        }
        for (int n = 0, j = 0; j < b.length; j = ++n) {
            final byte b3 = b[n];
            if (b3 == (b2 ? 5 : 64)) {
                final byte[] array2 = { b[++n], b[++n] };
                list.add(new SimpleRegister((array2[0] & 0xFF) | (array2[1] & 0xFF) << 8));
            }
            else {
                list.add((SimpleRegister)new SoundRegister(Sound.values()[b3], b[++n]));
            }
        }
        final ArrayList<SimpleRegister> list2 = list;
        return list2.toArray(new IRegister[list2.size()]);
    }
    
    @Override
    public void onLogin() {
        if (OyVey.moduleManager.isModuleEnabled("NoteBot")) {
            this.disable();
        }
    }
    
    private void fillSoundBytes() {
        this.soundBytes.clear();
        final Sound[] values = Sound.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            this.soundBytes.put(values[i], (Byte)0);
        }
    }
    
    public static void unzip(final File file, final File parent) {
        final byte[] array = new byte[1024];
        ZipInputStream zipInputStream;
        ZipEntry zipEntry;
        try {
            if (!parent.exists()) {
                parent.mkdir();
            }
            zipInputStream = new ZipInputStream(new FileInputStream(file));
            zipEntry = zipInputStream.getNextEntry();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        while (true) {
            FileOutputStream fileOutputStream;
            try {
                if (zipEntry == null) {
                    break;
                }
                final File file2 = new File(parent, zipEntry.getName());
                new File(file2.getParent()).mkdirs();
                fileOutputStream = new FileOutputStream(file2);
                int read;
                while ((read = zipInputStream.read(array)) > 0) {
                    fileOutputStream.write(array, 0, read);
                }
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
                return;
            }
            try {
                fileOutputStream.close();
                zipEntry = zipInputStream.getNextEntry();
            }
            catch (IOException ex3) {
                ex3.printStackTrace();
                return;
            }
        }
        try {
            zipInputStream.closeEntry();
            zipInputStream.close();
        }
        catch (IOException ex4) {
            ex4.printStackTrace();
        }
    }
    
    @Override
    public void onLogout() {
        if (OyVey.moduleManager.isModuleEnabled("NoteBot")) {
            this.disable();
        }
    }
    
    private void getNoteBlocks() {
        this.fillSoundBytes();
        for (int i = -6; i < 6; ++i) {
            for (int j = -1; j < 5; ++j) {
                for (int k = -6; k < 6; ++k) {
                    final BlockPos add = NoteBot.mc.player.getPosition().add(i, j, k);
                    final Block getBlock = NoteBot.mc.world.getBlockState(add).getBlock();
                    if (add.distanceSqToCenter(NoteBot.mc.player.posX, NoteBot.mc.player.posY + NoteBot.mc.player.getEyeHeight(), NoteBot.mc.player.posZ) < 27.0 && getBlock == Blocks.NOTEBLOCK) {
                        final Sound soundFromBlockState;
                        final byte byteValue;
                        if ((byteValue = this.soundBytes.get(soundFromBlockState = getSoundFromBlockState(NoteBot.mc.world.getBlockState(add.down())))) <= 25) {
                            this.soundEntries.add(new SoundEntry(add, new SoundRegister(soundFromBlockState, byteValue)));
                            this.soundBytes.replace(soundFromBlockState, (byte)(byteValue + 1));
                        }
                    }
                }
            }
        }
    }
    
    private void noteBotPre() {
        this.posList.clear();
        if (this.registers == null) {
            return;
        }
        while (this.index < this.registers.length) {
            final IRegister register = this.registers[this.index];
            if (register instanceof SimpleRegister) {
                if (++this.soundIndex >= ((SimpleRegister)register).getSound()) {
                    ++this.index;
                    this.soundIndex = 0;
                }
                if (this.posList.size() > 0) {
                    OyVey.rotationManager.lookAtPos(this.posList.get(0));
                }
                return;
            }
            if (!(register instanceof SoundRegister)) {
                continue;
            }
            final BlockPos registerPos = this.getRegisterPos((SoundRegister)register);
            if (registerPos != null) {
                this.posList.add(registerPos);
            }
            ++this.index;
        }
        this.index = 0;
    }
    
    public static Map<Sound, BlockPos[]> setUpSoundMap() {
        NoteBot.mc.player.getPosition();
        final LinkedHashMap<Sound, BlockPos[]> linkedHashMap = new LinkedHashMap<Sound, BlockPos[]>();
        final HashMap<Object, AtomicInteger> hashMap = new HashMap<Object, AtomicInteger>();
        final HashMap<Sound, BlockPos[]> hashMap2;
        final HashMap<Sound, AtomicInteger> hashMap3;
        Arrays.asList(Sound.values()).forEach(sound -> {
            hashMap2.put(sound, new BlockPos[25]);
            hashMap3.put(sound, new AtomicInteger());
            return;
        });
        for (int i = -6; i < 6; ++i) {
            for (int j = -1; j < 5; ++j) {
                for (int k = -6; k < 6; ++k) {
                    final BlockPos add = NoteBot.mc.player.getPosition().add(i, j, k);
                    final Block getBlock = NoteBot.mc.world.getBlockState(add).getBlock();
                    if (distanceSqToCenter(add) < 27.040000000000003 && getBlock == Blocks.NOTEBLOCK) {
                        final Sound soundFromBlockState;
                        final int andIncrement;
                        if ((andIncrement = hashMap.get(soundFromBlockState = getSoundFromBlockState(NoteBot.mc.world.getBlockState(add.down()))).getAndIncrement()) < 25) {
                            linkedHashMap.get(soundFromBlockState)[andIncrement] = add;
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }
    
    private void noteBotPost() {
        for (int i = 0; i < this.posList.size(); ++i) {
            final BlockPos blockPos = this.posList.get(i);
            if (blockPos != null) {
                if (i != 0) {
                    final float[] calcAngle = MathUtil.calcAngle(NoteBot.mc.player.getPositionEyes(NoteBot.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() + 0.5f), (double)(blockPos.getZ() + 0.5f)));
                    NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(calcAngle[0], calcAngle[1], NoteBot.mc.player.onGround));
                }
                this.clickNoteBlock(blockPos);
            }
        }
    }
    
    private void clickNoteBlock(final BlockPos blockPos) {
        final EnumFacing facing = BlockUtil.getFacing(blockPos);
        NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, facing));
        NoteBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, blockPos, facing));
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && this.equals(clientEvent.getSetting().getFeature())) {
            if (clientEvent.getSetting().equals(this.loadFileSet)) {
                final String str = this.loadFileSet.getPlannedValue();
                try {
                    this.registers = createRegister(new File(String.valueOf(new StringBuilder().append("ab-Hack/notebot/").append(str).append(".notebot"))));
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Loaded: ").append(str)));
                }
                catch (Exception ex) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("An Error occurred with ").append(str)));
                    ex.printStackTrace();
                }
                clientEvent.setCanceled(true);
            }
            else if (clientEvent.getSetting().equals(this.tune) && this.tune.getPlannedValue()) {
                this.resetTuning();
            }
        }
    }
    
    private void downloadSongs() {
        File file;
        new Thread(() -> {
            try {
                file = new File(this.file, "songs.zip");
                new FileOutputStream(file).getChannel().transferFrom(Channels.newChannel(new URL("https://www.futureclient.net/future/songs.zip").openStream()), 0L, Long.MAX_VALUE);
                unzip(file, this.file);
                file.deleteOnExit();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
    
    private void tunePre() {
        this.currentPos = null;
        if (this.tuneStage == 1 && this.getAtomicBlockPos(null) == null) {
            if (this.tuned) {
                Command.sendMessage("Done tuning.");
                this.tune.setValue(false);
            }
            else {
                this.tuned = true;
                this.tuneStage = 0;
                this.tuneIndex = 0;
            }
        }
        else {
            if (this.tuneStage != 0) {
                final BlockPos atomicBlockPos = this.getAtomicBlockPos(this.nextPos);
                this.currentPos = atomicBlockPos;
                this.nextPos = atomicBlockPos;
            }
            else {
                while (this.tuneIndex < 250 && this.currentPos == null) {
                    this.currentPos = this.soundPositions.get(Sound.values()[(int)Math.floor(this.tuneIndex / 25)])[this.tuneIndex % 25];
                    ++this.tuneIndex;
                }
            }
            if (this.currentPos != null) {
                OyVey.rotationManager.lookAtPos(this.currentPos);
            }
        }
    }
    
    private static double distanceSqToCenter(final BlockPos blockPos) {
        final double abs = Math.abs(NoteBot.mc.player.posX - blockPos.getX() - 0.5);
        final double abs2 = Math.abs(NoteBot.mc.player.posY + NoteBot.mc.player.getEyeHeight() - blockPos.getY() - 0.5);
        final double abs3 = Math.abs(NoteBot.mc.player.posZ - blockPos.getZ() - 0.5);
        return abs * abs + abs2 * abs2 + abs3 * abs3;
    }
    
    public NoteBot() {
        super("NoteBot", "Plays songs.", Category.MISC, true, false, false);
        this.tune = (Setting<Boolean>)this.register(new Setting("Tune", (T)false));
        this.active = (Setting<Boolean>)this.register(new Setting("Active", (T)false));
        this.downloadSongs = (Setting<Boolean>)this.register(new Setting("DownloadSongs", (T)false));
        this.loadFileSet = (Setting<String>)this.register(new Setting("Load", (T)"Load Music..."));
        this.soundBytes = new HashMap<Sound, Byte>();
        this.soundEntries = new ArrayList<SoundEntry>();
        this.posList = new ArrayList<BlockPos>();
        this.file = new File(OyVey.fileManager.getNotebot().toString());
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (this.tune.getValue() && receive.getPacket() instanceof SPacketBlockAction && this.tuneStage == 0 && this.soundPositions != null) {
            final SPacketBlockAction sPacketBlockAction = (SPacketBlockAction)receive.getPacket();
            final Sound sound = Sound.values()[sPacketBlockAction.getData1()];
            final int getData2 = sPacketBlockAction.getData2();
            final BlockPos[] array = this.soundPositions.get(sound);
            int i = 0;
            while (i < 25) {
                final BlockPos blockPos = array[i];
                if (!sPacketBlockAction.getBlockPosition().equals((Object)blockPos)) {
                    ++i;
                }
                else {
                    if (this.posPitch.get(blockPos).intValue() != -1) {
                        break;
                    }
                    int newValue = i - getData2;
                    if (newValue < 0) {
                        newValue += 25;
                    }
                    this.posPitch.get(blockPos).set(newValue);
                    if (newValue == 0) {
                        break;
                    }
                    this.tuned = false;
                    break;
                }
            }
            if (this.endPos.equals((Object)sPacketBlockAction.getBlockPosition()) && this.tuneIndex >= this.posPitch.values().size()) {
                this.tuneStage = 1;
            }
        }
    }
    
    public enum Sound
    {
        WOOD, 
        ICE, 
        WOOL, 
        SAND, 
        NONE, 
        CLAY, 
        BONE, 
        GLASS, 
        GOLD, 
        ROCK;
    }
    
    public static class SoundEntry
    {
        private final /* synthetic */ SoundRegister register;
        private final /* synthetic */ BlockPos pos;
        
        public SoundRegister getRegister() {
            return this.register;
        }
        
        public BlockPos getPos() {
            return this.pos;
        }
        
        public SoundEntry(final BlockPos pos, final SoundRegister register) {
            this.pos = pos;
            this.register = register;
        }
    }
    
    public static class SoundRegister implements IRegister
    {
        private final /* synthetic */ byte soundByte;
        private final /* synthetic */ Sound sound;
        
        public SoundRegister(final Sound sound, final byte soundByte) {
            this.sound = sound;
            this.soundByte = soundByte;
        }
        
        public Sound getSound() {
            return this.sound;
        }
        
        public byte getSoundByte() {
            return this.soundByte;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o instanceof SoundRegister) {
                final SoundRegister soundRegister = (SoundRegister)o;
                return soundRegister.getSound() == this.getSound() && soundRegister.getSoundByte() == this.getSoundByte();
            }
            return false;
        }
    }
    
    public interface IRegister
    {
    }
    
    public static class SimpleRegister implements IRegister
    {
        private /* synthetic */ int sound;
        
        public void setSound(final int sound) {
            this.sound = sound;
        }
        
        public int getSound() {
            return this.sound;
        }
        
        public SimpleRegister(final int sound) {
            this.sound = sound;
        }
    }
}
