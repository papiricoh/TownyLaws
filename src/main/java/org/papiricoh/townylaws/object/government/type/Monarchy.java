package org.papiricoh.townylaws.object.government.type;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;

import java.util.ArrayList;

public class Monarchy extends GovernmentType {
    public Monarchy(ArrayList<GovernmentMember> members) {
        super("Absolute Monarchy", members);
    }

    @Override
    public boolean addMember(GovernmentMember governmentMember) {
        return false; //Absolute Monarchies Can't have governments
    }

    @Override
    protected boolean removeMember(GovernmentMember governmentMember) {
        return false; //Absolute Monarchies Can't have governments
    }

    @Override
    protected GovernmentType voteChangeGovernmentType(String type, Resident res) {
        return super.changeGovernmentType(type);
    }
}
