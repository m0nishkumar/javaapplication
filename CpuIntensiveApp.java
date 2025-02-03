// Auto-updated via GitHub API
```java
import java.util.Scanner;

public class OptimizedCodeExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number for factorial:");
        int num = scanner.nextInt();
        System.out.println("Factorial of " + num + " is: " + calculateFactorial(num));

        System.out.println("\nEnter a number to check if it's prime:");
        int primeCheck = scanner.nextInt();
        System.out.println(primeCheck + " is prime? " + isPrime(primeCheck));

        System.out.println("\nEnter a string to reverse:");
        String inputString = scanner.next();
        System.out.println("Reversed string: " + reverseString(inputString));

        System.out.println("\nEnter size of array:");
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println("Maximum element in the array: " + findMax(array));

        scanner.close();
    }

    public static long calculateFactorial(int n) {
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
```