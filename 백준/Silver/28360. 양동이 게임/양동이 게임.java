import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 각 양동이 물의 양
        double[] water = new double[N + 1];
        
        // 인접 리스트
        List<Integer>[] adj = new ArrayList[N + 1];
        // 각 양동이에서 나가는 호스의 개수
        int[] hoseCount = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            hoseCount[i] = 0;
        }
        
        // 1번 양동이 초기의 물 설정
        water[1] = 100.0;
        
        // 호스 정보 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj[a].add(b);
            hoseCount[a]++;
        }
        
        // 물 흘려보내기 ( 1 ~ N )
        for (int i = 1; i <= N; i++) {
            //현재 양동이에 물이 있고, 나가는 호스가 있는 경우
            if (water[i] > 0 && hoseCount[i] > 0) {
                //분배될 물 계산
                double distributeWater = water[i] / hoseCount[i];
                
                //인접한 양동이들에 물 분배
                for (int j = 0; j < adj[i].size(); j++) {
                    int next = adj[i].get(j);
                    water[next] += distributeWater;
                }
                
                //현재 위치 물 소모
                water[i] = 0;
            }
        }
        
        final double maxWater = Arrays.stream(water).max().getAsDouble();
        System.out.println(maxWater);
    }
}
