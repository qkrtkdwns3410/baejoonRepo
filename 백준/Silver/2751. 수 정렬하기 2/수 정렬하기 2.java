import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * packageName    : 박상준.주차2.수정렬하기2
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 6. 9.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 9.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] nums = new int[N];
        
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        
        QuickSort.run(nums, 0, N - 1);
        
        StringBuilder sb = new StringBuilder();
        
        for (int num : nums) {
            sb.append(num).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static class QuickSort {
        
        public static void run(int[] elements, int start, int end) {
            if (start >= end) return;
            if (start < 0) return;
            if (end > elements.length - 1) return;
            
            int pivotIndex = partition(elements, start, end);
            run(elements, start, pivotIndex - 1);
            run(elements, pivotIndex + 1, end);
        }
        
        private static int partition(int[] elements, int start, int end) {
            // 1. 랜덤한 피봇을 설정합니다.
            Random rand = new Random();
            int randomPivotIndex = rand.nextInt(end - start + 1) + start;
            
            // 2. 피봇을 배열의 마지막 원소와 교환합니다.
            swap(elements, randomPivotIndex, end);
            
            // 3. 피봇을 제외한 나머지 원소들을 비교합니다.
            int pivot = elements[end];
            int left = start;
            int right = end - 1;
            
            while (left <= right) {
                if (elements[left] < pivot) {
                    left++;
                    continue;
                }
                
                swap(elements, left, right);
                right--;
            }
            
            // 4. 피봇을 제자리로 옮깁니다.
            swap(elements, left, end);
            
            // 5. 피봇의 인덱스를 반환합니다.
            return left;
        }
        
        private static void swap(int[] elements, int t1, int t2) {
            int temp = elements[t1];
            elements[t1] = elements[t2];
            elements[t2] = temp;
        }
    }
}