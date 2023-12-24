import java.util.Arrays;

class Solution {
    
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int boats = 0;
        
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--; // 무거운 사람 탑승
            boats++;
        }
        
        return boats;
    }
}