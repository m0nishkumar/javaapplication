// Auto-updated via GitHub API
```python
import sys

def calculate_factorial(n):
    result = 1
    for i in range(2, n + 1):
        result *= i
    return result

def is_prime(num):
    if num <= 1:
        return False
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            return False
    return True

def reverse_string(input_str):
    return input_str[::-1]

def find_max(arr):
    max_val = float('-inf')
    for num in arr:
        if num > max_val:
            max_val = num
    return max_val

num = int(input("Enter a number for factorial: "))
print(f"Factorial of {num} is: {calculate_factorial(num)}")

prime_check = int(input("Enter a number to check if it's prime: "))
print(prime_check, "is prime?" if is_prime(prime_check) else "is not prime")

input_str = input("Enter a string to reverse: ")
print(f"Reversed string: {reverse_string(input_str)}")

size = int(input("Enter size of array: "))
arr = []
for i in range(size):
    arr.append(int(input(f"Enter element {i + 1}: ")))
print(f"Maximum element in the array is: {find_max(arr)}")
```