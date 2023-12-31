package org.papiricoh.townylaws.object.senate.members;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.object.senate.Party;

public class Senator {
    private Resident resident;
    private Party party;

    public Senator(@NotNull Resident resident, Party party) {
        this.resident = resident;
        this.party = party;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Resident) {
            return ((Resident) obj).equals(this.resident);
        }else if(obj instanceof Senator) {
            return ((Senator) obj).resident.equals(this.resident);
        }
        return false;
    }

    public Party getParty() {
        return party;
    }

    public Resident getResident() {
        return resident;
    }

    public void setParty(Party p) {
        this.party = p;
    }
}
