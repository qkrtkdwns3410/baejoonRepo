import java.text.MessageFormat;
import java.util.Arrays;

class Solution {
    public int solution(int[] nums) {
        checkValid(nums);
        int answer = 0;
        
        //개별 원소에 프라임넘버체크
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                int second = nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    int third = nums[k];
                    int sum = first + second + third;
                    if (isPrime(sum)) {
                        answer++;
                    }
                }
            }
        }
        return answer;
    }
    
    private void checkValid(int[] nums) {
        if (!(3 <= nums.length && nums.length <= 50)) {
            String message = MessageFormat.format("nums에 들어있는 숫자의 개수는 3개이상 50개 이하입니다. nums.length= {0}", nums.length);
            throw new IllegalArgumentException(message);
        }
        boolean hasOverElement = Arrays.stream(nums)
                .anyMatch(num -> !(1 <= num && num <= 1000));
        
        if (hasOverElement) {
            throw new IllegalArgumentException("nums의 값은 1 ~ 1000 사이의 값이여야합니다.");
        }
        
        boolean hasDistinctElement = Arrays.stream(nums).distinct().count() != nums.length;
        
        if (hasDistinctElement) {
            throw new IllegalStateException("nums 안에 중복되는 요소가 있습니다");
        }
    }
    
    private boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false; // 소수가 아님
            }
        }
        
        return true;
    }
}