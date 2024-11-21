package Domain.Character;

public class RangedCharacter extends Character {
    private static final int RANGE = 20;

    public RangedCharacter(int position) {
        super(position, RANGE);
    }
}
