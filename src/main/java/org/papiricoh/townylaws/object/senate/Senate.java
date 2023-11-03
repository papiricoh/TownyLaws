package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.exceptions.LawsException;
import org.papiricoh.townylaws.object.laws.Law;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Senate {
    private Nation nation;
    private Resident primeMinister;
    private List<Senator> senators;
    private Map<Party, Integer> partySeats;
    private List<Law> laws;
    private GovernmentType governmentType;
    private Election currentElection;
    private Vote currentVote;

    public Senate(@NotNull Nation nation, Resident primeMinister, List<Senator> senators, Map<Party, Integer> partySeats, List<Law> laws, GovernmentType governmentType) {
        this.nation = nation;
        this.primeMinister = primeMinister;
        this.senators = senators != null ? senators : new ArrayList<>();
        this.partySeats = partySeats != null ? partySeats : new HashMap<>();
        this.laws = laws != null ? laws : new ArrayList<>();
        this.governmentType = governmentType != null ? governmentType : GovernmentType.ABSOLUTE_MONARCHY;
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
        return this.primeMinister.equals(res);
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
