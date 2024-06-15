import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * packageName    : 박상준.주차3.신입사원
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 6. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 15.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        // 테스트 케이스 순환
        for (int t = 0; t < T; t++) {
            // 지원자수
            int N = Integer.parseInt(br.readLine());
            int[][] applicants = new int[N][2];
            
            for (int applyCount = 0; applyCount < N; applyCount++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                
                applicants[applyCount][0] = Integer.parseInt(st.nextToken());
                applicants[applyCount][1] = Integer.parseInt(st.nextToken());
            }
            
            // 첫번째 친구는 무조건 1위라 뽑힐수밖에 없음.
            Arrays.sort(applicants, Comparator.comparingInt(score -> score[0]));
            
            int answer = 1;
            int minInterviewRank = applicants[0][1];
            
            for (int i = 1; i < N; i++) {
                // 최소 인터뷰 랭킹보다 높으면 무조건 둘다 열등한거임
                if (applicants[i][1] < minInterviewRank) {
                    answer++;
                    minInterviewRank = applicants[i][1];
                }
            }
            
            sb.append(answer).append("\n");
        }
        
        System.out.println(sb);
    }
}