package org.papiricoh.townylaws.object.votableElements;

import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.object.senate.Ideology;

import java.util.UUID;

public class Law implements VotableElement {
    private String title;
    private String description;
    private Ideology ideology;

    public Law(@NotNull String title, @NotNull String description, Ideology ideology) {
        this.title = title;
        this.description = description;
        this.ideology = ideology != null ? ideology : Ideology.CENTRISM;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getType() {
        return "Law";
    }

    public String getDescription() {
        return description;
    }

    public Ideology getIdeology() {
        return ideology;
    }
}
