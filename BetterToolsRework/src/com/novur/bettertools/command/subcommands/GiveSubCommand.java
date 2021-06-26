package com.novur.bettertools.command.subcommands;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.command.SubCommand;
import com.novur.bettertools.framework.string.StringUtil;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveSubCommand implements SubCommand {
    private final BetterTools betterTools;
    private StringUtil stringUtil;
    public GiveSubCommand(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.stringUtil = betterTools.getStringUtil();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
        if(args[0].equalsIgnoreCase("give")) {
            if(commandSender instanceof Player) {
                Player player = (Player)commandSender;

                if(!player.hasPermission("bettertools.give")) {
                    stringUtil.sendMessage(player,"messages.command.noPermission");
                    return true;
                }
            }

            if(args.length != 3) {
                stringUtil.sendMessage(commandSender,"messages.command.invalidFormatGive");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if(target == null) {
                stringUtil.sendMessage(commandSender, "messages.command.playerNotFound");
                return true;
            }

            if(!Tools.isRegistered(args[2])) {
                stringUtil.sendMessage(commandSender, "messages.command.invalidTool");
                return true;
            }

            Tools tools = Tools.index(args[2]);
            if(tools == null) {
                stringUtil.sendMessage(commandSender, "messages.command.invalidTool");
                return true;
            }

            give(target, tools, commandSender);
            return true;
        }
        return true;
    }

    private void give(Player target, Tools tools, CommandSender sender) {
        ItemStack itemStack = betterTools.getToolItems().index(tools);
        if(isFull(target)) {
            if(betterTools.getConfig().getBoolean("inventoryFull.dropItemAtFeet")) {
                if(itemStack != null || itemStack.getType() != Material.AIR) {
                    target.getWorld().dropItemNaturally(target.getLocation(), itemStack);
                    for(String string : betterTools.getConfig().getStringList("messages.inventory.invWarning")) {
                        target.sendMessage(stringUtil.color(string).replace("{tool}", tools.getName()));
                    }

                }
            }
            sender.sendMessage(stringUtil.color(betterTools.getConfig().getString("messages.command.targetInventoryFull").replace("{target}", target.getName()).replace("{tool}", tools.getName())));
        }

        else {
            target.getInventory().addItem(itemStack);
            target.sendMessage(stringUtil.color(betterTools.getConfig().getString("messages.command.toolReceived")).replace("{tool}", tools.getName()));
            sender.sendMessage(stringUtil.color(betterTools.getConfig().getString("messages.command.toolGiven")).replace("{tool}", tools.getName()).replace("{target}", target.getName()));
        }
    }

    private boolean isFull(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }
}
