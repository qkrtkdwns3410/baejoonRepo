import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 각 양동이의 물의 양 (double 타입으로 변경)
        double[] water = new double[N + 1];
        // 인접 리스트 (양동이 연결 정보)
        List<Integer>[] adj = new ArrayList[N + 1];
        // 각 양동이에서 나가는 호스 개수
        int[] outDegree = new int[N + 1];
        
        for( int i = 1; i <= N; i++ ) {
            adj[i] = new ArrayList<>();
            outDegree[i] = 0;
        }
        
        // 1번 양동이에 초기 물 설정
        water[1] = 100.0;
        
        // 호스 정보 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            adj[v].add(w);
            outDegree[v]++;
        }
        
        // 물 흘려보내기 (1번부터 N번 양동이 순서대로)
        for (int i = 1; i <= N; i++) {
            // 현재 양동이에 물이 있고, 나가는 호스가 있는 경우
            if (water[i] > 0 && outDegree[i] > 0) {
                double distributedWater = water[i] / outDegree[i];
                for (int nextBucket : adj[i]) {
                    water[nextBucket] += distributedWater;
                }
                // 물을 흘려보냈으므로 현재 양동이의 물은 0으로 설정
                water[i] = 0;
                // 현재 양동이의 물은 흘려보냈으므로 0으로 만들 수 있으나,
                // 문제 조건상 누적되므로 0으로 만들 필요는 없음 (어차피 더 작은 번호에서 큰 번호로만 흐름)
            }
        }
        
        // 최대 물의 양 찾기
        double maxWater = 0.0;
        for (int i = 1; i <= N; i++) {
            if (water[i] > maxWater) {
                maxWater = water[i];
            }
        }
        
        // 결과 출력 (소수점 정밀도 맞춰서)
        System.out.printf("%.10f\n", maxWater);
    }
}
