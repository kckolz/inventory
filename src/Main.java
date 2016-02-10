import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by PiratePowWow on 2/8/16.
 */
public class Main {
    private static Scanner scanner = new Scanner((System.in));
    static HashMap<String, User> users = new HashMap<>();


    public static void main(String[] args) throws CategoryException {
        users.put("Bob",new User("Bob", "Smith"));
        users.put("Alice",new User("Alice", "Smith"));
        users.put("Charlie",new User("Charlie", "Work"));

        User user = login();
        selection(user);
    }

    public static void selection(User user) throws CategoryException {
        boolean done = false;
        while(done == false) {
            System.out.println("Please enter your selection:");
            System.out.println("1. Add a new item to your inventory");
            System.out.println("2. Remove an item from your inventory");
            System.out.println("3. Update an item's quantity");
            System.out.println("4. List items in inventory");
            System.out.println("5. Change User");

            String selection = scanner.nextLine();
            switch  (selection){
                case ("1"):
                    addNewItem(user);
                    break;
                case ("2"):
                    System.out.println("What item would you like to remove?:");
                    String itemName = scanner.nextLine();
                    Item item = user.findItem(itemName);
                    if(item != null) {
                        user.removeItem(item);
                        System.out.println("That item has been removed. Returning to main menu.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case ("3"):
                    System.out.println("Which item would you like to update?:");
                    itemName = scanner.nextLine();
                    item = user.findItem(itemName);
                    if(item != null) {
                        System.out.printf("What is the new quantity for your %s?:\n", item);
                        int quantity = Integer.valueOf(scanner.nextLine());
                        item.setQuantity(quantity);
                        System.out.println("Item quantity has been updated. Returning to main menu.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case ("4"):
                    user.printInventory();
                case ("5"):
                    user = login();
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }

    public static User login() {
        boolean loggedIn = false;
        String name = null;
        User user = null;
        while(loggedIn == false) {
            System.out.printf("Please enter login credentials\n Name: ");
            name = scanner.nextLine();
            if ((users.containsKey(name))) {
                user = users.get(name);
                System.out.printf("Password: ");
                String password = scanner.nextLine();
                if(user.getPassword().equals(password)) {
                    System.out.printf("Listing %s's inventory:\n", name);
                    loggedIn = true;
                } else {
                    System.out.println("Invalid password.");
                }
            } else {
                System.out.println("User not found.");
            }
        }
        return user;
    }

    public static void addNewItem(User user) {
        boolean addedItem = false;
        while(addedItem == false) {
            System.out.println("What item would you like to add?:");
            String item = scanner.nextLine();
            System.out.println("How many would you like to add?:");
            int quantity = Integer.valueOf(scanner.nextLine());
            System.out.println("What category is the item?");
            String category = scanner.nextLine();
            try {
                user.addItem(createItem(item, quantity, category));
                System.out.println("Item added. Returning to main menu.");
                addedItem = true;
            } catch(CategoryException e) {
                System.out.println("Invalid Category.");
            }
        }

    }


    public static Item createItem(String name, int quantity, String category) throws CategoryException {
        try {
            Class<?> clazz = Class.forName(category);
            Constructor<?> ctor = clazz.getConstructor(String.class, Integer.class);
            return (Item)ctor.newInstance(name, quantity);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new CategoryException("Error creating class");
        } catch (ClassNotFoundException e) {
            throw new CategoryException("Invalid Category");
        }
    }
}
