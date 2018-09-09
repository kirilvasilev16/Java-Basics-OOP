import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    public static String gen_number() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for (int i = 0; i < 4; i++) {
            result += numbers.get(i).toString();
        }
        return result;
    }

    public static void main(String[] args) {
        String PC = "", person = "";
        Random r = new Random();
        char[] comp, human;
        int cows = 0, bulls = 0;
        PC = gen_number();
        comp = PC.toCharArray();
        while (bulls != 4) {
            Scanner sc = new Scanner(System.in);
            person = sc.nextLine();
            human = person.toCharArray();
            cows = 0;
            bulls = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (comp[i] == human[j]) {
                        if (i == j) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
            }
            if (bulls == 4) {
                System.out.println("4 bulls. You win!!");
            } else if (bulls != 0 && cows != 0) {
                System.out.println(bulls + " bulls and " + cows + " cows");
            } else if (bulls != 0) {
                System.out.println(bulls + " bulls");
            } else {
                System.out.println(cows + " cows");
            }
        }
    }
}
