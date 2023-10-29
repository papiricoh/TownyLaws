package org.papiricoh.townylaws.object.member;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class GovernmentMember {
    protected UUID uuid;
    protected Resident resident;

    public GovernmentMember(@NotNull Resident resident) {
        this.resident = resident;
        this.uuid = resident.getUUID();
    }


    public UUID getUuid() {
        return this.uuid;
    }
    public Resident getResident() {
        return this.resident;
    }
    public abstract String getType();
    public abstract int getAuthority();
}
