package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Nation;
import org.papiricoh.townylaws.object.party.Party;

import java.util.*;

public class SenateManager {
    private Nation nation;
    private List<Law> laws;
    private List<Party> parties;
    public static final int MAX_SENATORS = 80;
    private Map<UUID, Integer> numberOfVotes;
    private Election currentElection;

    public SenateManager(Nation nation, List<Law> laws, List<Party> parties, Map<UUID, Integer> numberOfVotes) {
        this.nation = nation;
        this.laws = laws != null ? laws : new ArrayList<>();
        this.parties = parties != null ? parties : new ArrayList<>();
        this.numberOfVotes = numberOfVotes != null ? numberOfVotes : new HashMap<>();
    }

    public Party getPartyByName(String name) {
        for (Party p : this.parties) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void addParty(Party party) {
        this.parties.add(party);
        this.numberOfVotes.put(party.getUuid(), 0);
    }

    public void newElection() {
        if(this.parties.size() != 0) {
            this.currentElection = new Election(this.parties, this.nation);
        }
    }

    public Election getCurrentElection() {
        return currentElection;
    }
}
