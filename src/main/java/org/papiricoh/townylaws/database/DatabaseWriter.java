package org.papiricoh.townylaws.database;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.TownyLaws;
import org.papiricoh.townylaws.object.senate.Party;
import org.papiricoh.townylaws.object.senate.Senate;
import org.papiricoh.townylaws.object.senate.members.Senator;
import org.papiricoh.townylaws.object.votableElements.Law;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DatabaseWriter {
    public static void saveSenates(List<Senate> senates) throws IOException {
        for (Senate s : senates) {
            saveSenate(s);
        }
    }

    private static void saveSenate(@NotNull Senate senate) throws IOException {
        UUID nationId = senate.getNation().getUUID();
        File nationFolder = new File(TownyLaws.getInstance().getDataFolder() + File.separator + "nations" + File.separator + nationId);
        if (!nationFolder.exists()) {
            nationFolder.mkdirs();
        }
        savePartiesFile(new File(nationFolder, "parties.txt"), senate.getParties());
        saveLawsFile(new File(nationFolder, "laws.txt"), senate.getLaws());
        saveSenateFile(new File(nationFolder, "senate.txt"), senate);


    }

    private static void saveLawsFile(File file, List<Law> laws) throws IOException {
        for (Law l : laws) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(l.getTitle() + " - " + l.getDescription() + " - "
                        + l.getIdeology().toString() + System.lineSeparator());
            }
        }
    }

    private static void saveSenateFile(File file, Senate senate) throws IOException {
        String senators = "";
        for (Senator r : senate.getSenators()) {
            senators += r.getResident().getUUID() + " " + r.getParty().getUuid() + " - ";
        }
        if(senators.length() != 0) {
            senators = senators.substring(0, senators.length() - 3);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("PrimeMinister: " + senate.getPrimeMinister().getResident().getUUID() + " "
                    + senate.getPrimeMinister().getParty().getUuid() + System.lineSeparator());
            writer.write("Senators: " + senators + System.lineSeparator());
            writer.write("GovernmentType: " + senate.getGovernmentType().toString() + System.lineSeparator());
        }
    }

    private static void savePartiesFile(File file, Map<Party, Integer> parties) throws IOException {
        for (Map.Entry<Party, Integer> entry : parties.entrySet()) {
            String members = "";
            for (Resident r : entry.getKey().getMembers()) {
                members += r.getUUID() + " - ";
            }
            if(members.length() != 0) {
                members = members.substring(0, members.length() - 3);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(entry.getKey().getUuid() + " : "
                        + entry.getKey().getName() + " : "
                        + entry.getKey().getLeader().getUUID() + " : "
                        + members + " : "
                        + entry.getKey().getIdeology().toString() + " : "
                        + entry.getValue().toString() + System.lineSeparator());
            }
        }
    }
}
