// Auto-updated via GitHub API
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

func factorial(n int) int {
	if n <= 1 {
		return 1
	}
	m := make([]int, n+1)
	m[0] = 1
	for i := 1; i <= n; i++ {
		m[i] = m[i-1]*i
	}
	return m[n]
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