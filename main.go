// Auto-updated via GitHub API
```python
import math
import logging
import threading
import socket
import time

def busy_work():
    while True:
        # optimized
        math.sqrt(math.pow(2.0, 10.0)) * math.pow(2.0, 10.0)

def more_busy_work():
    while True:
        # optimized
        math.sin(math.cos(runtime.cpu_count()))

def even_more_busy_work():
    while True:
        # optimized
        math.log(math.exp(runtime.cpu_count()))

# Unoptimized factorial function (recursive)
def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n-1)

if __name__ == "__main__":
    num_cpu = threading.cpu_count()
    logging.basicConfig(level=logging.INFO)

    # Set the maximum number of threads that can be executing simultaneously
    import multiprocessing
    multiprocessing.set_max_threads(num_cpu)

    # Start HTTP server for pprof
    def start_pprof_server():
        socket.socket(socket.AF_INET, socket.SOCK_STREAM).bind(("localhost", 6060))
        logging.info("Starting pprof server on port 6060")
    threading.Thread(target=start_pprofit_server).start()

    # Run the busy work in separate threads with more efficient loop
    for i in range(num_cpu):
        threading.Thread(target=busy_work).start()
        threading.Thread(target=more_busy_work).start()
        threading.Thread(target=even_more_busy_work).start()

    # Example usage of factorial function (you can modify this to test with different inputs)
    print(factorial(10))

    # Keep the program running
    time.sleep(float('inf'))
```