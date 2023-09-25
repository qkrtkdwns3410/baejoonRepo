import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * packageName    : org.example.알고리즘.요격시스템
 * fileName       : Solution
 * author         : ipeac
 * date           : 2023-09-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-25        ipeac       최초 생성
 */
public class Solution {
    
    public int solution(int[][] targets) {
        Objects.requireNonNull(targets, "targets is null");
        int answer = 0;
        // 첫번째 원소를 기준으로 오름차순 정렬 수행
        int[][] sortedTargets = Arrays.stream(targets)
                                      .sorted(Comparator.comparingInt((int[] o) -> o[1])
                                                        .thenComparingInt(o -> o[0]))
                                      .toArray(int[][]::new);
        
        int maxRight = sortedTargets[0][1];
        answer++;
        for (int[] target : sortedTargets) {
            int curLeft = target[0];
            int curRight = target[1];
            if (maxRight <= curLeft) {
                maxRight = curRight;
                answer++;
            }
        }
        
        return answer;
    }
}