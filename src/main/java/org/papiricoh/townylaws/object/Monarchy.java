package org.papiricoh.townylaws.object;

import com.palmergames.bukkit.towny.object.Nation;
import org.papiricoh.townylaws.object.member.GovernmentMember;
import org.papiricoh.townylaws.object.member.President;
import org.papiricoh.townylaws.object.senate.SenateManager;

import java.util.List;

public class Monarchy extends State {

    public Monarchy(Nation nation, President president, List<GovernmentMember> members, SenateManager senateManager) {
        super(nation, president, members, senateManager);
    }

    @Override
    public void proposeLaw(GovernmentMember proposer) {

    }

    @Override
    public String getGovernmentType() {
        return "Monarchy";
    }
}
