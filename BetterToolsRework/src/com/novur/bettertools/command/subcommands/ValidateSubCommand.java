package com.novur.bettertools.command.subcommands;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.command.SubCommand;
import com.novur.bettertools.framework.string.StringUtil;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ValidateSubCommand implements SubCommand {
    private final BetterTools betterTools;
    private StringUtil stringUtil;
    public ValidateSubCommand(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.stringUtil = betterTools.getStringUtil();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack itemStack = player.getInventory().getItemInMainHand();

            if(itemStack.getType() == Material.AIR || itemStack == null) {
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
            } else {
                stringUtil.sendMessage(player, "messages.command.invalidItem");
            }
            return true;
        }

        stringUtil.sendMessage(commandSender, "messages.command.mustBePlayer");
        return true;
    }

}
