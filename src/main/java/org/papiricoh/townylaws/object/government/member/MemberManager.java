package org.papiricoh.townylaws.object.government.member;

import com.palmergames.bukkit.towny.object.Resident;
import org.papiricoh.townylaws.object.government.parties.Party;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    protected Resident prime_minister;
    protected List<Resident> ministers;
    protected List<Resident> senators;
    protected List<Party> parties;

    public MemberManager(Resident prime_minister, ArrayList<Resident> ministers, ArrayList<Resident> senators, ArrayList<Party> parties) {
        this.prime_minister = prime_minister;
        this.ministers = ministers != null ? ministers : new ArrayList<>();
        this.senators = senators != null ? senators : new ArrayList<>();
        this.parties = parties != null ? parties : new ArrayList<>();
    }

    public boolean joinParty(Party party, Resident resident) {
        if(!this.isMember(resident) || this.getPartyByMember(resident) != null || party == null) {
            return false;
        }
        party.addMember(resident);
        return true;
    }

    public boolean createParty(Party party, Resident resident) {
        if(!this.isMember(resident)) {
            return false;
        }
        this.parties.add(party);
        return true;
    }

    public Party getPartyByMember(Resident resident) {
        for (Party p : this.parties) {
            if(p.isMember(resident)) {
                return p;
            }
        }
        return null;
    }

    public List<Party> getParties() {
        return parties;
    }

    public String memberType(Resident resident) {
        if(isMember(resident)) {
            if(this.ministers.contains(resident)) {
                return "Minister";
            }else if(this.ministers.contains(senators)) {
                return "Senator";
            }else if(this.prime_minister.equals(resident)) {
                return "Prime Minister";
            }
        }
        return "";
    }

    public String getMemberName(Resident resident) {
        return this.memberType(resident) + " " + resident.getName();
    }

    public boolean isMember(Resident resident) {
        if(this.ministers.contains(resident) || this.senators.contains(resident) || this.prime_minister.equals(resident)) {
            return true;
        }
        return false;
    }

    public ArrayList<Resident> getAllMembers() {
        ArrayList<Resident> members = new ArrayList<>();
        members.addAll(ministers);
        members.addAll(senators);
        members.add(prime_minister);
        return members;
    }
}
