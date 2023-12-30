import java.util.Arrays;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] costs = new int[n + 1][n + 1];
        
        // 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(costs[i], 100000001); // 요금의 최대값보다 큰 수로 초기화
            costs[i][i] = 0; // 자기 자신으로의 요금은 0
        }
        
        // fares 배열을 사용하여 costs 배열 업데이트
        for (int[] fare : fares) {
            costs[fare[0]][fare[1]] = fare[2];
            costs[fare[1]][fare[0]] = fare[2];
        }
        
        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (costs[i][k] + costs[k][j] < costs[i][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                    }
                }
            }
        }
        
        // 최소 요금 계산
        int answer = costs[s][a] + costs[s][b]; // 각자 이동하는 경우의 요금
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, costs[s][i] + costs[i][a] + costs[i][b]);
        }
        
        return answer;
    }
}