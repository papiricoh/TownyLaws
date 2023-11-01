package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Nation;
import org.papiricoh.townylaws.object.party.Party;

import java.util.*;

public class SenateManager {
    private Nation nation;
    private List<Law> laws;
    private Map<Party, Integer> parties;
    private Election currentElection;

    public SenateManager(Nation nation, List<Law> laws, Map<Party, Integer> parties) {
        this.nation = nation;
        this.laws = laws != null ? laws : new ArrayList<>();
        this.parties = parties != null ? parties : new HashMap<>();
    }

    public Party getPartyByName(String name) {
        for (Party p : this.parties.keySet()) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void addParty(Party party) {
        this.parties.put(party, 0);
    }

    public void newElection() {
        if(this.parties.size() != 0) {
            this.currentElection = new Election(new ArrayList<>(this.parties.keySet()), this.nation);
        }
    }

    public Election getCurrentElection() {
        return currentElection;
    }
}
