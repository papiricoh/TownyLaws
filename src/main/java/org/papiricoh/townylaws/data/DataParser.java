package org.papiricoh.townylaws.data;

import org.papiricoh.townylaws.TownyLaws;
import org.papiricoh.townylaws.object.government.Government;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataParser {
    public static void loadDatabase() {
        TownyLaws.db.getConnection();
    }

    public static void initializeDatabase(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS governments (" +
                "uuid TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "prime_minister TEXT," +
                "governmentType TEXT NOT NULL" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
        sql = "CREATE TABLE IF NOT EXISTS members (" +
                "player_uuid TEXT PRIMARY KEY," +
                "role TEXT DEFAULT 'senator'," +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
        sql = "CREATE TABLE IF NOT EXISTS parties (" +
                "uuid TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "leader TEXT NOT NULL," +
                "ideology TEXT NOT NULL," +
                "government_uuid TEXT NOT NULL," +
                "FOREIGN KEY (government_uuid) REFERENCES governments(uuid)" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
        sql = "CREATE TABLE IF NOT EXISTS laws (" +
                "uuid TEXT PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "ideology TEXT NOT NULL," +
                "body TEXT NOT NULL," +
                "proposedByUuid TEXT," +
                "government_uuid TEXT NOT NULL," +
                "FOREIGN KEY (government_uuid) REFERENCES governments(uuid)" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
        sql = "CREATE TABLE IF NOT EXISTS parties_members (" +
                "uuid TEXT PRIMARY KEY," +
                "party_uuid TEXT NOT NULL," +
                "FOREIGN KEY (party_uuid) REFERENCES parties(uuid)" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}
