// Auto-updated via GitHub API
```go
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
		x := math.Sqrt(float64(runtime.NumCPU())) * math.Pow(2.0, 10.0)
		y := math.Sin(float64(runtime.NumCPU())) * math.Cos(float64(runtime.NumCPU()))
		z := math.Log(float64(runtime.NumCPU())) * math.Exp(float64(runtime.NumCPU()))
		if x != float64(x) || y != float64(y) || z != float64(z) {
			return
		}
	}
}

func moreBusyWork() {
	for {
		x := math.Sqrt(float64(runtime.NumCPU())) * math.Pow(2.0, 10.0)
		y := math.Sin(float64(runtime.NumCPU())) * math.Cos(float64(runtime.NumCPU()))
		z := math.Log(float64(runtime.NumCPU())) * math.Exp(float64(runtime.NumCPU()))
		if x != float64(x) || y != float64(y) || z != float64(z) {
			return
		}
	}
}

func evenMoreBusyWork() {
	for {
		x := math.Sqrt(float64(runtime.NumCPU())) * math.Pow(2.0, 10.0)
		y := math.Sin(float64(runtime.NumCPU())) * math.Cos(float64(runtime.NumCPU()))
		z := math.Log(float64(runtime.NumCPU())) * math.Exp(float64(runtime.NumCPU()))
		if x != float64(x) || y != float64(y) || z != float64(z) {
			return
		}
	}
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

	select {}
}
```