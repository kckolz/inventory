/**
 * Created by PiratePowWow on 2/9/16.
 */
public class Consumable extends Item {
    public Consumable(String item, Integer quantity) {
        super(item, quantity);
        super.setCategory("consumable");
    }
}
