package org.papiricoh.townylaws.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.papiricoh.townylaws.TownyLaws;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SenateCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        Player player = null;
        if (commandSender instanceof Player) {
            player = (Player) commandSender;
        } else {
            commandSender.sendMessage("Command only available to players.");
            return true;
        }
        if (command.getName().equalsIgnoreCase("senate")) {
            if (args.length == 1) {
                if(args[0].equals("info")) {
                    player.sendMessage(TownyLaws.getInstance().getPlayerSenate(player).toString());
                }
            }
        }
        return false;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("senate")) {
            if (args.length == 1) {
                List<String> suggestions = new ArrayList<>();
                suggestions.add("vote");
                suggestions.add("new");
                suggestions.add("info");
                return suggestions;
            }
        }
        return null;
    }
}
