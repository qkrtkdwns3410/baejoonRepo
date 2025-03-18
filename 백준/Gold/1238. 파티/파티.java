import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName    : 박상준.연습.파티1238
 * fileName       : Main
 * author         : ipeac
 * date           : 25. 3. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 3. 18.        ipeac       최초 생성
 */
public class Main {
    static class Edge {
        private final int to;
        private final int weight;
        
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public String toString() {
            return "Edge{" +
                           "to=" + to +
                           ", weight=" + weight +
                           '}';
        }
    }
    
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final int X = Integer.parseInt(st.nextToken());
        
        List<List<Edge>> graph = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 일단 간선 기록
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            graph.get(from).add(new Edge(to, weight));
        }
        
        // 정방향 최단거리 기록
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;
        
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(X, 0));
        
        while (!pq.isEmpty()) {
            final Edge currEdge = pq.poll();
            final int currVertex = currEdge.to;
            
            if (visited[currVertex]) continue;
            visited[currVertex] = true;
            
            for (Edge edge : graph.get(currVertex)) {
                final int nextVertex = edge.to;
                final int nextEdgeWeight = edge.weight;
                
                if (!visited[nextVertex] && dist[nextVertex] > dist[currVertex] + nextEdgeWeight) {
                    dist[nextVertex] = dist[currVertex] + nextEdgeWeight;
                    pq.add(new Edge(nextVertex, dist[nextVertex]));
                }
            }
        }
        
        // 역방향 최단거리 기록
        int[] reverseDist = new int[N + 1];
        Arrays.fill(reverseDist, Integer.MAX_VALUE);
        reverseDist[X] = 0;
        List<List<Edge>> reverseGraph = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            reverseGraph.add(new ArrayList<>());
        }
        
        for (int i = 1; i <= N; i++) {
            for (Edge edge : graph.get(i)) {
                reverseGraph.get(edge.to).add(new Edge(i, edge.weight));
            }
        }
        
        visited = new boolean[N + 1];
        pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(X, 0));
        
        while (!pq.isEmpty()) {
            final Edge currEdge = pq.poll();
            final int currVertex = currEdge.to;
            
            if (visited[currVertex]) continue;
            visited[currVertex] = true;
            
            for (Edge edge : reverseGraph.get(currVertex)) {
                final int nextVertex = edge.to;
                final int nextEdgeWeight = edge.weight;
                
                if (!visited[nextVertex] && reverseDist[nextVertex] > reverseDist[currVertex] + nextEdgeWeight) {
                    reverseDist[nextVertex] = reverseDist[currVertex] + nextEdgeWeight;
                    pq.add(new Edge(nextVertex, reverseDist[nextVertex]));
                }
            }
        }
        
        // 순회하면서 최대값 찾기
        //
        int max = 0;
        for (int i = 1; i <= N; i++) {
            dist[i] += reverseDist[i];
            
            if (max < dist[i]) {
                max = dist[i];
            }
        }
        
        System.out.println(max);
    }
}
