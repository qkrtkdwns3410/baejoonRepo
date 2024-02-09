import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    
    static int N;
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] split = br.readLine().split(" ");
            N = Integer.parseInt(split[0]);
            int r = Integer.parseInt(split[1]);
            int c = Integer.parseInt(split[2]);
            
            int result = 0;
            
            int size = (int) Math.pow(2, N);
            
            result += divideAndConquer(size, r, c);
            
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static int divideAndConquer(int size, int r, int c) {
        if (size == 0) {
            return 0;
        }
        
        
        int half = size / 2;
        
        if (r < half && c < half) { // 1사분면
            return divideAndConquer(half, r, c);
            
        } else if (r < half && half <= c) { // 2사분면
            return (int) Math.pow(half, 2) + divideAndConquer(half, r, c - half);
            
        } else if (half <= r && c < half) { // 4사분면
            return 2 * ((int) Math.pow(half, 2)) + divideAndConquer(half, r - half, c);
            
        } else { // 3사분면
            return 3 * ((int) Math.pow(half, 2)) + divideAndConquer(half, r - half, c - half);
            
        }
        
    }
}