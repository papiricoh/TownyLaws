package org.papiricoh.townylaws.object.government.type;

public enum GovernmentType {
    ABSOLUTE_MONARCHY("Absolute_monarchy", false, false, "Absolute Monarchy"), //NAME, SENATE, POPULATION VOTE
    PARLIAMENTARY_MONARCHY("Parliamentary_monarchy", true, false, "Parliamentary Monarchy"),
    FEDERAL_MONARCHY("Federal_monarchy", true, false, "Federal Monarchy"),
    UNITARY_REPUBLIC("Unitary_republic", true, true, "Unitary Republic"),
    FEDERAL_REPUBLIC("Federal_republic", true, true, "Federal Republic");

    public final String typeName;
    public final boolean hasSenate;
    public final boolean citizensCanVote;
    public final String formatted_name;

    GovernmentType(String typeName, boolean hasSenate, boolean citizensCanVote, String formatted_name) {
        this.typeName = typeName;
        this.hasSenate = hasSenate;
        this.citizensCanVote = citizensCanVote;
        this.formatted_name = formatted_name;
    }
}
