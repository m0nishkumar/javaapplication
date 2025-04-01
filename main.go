package main

import (
	"errors"
	"fmt"
	"log"
	"math"
	"math/big"
	"net/http"
	_ "net/http/pprof" // Import pprof to enable profiling
	"runtime"
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

// Optimized factorial function with overflow detection
// Returns -1 for invalid inputs (negative numbers) or overflow
func factorial(n int) int {
	// Error handling for negative inputs
	if n < 0 {
		return -1 // Error indicator for negative input
	}
	
	// Base cases
	if n <= 1 {
		return 1 // 0! = 1! = 1 by mathematical definition
	}
	
	// Iterative implementation with overflow detection
	result := 1
	
	// Optimization: use a table for small values
	// This avoids multiplication for common cases
	switch n {
	case 2:
		return 2
	case 3:
		return 6
	case 4:
		return 24
	case 5:
		return 120
	case 6:
		return 720
	case 7:
		return 5040
	case 8:
		return 40320
	case 9:
		return 362880
	case 10:
		return 3628800
	case 11:
		return 39916800
	case 12:
		return 479001600
	}
	
	// For larger values, check for overflow before each multiplication
	for i := 2; i <= n; i++ {
		// Check if this multiplication would cause an overflow
		if result > math.MaxInt32/i {
			return -1 // Error indicator for overflow
		}
		result *= i
	}
	
	return result
}

// Enhanced factorial function for production use with error handling
func factorialWithErrorHandling(n int) (int, error) {
	if n < 0 {
		return 0, errors.New("factorial is not defined for negative numbers")
	}
	
	if n <= 1 {
		return 1, nil
	}
	
	// Int32 can only handle factorial up to 12! without overflow
	if n > 12 {
		return 0, fmt.Errorf("factorial of %d will overflow int32 (max safe value is 12)", n)
	}
	
	result := 1
	for i := 2; i <= n; i++ {
		result *= i
	}
	
	return result, nil
}

// BigFactorial calculates factorial for very large numbers
// Returns a big.Int to handle arbitrarily large results
func BigFactorial(n int64) (*big.Int, error) {
	if n < 0 {
		return nil, errors.New("factorial is not defined for negative numbers")
	}
	
	if n <= 1 {
		return big.NewInt(1), nil
	}
	
	result := big.NewInt(1)
	
	// Use a big.Int for arbitrary precision arithmetic
	for i := int64(2); i <= n; i++ {
		result.Mul(result, big.NewInt(i))
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

	// Example usage of factorial function
	fmt.Println("Regular factorial(10):", factorial(10)) // Output: 3628800
	
	// Example of using the error-handling version
	result, err := factorialWithErrorHandling(12)
	if err != nil {
		fmt.Printf("Error calculating factorial: %v\n", err)
	} else {
		fmt.Printf("Factorial result: %d\n", result)
	}
	
	// Example of using BigFactorial for large numbers
	bigResult, err := BigFactorial(50)
	if err != nil {
		fmt.Printf("Error calculating big factorial: %v\n", err)
	} else {
		fmt.Printf("Factorial of 50: %s\n", bigResult.String())
	}

	// Block main goroutine to keep the program running
	select {}
}