import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : 박상준.주차10.플로이드
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 8. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 8. 14.        ipeac       최초 생성
 */
public class Main {
    static int[][] GRAPH;
    static final int INF = 100000 * 100;
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // 도시의 개수 n
            int n = Integer.parseInt(br.readLine());
            // 버스의 개수 m
            int m = Integer.parseInt(br.readLine());
            
            GRAPH = new int[n + 1][n + 1];
            
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) GRAPH[i][j] = 0;
                    else GRAPH[i][j] = INF;
                }
            }
            
            // m + 2 줄 ... 버스 정보
            // 시작도시 a ;도착도시 b; 한번 타는데 필요한 비용 c;
            StringTokenizer st;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                GRAPH[a][b] = Math.min(GRAPH[a][b], c);
            }
            
            // 플로이드 와셜 알고리즘
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (GRAPH[i][j] > GRAPH[i][k] + GRAPH[k][j]) {
                            GRAPH[i][j] = GRAPH[i][k] + GRAPH[k][j];
                        }
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    sb.append(GRAPH[i][j] == INF ? 0 : GRAPH[i][j]).append(" ");
                }
                
                sb.append("\n");
            }
            
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}