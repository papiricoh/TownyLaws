package org.papiricoh.townylaws.object.votableElements;

import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.object.senate.types.GovernmentType;

public class GovernmentChange implements VotableElement {

    private GovernmentType gt;

    public GovernmentChange(@NotNull GovernmentType gt) {
        this.gt = gt;
    }

    public GovernmentType getGovernmentType() {
        return gt;
    }

    @Override
    public String getTitle() {
        return "Change of government to: " + gt.toString().toLowerCase().replace('_', ' ');
    }

    @Override
    public String getType() {
        return "GovernmentChange";
    }
}
