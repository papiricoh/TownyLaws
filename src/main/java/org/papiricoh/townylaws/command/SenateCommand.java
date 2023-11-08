package org.papiricoh.townylaws.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.papiricoh.townylaws.TownyLaws;
import org.papiricoh.townylaws.exceptions.LawsException;

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
            if (args.length >= 1) {
                if(args.length >= 2) {
                    if(args[0].equals("info") && args[1].equals("parties")) {
                        try {
                            player.sendMessage(TownyLaws.getInstance().getPlayerSenate(player).partiesToString());
                        } catch (LawsException e) {
                            player.sendMessage(e.getMessage());
                        }
                    }else if(args[0].equals("info") && args[1].equals("senators")) {
                        try {
                            player.sendMessage(TownyLaws.getInstance().getPlayerSenate(player).senatorsToString());
                        } catch (LawsException e) {
                            player.sendMessage(e.getMessage());
                        }
                        return true;
                    }
                }else {
                    if(args[0].equals("info")) {
                        player.sendMessage(TownyLaws.getInstance().getPlayerSenate(player).toString());
                        return true;
                    }
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
            if(args.length == 2) {
                if(args[0].equals("vote")) {
                    ArrayList<String> infoSuggestions = new ArrayList<>();
                    infoSuggestions.add("for");
                    infoSuggestions.add("against");
                    infoSuggestions.add("abstain");
                    return infoSuggestions;
                }
                if(args[0].equals("new")) {
                    ArrayList<String> infoSuggestions = new ArrayList<>();
                    infoSuggestions.add("investitureVote");
                    infoSuggestions.add("lawVote");
                    infoSuggestions.add("changeGovernmentTypeVote");
                    return infoSuggestions;
                }
                if(args[0].equals("info")) {
                    ArrayList<String> infoSuggestions = new ArrayList<>();
                    infoSuggestions.add("parties");
                    infoSuggestions.add("senators");
                    infoSuggestions.add("currentVote");
                    infoSuggestions.add("currentElection");
                    return infoSuggestions;
                }
            }
        }
        return null;
    }
}
