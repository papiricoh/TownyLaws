package org.papiricoh.townylaws.object.government.type;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;

import java.util.ArrayList;

public class ConstitutionalMonarchy extends GovernmentType {

    public ConstitutionalMonarchy(ArrayList<GovernmentMember> members) {
        super("Constitutional Monarchy", members);
    }

    @Override
    protected boolean addMember(GovernmentMember governmentMember) {
        if(!super.members.contains(governmentMember)) {
            super.members.add(governmentMember);
            return true;
        }
        return false;
    }

    @Override
    protected boolean removeMember(GovernmentMember governmentMember) {
        return false;//TODO
    }

    @Override
    protected GovernmentType voteChangeGovernmentType(String type, Resident res) {
        return null;//TODO
    }
}
