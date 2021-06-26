package com.novur.bettertools.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

    boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args);
}
