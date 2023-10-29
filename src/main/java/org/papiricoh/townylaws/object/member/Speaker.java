package org.papiricoh.townylaws.object.member;

import com.palmergames.bukkit.towny.object.Resident;
import org.jetbrains.annotations.NotNull;

public class Speaker extends GovernmentMember{
    public Speaker(@NotNull Resident resident) {
        super(resident);
    }

    @Override
    public String getType() {
        return "Speaker";
    }

    @Override
    public int getAuthority() {
        return 3;
    }
}
