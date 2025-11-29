
/*
Frog jump with K distances
A frog wants to climb a staircase with n steps. Given an integer array heights, 
where heights[i] contains the height of the ith step, and an integer k.
To jump from the ith step to the jth step, the frog requires abs(heights[i] - heights[j]) energy,
where abs() denotes the absolute difference. The frog can jump from the ith step to any step
in the range [i + 1, i + k], provided it exists.

Return the minimum amount of energy required by the frog to go from the 0th step to
the (n-1)th step.

Examples:
Input: heights = [10, 5, 20, 0, 15], k = 2

Output: 15

Explanation:

0th step -> 2nd step, cost = abs(10 - 20) = 10

2nd step -> 4th step, cost = abs(20 - 15) = 5

Total cost = 10 + 5 = 15.

Input: heights = [15, 4, 1, 14, 15], k = 3

Output: 2

Explanation:

0th step -> 3rd step, cost = abs(15 - 14) = 1

3rd step -> 4th step, cost = abs(14 - 15) = 1

Total cost = 1 + 1 = 2.
*/
import java.util.*;

public class FrogJumpKDistance {

    /**
     * Recursiev Approach
     * 
     * @param jump
     * @param k
     * @return
     */
    int rec_frogJump(int[] jump, int k) {
        int n = jump.length;
        return rec_frogJump(n - 1, jump, k);
    }

    int rec_frogJump(int ind, int[] jump, int k) {
        if (ind == 0)
            return 0;
        int minCost = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (ind - j >= 0) {
                int cost = rec_frogJump(ind - j, jump, k) + Math.abs(jump[ind] - jump[ind - j]);
                minCost = Math.min(cost, minCost);
            }

        }
        return minCost;
    }

    /**********************/

    /**
     * Memoization Approach
     * @param ind
     * @param jump
     * @param k
     * @param memo
     * @return
     */
    int memo_frogJump(int ind, int[] jump, int k, int[] memo) {
        if (ind == 0)
            return 0;
        if (memo[ind] != -1)
            return memo[ind];
        int minCost = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (ind - j >= 0) {
                int cost = memo_frogJump(ind - j, jump, k, memo) + Math.abs(jump[ind] - jump[ind - j]);
                minCost = Math.min(minCost, cost);
            }
        }
        return memo[ind] = minCost;
    }

    int memo_frogJump(int[] jump, int k) {
        int n = jump.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return memo_frogJump(n - 1, jump, k, memo);
    }

    /**********************/

    /**
     * Tabulation approach
     * @param jump
     * @param k
     * @return
     */
    int tabu_frogJump(int[] jump, int k)
    {
        int n = jump.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for(int i = 1;i<n;i++)
        {
            int minCost = Integer.MAX_VALUE;
            for(int j=1;j<=k;j++)
            {
                if(i-j>=0){
                int cost = dp[i-j] + Math.abs(jump[i]-jump[i-j]);
                minCost = Math.min(minCost,cost);
                }
            }
            dp[i] = minCost;
        }
        return dp[n-1];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        FrogJumpKDistance obj = new FrogJumpKDistance();
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Recursive Approach: " + obj.rec_frogJump(arr, k));
        System.out.println("Memoization Approach: " + obj.memo_frogJump(arr, k));
        System.out.println("Tabulation Approach: " + obj.tabu_frogJump(arr, k));

        sc.close();
    }
}
