package main

import (
	"fmt"
	"log"
	"math"
	"net/http"
	_ "net/http/pprof" // Import pprof to enable profiling
	"runtime"
	"errors"
)

func busyWork() {
	for {
		_ = math.Sqrt(float64(runtime.NumCPU())) * math.Pow(2.0, 10.0)
	}
}

func moreBusyWork() {
	for {
		_ = math.Sin(float64(runtime.NumCPU())) * math.Cos(float64(runtime.NumCPU()))
	}
}

func evenMoreBusyWork() {
	// Pre-compute constants outside the loop
	cpuCount := float64(runtime.NumCPU())
	logCPU := math.Log(cpuCount)
	expCPU := math.Exp(cpuCount)
	result := logCPU * expCPU
	
	// Use a more efficient approach that still consumes CPU but doesn't waste as many resources
	for {
		// Use the pre-computed value and add a small variation to prevent compiler optimizations
		_ = result + math.Sin(result)*0.000001
		// Optional: Add a small sleep to reduce CPU pressure if desired
		// time.Sleep(time.Microsecond)
	}
}

// Original recursive factorial function - kept for reference
// func factorial(n int) int {
// 	if n <= 1 {
// 		return 1
// 	}
// 	return n * factorial(n-1)
// }

// Optimized factorial function with proper error handling
// Returns -1 for invalid inputs (negative numbers)
// For large inputs, be aware of integer overflow
func factorial(n int) int {
	// Error handling for negative inputs
	if n < 0 {
		// In a production environment, consider using error return values
		// e.g., func factorial(n int) (int, error)
		return -1 // Or math.MinInt for a clear error indicator
	}
	
	// Base cases
	if n <= 1 {
		return 1 // 0! = 1! = 1 by mathematical definition
	}
	
	// Iterative implementation to avoid recursion overhead and stack limitations
	result := 1
	
	// Optimization: Start from 2 since multiplying by 1 is unnecessary
	for i := 2; i <= n; i++ {
		// In a production environment, consider checking for overflow
		// e.g., if result > math.MaxInt/i { return -1, errors.New("integer overflow") }
		result *= i
	}
	
	return result
}

// Enhanced factorial function for production use with error handling
// Not used in the current implementation but shown as an example of a more robust approach
func factorialWithErrorHandling(n int) (int, error) {
	if n < 0 {
		return 0, errors.New("factorial is not defined for negative numbers")
	}
	
	if n <= 1 {
		return 1, nil
	}
	
	result := 1
	for i := 2; i <= n; i++ {
		// Check for potential overflow before performing multiplication
		if result > math.MaxInt32/i {
			return 0, errors.New("factorial result too large for int type")
		}
		result *= i
	}
	
	return result, nil
}

func main() {
	numCPU := runtime.NumCPU()
	fmt.Printf("Starting infinite CPU-intensive tasks on %d cores...\n", numCPU)

	// Set the maximum number of CPUs that can be executing simultaneously
	runtime.GOMAXPROCS(numCPU)

	// Start HTTP server for pprof
	go func() {
		log.Println(http.ListenAndServe("localhost:6060", nil)) // Starts the pprof server on port 6060
	}()

	// Run the busy work in separate goroutines
	for i := 0; i < numCPU; i++ {
		go busyWork()
		go moreBusyWork()
		go evenMoreBusyWork()
	}

	// Example usage of factorial function (you can modify this to test with different inputs)
	fmt.Println(factorial(10)) // Output: 3628800
	
	// Example of using the error-handling version (commented out as it's not used in the main flow)
	// result, err := factorialWithErrorHandling(20)
	// if err != nil {
	//     fmt.Printf("Error calculating factorial: %v\n", err)
	// } else {
	//     fmt.Printf("Factorial result: %d\n", result)
	// }

	// Block main goroutine to keep the program running
	select {}
}