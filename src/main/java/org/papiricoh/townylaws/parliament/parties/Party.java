package org.papiricoh.townylaws.parliament.parties;

import java.util.UUID;

public class Party {

    private UUID party_uuid;
    private String party_name;
    private String ideology;

    public Party(String partyName, String ideology) {
        this.party_uuid = UUID.randomUUID();
        this.party_name = partyName;
        this.ideology = ideology;
    }

    public Party(UUID party_uuid, String party_name, String ideology) {
        this.party_uuid = party_uuid;
        this.party_name = party_name;
        this.ideology = ideology;
    }


    public UUID getParty_uuid() {
        return party_uuid;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public String getIdeology() {
        return ideology;
    }

    public void setIdeology(String ideology) {
        this.ideology = ideology;
    }
}
