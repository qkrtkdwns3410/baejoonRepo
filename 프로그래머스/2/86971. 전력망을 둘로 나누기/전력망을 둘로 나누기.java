import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    public int solution(int n, int[][] wires) {
        int answer = n;
        
        for (int i = 0; i < wires.length; i++) {
            //끊어진 전력망을 만듭니다.
            int[][] graph = buildGraph(n, wires, i);
            //하나의 전략망을 bfs 돌며 몇개의 송전탑을 가지고 잇는지 체크합니다.
            int count = bfs(graph, i);
            answer = Math.min(answer, Math.abs((n - count) - count));
        }
        
        return answer;
    }
    
    private int bfs(int[][] graph, int index) {
        int count = 1;
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        
        while (!queue.isEmpty()) {
            int polled = queue.poll();
            for (int i = 0; i < graph[polled].length; i++) {
                if (graph[polled][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int[][] buildGraph(int n, int[][] wires, int skipIndex) {
        int[][] graph = new int[n][n];
        
        for (int i = 0; i < wires.length; i++) {
            if (i == skipIndex) { // 끊을 인덱스
                continue;
            }
            int[] wire = wires[i];
            int x = wire[0] - 1;
            int y = wire[1] - 1;
            graph[x][y] = 1;
            graph[y][x] = 1;
        }
        
        return graph;
    }
}