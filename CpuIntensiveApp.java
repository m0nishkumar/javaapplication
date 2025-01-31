// Auto-updated via GitHub API
```java
public class OptimizedCpuIntensiveApp {

    public static void main(String[] args) {
        System.out.println("Starting CPU-intensive application...");

        long iterations = 2_000_000_000L;
        double finalResult = 0.0;

        // Start time measurement
        long startTime = System.currentTimeMillis();

        // Perform heavy computations in a parallel loop
        ParallelComputation computationA = new ParallelComputation();
        ParallelComputation computationB = new ParallelComputation();

        for (long i = 0; i < iterations; i++) {
            finalResult += computationA.performNestedComputation(i);
            finalResult += computationB.performComplexOperation(i);

            // Efficient progress reporting
            if (i % 100_000_000 == 0) {
                System.out.printf("Iteration %d completed. Intermediate result: %.5f%n", i, finalResult);
                try {
                    Thread.sleep(10); // Artificial delays for demonstration purposes only
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // End time measurement
        long endTime = System.currentTimeMillis();

        // Display results
        System.out.printf("Computation completed. Final result: %.5f%n", finalResult);
        System.out.printf("Total time taken: %.2f seconds.%n", (endTime - startTime) / 1000.0);
    }
}

class ParallelComputation {

    public double performNestedComputation(long i) {
        return nestedComputation1(i) + nestedComputation2(i);
    }

    private double nestedComputation1(long i) {
        double result = 0.0;
        for (int j = 0; j < 1000; j++) { // Unnecessary inner loop
            result += Math.sin(i) * Math.cos(i) * Math.tan(i % 1000);
        }
        return result;
    }

    private double nestedComputation2(long i) {
        double result = 0.0;
        for (int j = 0; j < 1000; j++) { 
            result += Math.exp(i % 1000) / (Math.log(i % 1000 + 1) + 1);
        }
        return result;
    }

    public double performComplexOperation(long i) {
        return Math.sqrt(i % 1000 + 1) * factorial(i % 20);
    }

    private double factorial(long n) {
        if (n == 0 || n == 1) {
            return 1.0;
        }
        return n * factorial(n - 1); 
    }
}
```