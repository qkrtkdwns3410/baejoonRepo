import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : 박상준.주차9.트리
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 8. 6.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 8. 6.        ipeac       최초 생성
 */
public class Main {
    public static final int DELETED_NODE = -10000;
    static int N;
    static List<Integer> tree = new ArrayList<>();
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            
            String[] input = br.readLine().split(" ");
            
            for (String inp : input) {
                tree.add(Integer.parseInt(inp));
            }
            
            int deletedNodeNumber = Integer.parseInt(br.readLine());
            
            dfs(deletedNodeNumber);
            
            int answer = 0;
            
            for (int i = 0; i < N; i++) {
                if (tree.get(i) != DELETED_NODE && !tree.contains(i)) {
                    answer++;
                }
            }
            
            System.out.println(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void dfs(int deletedNodeNumber) {
        tree.set(deletedNodeNumber, DELETED_NODE);
        
        for (int i = 0; i < N; i++) {
            if (tree.get(i) == deletedNodeNumber) {
                dfs(i);
            }
        }
    }
}