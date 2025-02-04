// Auto-updated via GitHub API
```python
import math
import logging
import http.server
import threading
import multiprocessing
import sys

def busy_work():
    while True:
        _ = math.sqrt(multiprocessing.cpu_count()) * (2**10)

def more_busy_work():
    while True:
        _ = math.sin(multiprocessing.cpu_count()) * math.cos(multiprocessing.cpu_count())

def even_more_busy_work():
    while True:
        _ = math.log(multiprocessing.cpu_count()) * math.exp(multiprocessing.cpu_count())

if __name__ == "__main__":
    num_cpu = multiprocessing.cpu_count()
    logging.basicConfig(level=logging.INFO)
    print(f"Starting infinite CPU-intensive tasks on {num_cpu} cores...")

    # Start pprof server
    with http.server.HTTPServer((None, 6060), http.server.SimpleHTTPRequestHandler) as httpd:
        threading.Thread(target=httpd.serve_forever).start()

    for _ in range(num_cpu):
        t1 = threading.Thread(target=busy_work)
        t2 = threading.Thread(target=more_busy_work)
        t3 = threading.Thread(target=even_more_busy_work)

        # Note: Go's gomaxprocs is not directly available in python,
        # we can use multiprocessing.cpu_count() instead
        t1.start()
        t2.start()
        t3.start()

    sys.exit(0)
```