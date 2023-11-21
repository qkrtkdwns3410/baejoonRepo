import java.util.Arrays;

/*
* x	y	n	result
10	40	5	2
10	40	30	1
2	5	4	-1
*  */
public class Solution {
    
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[x] = 0;
        for (int i = x; i < y + 1; i++) {
            // 처음부터 만들어질수없는 수는 접근 X
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            if (i * 2 <= y) {
                dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
            }
            if (i * 3 <= y) {
                dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
            }
            if (i + n <= y) {
                dp[i + n] = Math.min(dp[i] + 1, dp[i + n]);
            }
        }
        return dp[y] == Integer.MAX_VALUE ? -1 : dp[y];
    }
}