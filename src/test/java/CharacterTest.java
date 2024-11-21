import Domain.Character.Character;
import Domain.Character.MeeleCharacter;
import Domain.Character.RangedCharacter;
import Exceptions.AttackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    Character p1;
    Character p2;

    @BeforeEach
    public void setUp() {
        this.p1 = new RangedCharacter(1);
        this.p2 = new MeeleCharacter(5);
    }

    @Nested
    class AttackTests {
        @Test
        public void success_Attack_Without_Killing() {
            try {
                int targetInitialHealth = p2.getHealth();
                p1.attack(p2, 100);
                assertEquals(targetInitialHealth - 100, p2.getHealth());
                assertTrue(p2.isAlive());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void success_Attack_Kills() {
            try {
                p1.attack(p2, 1000);
                assertEquals(0, p2.getHealth());
                assertFalse(p2.isAlive());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        @Test
        public void fail_Attack_Dead_Target() {
            p2.setAlive(false);
            assertThrows(AttackException.class, () -> p1.attack(p2, 100));
        }

        @Test
        public void fail_Attack_self() {
            assertThrows(AttackException.class, () -> p1.attack(p1, 100));
        }
    }

    @Nested
    class HealTests {
        @Test
        public void success_Not_Full_Heal() {
            p1.setHealth(100);
            p1.heal(p1, 200);
            assertEquals(300, p1.getHealth());
        }

        @Test
        public void success_Full_Heal() {
            p1.heal(p1, 1001);
            assertEquals(1000, p1.getHealth());
        }
    }

}