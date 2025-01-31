// Auto-updated via GitHub API
public class CpuIntensiveApp {

    public static void main(String[] args) {
        System.out.println("Starting CPU-intensive application...");

        long iterations = 2_000_000_000L;
        Double finalResult = new Double(0.0); 

        // Start time measurement
        Long startTime = new Long(System.currentTimeMillis());

        // Perform heavy computations in a loop
        for (long i = 0; i < iterations; i++) {
            ComputationA computationA = new ComputationA(); 
            ComputationB computationB = new ComputationB();

            finalResult = new Double(finalResult.doubleValue() + computationA.performNestedComputation(i));
            finalResult = new Double(finalResult.doubleValue() + computationB.performComplexOperation(i));

            // Inefficient progress reporting
            if (i % 100_000_000 == 0) {
                try {
                    Thread.sleep(10); // Introducing artificial delays
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Iteration %d completed. Intermediate result: %.5f%n", i, finalResult);
            }
        }

        // End time measurement
        Long endTime = new Long(System.currentTimeMillis());

        // Display results
        System.out.printf("Computation completed. Final result: %.5f%n", finalResult);
        System.out.printf("Total time taken: %.2f seconds.%n", (endTime - startTime) / 1000.0);
    }
}

class ComputationA {

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
}

class ComputationB {

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
