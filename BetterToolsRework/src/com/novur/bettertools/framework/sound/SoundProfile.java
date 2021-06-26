package com.novur.bettertools.framework.sound;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundProfile {
    private final Tools tools;
    private final BetterTools betterTools;
    private Sound sound;
    private float pitch;
    private float volume;

    public SoundProfile(Tools tools, BetterTools betterTools) {
        this.tools = tools;
        this.betterTools = betterTools;

        if(isValidSound(betterTools.getConfig().getString("sounds." + tools.getConfigID() + ".sound"))) {
            this.sound = Sound.valueOf(betterTools.getConfig().getString("sounds." + tools.getConfigID() + ".sound"));
            this.pitch = (float) betterTools.getConfig().getDouble("sounds." + tools.getConfigID() + ".pitch");
            this.volume = (float) betterTools.getConfig().getDouble("sounds." + tools.getConfigID() + ".volume");
        }
    }

    public Sound getSound() {
        return sound;
    }

    public float getPitch() {
        return pitch;
    }

    public float getVolume() {
        return volume;
    }

    public void play(Player player) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

    private boolean isValidSound(String string) {
        Sound sound;

        try {
            sound = Sound.valueOf(string);
            return true;
        } catch(IllegalArgumentException | NullPointerException ex) {
            betterTools.getLogger().severe("Sound '" + string + "' could not be parsed for Tool " + tools.getName());
        }

        return false;
    }


}
