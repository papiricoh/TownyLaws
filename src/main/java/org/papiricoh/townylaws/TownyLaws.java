package org.papiricoh.townylaws;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.plugin.java.JavaPlugin;
import org.papiricoh.townylaws.object.senate.Senate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TownyLaws extends JavaPlugin {
    public static TownyLaws instance;
    private List<Senate> senates;

    @Override
    public void onEnable() {
        this.instance = this;
        this.senates = new ArrayList<>();
        //LOAD SENATES

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

    }

    @Override
    public void onDisable() {

    }

    public static TownyLaws getInstance() {
        return instance;
    }
}
