import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[9][9];
        
        for (int i = 0; i < 9; i++) {
            String[] split = br.readLine().split("");
            for (int ii = 0; ii < 9; ii++) {
                board[i][ii] = Integer.parseInt(split[ii]);
            }
        }
        
        solveSudoku(board, 0, 0);
        
        for(int i = 0 ; i < 9; i++){
            for(int j = 0 ; j < 9; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    
    private static boolean solveSudoku(int[][] board, int row, int col) {
        if (row == 9) return true;
        if (col == 9) {
            return solveSudoku(board, row + 1, 0);
        }
        
        if (board[row][col] != 0) {
            return solveSudoku(board, row, col + 1);
        }
        
        for (int value = 1; value <= 9; value++) {
            if (isPossible(row, col, value, board)) {
                board[row][col] = value;
                if (solveSudoku(board, row, col + 1)) return true;
                board[row][col] = 0;
            }
        }
        
        return false;
    }
    
    private static boolean isPossible(int x, int y, int input, int[][] board) {
        // 3 * 3 체크
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int ii = startY; ii < startY + 3; ii++) {
                if (board[i][ii] == input) {
                    return false;
                }
            }
        }
        
        // 가로 세로 체크
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == input || board[x][i] == input) {
                return false;
            }
        }
        
        return true;
    }
}
