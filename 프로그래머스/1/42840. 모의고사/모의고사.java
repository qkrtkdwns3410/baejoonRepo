import java.util.*;
import java.util.stream.*;

class Solution {
    
    static int[] one =   {1, 2, 3, 4, 5};
    static int[] two =   {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        int count = answers.length;
        int idx = 0;
        
        while(count-- > 0){
            int ans = answers[idx];
            if(one[idx%one.length] == ans){
                answer[0]++;
            }
            
            if(two[idx%two.length] == ans){
                answer[1]++;
            }
            
            if(three[idx%three.length] == ans){
                answer[2]++;
            }
            
            idx++;
        }
        
        int max = Arrays.stream(answer).max().getAsInt();
        
        return IntStream.range(0, answer.length).filter(i -> answer[i] == max).map(i -> i+1).sorted().toArray();
    }
    
    public static <T> void print(T[] arr){
        for(T a : arr){
            System.out.println(a);
        }
    }
}