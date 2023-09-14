package org.papiricoh.townylaws.government.types;

public enum GovernmentTypes {
    ABSOLUTE_MONARCHY(false, false),
    PARLIAMENTARY_MONARCHY(true, true),
    FEDERAL_MONARCHY(true, false),
    FEDERAL_REPUBLIC(true, false),
    PARLIAMENTARY_REPUBLIC(true, true),
    CONFEDERATE_REPUBLIC(true, true);

    public final boolean has_parliament;
    public final boolean has_separate_elections;

    GovernmentTypes(boolean hasParliament, boolean hasSeparateElections) {
        has_parliament = hasParliament;
        has_separate_elections = hasSeparateElections;
    }
}
