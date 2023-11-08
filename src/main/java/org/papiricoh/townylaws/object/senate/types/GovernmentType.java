package org.papiricoh.townylaws.object.senate.types;

public enum GovernmentType {
    ABSOLUTE_MONARCHY(false, false, false),
    PARLIAMENTARY_MONARCHY(true, false, false),
    CONSTITUTIONAL_MONARCHY(true, true, false),
    REPUBLIC(true, true, true)
    ;

    public final boolean hasSenate;
    public final boolean hasPrimeMinister;
    public final boolean primeMinisterIsRuler;

    GovernmentType(boolean hasSenate, boolean hasPrimeMinister, boolean primeMinisterIsRuler) {
        this.hasSenate = hasSenate;
        this.hasPrimeMinister = hasPrimeMinister;
        this.primeMinisterIsRuler = primeMinisterIsRuler;
    }

    public String toFormattedString() {
        return this.name().replace('_', ' ');
    }
}
