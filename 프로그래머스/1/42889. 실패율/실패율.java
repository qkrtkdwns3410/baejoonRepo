import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * packageName    : org.example.알고리즘.실패율
 * fileName       : Solution
 * author         : ipeac
 * date           : 24. 4. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 11.        ipeac       최초 생성
 */
public class Solution {
    public static int[] solution(int N, int[] stages) {
        Map<Integer, Double> results = new HashMap<>();
        int totalCount = stages.length;
        
        Map<Integer, Integer> stageMap = new TreeMap<>();
        
        for (int stage : stages) {
            stageMap.put(stage, stageMap.getOrDefault(stage, 0) + 1);
        }
        
        System.out.println();
        
        for (int i = 1; i <= N; i++) {
            int count = stageMap.getOrDefault(i, 0);
            double failRate = count == 0 ? 0 : (double) count / totalCount;
            totalCount -= count;
            
            results.put(i, failRate);
        }
        
        return IntStream.rangeClosed(1, N)
                .boxed()
                .sorted((s1, s2) -> results.get(s2).compareTo(results.get(s1)))
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    public static void print(Map t, String name) {
        t.entrySet().forEach(System.out::println);
    }
    
    public static void print(int[] ints) {
        Arrays.stream(ints).forEach(System.out::println);
    }
    
    public static <T> void print(T t, String name) {
        System.out.println(name + t);
    }
}