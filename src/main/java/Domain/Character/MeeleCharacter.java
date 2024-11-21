package Domain.Character;

public class MeeleCharacter extends Character {
    private static final int RANGE = 2;

    public MeeleCharacter(int position) {
        super(position, RANGE);
    }
}
