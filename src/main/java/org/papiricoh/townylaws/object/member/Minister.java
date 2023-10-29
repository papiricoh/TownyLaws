package org.papiricoh.townylaws.object.member;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;

public class Minister extends GovernmentMember{
    public Minister(@NotNull Resident resident) {
        super(resident);
    }

    @Override
    public String getType() {
        return "Minister";
    }

    @Override
    public int getAuthority() {
        return 2;
    }
}
