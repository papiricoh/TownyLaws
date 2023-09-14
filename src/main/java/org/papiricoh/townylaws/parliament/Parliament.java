package org.papiricoh.townylaws.parliament;

import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.papiricoh.townylaws.parliament.senator.Senator;
import org.papiricoh.townylaws.parliament.types.GovernmentTypes;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;

public class Parliament {
    private final UUID nation_uuid;
    private UUID parliament_president;
    private HashMap<UUID, Senator> senators;
    private GovernmentTypes government_type;

    public Parliament(UUID nation_uuid, Senator king) {
        this.nation_uuid = nation_uuid;
        this.parliament_president = king.getPlayer_uuid();
        this.senators = new HashMap<>();
        this.senators.put(king.getPlayer_uuid(), king);
        this.government_type = GovernmentTypes.ABSOLUTE_MONARCHY;
        this.buildSenate();
    }

    public Parliament(UUID nation_uuid, UUID parliament_president, HashMap<UUID, Senator> senators, GovernmentTypes government_type) {
        this.nation_uuid = nation_uuid;
        this.parliament_president = parliament_president;
        this.senators = senators;
        this.government_type = government_type;
    }

    private void buildSenate() {
        Nation nation = TownyUniverse.getInstance().getNation(this.nation_uuid);
        ArrayList<Town> towns = (ArrayList<Town>) nation.getTowns();
        for (Town town : towns) {
            if(!this.nation_uuid.equals(town.getMayor().getPlayer().getUniqueId())) { //Exempts King
                this.senators.put(town.getMayor().getPlayer().getUniqueId(), new Senator(town.getMayor().getPlayer().getUniqueId(), town.getUUID()));
            }
        }
    }

    public boolean appointSenator(UUID player_uuid) {
        Nation nation = TownyUniverse.getInstance().getNation(this.nation_uuid);
        ArrayList<Town> towns = (ArrayList<Town>) nation.getTowns();
        for (Town town : towns) {
            if(this.checkResident(town, player_uuid)) {
                this.senators.put(player_uuid, new Senator(player_uuid, town.getUUID()));
                return true;
            }
        }

        return false;
    }

    private boolean checkResident(Town town, UUID player_uuid) {
        ArrayList<Resident> residents = (ArrayList<Resident>) town.getResidents();
        for ( Resident resident : residents ) {
            if(resident.getPlayer().getUniqueId().equals(player_uuid)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSenator(UUID player_uuid) {
        return this.senators.containsKey(player_uuid);
    }

    public UUID getParliament_president() {
        return parliament_president;
    }

    public void setParliament_president(UUID parliament_president) {
        this.parliament_president = parliament_president;
    }

    public UUID getNation_uuid() {
        return nation_uuid;
    }

    public String getParliamentName() {
        return "Parliament of the " + TownyUniverse.getInstance().getNation(this.nation_uuid).getFormattedName();
    }

    public GovernmentTypes getGovernment_type() {
        return government_type;
    }

    public void setGovernment_type(GovernmentTypes government_type) {
        this.government_type = government_type;
    }
}
