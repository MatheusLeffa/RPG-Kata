package Domain.Faction;

import Domain.Character.Character;
import Exceptions.FactionManagementException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Faction {

    private String name;
    private List<Character> members = new ArrayList<>();

    public Faction(String name) {
        this.name = name;
    }

    public void addMember(Character character) throws Exception {
        if (this.members.contains(character)) {
            throw new FactionManagementException("The character is already in the faction.");
        }
        this.members.add(character);
        System.out.println("Character {" + character + "} added to the faction {" + this.name + "}");
    }

    public void removeMember(Character character) throws Exception {
        if (!this.members.contains(character)) {
            throw new FactionManagementException("The character is not in the faction.");
        }
        this.members.remove(character);
        System.out.println("Character {" + character + "} removed from the faction {" + this.name + "}.");
    }

}
