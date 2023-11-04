package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.exceptions.LawsException;
import org.papiricoh.townylaws.object.votableElements.GovernmentChange;
import org.papiricoh.townylaws.object.votableElements.Investiture;
import org.papiricoh.townylaws.object.votableElements.Law;
import org.papiricoh.townylaws.object.senate.members.Senator;
import org.papiricoh.townylaws.object.senate.types.GovernmentType;
import org.papiricoh.townylaws.object.senate.types.VoteType;
import org.papiricoh.townylaws.object.senate.vote.Vote;
import org.papiricoh.townylaws.object.votableElements.VotableElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Senate {
    private Nation nation;
    private Senator primeMinister;
    private List<Senator> senators;
    private Map<Party, Integer> partySeats;
    private List<Law> laws;
    private GovernmentType governmentType;
    private Election currentElection;
    private Vote currentVote;

    public Senate(@NotNull Nation nation, Senator primeMinister, List<Senator> senators, Map<Party, Integer> partySeats, List<Law> laws, GovernmentType governmentType) {
        this.nation = nation;
        this.primeMinister = primeMinister;
        this.senators = senators != null ? senators : new ArrayList<>();
        this.partySeats = partySeats != null ? partySeats : new HashMap<>();
        this.laws = laws != null ? laws : new ArrayList<>();
        this.governmentType = governmentType != null ? governmentType : GovernmentType.ABSOLUTE_MONARCHY;
    }

    private void startNewVote(Resident res, VotableElement ve) throws LawsException {
        if(!this.governmentType.hasSenate) {
            throw new LawsException("Government Type disallows senate voting");
        }
        if(this.currentVote != null) {
            throw new LawsException("A vote is in progress");
        }
        if(this.currentElection != null) {
            throw new LawsException("A election is in progress");
        }
        if(nation.isKing(res) || (this.primeMinister != null && this.primeMinister.equals(res)) || isSenator(res)) {
            this.currentVote = new Vote(this.senators, ve);
        }
    }

    public VotableElement finnishVote() throws LawsException, TownyException {
        if(this.currentVote == null) {
            throw new LawsException("Senate not in session");
        }
        VotableElement ve = this.currentVote.finnishVoteSession();
        if(ve == null) {
            throw new LawsException("Vote for " + this.currentVote.getVotableElement().getTitle() + " Failed");
        }
        this.currentVote = null;
        //TODO: DIFFERENTIATE VOTABLE ELEMENTS
        if(ve.getType().equals("Investiture")) {
            if(this.governmentType.primeMinisterIsRuler) {
                this.primeMinister = null;
                this.nation.setKing(((Investiture) ve).getInvestedSenator().getResident());
            }else {
                this.primeMinister = ((Investiture) ve).getInvestedSenator();
            }
        } else if (ve.getType().equals("GovernmentChange")) {
            GovernmentType gt = ((GovernmentChange) ve).getGovernmentType();
            if(gt.hasSenate) {
                this.currentElection = new Election();
            }
            this.governmentType = gt;
        } else if (ve.getType().equals("Law")) {
            this.laws.add((Law) ve);
        }

        return ve;
    }

    public String getCurrentVoteToString() throws LawsException {
        if(this.currentVote == null) {
            throw new LawsException("Senate not in session");
        }
        return this.currentVote.getVotableElement().getType() + " with title: " +
                this.currentVote.getVotableElement().getTitle();
    }

    public void setVoteAsSenator(Resident res, VoteType vote) throws LawsException {
        if(this.currentVote == null) {
            throw new LawsException("Senate not in session");
        }
        if(!isSenator(res) && !isPrimeMinister(res)) {
            throw new LawsException("You are not a senator");
        }
        this.currentVote.setVote(getSenatorByResident(res), vote);
    }

    public void finishElection() throws LawsException {
        if(this.currentElection == null) {
            throw new LawsException(nation.getFormattedName() + " not in election period");
        }
        this.partySeats = this.currentElection.finishElections(this.partySeats);
        this.senators = new ArrayList<>();
        for (Map.Entry<Party, Integer> entry : this.partySeats.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                if(i >= entry.getKey().getMembers().size()) {
                    break;
                }
                this.senators.add(new Senator(entry.getKey().getMembers().get(i), entry.getKey()));
            }
        }
        this.currentElection = null;
    }

    public Senator getSenatorByResident(Resident res) {
        for (Senator s : this.senators) {
            if(s.getResident().equals(res)) {
                return s;
            }
        }
        return null;
    }

    public boolean isSenator(Resident res) {
        return getSenatorByResident(res) != null;
    }

    public void startNewElections(Resident res) throws LawsException {
        if(!this.governmentType.hasSenate) {
            throw new LawsException("Government Type disallows elections");
        }
        if(this.currentVote != null) {
            throw new LawsException("Vote in senate in progress");
        }
        if(nation.isKing(res) || (this.primeMinister != null && this.primeMinister.equals(res))) {
            this.currentElection = new Election();
        }
    }

    public void voteInCurrentElection(Resident res, Party party) throws LawsException {
        if(this.currentElection == null) {
            throw new LawsException(nation.getFormattedName() + " not in election period");
        }
        if(!this.nation.getResidents().contains(res)) {
            throw new LawsException("You are not a resident of the " + this.nation.getFormattedName());
        }
        this.currentElection.setVote(res, party);
    }

    public boolean isPrimeMinister(Resident res) {
        return this.primeMinister.getResident().equals(res);
    }

    public boolean createNewParty(Resident res, String partyName, Ideology ide) throws LawsException {
        if(!this.governmentType.hasSenate) {
            throw new LawsException("Government Type disallows parties");
        }
        if(this.currentElection != null) {
            throw new LawsException("Election in course new political parties are forbidden");
        }
        if(getPartyByMember(res) != null) {
            return false;
        }
        this.partySeats.put(new Party(null, partyName, res, null, ide), 0);
        return true;
    }

    private Party getPartyByMember(Resident res) {
        for (Party p : this.partySeats.keySet()) {
            if(p.isMember(res)) {
                return p;
            }
        }
        return null;
    }

    public void joinParty(Resident res, String partyName) throws LawsException {
        if(!this.governmentType.hasSenate) {
            throw new LawsException("Government Type disallows parties");
        }
        Party memberParty = getPartyByMember(res);
        if(memberParty != null) {
            throw new LawsException("You already are a member of a party");
        }
        Party p = getPartyByName(partyName);
        if(p == null) {
            throw new LawsException("Party " + partyName + " does not exists");
        }
        p.addMember(res);
        Senator s = getSenatorByResident(res);
        if(s != null) {
            s.setParty(p);
        }
    }

    private Party getPartyByName(String partyName) {
        for (Party p : this.partySeats.keySet()) {
            if(p.getName().equals(partyName)) {
                return p;
            }
        }
        return null;
    }

    private void leaveParty(Resident res) throws LawsException {
        Party p = getPartyByMember(res);
        if(p == null) {
            throw new LawsException("You don't are member of any party");
        }
        boolean isEmpty = p.removeMember(res);
        if(isEmpty) {
            this.partySeats.remove(p);
        }
        Senator s = getSenatorByResident(res);
        if(s != null) {
            s.setParty(null);
        }
    }

    public Nation getNation() {
        return nation;
    }

    public GovernmentType getGovernmentType() {
        return governmentType;
    }

    public String senatorsToString() {
        String senatorsToString = "";
        for (Senator s : this.senators) {
            senatorsToString += "Senator " + s.getResident().getFormattedName() + " - Member of " + s.getParty().getName() + "\n";
        }
        return senatorsToString;
    }

}
