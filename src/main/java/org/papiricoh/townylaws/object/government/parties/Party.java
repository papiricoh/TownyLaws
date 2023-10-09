package org.papiricoh.townylaws.object.government.parties;

import com.palmergames.bukkit.towny.object.Resident;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Party {
    private UUID uuid;
    private Resident leader;
    private List<Resident> residents;

    public Party(UUID uuid, Resident leader, List<Resident> residents) {
        this.uuid = uuid;
        this.leader = leader;
        this.residents = residents != null ? residents : new ArrayList<>();
    }


}
