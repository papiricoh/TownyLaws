package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.party.Party;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Election {
    private Map<Party, Integer> parties;
    private Nation nation;

    public Election(List<Party> parties, Nation nation) {
        this.parties = transformParties(parties);
        this.nation = nation;
    }

    private Map<Party, Integer> transformParties(List<Party> parties) {
        HashMap<Party, Integer> partiesMap = new HashMap<>();
        for (Party p: parties) {
            partiesMap.put(p, 0);
        }
        return partiesMap;
    }

    public void setVote() {

    }

    public Nation getNation() {
        return nation;
    }
}
