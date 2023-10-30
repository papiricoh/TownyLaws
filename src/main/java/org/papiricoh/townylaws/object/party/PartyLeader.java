package org.papiricoh.townylaws.object.party;

import com.palmergames.bukkit.towny.object.Resident;

public class PartyLeader extends PartyMember {
    public PartyLeader(Resident res) {
        super(res);
    }

    @Override
    public String getType() {
        return "Leader";
    }
}
