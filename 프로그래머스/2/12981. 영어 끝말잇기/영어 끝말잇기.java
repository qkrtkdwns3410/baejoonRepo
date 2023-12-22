import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        
        Set<String> wordSet = new HashSet<>();
        
        int count = 0;
        
        char lastChar = words[0].charAt(0);
        
        loop1:
        for (int i = 0; i < words.length; i += n) {
            
            for (int j = 0; j < n; j++) {
                if (i + j >= words.length) {
                    break loop1;
                }
                
                String word = words[i + j];
                
                if (wordSet.contains(word) || lastChar != word.charAt(0)) {
                    answer = new int[]{j + 1, count + 1};
                    return answer;
                }
                lastChar = word.charAt(word.length() - 1);
                wordSet.add(word);
            }
            
            count++;
        }
        
        return answer;
    }
}