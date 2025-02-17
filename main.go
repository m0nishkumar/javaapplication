// Auto-updated via GitHub API
package main
// this line added
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
	numCPU := runtime.NumCPU()
	logNumCPU := math.Log(float64(numCPU))
	expNumCPU := math.Exp(float64(numCPU))
	for {
		_ = logNumCPU * expNumCPU
	}
}

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