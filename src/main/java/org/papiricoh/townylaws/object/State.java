package org.papiricoh.townylaws.object;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.member.GovernmentMember;

import java.util.List;
import java.util.UUID;

public abstract class State {
    private UUID uuid;
    private Resident president;
    private List<GovernmentMember> members;
}
