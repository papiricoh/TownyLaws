package org.papiricoh.townylaws.data;

import org.papiricoh.townylaws.object.government.Government;
import org.papiricoh.townylaws.object.government.type.GovernmentType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataParser {
    public static void SaveGovernment(Government government, File dataFolder) {
        File governmentFile = new File(dataFolder, "governments/" + government.getUuid() + ".txt");
        try {
            FileWriter writer = new FileWriter(governmentFile);
            writer.write("uuid: " + government.getUuid());
            writer.write("leader: " + government.getLeader().getUUID());
            writer.write("governmentType: " + government.getGovernmentType().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
