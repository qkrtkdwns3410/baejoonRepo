import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * packageName    : org.example.알고리즘.RGB거리 fileName       : Main author         : ipeac date :
 * 2024-02-07 description    : =========================================================== DATE
 * AUTHOR             NOTE ----------------------------------------------------------- 2024-02-07
 * ipeac       최초 생성
 */
public class Main {
    
    public static int[][] house;
    public static int[][] dp;
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            house = new int[N][3];
            dp = new int[N][3];
            
            for (int i = 0; i < N; i++) {
                String[] split = br.readLine().split(" ");
                house[i][0] = Integer.parseInt(split[0]);
                house[i][1] = Integer.parseInt(split[1]);
                house[i][2] = Integer.parseInt(split[2]);
            }
            
            initDp();
            
            calcDpCos(N);
            
            int asInt = Arrays.stream(dp[N - 1]).min().getAsInt();
            System.out.println(asInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void calcDpCos(int N) {
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + house[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + house[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + house[i][2];
        }
    }
    
    private static void initDp() {
        dp[0][0] = house[0][0];
        dp[0][1] = house[0][1];
        dp[0][2] = house[0][2];
    }
}