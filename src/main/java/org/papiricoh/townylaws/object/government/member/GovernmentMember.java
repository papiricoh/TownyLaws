package org.papiricoh.townylaws.object.government.member;

import com.palmergames.bukkit.towny.object.Resident;

public class GovernmentMember {
    private Resident member;
    private char type; //P -> President/Prime Minister (With monarchy), S -> Senator, M -> Minister

    public GovernmentMember(Resident member, char type) {
        this.member = member;
        if('S' != type && 'M' != type && 'P' != type) {
            this.type = 'S';
        }else {
            this.type = type;
        }
    }

    public Resident getMember() {
        return member;
    }

    public char getType() {
        return type;
    }

    public void setType(char s) {
        this.type = s;
    }
}
