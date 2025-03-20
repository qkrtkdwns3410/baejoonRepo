import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName    : 박상준.연습.탑2493
 * fileName       : Main
 * author         : ipeac
 * date           : 25. 3. 20.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 3. 20.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 탑의 수
        final int N = Integer.parseInt(br.readLine());
        
        // N 개 탑들의 높이 배열
        List<Integer> heights = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            heights.add(Integer.parseInt(st.nextToken()));
        }
        
        // 탑 인덱슷
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            // 현재 탑보다 낮은 탑 스택 제거
            while (!stack.isEmpty() && heights.get(stack.peek()) < heights.get(i)) {
                stack.pop();
            }
            
            if (stack.isEmpty()) {
                answer[i] = 0;
            } else {
                answer[i] = stack.peek() + 1;
            }
            
            stack.push(i);
        }
        
        Arrays.stream(answer).forEach(i -> System.out.print(i + " "));
    }
}
