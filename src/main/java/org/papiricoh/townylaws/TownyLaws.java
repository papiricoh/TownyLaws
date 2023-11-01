package org.papiricoh.townylaws;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.List;

public final class TownyLaws extends JavaPlugin {
    public static TownyLaws instance;


    @Override
    public void onEnable() {
        this.instance = this;


    }

    @Override
    public void onDisable() {

    }

    public static TownyLaws getInstance() {
        return instance;
    }
}
