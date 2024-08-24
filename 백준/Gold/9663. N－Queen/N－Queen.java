import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * packageName    : 박상준.주차11.NQUEEN
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 8. 24.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 8. 24.        ipeac       최초 생성
 */
public class Main {
    static int[] PLACE_HOLDER;
    static int COUNT = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        PLACE_HOLDER = new int[N];
        
        place(0);
        
        System.out.println(COUNT);
    }
    
    private static void place(int col) {
        if (col == PLACE_HOLDER.length) {
            COUNT++;
            return;
        }
        
        for (int i = 0; i < PLACE_HOLDER.length; i++) {
            PLACE_HOLDER[col] = i;
            
            if (isPossible(col)) {
                place(col + 1);
            }
        }
    }
    
    private static boolean isPossible(int col) {
        for (int row = 0; row < col; row++) {
            if (isHorizonal(col, row) || isDiagonal(col, row)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean isHorizonal(int col, int row) {
        return PLACE_HOLDER[row] == PLACE_HOLDER[col];
    }
    
    private static boolean isDiagonal(int col, int row) {
        return Math.abs(PLACE_HOLDER[row] - PLACE_HOLDER[col]) == Math.abs(row - col);
    }
}
