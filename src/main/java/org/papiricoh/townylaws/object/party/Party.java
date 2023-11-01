package org.papiricoh.townylaws.object.party;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Party {
    private UUID uuid;
    private String name;
    private Resident leader;
    private List<Resident> members;
    private Ideology ideology;

    public Party(UUID uuid, @NotNull String name, @NotNull Resident leader, List<Resident> members, Ideology ideology) {
        this.uuid = uuid != null ? uuid : UUID.randomUUID();
        this.name = name;
        this.leader = leader;
        this.members = members != null ? members : new ArrayList<>();
        this.ideology = ideology != null ? ideology : Ideology.CENTRISM;
    }

    public void addMember(Resident res) {
        if(!isMember(res)) {
            this.members.add(res);
        }
    }

    /**
     * removes Member in party if the party is empty and the leader is removed the method will return true
     * @param res resident to remove
     * @return true if party does not have any members
     */
    public boolean removeMember(Resident res) {
        if(isLeader(res)){
            if(this.members.size() == 0) {
                this.leader = null;
                return true;
            }else {
                Resident newLeader = this.members.get(0);
                this.leader = newLeader;
                this.members.remove(newLeader);
                return false;
            }
        }else if(isMember(res)) {
            this.members.remove(res);
            return false;
        }
        return false;
    }

    public boolean isMember(Resident res) {
        if(res.equals(leader) || this.members.stream().anyMatch(member -> member.getUUID().equals(res))) {
            return true;
        }
        return false;
    }

    public boolean isLeader(Resident res) {
        return this.leader.equals(res);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Resident getLeader() {
        return leader;
    }

    public void setLeader(Resident leader) {
        this.leader = leader;
    }

    public Ideology getIdeology() {
        return ideology;
    }

    public void setIdeology(Ideology ideology) {
        this.ideology = ideology;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.uuid);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Party) {
            return ((Party) o).uuid.equals(this.uuid);
        }
        return false;
    }
}
