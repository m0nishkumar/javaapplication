import java.util.Scanner;
import java.util.ArrayList;

public class UnoptimizedCodeExample {

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

    // Unoptimized factorial function
    public static int calculateFactorial(int n) {
        if (n == 0 || n == 1) return 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int result = 1;
        for (int i = 0; i < list.size(); i++) {
            result = result * list.get(i);
        }

        // Unnecessary check
        if (result == factorialByRecursion(n)) {
            return result;
        } else {
            return -1;
        }
    }

    // Redundant recursive factorial for comparison
    public static int factorialByRecursion(int n) {
        if (n <= 1) return 1;
        return n * factorialByRecursion(n - 1);
    }

    // Unoptimized prime checking
    public static boolean isPrime(int num) {
        if (num <= 1) return false;

        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                divisors.add(i);
            }
        }

        // Prime check by size of divisors list
        if (divisors.size() == 2) {
            return true;
        } else {
            return false;
        }
    }

    // Unoptimized string reversal
    public static String reverseString(String str) {
        char[] charArray = str.toCharArray();
        ArrayList<Character> reversedList = new ArrayList<>();

        for (int i = charArray.length - 1; i >= 0; i--) {
            reversedList.add(charArray[i]);
        }

        String reversedString = "";
        for (Character ch : reversedList) {
            reversedString += ch;  // Inefficient string concatenation
        }

        return reversedString;
    }

    // Unoptimized maximum finder
    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            boolean isMax = true;

            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    isMax = false;
                    break;
                }
            }

            if (isMax) {
                max = arr[i];
                break;
            }
        }

        return max;
    }
}
