package com.novur.bettertools.framework.string;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.region.RegionType;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    private final BetterTools betterTools;
    public StringUtil(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public List<String> color(List<String> stringList) {
        return stringList.stream().map(this::color).collect(Collectors.toList());
    }

    public void sendMessage(CommandSender sender, String configPath) {
        sender.sendMessage(color(betterTools.getConfig().getString(configPath)));
    }

    public void sendHelp(CommandSender sender) {
        for(String string : betterTools.getConfig().getStringList("messages.help")) {
            sender.sendMessage(color(string));
        }


    }

    public void sendMessage(Player player, String configPath) {
        player.sendMessage(color(betterTools.getConfig().getString(configPath)));
    }

    public void sendRegions(CommandSender commandSender) {
        for(String string : betterTools.getConfig().getStringList("messages.regions")) {
            commandSender.sendMessage(color(string).replace("{mineRegions}", betterTools.getRegionHandler().getRegionList(RegionType.MINE))
            .replace("{woodRegions}", betterTools.getRegionHandler().getRegionList(RegionType.LOG)));
        }
    }

    public void sendAliases(CommandSender commandSender) {
        for(String string : betterTools.getConfig().getStringList("messages.aliases")) {
            commandSender.sendMessage(color(string));
        }
    }

    public void sendGems(CommandSender commandSender) {
        for(String string : betterTools.getConfig().getStringList("messages.gems.help")) {
            commandSender.sendMessage(color(string)
            .replace("{gemChance}", String.valueOf(betterTools.getConfig().getDouble("gems.chance")))
            .replace("{minimumGems}", String.valueOf(betterTools.getConfig().getInt("gems.min")))
            .replace("{maximumGems}", String.valueOf(betterTools.getConfig().getInt("gems.max")))
            .replace("{explosiveMultiplier}", String.valueOf(betterTools.getConfig().getDouble("gems.multiplier.explosive")))
            .replace("{laserExplosiveMultiplier}", String.valueOf(betterTools.getConfig().getDouble("gems.multiplier.laser"))));
        }
    }
}
