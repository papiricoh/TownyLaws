package org.papiricoh.townylaws.object;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.member.GovernmentMember;
import org.papiricoh.townylaws.object.member.President;
import org.papiricoh.townylaws.object.senate.SenateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class State {
    private Nation nation;
    private President president;
    private List<GovernmentMember> members;
    private SenateManager senateManager;

    public State(Nation nation, President president, List<GovernmentMember> members, SenateManager senateManager) {
        this.nation = nation;
        this.president = president != null ? president : null;
        this.members = members != null ? members : new ArrayList<>();
        this.senateManager = senateManager != null ? senateManager : new SenateManager(nation, null, null);
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
            }else {
                return getFromMemberList(res);
            }
        }
        return null;
    }

    private GovernmentMember getFromMemberList(Resident res) {
        for (GovernmentMember m : this.members) {
            if(m.getResident().equals(res)) {
                return m;
            }
        }
        return null;
    }

    public abstract void proposeLaw(GovernmentMember proposer);

    public abstract String getGovernmentType();

    public SenateManager getSenateManager() {
        return senateManager;
    }

    public Nation getNation() {
        return nation;
    }
}
