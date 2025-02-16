// Auto-updated via GitHub API
package main

import (
	"fmt"
	"log"
	"math"
	"net/http"
	_ "net/http/pprof"
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

// Optimized factorial function (iterative)
func factorial(n int) int {
	result := 1
	for i := 2; i <= n; i++ {
		result *= i
	}
	return result
}

func main() {
	numCPU := runtime.NumCPU()
	fmt.Printf("Starting infinite CPU-intensive tasks on %d cores...\n", numCPU)

	runtime.GOMAXPROCS(numCPU)

	go func() {
		log.Println(http.ListenAndServe("localhost:6060", nil))
	}()

	for i := 0; i < numCPU; i++ {
		go busyWork()
		go moreBusyWork()
		go evenMoreBusyWork()
	}

	fmt.Println(factorial(10))

	select {}
}