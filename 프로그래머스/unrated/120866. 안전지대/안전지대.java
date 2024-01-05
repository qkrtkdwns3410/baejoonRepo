import java.util.Arrays;

class Solution {
    public static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
    public int solution(int[][] board) {
        int totalCount = board.length * board[0].length;
        int boomCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    boomCount++;
                    boomCount += boom(board, i, j);
                }
            }
        }
        
        return totalCount - boomCount;
    }
    
    private int boom(int[][] board, int i, int j) {
        int boomCount = 0;
        for (int k = 0; k < 8; k++) {
            int[] direction = DIRECTIONS[k];
            int nextRow = i + direction[0];
            int nextCol = j + direction[1];
            if (!(0 <= nextRow && nextRow < board.length) || !(0 <= nextCol && nextCol < board[0].length)) {
                continue;
            }
            if (board[nextRow][nextCol] == 0) {
                // 위험지역 분류
                board[nextRow][nextCol] = 2;
                boomCount++;
            }
        }
        
        return boomCount;
    }
}