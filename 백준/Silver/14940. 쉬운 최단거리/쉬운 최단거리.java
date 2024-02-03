import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * packageName    : org.example.알고리즘.쉬운최단거리 fileName       : Main author         : ipeac date :
 * 2024-02-03 description    : =========================================================== DATE
 * AUTHOR             NOTE ----------------------------------------------------------- 2024-02-03
 * ipeac       최초 생성
 */
public class Main {
    
    public static Position destination;
    
    public static int n;
    
    public static int m;
    
    public static int[][] distance;
    
    public static boolean[][] visited;
    
    public static int[][] graph;
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] split = br.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            m = Integer.parseInt(split[1]);
            
            graph = new int[n + 1][m + 1];
            distance = new int[n + 1][m + 1];
            visited = new boolean[n + 1][m + 1];
            
            for (int i = 1; i <= n; i++) {
                split = br.readLine().split(" ");
                for (int j = 1; j <= m; j++) {
                    int value = Integer.parseInt(split[j - 1]);
                    //목표지점 0 처리
                    if (value == NodeType.DESTINATION.getValue()) {
                        // 목적지
                        destination = new Position(i, j);
                    }
                    // 갈수  없는 땅 0
                    if (value == NodeType.PROHIBITED.getValue()) {
                        distance[i][j] = 0;
                    } else {
                        //그 외 의 땅 -1 처리
                        distance[i][j] = -1;
                    }
                    graph[i][j] = value;
                }
            }
            
            distance = bfs(destination);
            
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    sb.append(distance[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private static int[][] bfs(Position start) {
        
        Queue<Position> queue = new LinkedList<>();
        distance[start.x][start.y] = 0;
        visited[start.x][start.y] = true;
        queue.add(start);
        
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            for (Direction direction : Direction.values()) {
                int nextX = position.x + direction.x;
                int nextY = position.y + direction.y;
                
                if (!(1 <= nextX && nextX <= n && 1 <= nextY && nextY <= m)) {
                    continue;
                }
                
                if (graph[nextX][nextY] == NodeType.PROHIBITED.getValue()) {
                    continue;
                }
                
                if (!visited[nextX][nextY]) {
                    distance[nextX][nextY] = distance[position.x][position.y] + 1;
                    visited[nextX][nextY] = true;
                    queue.add(new Position(nextX, nextY));
                }
            }
        }
        
        return distance;
    }
    
    static class Position {
        
        private final int x;
        private final int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    enum Direction {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);
        
        private final int x;
        private final int y;
        
        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    enum NodeType {
        PROHIBITED(0),
        ROAD(1),
        DESTINATION(2);
        
        private final int value;
        
        NodeType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
}