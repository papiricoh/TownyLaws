package org.papiricoh.townylaws.object.senate;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Party {
    private UUID uuid;
    private String name;
    private Resident leader;
    private List<Resident> members;
    private Ideology ideology;

    public Party(UUID uuid,@NotNull String name, @NotNull Resident leader, List<Resident> members, Ideology ideology) {
        this.uuid = uuid != null ? uuid : UUID.randomUUID();
        this.name = name;
        this.leader = leader;
        this.members = members != null ? members : new ArrayList<>();
        this.ideology = ideology != null ? ideology : Ideology.CENTRISM;
    }

    public void addMember(Resident res) {
        this.members.add(res);
    }

    public boolean isMember(Resident res) {
        return this.leader.equals(res) || this.members.contains(res);
    }

    public boolean isLeader(Resident res) {
        return this.leader.equals(res);
    }

    public boolean removeMember(Resident res) {
        if(this.leader.equals(res)) {
            if(this.members.size() == 0) {
                this.leader = null;
                return true;
            }
            this.leader = this.members.get(0);
            this.members.remove(0);
            return false;
        }
        this.members.remove(res);
        return false;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ideology getIdeology() {
        return ideology;
    }

    public void setIdeology(Ideology ideology) {
        this.ideology = ideology;
    }
}
