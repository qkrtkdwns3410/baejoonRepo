import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * packageName    : org.example.알고리즘.줄세우기
 * fileName       : Solution
 * author         : ipeac
 * date           : 24. 5. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 23.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        
        int[] parent = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < m; i++) {
            s = br.readLine().split(" ");
            int q = Integer.parseInt(s[0]);
            int a = Integer.parseInt(s[1]);
            int b = Integer.parseInt(s[2]);
            
            if (q == 0) {
                union(parent, a, b);
            } else {
                if (isSameParent(parent, a, b)) {
                    sb.append("YES\n");
                    continue;
                }
                
                sb.append("NO\n");
            }
        }
        
        System.out.println(sb);
        
    }
    
    static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent, parent[x]);
    }
    
    static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        
        if (a != b) {
            parent[b] = a;
        }
    }
    
    static boolean isSameParent(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        
        return a == b;
    }
}