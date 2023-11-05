import java.util.Scanner;

public class TakoyakiOrderingSystem {

    private double totalCost = 0.0;

    public void displayMenu() {
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

    public void displayDrinks() {
        System.out.println("\t\t\t\t+=======================================+");
        System.out.println("\t\t\t\t                  Drinks                 ");
        System.out.println("\t\t\t\t Available canned:                       ");
        System.out.println("\t\t\t\t   1. coke Original 325ml      Php. 34.10");
        System.out.println("\t\t\t\t   2. Coke Zero 325ml          Php. 34.50");
        System.out.println("\t\t\t\t   3. Sprite 325ml             Php. 34.10");
        System.out.println("\t\t\t\t   4. Royal 330ml              Php. 31.10");
        System.out.println("\t\t\t\t   5. Pepsi 320ml              Php. 26.95");
        System.out.println("\t\t\t\t   6. Mountain Dew 320ml       Php. 26.95");
        System.out.println("\t\t\t\t   7. CANCEL                             ");
        System.out.println("\t\t\t\t+=======================================+");
    }

    public void orderTakoyaki(String flavor, double price) {
        Scanner scanQuantity = new Scanner(System.in);
        System.out.println("You selected " + flavor + " Takoyaki.");
        System.out.print("Order Quantity: ");
        int orderQuantity = scanQuantity.nextInt();

        double orderCost = price * orderQuantity;

        totalCost += orderCost;

        System.out.println("Total cost for " + flavor + " Takoyaki: Php. " + orderCost);

        System.out.print("Would you like to add additional items? (Y/N): ");
        Scanner scanAddItems = new Scanner(System.in);
        String addItems = scanAddItems.nextLine();

        if (addItems.equalsIgnoreCase("Y")) {
            do {
                Scanner drinkOrAddTakoyaki = new Scanner(System.in);
                System.out.println("What item would you like to add?");
                System.out.println("1. Drink");
                System.out.println("2. Add New Takoyaki Order");
                int choice1or2 = drinkOrAddTakoyaki.nextInt();

                switch (choice1or2) {
                    case 1:
                        displayDrinks();
                        System.out.print("Select a drink (1-6) or 7 to cancel: ");
                        int drinkChoice = drinkOrAddTakoyaki.nextInt();
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
                            Scanner drinkQ = new Scanner(System.in);
                            System.out.print("Drink Quantity: ");
                            int drinkQuantity = drinkQ.nextInt();
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
                        System.out.println("You've chosen to add a new takoyaki order.");
                        displayMenu();
                        Scanner newOrderScanner = new Scanner(System.in);
                        System.out.print("Your choice for the new takoyaki order: ");
                        int newOrderChoice = newOrderScanner.nextInt();

                        switch (newOrderChoice) {
                            case 1:
                                orderTakoyaki("Classic", 55.00);
                                break;
                            case 2:
                                orderTakoyaki("Octopus", 65.00);
                                break;
                            case 3:
                                orderTakoyaki("Green Onion", 55.00);
                                break;
                            case 4:
                                orderTakoyaki("Cheese", 55.00);
                                break;
                            case 5:
                                orderTakoyaki("Bacon", 65.00);
                                break;
                            case 6:
                                orderTakoyaki("Crab", 65.00);
                                break;
                            case 7:
                                System.out.println("Order canceled.");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }

                // After adding items, ask if the user wants to add more
                System.out.print("Would you like to add more items? (Y/N): ");
                addItems = drinkOrAddTakoyaki.next();

            } while (addItems.equalsIgnoreCase("Y"));

            // Payment handling
            System.out.println("Total cost for your order: Php. " + totalCost);
            System.out.println("Select Payment method: ");
            System.out.println("1. Cash");
            System.out.println("2. Gcash or Maya");
            Scanner paymentScanner = new Scanner(System.in);
            int paymentChoice = paymentScanner.nextInt();

            switch (paymentChoice) {
                case 1:
                    System.out.println("You've chosen to pay with Cash.");
                    handleCashPayment();
                    break;
                case 2:
                    System.out.println("You've chosen to pay with Gcash or Maya.");
                    handleGcashOrMayaPayment();
                    break;
                default:
                    System.out.println("Invalid payment method choice.");
                    break;
            }

        }
    
        if (addItems.equalsIgnoreCase("N")) {
            // Payment handling
            System.out.println("Total cost for your order: Php. " + totalCost);
            System.out.println("Select Payment method: ");
            System.out.println("1. Cash");
            System.out.println("2. Gcash or Maya");
            Scanner paymentScanner = new Scanner(System.in);
            int paymentChoice = paymentScanner.nextInt();
    
            switch (paymentChoice) {
                case 1:
                    System.out.println("You've chosen to pay with Cash.");
                    handleCashPayment();
                    break;
                case 2:
                    System.out.println("You've chosen to pay with Gcash or Maya.");
                    handleGcashOrMayaPayment();
                    break;
                default:
                    System.out.println("Invalid payment method choice.");
                    break;
            }
        }
    
    }

    public void handleCashPayment() {
        System.out.println("This is cash payment");
        // Implement the logic for handling cash payment
        // This can include taking the cash, calculating change, etc.
        // You can add this logic here.
    }

    public void handleGcashOrMayaPayment() {
        System.out.println("This is E=Wallet payment");
        // Implement the logic for handling Gcash or Maya payment
        // This can include scanning QR codes, verifying transactions, etc.
        // You can add this logic here.
    }

    public double getTotalCost() {
        return totalCost;
    }

    public static void main(String[] args) {
        TakoyakiOrderingSystem orderingSystem = new TakoyakiOrderingSystem();
        orderingSystem.displayMenu();
        Scanner scan = new Scanner(System.in);

        System.out.print("Your choice: ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                orderingSystem.orderTakoyaki("Classic", 55.00);
                break;
            case 2:
                orderingSystem.orderTakoyaki("Octopus", 65.00);
                break;
            case 3:
                orderingSystem.orderTakoyaki("Green Onion", 55.00);
                break;
            case 4:
                orderingSystem.orderTakoyaki("Cheese", 55.00);
                break;
            case 5:
                orderingSystem.orderTakoyaki("Bacon", 65.00);
                break;
            case 6:
                orderingSystem.orderTakoyaki("Crab", 65.00);
                break;
            case 7:
                System.out.println("Order canceled.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
}
