package org.papiricoh.townylaws;

import org.bukkit.plugin.java.JavaPlugin;
import org.papiricoh.townylaws.object.government.Government;

import java.util.ArrayList;
import java.util.List;

public final class TownyLaws extends JavaPlugin {
    protected static List<Government> governments;

    @Override
    public void onEnable() {
        this.governments = new ArrayList<>();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
