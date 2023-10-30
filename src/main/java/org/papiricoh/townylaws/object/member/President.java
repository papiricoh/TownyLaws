package org.papiricoh.townylaws.object.member;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;

public class President extends GovernmentMember {
    public President(@NotNull Resident resident) {
        super(resident);
    }

    @Override
    public String getType() {
        return "President";
    }

    @Override
    public int getAuthority() {
        return 10;
    }
}
