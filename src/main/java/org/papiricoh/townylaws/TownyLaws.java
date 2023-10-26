package org.papiricoh.townylaws;

import org.bukkit.plugin.java.JavaPlugin;
import org.papiricoh.townylaws.data.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

public final class TownyLaws extends JavaPlugin {
    public static TownyLaws instance;
    private final DatabaseManager db = new DatabaseManager();

    @Override
    public void onEnable() {
        this.instance = this;
        try {
            this.db.connect(this);
            //DataLoader.initializeDatabase(this.db.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        try {
            if (db.getConnection() != null) {
                db.getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TownyLaws getInstance() {
        return instance;
    }
}
