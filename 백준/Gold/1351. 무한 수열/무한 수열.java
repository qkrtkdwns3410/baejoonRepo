import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * packageName    : 박상준.주차8.무한수열
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 7. 27.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 7. 27.        ipeac       최초 생성
 */
public class Main {
    static Map<Long, Long> MEMOIZATION = new HashMap<>();
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
            // A n
            long N = Long.parseLong(st.nextToken());
            long P = Long.parseLong(st.nextToken());
            long Q = Long.parseLong(st.nextToken());
            
            MEMOIZATION.put(0L, 1L);
            
            calc(N, P, Q);
            
            System.out.println(MEMOIZATION.get(N));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static long calc(long n, long p, long q) {
        if (n == 0) {
            return 1;
        }
        
        if (MEMOIZATION.containsKey(n)) {
            return MEMOIZATION.get(n);
        }
        
        long left = n / p;
        long right = n / q;
        
        long result = calc(left, p, q) + calc(right, p, q);
        
        MEMOIZATION.put(n, result);
        
        return result;
    }
}