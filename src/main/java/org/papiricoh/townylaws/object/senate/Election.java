package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Resident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Election {
    private Map<Resident, Party> votes;

    public Election() {
        this.votes = new HashMap<>();
    }

    public void setVote(Resident res, Party party) {
        this.votes.put(res, party);
    }

    public Map<Party, Integer> finishElections(Map<Party, Integer> oldPartySeats) {
        Map<Party, Integer> votesByParty = new HashMap<>();
        for (Party p : oldPartySeats.keySet()) {
            int numVotes = 0;
            for (Party pv : this.votes.values()) {
                if(pv.equals(p)) {
                    numVotes++;
                }
            }
            votesByParty.put(p, numVotes);
        }


        return assignSeatsDHont(votesByParty, 80); //TODO: CONFIG TO MAX SEATS IN PARLIAMENT
    }

    @Override
    public String toString() {
        String str = "";
        Map<Party, Integer> parties = new HashMap<>();
        for (Party p: this.votes.values()) {
            parties.put(p, parties.get(p) != null ? parties.get(p) + 1 : 1);
        }
        for (Map.Entry<Party, Integer> entry: parties.entrySet()) {
            str += entry.getKey().getName() + " Votes: " + entry.getValue();
        }

        return str;
    }

    public HashMap<Party, Integer> assignSeatsDHont(Map<Party, Integer> votes, int totalSeats) {
        HashMap<Party, List<Double>> quotients = new HashMap<>();
        HashMap<Party, Integer> seats = new HashMap<>();

        // Initialize quotients and seats
        for (Party party : votes.keySet()) {
            quotients.put(party, new ArrayList<>());
            seats.put(party, 0);
            for (int i = 1; i <= totalSeats; i++) {
                quotients.get(party).add((double) votes.get(party) / i);
            }
        }

        // Assign seats
        for (int i = 0; i < totalSeats; i++) {
            Party winningParty = null;
            double maxQuotient = 0;
            for (Party party : quotients.keySet()) {
                for (double quotient : quotients.get(party)) {
                    if (quotient > maxQuotient) {
                        maxQuotient = quotient;
                        winningParty = party;
                    }
                }
            }
            // Assign seat and update quotients
            seats.put(winningParty, seats.get(winningParty) + 1);
            quotients.get(winningParty).remove(maxQuotient);
        }

        return seats;
    }

}
