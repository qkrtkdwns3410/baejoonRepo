import java.text.MessageFormat;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(begin, 0));
        while (!queue.isEmpty()) {
            Word curWord = queue.poll();
            
            if (curWord.isSame(target)) {
                answer = curWord.getCnt();
                break;
            }
            
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (visited[i] || !isNeighbor(curWord.getValue(), word)) {
                    continue;
                }
                queue.add(new Word(word, curWord.getCnt() + 1));
                visited[i] = true;  
            }
            
        }
        return answer;
        
    }
    
    private boolean isNeighbor(String curWord, String other) {
        Objects.requireNonNull(curWord, "curWord must not be null");
        Objects.requireNonNull(other, "other must not be null");
        
        int cnt = 0;
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != other.charAt(i)) {
                cnt++;
            }
        }
        return cnt == 1;
    }
    
    public static class Word {
        private final String value;
        private final int cnt;
        
        public Word(String value, int cnt) {
            Objects.requireNonNull(value, "value must not be null");
            if (!(cnt >= 0)) {
                String errMsg = MessageFormat.format("cnt must be greater than or equal to {0}", 0);
                throw new IllegalArgumentException(errMsg);
            }
            this.value = value;
            this.cnt = cnt;
        }
        
        public boolean isSame(String other) {
            Objects.requireNonNull(other, "other must not be null");
            return Objects.equals(this.value, other);
        }
        
        public boolean isSame(Word other) {
            Objects.requireNonNull(other, "other must not be null");
            return Objects.equals(this.value, other.value);
        }
        
        public String getValue() {
            return this.value;
        }
        
        public int getCnt() {
            return cnt;
        }
    }
}