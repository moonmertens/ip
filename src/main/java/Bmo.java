import java.util.Scanner;

public class Bmo {

    private static final String LINE = "________________________________________________________________";
    private static final String NAME = "BMO";

    public static void main(String[] args) {
        
        System.out.println(LINE);
        Bmo.greet();

        Scanner sc = new Scanner(System.in);
        
        while (true) {
            String input = sc.nextLine();
            System.out.println(LINE);

            if (input.equals("bye")) {
                Bmo.exit();
                break;
            }
            System.out.println(input);
            System.out.println(LINE);
        }


    }

    private static void greet() {
        System.out.println("Hello! I'm " + Bmo.NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }



}
