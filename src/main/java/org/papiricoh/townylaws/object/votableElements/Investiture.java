package org.papiricoh.townylaws.object.votableElements;

import org.jetbrains.annotations.NotNull;
import org.papiricoh.townylaws.object.senate.members.Senator;

public class Investiture implements VotableElement{
    private Senator investedSenator;

    public Investiture(@NotNull Senator investedSenator) {
        this.investedSenator = investedSenator;
    }

    @Override
    public String getTitle() {
        return "Investiture of " + investedSenator.getResident().getName() + " of " +
                (investedSenator.getParty() != null ? investedSenator.getParty().getName() : "Independent Party");
    }

    @Override
    public String getType() {
        return "Investiture";
    }

    public Senator getInvestedSenator() {
        return investedSenator;
    }
}
