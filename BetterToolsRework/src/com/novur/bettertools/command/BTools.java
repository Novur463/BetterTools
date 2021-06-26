package com.novur.bettertools.command;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.string.StringUtil;
import com.novur.bettertools.framework.tool.Tools;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BTools implements CommandExecutor {
    private final BetterTools betterTools;
    private StringUtil stringUtil;
    public BTools(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.stringUtil = betterTools.getStringUtil();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        if(args.length == 0) {

            if(sender instanceof Player) {
                Player player = (Player)sender;

                if(!player.hasPermission("bettertools.give")) {
                    stringUtil.sendMessage(player, "messages.command.noPermission");
                    return true;
                }

                stringUtil.sendMessage(player, "messages.command.invalidCommand");
                return true;
            }

            stringUtil.sendMessage(sender,"messages.command.invalidCommand");
            return true;
        }

        if(args[0].equalsIgnoreCase("help")) {
            stringUtil.sendHelp(sender);
            return true;
        }

        if(args[0].equalsIgnoreCase("regions")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;

                if(!player.hasPermission("bettertools.region")) {
                    stringUtil.sendMessage(player, "messages.command.noPermission");
                    return true;
                }
            }

            stringUtil.sendRegions(sender);
            return true;
        }

        if(args[0].equalsIgnoreCase("reload")) {
            if(sender instanceof Player) {
                Player player = (Player)sender;

                if(!player.hasPermission("bettertools.reload")) {
                    stringUtil.sendMessage(player, "messages.command.noPermission");
                    return true;
                }
            }

            betterTools.reload();
            stringUtil.sendMessage(sender, "messages.command.pluginReloaded");
            return true;
        }

        if(args[0].equalsIgnoreCase("list")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack itemStack = player.getInventory().getItemInMainHand();

                if(itemStack == null) {
                    player.sendMessage("Must be valid item!");
                    return true;
                }

                NBTItem nbtItem = new NBTItem(itemStack);

                for(String keys : nbtItem.getKeys()) {
                    player.sendMessage("Key: " + keys + " | Value: " + nbtItem.getString(keys));
                }

                return true;
            }

            sender.sendMessage("Must be player");
            return true;
        }

        if(args[0].equalsIgnoreCase("validate")) {
            if(sender instanceof Player) {
                Player player = (Player)sender;
                ItemStack itemStack = player.getInventory().getItemInMainHand();

                if(itemStack.getType() == Material.AIR) {
                    stringUtil.sendMessage(player, "messages.command.invalidItem");
                    return true;
                }

                if(!betterTools.getToolUtility().isDonorItem(itemStack)) {
                    stringUtil.sendMessage(player, "messages.command.invalidItem");
                    return true;
                }

                Tools tools = betterTools.getToolUtility().getFromItem(itemStack);

                if(tools != null) {
                    player.sendMessage(stringUtil.color(betterTools.getConfig().getString("messages.command.validatedItem")).replace("%tool%", tools.getName()));
                    return true;
                } else {
                    stringUtil.sendMessage(player, "messages.command.invalidItem");
                    return true;
                }
            }

            stringUtil.sendMessage(sender, "messages.command.mustBePlayer");
            return true;
        }

        if(args[0].equalsIgnoreCase("give")) {
            if(sender instanceof Player) {
                Player player = (Player)sender;

                if(!player.hasPermission("bettertools.give")) {
                    stringUtil.sendMessage(player,"messages.command.noPermission");
                    return true;
                }
            }

            if(args.length != 3) {
                stringUtil.sendMessage(sender,"messages.command.invalidFormatGive");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if(target == null) {
                stringUtil.sendMessage(sender, "messages.command.playerNotFound");
                return true;
            }

            if(!Tools.isRegistered(args[2])) {
                stringUtil.sendMessage(sender, "messages.command.invalidTool");
                return true;
            }

            Tools tools = Tools.index(args[2]);
            if(tools == null) {
                stringUtil.sendMessage(sender, "messages.command.invalidTool");
                return true;
            }

            give(target, tools, sender);
            return true;
        }

        return true;
    }

    private void give(Player target, Tools tools, CommandSender sender) {
        if(canAccept(target)) {
            stringUtil.sendMessage(sender,"messages.command.targetInventoryFull");

            target.sendMessage(stringUtil.color(betterTools.getConfig().getString("messages.command.inventoryFull").replace("%tool%", tools.getName())));
        } else {

            target.getInventory().addItem(betterTools.getToolItems().index(tools));
            target.sendMessage(stringUtil.color(betterTools.getConfig().getString("messages.command.toolReceived")).replace("%tool%", tools.getName()));

            sender.sendMessage(stringUtil.color(betterTools.getConfig().getString("messages.command.toolGiven")).replace("%tool%", tools.getName()).replace("%target%", target.getName()));
        }
    }

    private boolean canAccept(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }
}
