package org.papiricoh.townylaws.parliament.senator;

import java.util.UUID;

public class Senator {
    private UUID player_uuid;
    private UUID town_uuid;
    private UUID party_uuid;

    public Senator(UUID playerUuid, UUID townUuid) {
        this.player_uuid = playerUuid;
        this.town_uuid = townUuid;
        this.party_uuid = null;
    }

    public Senator(UUID playerUuid, UUID townUuid, UUID partyUuid) {
        this.player_uuid = playerUuid;
        this.town_uuid = townUuid;
        this.party_uuid = partyUuid;
    }


    public UUID getPlayer_uuid() {
        return player_uuid;
    }

    public UUID getTown_uuid() {
        return town_uuid;
    }

    public UUID getParty_uuid() {
        return party_uuid;
    }

    public void setParty_uuid(UUID party_uuid) {
        this.party_uuid = party_uuid;
    }
}
