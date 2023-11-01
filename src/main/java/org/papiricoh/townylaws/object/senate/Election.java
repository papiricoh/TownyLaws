package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.party.Party;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Election {
    private List<Party> parties;
    private Nation nation;
    private Map<Resident, Party> votes;

    public Election(List<Party> parties, Nation nation) {
        this.parties = parties;
        this.nation = nation;
        this.votes = generateVotes();
    }

    private Map<Resident, Party> generateVotes() {
        HashMap<Resident, Party> votes = new HashMap<>();
        for (Resident r : this.nation.getResidents()) {
            votes.put(r, null);
        }
        return votes;
    }

    public void setVote(Resident res, Party party) {
        this.votes.replace(res, party);
    }

    public Nation getNation() {
        return nation;
    }
}
