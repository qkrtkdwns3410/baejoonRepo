import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            
            List<Edge> edges = new ArrayList<>();
            
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int src = Integer.parseInt(st.nextToken());//출발 정점
                int dest = Integer.parseInt(st.nextToken()); //도착 정점
                int weight = Integer.parseInt(st.nextToken());//가중치
                Edge edge = new Edge(src, dest, weight);
                edges.add(edge);
            }
            
            Collections.sort(edges); // 가중치 오름차순으로 정렬한다.
            
            // 유니온 파인드 초기화 단계 - 정점 개수만큼 초기화 수행
            // 각 정점의 부모를 자기 자신으로 초기화해야한다.
            /*1   2   3   4   5
            |      |   |   |     |
            1   2   3   4   5*/
            parent = new int[V + 1];
            
            for (int i = 1; i <= V; i++) {
                parent[i] = i;
            }
            
            int mstWeight = 0;
            
            for (Edge edge : edges) {
                if (find(edge.src) != find(edge.dest)) {
                    union(edge.src, edge.dest);
                    mstWeight += edge.weight;
                }
            }
            
            System.out.println(mstWeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 유니온 파인드 알고리즘
     * 주어진 정점의 루트 정점을 찾는다.
     *
     * @param x
     */
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    /**
     * 두 정점을 연결한다. - 두 원소가 속한 집합을 합친다.
     * 한 집합의 대표 원소를 다른 집합의 대표 원소에 연결해 두 집합을 합친다.
     */
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if (x != y) {
            parent[y] = x;
        }
    }
    
    public static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;
        
        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}