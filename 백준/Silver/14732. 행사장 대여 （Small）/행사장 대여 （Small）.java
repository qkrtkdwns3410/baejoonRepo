import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 직사각형의 개수
        int N = Integer.parseInt(br.readLine());
        
        // 500 * 500 크기의 boolean
        boolean[][] board = new boolean[501][501];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            // 직사각형의 좌표를 true로 설정
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    board[x][y] = true;
                }
            }
        }
        
        // 면적 계산
        int area = 0;
        
        for (int i = 0; i < 501; i++) {
            for (int j = 0; j < 501; j++) {
                if (board[i][j]) {
                    area++;
                }
            }
        }
        
        System.out.println(area);
    }
}
