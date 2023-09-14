package org.papiricoh.townylaws.government;

import org.papiricoh.townylaws.government.senator.Senator;

import java.util.UUID;
import java.util.HashMap;

public class Parliament {

    private final UUID nation_uuid;
    private UUID parliament_president;
    private HashMap<UUID, Senator> senators;

    public Parliament(UUID nation_uuid, Senator king) {
        this.nation_uuid = nation_uuid;
        this.parliament_president = king.getPlayer_uuid();
        this.senators = new HashMap<>();
        this.senators.put(king.getPlayer_uuid(), king);
    }

    public Parliament(UUID nation_uuid, UUID parliament_president, HashMap<UUID, Senator> senators) {
        this.nation_uuid = nation_uuid;
        this.parliament_president = parliament_president;
        this.senators = senators;
    }

    public UUID getParliament_president() {
        return parliament_president;
    }

    public void setParliament_president(UUID parliament_president) {
        this.parliament_president = parliament_president;
    }

    public UUID getNation_uuid() {
        return nation_uuid;
    }
}
