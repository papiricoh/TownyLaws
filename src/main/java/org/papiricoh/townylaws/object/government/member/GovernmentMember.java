package org.papiricoh.townylaws.object.government.member;

import com.palmergames.bukkit.towny.object.Resident;

public class GovernmentMember {
    private Resident member;
    private char type; //S -> Senator, M -> Minister

    public GovernmentMember(Resident member, char type) {
        this.member = member;
        if('S' != type && 'M' != type) {
            this.type = 'S';
        }else {
            this.type = type;
        }
    }
}
