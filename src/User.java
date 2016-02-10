import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by PiratePowWow on 2/9/16.
 */
public class User {
    private String name;
    private String password;
    private ArrayList<Item> inventory;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public Item findItem(String name) {
        Optional<Item> item = this.inventory.stream().filter(i -> i.getItem().equals(name)).findFirst();
        if(item.isPresent()) {
            return item.get();
        } else {
            return null;
        }
    }

    public void printInventory() {
        for (Item item : this.inventory){
            System.out.printf("%s: %s [%d]\n", item.getCategory(), item.getItem(), item.getQuantity());
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
