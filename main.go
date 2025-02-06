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
	for {
		_ = math.Sqrt(float64(runtime.NumCPU())) * float64(math.Pow(2.0, 10.0))
	}
}

func moreBusyWork() {
	for {
		_ = float64(math.Sin(runtime.NumCPU())) * float64(math.Cos(runtime.NumCPU()))
	}
}

func evenMoreBusyWork() {
	for {
		_ = float64(math.Log(float64(runtime.NumCPU()))) * math.Exp(float64(runtime.NumCPU()))
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

	runtime.GOMAXPROCS(numCPU)

	go func() {
		log.Println(http.ListenAndServe("localhost:6060", nil)) // Starts the pprof server on port 6060
	}()

	for i := 0; i < numCPU; i++ {
		go busyWork()
		go moreBusyWork()
		go evenMoreBusyWork()
	}

	fmt.Println(factorial(10)) // Output: 3628800

	select {}
}