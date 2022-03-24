import java.util.Scanner;

public class Driver { 
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in);
        System.out.println("Select difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Intermediate");
        System.out.println("3. Expert");
        int diff = in.nextInt();
        Minesweeper ms = new Minesweeper(diff);
        ms.run();
    }
}