// Auto-updated via GitHub API
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OptimizedCpuIntensiveApp {

    public static void main(String[] args) {
        System.out.println("Starting CPU-intensive application...");

        long iterations = 2_000_000_000L;
        double finalResult = 0.0;

        ExecutorService executor = Executors.newFixedThreadPool(4);

        ParallelComputation computationA = new ParallelComputation();
        ParallelComputation computationB = new ParallelComputation();

        for (long i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            finalResult += computationA.performNestedComputation(i);
            finalResult += computationB.performComplexOperation(i);

            if (i % 100_000_000 == 0) {
                double endTime = System.nanoTime() - start;
                System.out.printf("Iteration %d completed. Intermediate result: %.5f%n", i, finalResult);
            }
        }

        executor.submit(() -> {
            long endTime = System.nanoTime();
            double totalEndTime = (endTime - System.nanoTime()) / 1e9;
            System.out.printf("Computation completed. Final result: %.5f%n", finalResult);
            System.out.printf("Total time taken: %.2f seconds.%n", totalEndTime);
        });

        executor.shutdown();

        while (!executor.isTerminated()) {
            // Keep the main thread alive
        }
    }

}

class ParallelComputation {

    public double performNestedComputation(long i) {
        return Math.sin(i) * Math.cos(i) * Math.tan(i % 1000);
    }

    public double performComplexOperation(long i) {
        long n = (long) (Math.sqrt((i%1000 + 1)/2));
        if(n == 0 || n==1){
            return 1.0;
        }
        double result = calculateFactorial((int)(i %20));
        return n *result;
    }

    private double calculateFactorial(long n) {
        long limit = Math.min(n, 10000L);
        double result = 1.0;
        for (long i = 2; i <= limit; i++) {
            if (n < i) break;
            result *= i;
        }
        return result;
    }
}
```