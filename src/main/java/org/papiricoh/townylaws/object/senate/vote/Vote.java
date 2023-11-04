package org.papiricoh.townylaws.object.senate.vote;

import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.object.senate.members.Senator;
import org.papiricoh.townylaws.object.senate.types.VoteType;
import org.papiricoh.townylaws.object.votableElements.VotableElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vote {
    private Map<Senator, VoteType> votes;

    private VotableElement votableElement;

    public Vote(@NotNull List<Senator> senators,@NotNull VotableElement ve) {
        this.votes = createVotes(senators);
        this.votableElement = ve;
    }

    public VotableElement finnishVoteSession() {
        if(checkWin()) {
            return this.votableElement;
        }
        return null;
    }

    public VotableElement getVotableElement() {
        return votableElement;
    }

    private boolean checkWin() {
        int forVotes = 0;
        int againstVotes = 0;
        for (VoteType vt: this.votes.values()) {
            if(vt.equals(VoteType.FOR)) {
                forVotes++;
            } else if (vt.equals(VoteType.AGAINST)) {
                againstVotes++;
            }
        }
        return forVotes - againstVotes > 0;
    }

    private Map<Senator, VoteType> createVotes(List<Senator> senators) {
        HashMap<Senator, VoteType> votes = new HashMap<>();
        for (Senator s : senators) {
            votes.put(s, VoteType.ABSTAIN);
        }
        return votes;
    }

    public void setVote(Senator sen, VoteType vote) {
        this.votes.put(sen, vote);
    }
}
