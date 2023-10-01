package org.papiricoh.townylaws.object.government.type;

import org.papiricoh.townylaws.object.government.member.GovernmentMember;

import java.util.ArrayList;

public class Monarchy extends GovernmentType {
    public Monarchy(ArrayList<GovernmentMember> members) {
        super("Absolute Monarchy", members);
    }
}
