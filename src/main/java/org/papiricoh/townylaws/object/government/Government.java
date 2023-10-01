package org.papiricoh.townylaws.object.government;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;
import org.papiricoh.townylaws.object.government.type.GovernmentType;
import org.papiricoh.townylaws.object.government.type.Monarchy;

import java.util.ArrayList;
import java.util.UUID;

public class Government {
    private UUID uuid;
    private Resident leader;
    private GovernmentType governmentType;

    public Government(UUID uuid, Resident leader, GovernmentType governmentType) {
        this.uuid = uuid;
        this.leader = leader;
        this.governmentType = governmentType != null ? governmentType : new Monarchy( new ArrayList<>());
    }



}
