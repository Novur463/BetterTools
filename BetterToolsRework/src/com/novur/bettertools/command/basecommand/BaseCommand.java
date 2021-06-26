package com.novur.bettertools.command.basecommand;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.command.CommandHandler;
import com.novur.bettertools.framework.string.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandExecutor {
    private final BetterTools betterTools;
    private StringUtil stringUtil;
    private CommandHandler commandHandler;
    public BaseCommand(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.stringUtil = betterTools.getStringUtil();
        this.commandHandler = betterTools.getCommandHandler();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player)commandSender;

            if(!player.hasPermission("bettertools.help")) {
                stringUtil.sendMessage(player,"messages.command.noPermission");
                return true;
            }

        }

        if(args.length == 0) {
            stringUtil.sendHelp(commandSender);
            return true;
        }

        if(args.length > 0) {

            if(!commandHandler.exists(args[0])) {
                stringUtil.sendMessage(commandSender, "messages.command.invalidCommand");
                return true;
            }

            commandHandler.getExecutor(args[0]).onCommand(commandSender,command,commandLabel,args);
            return true;
        }


        return true;
    }
}
