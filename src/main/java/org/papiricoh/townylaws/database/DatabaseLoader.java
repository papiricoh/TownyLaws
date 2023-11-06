package org.papiricoh.townylaws.database;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.TownyLaws;
import org.papiricoh.townylaws.object.senate.Ideology;
import org.papiricoh.townylaws.object.senate.Party;
import org.papiricoh.townylaws.object.senate.Senate;
import org.papiricoh.townylaws.object.senate.members.Senator;
import org.papiricoh.townylaws.object.senate.types.GovernmentType;
import org.papiricoh.townylaws.object.votableElements.Law;

import java.io.*;
import java.util.*;

public class DatabaseLoader {
    public static List<Senate> loadSenates() {
        List<Senate> senates = new ArrayList<>();
        File nationsFolder = new File(TownyLaws.getInstance().getDataFolder(), "nations");

        if (nationsFolder.exists() && nationsFolder.isDirectory()) {
            File[] files = nationsFolder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        try {
                            UUID uuid = UUID.fromString(file.getName());
                            File partiesFile = new File(file, "parties.txt");
                            File senateFile = new File(file, "senate.txt");
                            File lawsFile = new File(file, "laws.txt");
                            senates.add(loadSenateFile(senateFile, partiesFile, lawsFile, uuid)); //FILE
                        } catch (IllegalArgumentException e) {
                            System.err.println("No valid directory: " + file.getName());
                        } catch (IOException e) {
                            System.err.println(file.getName() + " Couldn't load");
                        }
                    }
                }
            }
        }

        return senates;
    }

    public static Senate loadSenateFile(File senateFile, File partiesFile, File lawsFile, UUID nationUuid) throws IOException {
        Nation nation = TownyUniverse.getInstance().getNation(nationUuid);
        Map<Party, Integer> partySeats = loadPartySeats(partiesFile);

        Senator primeMinister = null;
        List<Senator> senators = new ArrayList<>();
        GovernmentType governmentType = null;
        List<Law> laws = loadLaws(lawsFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(senateFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("PrimeMinister: ")) {
                    String[] split = line.substring("PrimeMinister: ".length()).split(" ");
                    primeMinister = new Senator(TownyUniverse.getInstance().getResident(UUID.fromString(split[0])), 
                            split[1] != null ? getPartyFromUUID(UUID.fromString(split[1]), partySeats) : null);
                }else if (line.startsWith("Senators: ")) {
                    String[] senatorsString = line.substring("Senators: ".length()).split(" - ");
                    for (String senatorString : senatorsString) {
                        String[] split = senatorString.split(" ");
                        senators.add(new Senator(TownyUniverse.getInstance().getResident(UUID.fromString(split[0])),
                                split[1] != null ? getPartyFromUUID(UUID.fromString(split[1]), partySeats) : null));
                    }

                }else if (line.startsWith("GovernmentType: ")) {
                    governmentType = GovernmentType.valueOf(line.substring("GovernmentType: ".length()));
                }
            }
        }

        return new Senate(nation, primeMinister, senators, partySeats, laws, governmentType);
    }

    private static List<Law> loadLaws(File lawsFile) throws IOException {
        List<Law> laws = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(lawsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" - ");
                laws.add(new Law(split[0], split[1], Ideology.valueOf(split[2])));
            }
        }
        return laws;
    }

    private static Party getPartyFromUUID(UUID uuid, Map<Party, Integer> partySeats) {
        for (Party p : partySeats.keySet()) {
            if(p.getUuid().equals(uuid)) {
                return p;
            }
        }
        return null;
    }

    private static Map<Party, Integer> loadPartySeats(File partiesFile) throws IOException {
        Map<Party, Integer> partySeats = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(partiesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" : ");
                UUID partyUuid = UUID.fromString(split[0]);
                String partyName = split[1];
                Resident leader = TownyUniverse.getInstance().getResident(UUID.fromString(split[2]));

                String[] membersString = split[3].split(" - ");
                List<Resident> members = new ArrayList<>();
                for (String s : membersString) {
                    members.add(TownyUniverse.getInstance().getResident(UUID.fromString(s)));
                }
                Ideology ideology = Ideology.valueOf(split[4]);
                Integer seats = Integer.parseInt(split[5]);

                partySeats.put(new Party(partyUuid, partyName, leader, members, ideology), seats);
            }
        }

        return partySeats;
    }
}