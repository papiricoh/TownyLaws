package org.papiricoh.townylaws.object;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.member.GovernmentMember;
import org.papiricoh.townylaws.object.member.President;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class State {
    private UUID uuid;
    private President president;
    private List<GovernmentMember> members;

    public State(Nation nation, President president, List<GovernmentMember> members) {
        this.uuid = nation.getUUID();
        this.president = president != null ? president : null;
        this.members = members != null ? members : new ArrayList<>();
    }

    public boolean isMember(Resident res) {
        if(this.president.equals(res) || this.members.stream().anyMatch(member -> res.equals(member.getResident()))) {
            return true;
        }
        return false;
    }
    public GovernmentMember getMember(Resident res) {
        if(isMember(res)) {
            if(this.president.equals(res)) {
                return president;
            }
        }
        return null;
    }

    public abstract void proposeLaw(GovernmentMember proposer);

}
