package org.papiricoh.townylaws.object.government.parties;

public enum Ideology {
    COMMUNISM("Communism", "Communist"),
    MARXISM_LENINISM("Marxism Leninism", "Marxist Leninist"),
    MARXISM("Marxism", "Marxist"),
    SYNDICALISM("Syndicalism", "Syndicalist"),
    SOCIALISM("Socialism", "Socialist"),
    SOCIAL_DEMOCRATIC("Social Democratic", "Social Democrat"),
    MONARCHISM("Monarchism", "Monarchic"),
    CENTRISM("Centrism", "Centrist"),
    CLASSICAL_LIBERALISM("Classical Liberalism", "Classical Liberal"),
    CORPORATISM("Corporatism", "Corporatist"),
    CONSERVATISM("Conservationism", "Conservationist"),
    NATIONALISM("Nationalism", "Nationalist"),
    FASCISM("Fascism", "Fascist"),
    NATIONAL_SOCIALISM("National Socialism", "National Socialist");

    public final String name;
    public final String person;

    Ideology(String name, String person) {
        this.name = name;
        this.person = person;
    }
}
