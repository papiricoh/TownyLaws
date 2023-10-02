package org.papiricoh.townylaws.object.government.vote;

import org.papiricoh.townylaws.object.government.law.Law;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;
import org.papiricoh.townylaws.object.government.type.GovernmentType;

import java.util.ArrayList;

public class Vote {
    protected ArrayList<GovernmentMember> forVote;
    protected ArrayList<GovernmentMember> againstVote;
    protected ArrayList<GovernmentMember> abstainVote;
    private GovernmentType toGovernment;
    private Law proposedLaw;

    public Vote(GovernmentType governmentType, ArrayList<GovernmentMember> members) {
        this.abstainVote = new ArrayList<>(members);
        this.againstVote = new ArrayList<>();
        this.forVote = new ArrayList<>();
        this.toGovernment = governmentType;
        this.proposedLaw = null;
    }
    public Vote(Law law, ArrayList<GovernmentMember> members) {
        this.abstainVote = new ArrayList<>(members);
        this.againstVote = new ArrayList<>();
        this.forVote = new ArrayList<>();
        this.toGovernment = null;
        this.proposedLaw = law;
    }

    /**
     * Check the type of vote
     * @return char G if is government type; char L if is law
     */
    public char getType() {
        if(toGovernment != null) {
            return 'G';
        }
        return 'L';
    }

    public GovernmentType finishVote() {
        if(getResult()) {
            return this.toGovernment;
        }
        return null;
    }

    private boolean getResult() {
        if(this.forVote.size() > this.againstVote.size()) {
            return true;
        }
        return false;
    }

    public boolean changeVote(GovernmentMember gm, char vote) { //F - for, A - against, N - Abstain
        if(gm == null) {
            return false; //GovernmentMember is null
        }
        if(!this.forVote.contains(gm) && !this.againstVote.contains(gm) && !this.abstainVote.contains(gm) ) {
            return false; //GovernmentMember does not exist
        }
        GovernmentMember ngm = removeOldVote(gm);
        if(vote == 'F') {
            this.forVote.add(ngm);
            return true;
        }else if(vote == 'A') {
            this.againstVote.add(ngm);
            return true;
        }else {
            this.abstainVote.add(ngm);
            return true;
        }
    }

    private GovernmentMember removeOldVote(GovernmentMember gm) {
        if(this.abstainVote.contains(gm)) {
            this.abstainVote.remove(gm);
            return gm;
        }
        if(this.forVote.contains(gm)) {
            this.forVote.remove(gm);
            return gm;
        }
        if(this.againstVote.contains(gm)) {
            this.againstVote.remove(gm);
            return gm;
        }
        return null;
    }
}
