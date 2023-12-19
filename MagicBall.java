import java.util.Random;
import java.util.Scanner;

public class MagicBall {

    public static void main(String[] args) {
        System.out.println("Enter your Question");
        Scanner scan = new Scanner(System.in);
        String a = scan.nextLine();
        scan.close();

        Random rand = new Random();

        String[] responses = {
                "It is certain.",
                "It is decidedly so.",
                "Without a doubt.",
                "Yes - definitely.",
                "You may rely on it.",
                "As I see it, yes.",
                "Most likely.",
                "Outlook good.",
                "Yes.",
                "Signs point to yes."
        };
        int index = rand.nextInt(responses.length);
        System.out.println(responses[index]);

    }
}
