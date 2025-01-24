import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // 삼각형의 크기
            final int N = Integer.parseInt(br.readLine());
            
            // 2 ~ n + 1 정수 삼각형
            int[][] triangle = new int[N + 1][N + 1];
            
            // 1 ~ n 정수 삼각형
            int[][] dp = new int[N + 1][N + 1];
            
            for (int i = 1; i <= N; i++) {
                String[] input = br.readLine().split(" ");
                
                for (int j = 1; j <= i; j++) {
                    triangle[i][j] = Integer.parseInt(input[j - 1]);
                }
            }
            
            dp[1][1] = triangle[1][1];
            
            // 2번째 부터 dp 에 합산 값을 저장한다.
            for (int i = 2; i <= N; i++) {
                for (int j = 1; j <= i; j++) {
                    // 제일 왼쪽의 요소는 항상 오른쪽 대각선 위의 요소만 더해준다.
                    if (j == 1) {
                        dp[i][j] = dp[i - 1][j] + triangle[i][j];
                    }
                    // 제일 오른쪽의 요소는 항상 왼쪽 대각선 위의 요소만 더해준다.
                    else if (j == i) {
                        dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                    }
                    // 그외 요소는 왼쪽, 오른쪽 대각선 위의 요소에 대하여 더해준다.
                    else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                    }
                }
            }
            
            System.out.println(Arrays.stream(dp[N]).max().getAsInt());
        }
    }
}
