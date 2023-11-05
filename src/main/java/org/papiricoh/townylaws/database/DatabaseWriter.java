package org.papiricoh.townylaws.database;

import org.papiricoh.townylaws.TownyLaws;
import org.papiricoh.townylaws.object.senate.Party;
import org.papiricoh.townylaws.object.senate.Senate;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DatabaseWriter {
    public static void saveSenates(List<Senate> senates) throws IOException {
        for (Senate s : senates) {
            saveSenate(s);
        }
    }

    private static void saveSenate(Senate senate) throws IOException {
        UUID nationId = senate.getNation().getUUID();
        File nationFolder = new File(TownyLaws.getInstance().getDataFolder() + File.separator + "nations" + File.separator + nationId);
        if (!nationFolder.exists()) {
            nationFolder.mkdirs();
        }
        saveParties(new File(nationFolder, "parties.txt"), senate.getParties());

    }

    private static void saveParties(File file, Map<Party, Integer> parties) {
    }
}
