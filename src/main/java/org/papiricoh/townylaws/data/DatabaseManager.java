package org.papiricoh.townylaws.data;

import org.papiricoh.townylaws.TownyLaws;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public void connect(TownyLaws tl) throws SQLException {
        File dataFolder = tl.getDataFolder();
        File databaseFile = new File(dataFolder, "governments.db");

        String url = "jdbc:sqlite:" + databaseFile;
        connection = DriverManager.getConnection(url);
    }

    public Connection getConnection() {
        return connection;
    }
}
