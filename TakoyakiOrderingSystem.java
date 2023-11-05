import java.util.Scanner;

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
        System.out.println("\t\t\t\t   1. Classic 4pcs             Php. 55.00");
        System.out.println("\t\t\t\t   2. Octopus 4pcs             Php. 65.00");
        System.out.println("\t\t\t\t   3. Green Onion 4pcs         Php. 55.00");
        System.out.println("\t\t\t\t   4. Cheese 4pcs              Php. 55.00");
        System.out.println("\t\t\t\t   5. Bacon 4pcs               Php. 65.00");
        System.out.println("\t\t\t\t   6. Crab 4pcs                Php. 65.00");
        System.out.println("\t\t\t\t   7. CANCEL");
        System.out.println("\t\t\t\t+=======================================+");
        
    }

    public void orderMenuItem(MenuItem item) {
        Scanner scan = new Scanner(System.in);

        displayMenu(new MenuItem[] { item });

        System.out.print("Your choice: ");
        int choice = scan.nextInt();

        if (choice >= 1 && choice <= 1) {
            System.out.println("You selected " + item.getName() + ".");
            System.out.print("Order Quantity: ");
            int orderQuantity = scan.nextInt();

            double orderCost = item.calculateCost(orderQuantity);
            totalCost += orderCost;

            System.out.println("Total cost for " + item.getName() + ": Php. " + orderCost);

            System.out.println("Would you like to add additional items? (Y/N): ");
            String addItems = scan.next();

            if (addItems.equalsIgnoreCase("Y")) {
                addItems(item);
            }
        } else if (choice == 2) {
            System.out.println("Order canceled.");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void addItems(MenuItem selectedItem) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("What item would you like to add?");
            System.out.println("1. Drink");
            System.out.println("2. Add New " + selectedItem.getCategory());
            System.out.println("3. Finish Order");

            System.out.print("Your choice: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    displayDrinks();
                    System.out.print("Select a drink (1-6) or 7 to cancel: ");
                    int drinkChoice = scan.nextInt();
                    if (drinkChoice >= 1 && drinkChoice <= 6) {
                        String[] drinkMenu = {
                            "Coke Original 325ml",
                            "Coke Zero 325ml",
                            "Sprite 325ml",
                            "Royal 330ml",
                            "Pepsi 320ml",
                            "Mountain Dew 320ml"
                        };
                        double[] drinkPrices = { 34.10, 34.50, 34.10, 31.10, 26.95, 26.95 };
                        System.out.print("Drink Quantity: ");
                        int drinkQuantity = scan.nextInt();
                        if (drinkQuantity == 0) {
                            System.out.println("You've added a " + drinkMenu[drinkChoice - 1] + " to your order.");
                            totalCost += drinkPrices[drinkChoice - 1];
                        } else {
                            System.out.println("You've added " + drinkQuantity + " " + drinkMenu[drinkChoice - 1]
                                + " to your order.");
                            totalCost += drinkPrices[drinkChoice - 1] * drinkQuantity;
                        }
                    } else if (drinkChoice == 7) {
                        System.out.println("Drink order canceled.");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 2:
                    orderMenuItem(selectedItem);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void displayTotalCost() {
        System.out.println("Total cost for your order: Php. " + totalCost);
    }

    public void displayDrinks() {
        System.out.println("\t\t\t\t+=======================================+");
        System.out.println("\t\t\t\t                  Drinks                 ");
        System.out.println("\t\t\t\t Available canned:                       ");
        System.out.println("\t\t\t\t   1. Coke Original 325ml      Php. 34.10");
        System.out.println("\t\t\t\t   2. Coke Zero 325ml          Php. 34.50");
        System.out.println("\t\t\t\t   3. Sprite 325ml             Php. 34.10");
        System.out.println("\t\t\t\t   4. Royal 330ml              Php. 31.10");
        System.out.println("\t\t\t\t   5. Pepsi 320ml              Php. 26.95");
        System.out.println("\t\t\t\t   6. Mountain Dew 320ml       Php. 26.95");
        System.out.println("\t\t\t\t   7. CANCEL                             ");
        System.out.println("\t\t\t\t+=======================================+");
    }

    public static void main(String[] args) {
        TakoyakiOrderingSystem orderingSystem = new TakoyakiOrderingSystem();
        MenuItem takoyakiItem = new Takoyaki("Classic 4pcs", 55.00);
        orderingSystem.orderMenuItem(takoyakiItem);
        orderingSystem.displayTotalCost();
    }
}
