import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] lessons = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        int sum = 0;
        
        for (int i = 0; i < N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, lessons[i]);
            sum += lessons[i];
        }
        
        System.out.println(binarySearch(lessons, N, M, max, sum));
    }
    
    private static int binarySearch(int[] lessons, int N, int M, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int count = countBlurays(lessons, N, mid);
            
            if (count <= M) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    private static int countBlurays(int[] lessons, int N, int size) {
        int count = 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + lessons[i] > size) {
                count++;
                sum = lessons[i];
            } else {
                sum += lessons[i];
            }
        }
        return count;
    }
}