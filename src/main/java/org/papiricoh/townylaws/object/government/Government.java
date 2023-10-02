package org.papiricoh.townylaws.object.government;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.papiricoh.townylaws.object.government.law.Law;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;
import org.papiricoh.townylaws.object.government.type.GovernmentType;
import org.papiricoh.townylaws.object.government.vote.Vote;

import java.util.ArrayList;
import java.util.UUID;

public class Government {
    private UUID uuid;
    private Resident leader;
    private GovernmentType governmentType;
    protected ArrayList<GovernmentMember> members;
    private Vote currentVote;
    protected ArrayList<Law> laws;

    public Government(UUID uuid, Resident leader, GovernmentType governmentType, ArrayList<GovernmentMember> members, ArrayList<Law>  laws) {
        this.uuid = uuid;
        this.leader = leader;
        this.governmentType = governmentType != null ? governmentType : GovernmentType.ABSOLUTE_MONARCHY;
        this.currentVote = null;
        this.members = members != null ? members : new ArrayList<>();
        this.laws = laws != null ? laws : new ArrayList<>();
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
                this.currentVote = new Vote(governmentType, members);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean finishVote() {
        if(currentVote == null) {
            return false;
        }
        char type = currentVote.getType();
        if(type == 'G') {
            GovernmentType votePassed = currentVote.finishVote();
            if(votePassed == null) {
                return false;
            }
            this.governmentType = votePassed;
            return true;
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

    public UUID getUuid() {
        return uuid;
    }

    public Nation getNation() {
        return TownyUniverse.getInstance().getNation(this.uuid);
    }
}
