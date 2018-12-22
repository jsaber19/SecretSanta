import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

// used CLI to take in input
public class main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        // prompts to find out number of santas
        System.out.println("How many Santas will there be?");
        int SantaNum = kb.nextInt();

        // arraylist that will be used to initialize the santalist
        ArrayList<ListNode> unrandomizedSantas = new ArrayList<ListNode>(SantaNum);

        // gets emails and names for each santa (the amount of santas specified above)
        for (int i = 0; i < SantaNum; i++){
            System.out.println("Please input the name of the next Santa.");
            String name = kb.next();

            System.out.println("Please input " + name + "'s email.");
            String email = kb.next();

            ListNode newSanta = new ListNode(name, email);
            unrandomizedSantas.add(newSanta);
        }

        // makes santalist
        SantaList santas = new SantaList(unrandomizedSantas);

        // prompts user to continue to randomize santa assignments and send out emails
        System.out.println("When you're ready to generate Santas and send out assignments through email, please type 'go'");

        // randomizes and assigns santas
        santas.generateSantas();

        // sends emails
        String cue = kb.next();
        if (cue.equalsIgnoreCase("go")){
            try {
                santas.sendEmails();
            }
            catch (IOException ex) {

            }
            catch (URISyntaxException ex) {

            }
        }
    }
}
