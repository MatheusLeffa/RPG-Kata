package Repositories;

import Domain.Character.Character;
import Domain.Faction.Faction;
import Exceptions.FactionsRepositoryException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FactionRepository {

    @Getter
    private static final List<Faction> factions = new ArrayList<>();

    public static void createFaction(String name) throws FactionsRepositoryException {
        for (Faction faction : factions) {
            if (faction.getName().equals(name)) {
                throw new FactionsRepositoryException("Faction already exists.");
            }
        }
        factions.add(new Faction(name));
        System.out.println("Faction {" + name + "} created.");
    }

    public static Faction getFaction(String name) throws FactionsRepositoryException {
        for (Faction faction : factions) {
            if (faction.getName().equals(name)) {
                return faction;
            }
        }
        throw new FactionsRepositoryException("Faction not found.");
    }

    public static boolean isAlly(Character sourceChar, Character targetChar) {
        for (Faction faction : factions) {
            if (faction.getMembers().contains(sourceChar) && faction.getMembers().contains(targetChar)) {
                return true;
            }
        }
        return false;
    }
}
