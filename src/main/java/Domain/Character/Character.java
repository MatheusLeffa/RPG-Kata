package Domain.Character;

import Exceptions.AttackException;
import Repositories.FactionRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Character {
    private int health;
    private int level;
    private boolean isAlive;
    private int range;
    private int position;

    public Character(int position, int range) {
        this.health = 1000;
        this.level = 1;
        this.isAlive = true;
        this.range = range;
        this.position = position;
    }

    public void attack(Character target, double damage) throws Exception {
        if (target == this) {
            throw new AttackException("The character cannot attack itself.");
        }
        if (FactionRepository.isAlly(this, target)) {
            throw new AttackException("The character cannot attack an ally.");
        }
        if (!target.isAlive) {
            throw new AttackException("The target is dead.");
        }
        if (Math.abs(target.position - this.position) > this.range) {
            throw new AttackException("The target is out of range!");
        }

        damage = calculateDamage(target, damage);

        target.health -= (int) damage;
        if (target.health <= 0) {
            target.health = 0;
            target.isAlive = false;
        }
    }

    public void heal(Character target, int heal) {
        if (FactionRepository.isAlly(this, target)) {
            target.health += heal;
            if (target.health > 1000) {
                target.health = 1000;
            }
        }
    }

    public double calculateDamage(Character target, double damage) {
        int levelDiff = this.level - target.level;
        if (levelDiff >= 5) {
            damage *= 0.5;
        } else if (levelDiff <= -5) {
            damage *= 1.5;
        }
        return damage;
    }
}
