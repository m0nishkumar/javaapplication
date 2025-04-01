package main

import (
	"fmt"
	"log"
	"math"
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

// Optimized factorial function (iterative)
func factorial(n int) int {
	if n < 0 {
		return -1 // Error case: factorial not defined for negative numbers
	}
	if n <= 1 {
		return 1 // Base case: 0! = 1! = 1
	}
	
	result := 1
	for i := 2; i <= n; i++ {
		result *= i
	}
	return result
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

	// Block main goroutine to keep the program running
	select {}
}