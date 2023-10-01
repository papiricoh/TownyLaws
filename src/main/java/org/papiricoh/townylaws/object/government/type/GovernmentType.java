package org.papiricoh.townylaws.object.government.type;

import org.papiricoh.townylaws.object.government.member.GovernmentMember;

import java.util.ArrayList;

public abstract class GovernmentType {
    private String governmentName;
    private ArrayList<GovernmentMember> members;
    public GovernmentType(String governmentName, ArrayList<GovernmentMember> members) {
        this.governmentName = governmentName;
        this.members = members;
    }


}
