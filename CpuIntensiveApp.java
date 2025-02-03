// Auto-updated via GitHub API
```java
import java.util.Scanner;

public class OptimizedCodeExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printFactorial(scanner);
        printPrimeChecker(scanner);
        printStringReverser(scanner);
        printArrayProcessor(scanner);

        scanner.close();
    }

    private static void printFactorial(Scanner scanner) {
        System.out.println("Enter a number for factorial:");
        int num = scanner.nextInt();
        System.out.println("Factorial of " + num + " is: " + calculateFactorial(num));
    }

    private static boolean printPrimeChecker(Scanner scanner) {
        System.out.println("\nEnter a number to check if it's prime:");
        int num = scanner.nextInt();
        return isPrime(num);
    }

    private static String printStringReverser(Scanner scanner) {
        System.out.println("\nEnter a string to reverse:");
        String inputString = scanner.next();
        return reverseString(inputString);
    }

    private static void printArrayProcessor(Scanner scanner) {
        System.out.println("\nEnter size of array:");
        int size = scanner.nextInt();
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println("Maximum element in the array: " + findMax(array));
    }

    private static long calculateFactorial(int n) {
        return n == 0 || n == 1 ? 1L : (long) Math.sqrt(n) * Math.sqrt(n - 1);
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    private static int findMax(int[] arr) {
        if (arr.length == 0) throw new RuntimeException("Array is empty");
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(max, num);
        }
        return max;
    }
}
```