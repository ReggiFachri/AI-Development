package recursion;

import java.util.Scanner;

public class Recursion {
    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {
            int input = scan.nextInt();
            int result = fact(input);
            System.out.println("Hasil dari " + input + "! " + result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int fact(int n) {
        // Base case
        if (n == 1) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }
}