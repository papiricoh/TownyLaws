package org.papiricoh.townylaws.object.government;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;
import org.papiricoh.townylaws.object.government.type.GovernmentType;

import java.util.ArrayList;
import java.util.UUID;

public class Government {
    private UUID uuid;
    private Resident leader;
    private GovernmentType governmentType;
    protected ArrayList<GovernmentMember> members;
    private Vote currentVote;

    public Government(UUID uuid, Resident leader, GovernmentType governmentType) {
        this.uuid = uuid;
        this.leader = leader;
        this.governmentType = governmentType != null ? governmentType : GovernmentType.ABSOLUTE_MONARCHY;
        this.currentVote = null;
    }

    public boolean startChangeGovernmentVote(GovernmentType governmentType, Resident proposer) {
        if(!this.governmentType.hasSenate) {
            if(this.leader.equals(proposer)) {
                this.governmentType = governmentType;
                return true;
            }else {
                return false;
            }
        }else if(this.governmentType.hasSenate) {
            if(checkIsPartOfGovernment(proposer)) {

            }
            return false;
        }
        return false;
    }

    private boolean checkIsPartOfGovernment(Resident proposer) {
        for (GovernmentMember m: this.members) {
            if(m.getMember().equals(proposer)) {
                return true;
            }
        }
        return false;
    }

}
