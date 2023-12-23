import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Long> collected = Arrays.stream(tangerine)
                .boxed()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        
        List<Map.Entry<Integer, Long>> collect = collected.entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
        
        while (k > 0) {
            long value = collect.get(0).getValue();
            k -= (int) value;
            answer++;
            collect.remove(0);
        }
        
        return answer;
    }
}