/**
 * Created by PiratePowWow on 2/9/16.
 */
public class Weapon extends Item {

    public Weapon(String item, Integer quantity) {
        super(item, quantity);
        super.setCategory("weapon");
    }
}
