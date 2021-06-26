package com.novur.bettertools.command;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.command.basecommand.BaseCommand;
import com.novur.bettertools.command.subcommands.GiveSubCommand;
import com.novur.bettertools.command.subcommands.InfoSubCommand;
import com.novur.bettertools.command.subcommands.ReloadSubCommand;
import com.novur.bettertools.command.subcommands.ValidateSubCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final BetterTools betterTools;
    public CommandHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    private Map<String, SubCommand> commands = new HashMap<>();

    public void register(String string, SubCommand subCommand) {
        commands.put(string.toLowerCase(), subCommand);
    }

    public boolean exists(String string) {
        return commands.containsKey(string.toLowerCase());
    }

    public SubCommand getExecutor(String string) {
        return commands.get(string.toLowerCase());
    }

    public void registerCommands() {
        CommandHandler commandHandler = betterTools.getCommandHandler();

        commandHandler.register("give", new GiveSubCommand(betterTools));
        commandHandler.register("info", new InfoSubCommand(betterTools));
        commandHandler.register("reload", new ReloadSubCommand(betterTools));
        commandHandler.register("validate", new ValidateSubCommand(betterTools));
        betterTools.getCommand("bettertools").setExecutor(new BaseCommand(betterTools));
    }
}
