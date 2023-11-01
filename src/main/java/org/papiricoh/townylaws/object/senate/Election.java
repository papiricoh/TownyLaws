package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.party.Party;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Election {
    private List<Party> parties;
    private Nation nation;
    private Map<Resident, Party> votes;
    public static final int MAX_SENATORS = 80;

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

    public Map<Party, Integer> finalizeElection() {
        HashMap<Party, Integer> parties = new HashMap<>();
        for (Party p : this.parties) {
            int voteCounter = 0;
            for (Party vp : this.votes.values()) {
                if(vp.equals(p)) {
                    voteCounter++;
                }
            }
            parties.put(p, voteCounter);
        }
        return generateSeats(parties);
    }

    /**
     * D'Hont partition method
     * @param votes HashMap of parties and total votes
     * @return seats formatted
     */
    private HashMap<Party, Integer> generateSeats(HashMap<Party, Integer> votes) {
        HashMap<Party, List<Double>> contents = new HashMap<>();
        HashMap<Party, Integer> seats = new HashMap<>();

        for (Party p : votes.keySet()) {
            contents.put(p, new ArrayList<>());
            seats.put(p, 0);
            for (int i = 1; i <= MAX_SENATORS; i++) {
                contents.get(p).add((double) votes.get(p) / i);
            }
        }

        for (int i = 0; i < MAX_SENATORS; i++) {
            Party winnerParty = null;
            double maxContent = 0;
            for (Party p : contents.keySet()) {
                for (double content : contents.get(p)) {
                    if (content > maxContent) {
                        maxContent = content;
                        winnerParty = p;
                    }
                }
            }

            seats.put(winnerParty, seats.get(winnerParty) + 1);
            contents.get(winnerParty).remove(maxContent);
        }

        return seats;
    }
}
