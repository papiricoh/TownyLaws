package org.papiricoh.townylaws;

import org.bukkit.plugin.java.JavaPlugin;
import org.papiricoh.townylaws.data.DataParser;
import org.papiricoh.townylaws.data.DatabaseManager;
import org.papiricoh.townylaws.object.government.Government;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class TownyLaws extends JavaPlugin {
    protected static DatabaseManager db;
    protected static List<Government> governments;

    @Override
    public void onEnable() {
        this.db = new DatabaseManager();
        try {
            this.db.connect(this);
            DataParser.initializeDatabase(this.db.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.governments = new ArrayList<>();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
