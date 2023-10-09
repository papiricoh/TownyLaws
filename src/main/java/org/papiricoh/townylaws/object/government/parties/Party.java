package org.papiricoh.townylaws.object.government.parties;

import com.palmergames.bukkit.towny.object.Resident;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Party {
    private UUID uuid;
    public String name;
    private Resident leader;
    private List<Resident> members;
    private Ideology ideology;

    public Party(UUID uuid, String name, Resident leader, List<Resident> members, Ideology ideology) {
        this.uuid = uuid != null ? uuid : UUID.randomUUID();
        this.name = name;
        this.leader = leader;
        this.members = members != null ? members : new ArrayList<>();
        this.ideology = ideology != null ? ideology : Ideology.CENTRISM;
    }

    public String getName() {
        return this.name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Resident getLeader() {
        return leader;
    }

    public List<Resident> getMembers() {
        return members;
    }

    public boolean isMember(Resident resident) {
        if(this.members.contains(resident) || this.leader.equals(resident)) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (this.leader == null && (this.members == null || this.members.isEmpty())) {
            return true;
        }
        return false;
    }

    public void removeMember(Resident member) {
        if(this.members.contains(member)) {
            this.members.remove(member);
        }else if(this.leader.equals(member)) {
            this.leader = null;
            if(!this.members.isEmpty()) {
                this.leader = this.members.get(0);
                this.members.remove(0);
            }
        }
    }

    public Ideology getIdeology() {
        return ideology;
    }

    public void changeIdeology(Ideology id) {
        this.ideology = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Resident resident) {
        this.members.add(resident);
    }
}
