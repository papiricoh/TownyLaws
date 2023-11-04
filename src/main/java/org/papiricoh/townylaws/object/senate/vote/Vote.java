package org.papiricoh.townylaws.object.senate.vote;

import org.papiricoh.townylaws.object.senate.members.Senator;
import org.papiricoh.townylaws.object.senate.types.VoteType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vote {
    private Map<Senator, VoteType> votes;

    public Vote(List<Senator> senators) {
        this.votes = createVotes(senators);
    }

    private Map<Senator, VoteType> createVotes(List<Senator> senators) {
        HashMap<Senator, VoteType> votes = new HashMap<>();
        for (Senator s : senators) {
            votes.put(s, VoteType.ABSTAIN);
        }
        return votes;
    }

    private void setVote(Senator sen, VoteType vote) {
        this.votes.put(sen, vote);
    }
}
