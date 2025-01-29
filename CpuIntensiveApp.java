public class CpuIntensiveApp {

    public static void main(String[] args) {
        System.out.println("Starting CPU-intensive application...");

        long iterations = 2_000_000_000L; // Total number of iterations
        double finalResult = 0.0;

        // Start time measurement
        long startTime = System.currentTimeMillis();

        // Use helper classes for computations
        ComputationA computationA = new ComputationA();
        ComputationB computationB = new ComputationB();

        // Perform heavy computations in a loop
        for (long i = 0; i < iterations; i++) {
            finalResult += computationA.performNestedComputation(i);
            finalResult += computationB.performComplexOperation(i);

            // Print progress every 100 million iterations (optional)
            if (i % 100_000_000 == 0) {
                System.out.printf("Iteration %d completed. Intermediate result: %.5f%n", i, finalResult);
            }
        }

        // End time measurement
        long endTime = System.currentTimeMillis();

        // Display results
        System.out.printf("Computation completed. Final result: %.5f%n", finalResult);
        System.out.printf("Total time taken: %.2f seconds.%n", (endTime - startTime) / 1000.0);
    }
}

// Class 1: Handles nested mathematical operations
class ComputationA {

    // Perform nested trigonometric computations
    public double performNestedComputation(long i) {
        return nestedComputation1(i) + nestedComputation2(i);
    }

    // Nested function 1: Trigonometric calculations
    private double nestedComputation1(long i) {
        return Math.sin(i) * Math.cos(i) * Math.tan(i % 1000);
    }

    // Nested function 2: Exponential and logarithmic operations
    private double nestedComputation2(long i) {
        return Math.exp(i % 1000) / (Math.log(i % 1000 + 1) + 1);
    }
}

// Class 2: Handles factorial and square root operations
class ComputationB {

    // Perform complex calculations involving square roots and modulos
    public double performComplexOperation(long i) {
        return Math.sqrt(i % 1000 + 1) * factorial(i % 20);
    }

    // Helper method: Factorial calculation
    private double factorial(long n) {
        double result = 1.0;
        for (long i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
