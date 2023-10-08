package org.papiricoh.townylaws.object.government.member;

import com.palmergames.bukkit.towny.object.Resident;

import java.util.ArrayList;

public class MemberManager {
    protected Resident prime_minister;
    protected ArrayList<Resident> ministers;
    protected ArrayList<Resident> senators;

    public ArrayList<Resident> getAllMembers() {
        ArrayList<Resident> members = new ArrayList<>();
        members.addAll(ministers);
        members.addAll(senators);
        members.add(prime_minister);
        return members;
    }
}
