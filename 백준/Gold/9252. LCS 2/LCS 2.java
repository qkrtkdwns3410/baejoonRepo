import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * packageName    : 박상준.연습.LCS2
 * fileName       : Main
 * author         : Jun
 * date           : 25. 1. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 28.        Jun       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str1 = br.readLine();
            String str2 = br.readLine();
            
            int str1Length = str1.length();
            int str2Length = str2.length();
            
            // 2차원 배열 만들기
            int[][] dp = new int[str1Length + 1][str2Length + 1];
            
            // LCS 길이 테이블 채우기
            for (int i = 1; i <= str1Length; i++) {
                for (int j = 1; j <= str2Length; j++) {
                    // 문자열이 같은 경우
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            
            // 역소거
            StringBuilder answer = new StringBuilder();
            
            int x = str1Length;
            int y = str2Length;
            
            while (x > 0 && y > 0) {
                // 같은 경우 해당 값을 더한다.
                if (str1.charAt(x - 1) == str2.charAt(y - 1)) {
                    answer.append(str1.charAt(x - 1));
                    x--;
                    y--;
                } else if (dp[x - 1][y] > dp[x][y - 1]) {
                    x--;
                } else {
                    y--;
                }
            }
            
            System.out.println(answer.length());
            
            if (answer.length() > 0) {
                System.out.println(answer.reverse());
            }
        }
    }
}
