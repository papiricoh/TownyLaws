package org.papiricoh.townylaws.data;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.TownyLaws;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataLoader {
    /*
    public static List<Government> loadDatabase() {
        //TownyLaws.db.getConnection();
        ArrayList<Governments> governments = new ArrayList<>();

        ArrayList<String> uuid_list = new ArrayList<>();
        try (Statement stmt = TownyLaws.db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT uuid FROM governments")) {

            while (rs.next()) {
                uuid_list.add(rs.getString("uuid"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for (String uuid: uuid_list) {
            Government government = loadGovernment(uuid);
            if(government != null) {
                governments.add(government);
            }
        }

        return governments;
    }

    private static Government loadGovernment(String uuid) {
        UUID government_uuid = null;
        UUID leader = null;
        UUID prime_minister = null;
        GovernmentType govType = GovernmentType.ABSOLUTE_MONARCHY;
        try (Statement stmt = TownyLaws.db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM governments WHERE uuid = '" + uuid + "'")) {

            while (rs.next()) {
                government_uuid = UUID.fromString(rs.getString("uuid"));
                leader = UUID.fromString(rs.getString("leader"));
                prime_minister = UUID.fromString(rs.getString("prime_minister"));
                govType = GovernmentType.valueOf(rs.getString("governmentType"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(government_uuid == null || leader == null) {
            return null;
        }

        ArrayList<Resident> senators = loadSenators(uuid);
        ArrayList<Resident> ministers = loadMinisters(uuid);
        ArrayList<Party> parties = loadParties(uuid);
        MemberManager memberManager = new MemberManager(prime_minister != null ? TownyUniverse.getInstance().getResident(prime_minister) : null, ministers, senators, parties);
        //ArrayList<Law> laws = loadLaws(uuid); TODO lawsDatabase


        return new Government(government_uuid, TownyUniverse.getInstance().getResident(leader), govType, null, memberManager);
    }

    private static ArrayList<Party> loadParties(String uuid) {
        ArrayList<Party> parties = new ArrayList<>();

        try (Statement stmt = TownyLaws.db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM parties WHERE government_uuid = '" + uuid + "'")) {

            while (rs.next()) {
                UUID party_uuid = UUID.fromString(rs.getString("uuid"));
                String name = rs.getString("name");
                Resident leader = TownyUniverse.getInstance().getResident(rs.getString("leader"));
                ArrayList<Resident> members = loadPartyMembers(party_uuid.toString());
                Ideology ideology = Ideology.valueOf(rs.getString("ideology"));

                Party party = new Party(party_uuid, name, leader, members, ideology);

                parties.add(party);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return parties;
    }

    private static ArrayList<Resident> loadPartyMembers(String party_uuid) {
        ArrayList<Resident> members = new ArrayList<>();

        try (Statement stmt = TownyLaws.db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM parties_members WHERE party_uuid = '" + party_uuid + "'")) {

            while (rs.next()) {
                members.add(TownyUniverse.getInstance().getResident(rs.getString("uuid")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return members;
    }

    private static ArrayList<Resident> loadSenators(String uuid) {
        ArrayList<Resident> senators = new ArrayList<>();

        try (Statement stmt = TownyLaws.db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM members WHERE role = 'senator' AND government_uuid = '" + uuid + "'")) {

            while (rs.next()) {
                senators.add(TownyUniverse.getInstance().getResident(rs.getString("player_uuid")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return senators;
    }

    private static ArrayList<Resident> loadMinisters(String uuid) {
        ArrayList<Resident> ministers = new ArrayList<>();

        try (Statement stmt = TownyLaws.db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM members WHERE role = 'minister' AND government_uuid = '" + uuid + "'")) {

            while (rs.next()) {
                ministers.add(TownyUniverse.getInstance().getResident(rs.getString("player_uuid")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ministers;
    }

    public static void initializeDatabase(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS governments (" +
                "uuid TEXT PRIMARY KEY," +
                "leader TEXT NOT NULL," +
                "prime_minister TEXT," +
                "governmentType TEXT NOT NULL" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
        sql = "CREATE TABLE IF NOT EXISTS members (" +
                "player_uuid TEXT PRIMARY KEY," +
                "role TEXT DEFAULT 'senator'," +
                "government_uuid TEXT NOT NULL," +
                "FOREIGN KEY (government_uuid) REFERENCES governments(uuid)" +
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
    }*/
}
