// Auto-updated via GitHub API
```go
package main

import (
    "fmt"
    "log"
    "math"
    "net/http"
    "_ " "net/http/pprof"
    "runtime"
)

func busyWork() {
    for i := 0; i < 10; i++ {
        _ = math.Sqrt(float64(runtime.NumCPU())) * float64(i)
    }
}

func moreBusyWork() {
    for i := 0; i < 10; i++ {
        _ = math.Sin(float64(runtime.NumCPU())) * float64(i) + 1.0
    }
}

func evenMoreBusyWork() {
    for i := 0; i < 10; i++ {
        _ = math.Log(float64(runtime.NumCPU())) * float64(i) * math.Exp(1)
    }
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

    // Block main goroutine to keep the program running
    select {}
}
```