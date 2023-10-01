package org.papiricoh.townylaws.object.government.type;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.member.GovernmentMember;

import java.util.ArrayList;

public enum GovernmentType {
    ABSOLUTE_MONARCHY("Absolute_monarchy", false, false), //NAME, SENATE, POPULATION VOTE
    PARLIAMENTARY_MONARCHY("Parliamentary_monarchy", true, false);

    public final String typeName;
    public final boolean hasSenate;
    public final boolean citizensCanVote;

    GovernmentType(String typeName, boolean hasSenate, boolean citizensCanVote) {
        this.typeName = typeName;
        this.hasSenate = hasSenate;
        this.citizensCanVote = citizensCanVote;
    }
}
