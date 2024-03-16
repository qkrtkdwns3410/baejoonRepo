import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * packageName    : org.example.알고리즘.두용액
 * fileName       : Main
 * author         : ipeac
 * date           : 2024-03-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-17        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(input[i]);
            }
            
            Arrays.sort(arr);
            
            int left = 0;
            int right = N - 1;
            int min = Integer.MAX_VALUE;
            int resultLeft = 0;
            int resultRight = 0;
            
            while (left < right) {
                int sum = arr[left] + arr[right];
                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    resultLeft = arr[left];
                    resultRight = arr[right];
                }
                
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
            
            System.out.println(resultLeft + " " + resultRight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}