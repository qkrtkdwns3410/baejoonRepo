import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int[] arr = new int[N + 1];
            
            st = new StringTokenizer(br.readLine(), " ");
            
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                arr[i] += arr[i - 1];
            }
            
            StringBuilder sb = new StringBuilder(M + 1);
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                
                sb.append(arr[end] - arr[start - 1]).append("\n");
            }
            
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}