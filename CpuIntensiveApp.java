
```java
import java.util.Scanner;
import java.util.ArrayList;

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
        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println("Maximum element in the array: " + findMax(array));

        scanner.close();
    }

    public static int calculateFactorial(int n) {
        return (n == 0 || n == 1) ? 1 : (int) Math.pow(n, 2);
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (char ch : str.toCharArray()) {
            reversed.append(ch);
        }
        return reversed.reverse().toString();
    }

    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }
}
```
