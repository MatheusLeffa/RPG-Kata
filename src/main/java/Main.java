import Domain.Character.Character;
import Domain.Character.MeeleCharacter;
import Domain.Character.RangedCharacter;
import Domain.Faction.Faction;
import Repositories.FactionRepository;

public class Main {

    public static void main(String[] args) {
        Character p1 = new RangedCharacter(1);
        Character p2 = new MeeleCharacter(5);

        try {
            FactionRepository.createFaction("Os Batutinhas");
            FactionRepository.createFaction("Os K-popers");

            Faction factionTest = FactionRepository.getFaction("Os Batutinhas");
            Faction factionTest2 = FactionRepository.getFaction("Os K-popers");
            factionTest.addMember(p1);
            factionTest2.addMember(p2);

            factionTest.removeMember(p1);
            System.out.println("Lista de facções: " + FactionRepository.getFactions());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}