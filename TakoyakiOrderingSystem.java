import java.util.Scanner;
import java.text.DecimalFormat;

public class TakoyakiOrderingSystem {

    private double totalCost = 0.0;

    abstract static class MenuItem {
        private String name;
        private double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public abstract double calculateCost(int quantity);

        public abstract String getCategory();
    }

    static class Takoyaki extends MenuItem {
        public Takoyaki(String name, double price) {
            super(name, price);
        }

        @Override
        public double calculateCost(int quantity) {
            return getPrice() * quantity;
        }

        @Override
        public String getCategory() {
            return "Flavors";
        }
    }

    public void displayMenu(MenuItem[] items) {
        System.out.println("\t\t\t\t+=======================================+");
        System.out.println("\t\t\t\t            GELO'S TAKOYAKI MENU         ");
        System.out.println("\t\t\t\t Flavors:                                ");
        for (int i = 0; i < items.length; i++) {
            System.out.println("\t\t\t\t   " + (i + 1) + ". " + items[i].getName() + " Php. " + items[i].getPrice());
        }
        System.out.println("\t\t\t\t   " + (items.length + 1) + ". CANCEL");
        System.out.println("\t\t\t\t+=======================================+");
    }

    public int getUserChoice(int min, int max) {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Your choice: ");
            choice = scan.nextInt();
            if (choice >= min && choice <= max) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        return choice;
    }

    public int getQuantity() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Order Quantity: ");
        return scan.nextInt();
    }

    public void orderMenuItem(MenuItem item) {
        displayMenu(new MenuItem[] { item });

        int choice = getUserChoice(1, 7);

        if (choice >= 1 && choice <= 6) {
            System.out.println("You selected " + item.getName() + ".");
            int orderQuantity = getQuantity();
            double orderCost = item.calculateCost(orderQuantity);
            totalCost += orderCost;
            System.out.println("Total cost for " + item.getName() + ": Php. " + formatPrice(orderCost));

            System.out.print("Would you like to add additional items? (Y/N): ");
            Scanner scan = new Scanner(System.in);
            String addItems = scan.next();

            if (addItems.equalsIgnoreCase("Y")) {
                addItems(item);
            }
        } else if (choice == 7) {
            System.out.println("Order canceled.");
        }
    }

    public void addItems(MenuItem selectedItem) {
        while (true) {
            System.out.println("What item would you like to add?");
            System.out.println("1. Drink");
            System.out.println("2. Add New " + selectedItem.getCategory());
            System.out.println("3. Finish Order");

            int choice = getUserChoice(1, 3);

            switch (choice) {
                case 1:
                    displayDrinks();
                    int drinkChoice = getUserChoice(1, 7);
                    if (drinkChoice >= 1 && drinkChoice <= 6) {
                        double[] drinkPrices = { 34.10, 34.50, 34.10, 31.10, 26.95, 26.95 };
                        System.out.print("Drink Quantity: ");
                        int drinkQuantity = getQuantity();
                        double drinkCost = drinkPrices[drinkChoice - 1] * drinkQuantity;
                        totalCost += drinkCost;
                        System.out.println("You've added " + drinkQuantity + " " + formatDrink(drinkChoice) +
                                " to your order. Total cost for drinks: Php. " + formatPrice(drinkCost));
                    } else if (drinkChoice == 7) {
                        System.out.println("Drink order canceled.");
                    }
                    break;

                case 2:
                    displayMenu(new MenuItem[] { selectedItem });
                    int newItemChoice = getUserChoice(1, 7);
                    if (newItemChoice >= 1 && newItemChoice <= 6) {
                        orderMenuItem(selectedItem);
                    } else if (newItemChoice == 7) {
                        System.out.println("Add new " + selectedItem.getCategory() + " canceled.");
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void displayTotalCost() {
        System.out.println("Total cost for your order: Php. " + formatPrice(totalCost));
    }

    public void displayDrinks() {
        System.out.println("\t\t\t\t+=======================================+");
        System.out.println("\t\t\t\t                  Drinks                 ");
        System.out.println("\t\t\t\t Available canned:                       ");
        String[] drinkMenu = {
            "Coke Original 325ml",
            "Coke Zero 325ml",
            "Sprite 325ml",
            "Royal 330ml",
            "Pepsi 320ml",
            "Mountain Dew 320ml"
        };
        double[] drinkPrices = { 34.10, 34.50, 34.10, 31.10, 26.95, 26.95 };
        for (int i = 0; i < drinkMenu.length; i++) {
            System.out.println("\t\t\t\t   " + (i + 1) + ". " + drinkMenu[i] + " Php. " + drinkPrices[i]);
        }
        System.out.println("\t\t\t\t   " + (drinkMenu.length + 1) + ". CANCEL");
        System.out.println("\t\t\t\t+=======================================+");
    }

    public String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(price);
    }

    public String formatDrink(int choice) {
        String[] drinkMenu = {
            "Coke Original 325ml",
            "Coke Zero 325ml",
            "Sprite 325ml",
            "Royal 330ml",
            "Pepsi 320ml",
            "Mountain Dew 320ml"
        };
        return drinkMenu[choice - 1];
    }

    public static void main(String[] args) {
        TakoyakiOrderingSystem orderingSystem = new TakoyakiOrderingSystem();
        MenuItem[] takoyakiFlavors = {
            new Takoyaki("Classic 4pcs", 55.00),
            new Takoyaki("Octopus 4pcs", 65.00),
            new Takoyaki("Green Onion 4pcs", 55.00),
            new Takoyaki("Cheese 4pcs", 55.00),
            new Takoyaki("Bacon 4pcs", 65.00),
            new Takoyaki("Crab 4pcs", 65.00)
        };
        orderingSystem.displayMenu(takoyakiFlavors);
        int choice = orderingSystem.getUserChoice(1, takoyakiFlavors.length + 1);

        if (choice >= 1 && choice <= takoyakiFlavors.length) {
            MenuItem selectedTakoyaki = takoyakiFlavors[choice - 1];
            orderingSystem.orderMenuItem(selectedTakoyaki);
        } else if (choice == takoyakiFlavors.length + 1) {
            System.out.println("Order canceled.");
        }
        orderingSystem.displayTotalCost();
    }
}
