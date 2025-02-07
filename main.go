// Auto-updated via GitHub API
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
	for i := 0; i < math.Pow(2.0, 10.0); i++ {
		_ = math.Sqrt(float64(runtime.NumCPU()))
	}
}

func moreBusyWork() {
	for i := 0; i < math.Sin(float64(math.Pi)); i++ {
		_ = math.Cos(float64(math.Pi))
	}
}

func evenMoreBusyWork() {
	for i := 0; i < math.Log(2.0); i++ {
		_ = math.Exp(float64(math.E))
	}
}

// Unoptimized factorial function (recursive)
func factorial(n int) int {
	if n <= 1 {
		return 1
	}
	return n * factorial(n-1)
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