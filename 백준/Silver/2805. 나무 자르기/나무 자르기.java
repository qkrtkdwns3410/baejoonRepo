import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 *
 *
 *
 *
 *
 *
 *
 * */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] s = br.readLine().split(" ");
            int N = Integer.parseInt(s[0]);
            int M = Integer.parseInt(s[1]);
            
            //나무의 높이  - 나무 높이의 합은 항상 M 보다 크거나 같다.
            int[] trees = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            
            //max
            int max = Arrays.stream(trees)
                    .max().getAsInt();
            
            int answer = getMaxCutter(max, trees, M);
            
            System.out.println(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static int getMaxCutter(int max, int[] trees, int M) {
        int low = 0;
        int high = max;
        int answer = 0;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            long sum = 0;
            
            //절단기보다 위에 있는 부분만 잘림
            for (int tree : trees) {
                if (tree > mid) {
                    sum += (tree - mid);
                }
            }
            
            //해당 잘린 부분이 M 을 넘으면 안됨
            if (M <= sum) {
                low = mid + 1;
                answer = mid;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }
}