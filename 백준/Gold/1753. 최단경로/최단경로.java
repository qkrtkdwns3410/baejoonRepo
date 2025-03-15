import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName    : 박상준.연습.최단경로1753
 * fileName       : Main
 * author         : ipeac
 * date           : 25. 3. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 3. 15.        ipeac       최초 생성
 */
public class Main {
    static class Edge {
        int to, weight;
        
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken()); // 5 ( 정점 개수 )
        int E = Integer.parseInt(st.nextToken()); // 6 ( 간선 개수 )
        
        // 시작 정점 번호  k
        int K = Integer.parseInt(br.readLine()); // 1
        
        // 3 ~ e +2 줄까지 각 간선 (u, v, w)를 의미 u >> v 로 가는 가중치 w 인 간선이 존재함.
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph.get(u).add(new Edge(v, w));
        }
        
        // 최단 거리 배열 과 방문 체크
        int[] dist = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0; // 시작점 거리는 0이다.
        
        // 우선순위 큐 : 거리가 짧은 순으로 정렬한다.
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.add(new Edge(K, 0)); // 시작점 거리 0
        
        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currVertex = current.to;
            
            if (visited[currVertex]) continue;
            visited[currVertex] = true;
            
            // 현재정점에서 연결된 모든 간선을 탐색한다
            for (Edge edge : graph.get(currVertex)) {
                int nextVertex = edge.to;
                int weight = edge.weight;
                
                // 더 짧은 경로를 발견하면 갱신한다.
                if (!visited[nextVertex] && dist[currVertex] + weight < dist[nextVertex]) {
                    dist[nextVertex] = dist[currVertex] + weight;
                    pq.add(new Edge(nextVertex, dist[nextVertex]));
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
            }
            
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}
