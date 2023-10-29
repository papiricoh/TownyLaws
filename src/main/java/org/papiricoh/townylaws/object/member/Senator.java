package org.papiricoh.townylaws.object.member;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Senator extends GovernmentMember{

    public Senator(@NotNull Resident resident) {
        super(resident);
    }

    @Override
    public String getType() {
        return "Senator";
    }

    @Override
    public int getAuthority() {
        return 1;
    }
}
