import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class Solution {
    public List<Integer> solution(int[] sequence, int k) {
        Objects.requireNonNull(sequence, "sequence must not be null");
        // sequence 길이 5 이상 1,000, 000 이하
        if (!(5 <= sequence.length && sequence.length <= 1_000_000)) {
            throw new IllegalArgumentException("sequence 길이는 5 이상 1,000, 000 이하입니다.");
        }
        
        // k 5이상 1,000,000,000 이하
        if (!(5 <= k && k <= 1_000_000_000)) {
            throw new IllegalArgumentException("k는 5 이상 1,000,000,000 이하입니다.");
        }
        int[] answer = {};
        // 투 포인터 알고리즘
        List<List<Integer>> subAnswer = findTargetSum(sequence, k);
        List<Integer> shortestAnswer = findShortestAnswer(subAnswer);
        return shortestAnswer;
    }
    
    private List<List<Integer>> findTargetSum(int[] arr, int targetSum) {
        List<List<Integer>> saveIndex = new ArrayList<>();
        int start = 0, end = 0, sum = arr[0], count = 0;
        while (end < arr.length) {
            if (sum == targetSum) {
                saveIndex.add(List.of(start, end));
                sum -= arr[start];
                start++;
            } else if (sum < targetSum) {
                end++;
                if (end < arr.length) {
                    sum += arr[end];
                }
            } else {
                sum -= arr[start];
                start++;
            }
        }
        return saveIndex;
    }
    
    private List<Integer> findShortestAnswer(List<List<Integer>> arr) {
        
        return arr.stream()
                  .min(Comparator.comparingInt(list -> Math.abs(list.get(1) - list.get(0))))
                  .get();
    }
}