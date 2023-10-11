package org.papiricoh.townylaws;

import org.bukkit.plugin.java.JavaPlugin;
import org.papiricoh.townylaws.data.DataLoader;
import org.papiricoh.townylaws.data.DatabaseManager;
import org.papiricoh.townylaws.object.government.Government;

import java.sql.SQLException;
import java.util.List;

public final class TownyLaws extends JavaPlugin {
    public static DatabaseManager db;
    protected static List<Government> governments;

    @Override
    public void onEnable() {
        this.db = new DatabaseManager();
        try {
            this.db.connect(this);
            DataLoader.initializeDatabase(this.db.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.governments = DataLoader.loadDatabase();

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
}
