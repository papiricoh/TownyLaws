package org.papiricoh.townylaws.object.government.type;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;

import java.util.ArrayList;

public abstract class GovernmentType {
    private String governmentName;
    protected ArrayList<GovernmentMember> members;
    public GovernmentType(String governmentName, ArrayList<GovernmentMember> members) {
        this.governmentName = governmentName;
        this.members = members != null ? members : new ArrayList<>();
    }

    public boolean addNewMember(GovernmentMember governmentMember) {
        if(governmentMember.getType() == 'P') {
            GovernmentMember actualPresident = getPresident();
            if(actualPresident != null) {
                actualPresident.setType('S');
                return addMember(governmentMember);
            }
        }
        return addMember(governmentMember);
    }

    public GovernmentMember getPresident() {
        for (GovernmentMember m : this.members) {
            if(m.getType() == 'P') {
                return m;
            }
        }
        return null;
    }

    protected abstract boolean addMember(GovernmentMember governmentMember);
    protected abstract boolean removeMember(GovernmentMember governmentMember);
    protected abstract GovernmentType voteChangeGovernmentType(String type, Resident res);

    protected GovernmentType changeGovernmentType(String type) {
        if(type.equals("Absolute_Monarchy")) {
            return new Monarchy(null);
        }else if(type.equals("Constitutional_Monarchy")) {
            return new ConstitutionalMonarchy(this.members);
        }
        return null;
    }

    public ArrayList<GovernmentMember> getMembersList() {
        return this.members;
    }

    public GovernmentMember getMember(Resident resident) {
        for (GovernmentMember gm : members) {
            if(gm.getMember().equals(resident)) {
                return gm;
            }
        }
        return null;
    }
}
