import java.util.Arrays;
import java.util.Objects;

class Solution {
    public int solution(int[] mats, String[][] park) {
        Arrays.sort(mats);
        
        for (int i = mats.length - 1; i >= 0; i--) {
            int mat = mats[i];
            if (canPlaceMat(mat, park)) {
                return mat;
            }
        }
        
        return -1;
    }
    
    private boolean canPlaceMat(int mat, String[][] park) {
        for (int i = 0; i <= park.length - mat; i++) {
            for (int j = 0; j <= park[i].length - mat; j++) {
                if (canPlaceAtPosition(mat, park, i, j)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean canPlaceAtPosition(int mat, String[][] park, int row, int col) {
        for (int i = 0; i < mat; i++) {
            for (int j = 0; j < mat; j++) {
                if (!Objects.equals(park[row + i][col + j], "-1")) {
                    return false;
                }
            }
        }
        return true;
    }
}
