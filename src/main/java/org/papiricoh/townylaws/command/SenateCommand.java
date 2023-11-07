package org.papiricoh.townylaws.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SenateCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("senate")) {
            if (args.length == 1) {
                List<String> suggestions = new ArrayList<>();
                suggestions.add("vote");
                suggestions.add("new");
                suggestions.add("new");
                return suggestions;
            }
        }
        return null;
    }
}
