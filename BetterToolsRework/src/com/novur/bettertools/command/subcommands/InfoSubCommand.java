package com.novur.bettertools.command.subcommands;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.command.SubCommand;
import com.novur.bettertools.framework.string.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoSubCommand implements SubCommand {
    private final BetterTools betterTools;
    private StringUtil stringUtil;
    public InfoSubCommand(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.stringUtil = betterTools.getStringUtil();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player)commandSender;

            if(!player.hasPermission("bettertools.info")) {
                stringUtil.sendMessage(player, "messages.command.noPermission");
                return true;
            }
        }

        if(args.length != 2) {
            stringUtil.sendHelp(commandSender);
            return true;
        }

        if(args[1].equalsIgnoreCase("regions")) {
            stringUtil.sendRegions(commandSender);
            return true;
        }

        else if(args[1].equalsIgnoreCase("aliases")) {
            stringUtil.sendAliases(commandSender);
            return true;
        }

        else if(args[1].equalsIgnoreCase("gems") || (args[1].equalsIgnoreCase("gem"))) {
            stringUtil.sendGems(commandSender);
            return true;
        }


        stringUtil.sendMessage(commandSender, "messages.command.invalidInfoCommand");
        return true;
    }

}
