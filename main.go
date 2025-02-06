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
		_ = math.Sqrt(float64(runtime.NumCPU())) * math.Pow(2.0, 10.0)
	}
}

func moreBusyWork() {
	for {
		_ = math.Sin(float64(runtime.NumCPU())) * math.Cos(float64(runtime.NumCPU()))
	}
}

func evenMoreBusyWork() {
	for {
		_ = math.Log(float64(runtime.NumCPU())) * math.Exp(float64(runtime.NumCPU()))
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

	// Run the busy work in separate goroutines using channels to avoid busy waits
	channels := make([]chan bool, numCPU)
	for i := range channels {
		channels[i] = make(chan bool)
		go func() {
			_ = math.Sqrt(float64(runtime.NumCPU())) * math.Pow(2.0, 10.0)
			channels[i] <- true
		}()
	}
	for _, channel := range channels {
		<-channel // Wait for the busy work to finish
	}

	channels = make([]chan bool, numCPU)
	for i := range channels {
		channels[i] = make(chan bool)
		go func() {
			_ = math.Sin(float64(runtime.NumCPU())) * math.Cos(float64(runtime.NumCPU()))
			channels[i] <- true
		}()
	}
	for _, channel := range channels {
		<-channel // Wait for the busy work to finish
	}

	channels = make([]chan bool, numCPU)
	for i := range channels {
		channels[i] = make(chan bool)
		go func() {
			_ = math.Log(float64(runtime.NumCPU())) * math.Exp(float64(runtime.NumCPU()))
			channels[i] <- true
		}()
	}
	for _, channel := range channels {
		<-channel // Wait for the busy work to finish
	}

	fmt.Println(factorial(10)) // Output: 3628800

	// Block main goroutine to keep the program running
	select {}
}