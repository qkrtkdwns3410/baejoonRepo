import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : 박상준.연습.동전12293
 * fileName       : Main
 * author         : ipeac
 * date           : 25. 3. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 3. 23.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        // n 가지 동전
        int n = Integer.parseInt(st.nextToken());
        
        // 가치의 합 k 원
        int k = Integer.parseInt(st.nextToken());
        
        int[] coinBox = new int[n];
        
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            coinBox[i] = input;
        }
        
        long[] dp = new long[k + 1];
        dp[0] = 1;
        
        for (int coin : coinBox) {
            for (int start = coin; start <= k; start++) {
                dp[start] += dp[start - coin];
            }
        }
        
        System.out.println(dp[k]);
    }
    
}
