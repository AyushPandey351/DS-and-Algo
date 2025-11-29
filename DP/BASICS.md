# 🧠 Dynamic Programming (DP) — Master Notes

## 📌 How to Decide `ind` (Index)

`ind` represents **which state you are solving** in your DP.

### ✔ How to choose `ind`?
1. Understand what state the problem is asking for  
2. Determine the final target state  
3. Build recursion/memoization from that state

### ✔ Common Index → DP Size Rules
```
0 to N-1  → dp size = N
0 to N    → dp size = N+1
1 to N    → dp size = N+1
1 to N-1  → dp size = N
```

👉 **dp size = (max index you access) + 1**

---

## 📌 How to Decide DP Array Size?

1. Find the **largest index** you access  
2. Set  
   ```
   dp size = largest_index + 1
   ```

Examples:
- Using indices `0 … N-1` → size = `N`  
- Using indices `1 … N` → size = `N+1`

---

# 🚀 DP Thinking — The Master Framework

## ⭐ STEP 1 — Define the State (`dp[i]`)

Ask:  
➡ **“What exactly does dp[i] represent?”**

Examples:

### Frog Jump
```java
dp[i] = minimum energy to reach stair i
```

### Fibonacci
```java
dp[i] = ith Fibonacci number
```

### Climbing Stairs
```java
dp[i] = number of ways to reach stair i
```

A good state definition simplifies everything else.

---

## ⭐ STEP 2 — Define Base Cases

Ask:
- What is the smallest sub-problem?
- What happens at `i = 0` and `i = 1`?

Examples:
- Frog Jump → `dp[0] = 0`
- Fibonacci → `dp[0] = 0`, `dp[1] = 1`
- Knapsack → `dp[0][*] = 0`

---

## ⭐ STEP 3 — Define the Recurrence Relation

Ask:

➡ **“How does dp[i] depend on previous states?”**

### Frog Jump Example

To reach stair `i`, you can:

- Come from `i-1`
- Or from `i-2`

```java
dp[i] = min(
    dp[i-1] + abs(h[i] - h[i-1]),
    dp[i-2] + abs(h[i] - h[i-2])
)
```

---

## ⭐ STEP 4 — Decide DP Size & Index Range

Ask:

- From where to where does `i` move?
- What indices do I need to store?

Examples:

### Frog Jump
- i ranges: `0 → N-1`  
- dp size: `N`

### Climbing Stairs
- i ranges: `1 → N`  
- dp size: `N+1`

### Knapsack
- i ranges: `0 → N`  
- w ranges: `0 → W`  
- dp size: `(N+1) × (W+1)`

👉 **DP size always depends on the maximum index used.**

---

# 🎉 Summary

✔ `ind` = the DP state  
✔ dp size = `max_index + 1`  
✔ To solve any DP:
1. Define the state  
2. Define base cases  
3. Write the recurrence  
4. Decide the dp size & range  

This framework works for all DP problems — from Frog Jump to Knapsack to LCS.
