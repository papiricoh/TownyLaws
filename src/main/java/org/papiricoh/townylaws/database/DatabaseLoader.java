package org.papiricoh.townylaws.database;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.TownyLaws;
import org.papiricoh.townylaws.object.senate.Party;
import org.papiricoh.townylaws.object.senate.Senate;
import org.papiricoh.townylaws.object.senate.members.Senator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                            senates.add(loadSenateFile(file, uuid)); //FILE
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

    public static Senate loadSenateFile(File file, UUID nationUuid) throws IOException {
        List<Senator> senatorList = new ArrayList<>();

        Nation nation = TownyUniverse.getInstance().getNation(nationUuid);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("PrimeMinister: ")) {

                }
            }
        }

        return new Senate(nation, null, null, null, null, null);
    }
}