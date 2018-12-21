import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        int SantaNum = 0;

        System.out.println("How many Santas will there be?");
        SantaNum = kb.nextInt();

        ArrayList<ListNode> unrandomizedSantas = new ArrayList<ListNode>(SantaNum);

        for (int i = 0; i < SantaNum; i++){
            System.out.println("Please input the name of the next Santa.");
            String name = kb.next();

            System.out.println("Please input " + name + "'s email.");
            String email = kb.next();

            ListNode newSanta = new ListNode(name, email);
            unrandomizedSantas.add(newSanta);
        }

        SantaList santas = new SantaList(unrandomizedSantas);

        System.out.println("When you're ready to generate Santas and send out assignments through email, please type 'go'");
        santas.generateSantas();

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
