/*
Problem Statement: Given a number of stairs and a frog, the frog wants to climb from the 0th
stair to the (N-1)th stair. At a time the frog can climb either one or two steps. A height[N]
array is also given. Whenever the frog jumps from a stair i to stair j, the energy consumed
in the jump is abs(height[i]- height[j]), where abs() means the absolute difference.
We need to return the minimum energy that can be used by the frog to jump from stair 0
to stair N-1..
 */

import java.util.*;

public class FrogJump {

    /**
     * Recursive approach
     * 
     * @param arr
     * @return
     */
    int rec_frogJump(int[] arr) {
        return rec_frogJump(arr.length - 1, arr);
    }

    int rec_frogJump(int ind, int[] jump) {
        if (ind == 0)
            return 0;
        int right = rec_frogJump(ind - 1, jump) + Math.abs(jump[ind] - jump[ind - 1]);
        int left = Integer.MAX_VALUE;
        if (ind > 1)
            left = rec_frogJump(ind - 2, jump) + Math.abs(jump[ind] - jump[ind - 2]);

        return Math.min(left, right);
    }

    /*****************************/

    /**
     * Memoization Approach
     * 
     * @param ind
     * @param jump
     * @param memo
     * @return
     */
    int memo_frogJump(int ind, int[] jump, int[] memo) {
        if (ind == 0)
            return 0;
        if (memo[ind] != -1)
            return memo[ind];
        int right = memo_frogJump(ind - 1, jump, memo) + Math.abs(jump[ind] - jump[ind - 1]);
        int left = Integer.MAX_VALUE;
        if (ind > 1)
            left = memo_frogJump(ind - 2, jump, memo) + Math.abs(jump[ind] - jump[ind - 2]);
        memo[ind] = Math.min(left, right);
        return memo[ind];
    }

    int memo_frogJump(int[] jump) {
        int[] memo = new int[jump.length];
        Arrays.fill(memo, -1);
        return memo_frogJump(jump.length - 1, jump, memo);
    }

    /*****************************/

    /**
     * Tabulation approach
     * 
     * @param height
     * @return
     */
    int tab_frogJump(int[] height) {
        int[] dp = new int[height.length];
        dp[0] = 0;
        for (int i = 1; i < height.length; i++) {
            int left = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int right = Integer.MAX_VALUE;
            if (i > 1) {
                right = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            }
            dp[i] = Math.min(left, right);
        }
        return dp[height.length - 1];
    }

    /*****************************/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        FrogJump obj = new FrogJump();
        System.out.println("Recursive Approach: " + obj.rec_frogJump(arr));
        System.out.println("Memoization Approach: " + obj.memo_frogJump(arr));
        System.out.println("Tabulation Approach: " + obj.tab_frogJump(arr));

        sc.close();
    }
}