import java.util.Scanner;

public class workingWithNumbers {
    public static void main(String[] args) {
        int myInt = 5;
        double myDouble = 5.99;
        System.out.println(myInt);
        System.out.println(myDouble);

        Scanner scan= new Scanner(System.in);
        System.out.println("Enter a number: ");
        double num1 = scan.nextDouble();
        System.out.println("Enter another number: ");
        double num2 = scan.nextDouble();
        System.out.println(num1 + num2);
        scan.close();

    }
}
