package org.papiricoh.townylaws.object.government.law;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.parties.Ideology;

public class Law {
    private String title;
    private Ideology ideology;
    private String body;
    private Resident proposedBy;

    public Law(String title, Ideology ideology, String body, Resident resident) {
        this.title = title;
        this.ideology = ideology != null ? ideology : Ideology.CENTRISM;
        this.body = body;
        this.proposedBy = resident;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Ideology getIdeology() {
        return ideology;
    }

    public Resident getProposedBy() {
        return proposedBy;
    }

    @Override
    public String toString() {
        return this.title + " with " + this.ideology.person + " ideology proposed by " + (this.proposedBy != null ? this.proposedBy : "Anonymous");
    }
}
