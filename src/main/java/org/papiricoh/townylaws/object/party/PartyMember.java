package org.papiricoh.townylaws.object.party;

import com.palmergames.bukkit.towny.object.Resident;

public abstract class PartyMember {
    private Resident resident;

    public PartyMember(Resident res) {
        this.resident = res;
    }

    public Resident getResident() {
        return resident;
    }

    public abstract String getType();
}
