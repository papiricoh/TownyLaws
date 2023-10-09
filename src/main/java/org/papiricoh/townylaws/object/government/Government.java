package org.papiricoh.townylaws.object.government;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.law.Law;
import org.papiricoh.townylaws.object.government.member.MemberManager;
import org.papiricoh.townylaws.object.government.type.GovernmentType;
import org.papiricoh.townylaws.object.government.vote.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Government {
    private UUID uuid;
    private Resident leader;
    private GovernmentType governmentType;
    protected MemberManager memberManager;
    private Vote currentVote;
    private List<Law> laws;

    public Government(UUID uuid, Resident leader, GovernmentType governmentType, ArrayList<Law> laws, MemberManager memberManager) {
        this.uuid = uuid;
        this.leader = leader;
        this.governmentType = governmentType != null ? governmentType : GovernmentType.ABSOLUTE_MONARCHY;
        this.currentVote = null;
        this.laws = laws != null ? laws : new ArrayList<>();
        this.memberManager = memberManager;
    }

    public List<Law> getLaws() {
        return laws;
    }

    public boolean startRevokeLawVote(Law law, Resident proposer) {
        if(!this.governmentType.hasSenate) {
            if(this.leader.equals(proposer)) {
                this.laws.remove(law);
                return true;
            }else {
                return false;
            }
        }else if(this.governmentType.hasSenate) {
            if(checkIsPartOfGovernment(proposer)) {
                this.currentVote = new Vote(law, this.memberManager.getAllMembers(), false);
                return true;
            }
            return false;
        }
        return false;
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
            if(checkIsPartOfGovernment(proposer) || this.leader.equals(proposer)) {
                this.currentVote = new Vote(governmentType, this.memberManager.getAllMembers());
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean startNewLawVote(Law law, Resident proposer) {
        if(!this.governmentType.hasSenate) {
            if(this.leader.equals(proposer)) {
                this.laws.add(law);
                return true;
            }else {
                return false;
            }
        }else if(this.governmentType.hasSenate) {
            if(checkIsPartOfGovernment(proposer)) {
                this.currentVote = new Vote(law, this.memberManager.getAllMembers(), true);
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
            GovernmentType votePassed = currentVote.finishGovernmentVote();
            if(votePassed == null) {
                return false;
            }
            this.governmentType = votePassed;
            this.currentVote = null;
            return true;
        }else if(type == 'L') {
            Law votePassed = currentVote.finishLawVote();
            if(votePassed == null) {
                return false;
            }
            this.laws.add(votePassed);
            this.currentVote = null;
            return true;
        }else if(type == 'R') {
            Law votePassed = currentVote.finishLawVote();
            if(votePassed == null) {
                return false;
            }
            this.laws.remove(votePassed);
            this.currentVote = null;
            return true;
        }
        return false;
    }

    public Vote getCurrentVote() {
        return this.currentVote;
    }

    public boolean checkIsPartOfGovernment(Resident proposer) {
        for (Resident r: this.memberManager.getAllMembers()) {
            if(r.equals(proposer)) {
                return true;
            }
        }
        return false;
    }

    public String governmentTypeToString() {
        return this.governmentType.formatted_name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Nation getGovernmentNation() {
        return TownyUniverse.getInstance().getNation(this.uuid);
    }
}
