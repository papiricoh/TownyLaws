package org.papiricoh.townylaws;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.papiricoh.townylaws.command.SenateCommand;
import org.papiricoh.townylaws.database.DatabaseLoader;
import org.papiricoh.townylaws.database.DatabaseWriter;
import org.papiricoh.townylaws.object.senate.Senate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class TownyLaws extends JavaPlugin {
    private static TownyLaws instance;
    private List<Senate> senates;

    @Override
    public void onEnable() {
        this.instance = this;
        this.senates = new ArrayList<>();
        //LOAD SENATES
        this.senates = DatabaseLoader.loadSenates();

        //Check all not senate nations and assign a new senate
        ArrayList<Nation> nations = new ArrayList<>(TownyUniverse.getInstance().getNations());
        for (Senate s : this.senates) {
            Iterator<Nation> iterator = nations.iterator();
            while (iterator.hasNext()) {
                Nation n = iterator.next();
                if (s.getNation().equals(n)) {
                    iterator.remove();
                }
            }
        }
        for (Nation n : nations) {
            this.senates.add(new Senate(n, null, null, null, null, null));
        }

        this.getServer().getScheduler().runTaskTimer(this, () -> {
            try {
                DatabaseWriter.saveSenates(this.senates);
            } catch (IOException e) {
                this.getLogger().severe(e.getMessage());
            }
        }, 0, 3 * 60 * 20L);

        SenateCommand senateCommand = new SenateCommand();
        this.getCommand("senate").setExecutor(senateCommand);
        this.getCommand("senate").setTabCompleter(senateCommand);
    }

    @Override
    public void onDisable() {
        try {
            DatabaseWriter.saveSenates(this.senates);
        } catch (IOException e) {
            this.getLogger().severe(e.getMessage());
        }
    }

    public static TownyLaws getInstance() {
        return instance;
    }

    public Senate getPlayerSenate(Player player) {
        Resident res = TownyUniverse.getInstance().getResident(player.getUniqueId());
        for (Senate s : this.senates) {
            if(s.getNation().hasResident(res)) {
                return s;
            }
        }
        return null;
    }
}
