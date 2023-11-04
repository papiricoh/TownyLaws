package org.papiricoh.townylaws.object.votableElements;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Law implements VotableElement {
    private String title;
    private String description;

    public Law(@NotNull String title, @NotNull String description) {
        this.title = title;
        this.description = description;
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
}
