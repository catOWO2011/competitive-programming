# Algorithms Tips

This file contains some sugestions to solve some problems using algorithms

## Monotonic Stack
 Monotonic stack is a stack whose elements are monotonically increasing or descreasing.

 If we need to pop smaller elements from the stack before pushing a new element, the stack is decreasing from bottom to top.

 For example,
 ```java
 Mono-decreasing Stack

Before: [5,4,2,1]
To push 3, we need to pop smaller (or equal) elements first
After: [5,4,3]
 ```

This can help you to solve programming problems like find:
* [Find The Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
* 