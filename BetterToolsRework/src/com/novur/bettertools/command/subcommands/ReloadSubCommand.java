package com.novur.bettertools.command.subcommands;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.command.SubCommand;
import com.novur.bettertools.framework.string.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadSubCommand implements SubCommand {
    private final BetterTools betterTools;
    private StringUtil stringUtil;
    public ReloadSubCommand(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.stringUtil = betterTools.getStringUtil();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player)commandSender;

            if(!player.hasPermission("bettertools.reload")) {
                stringUtil.sendMessage(player, "messages.command.noPermission");
                return true;
            }
        }

        betterTools.reload();
        stringUtil.sendMessage(commandSender, "messages.command.pluginReloaded");
        return true;
    }
}
