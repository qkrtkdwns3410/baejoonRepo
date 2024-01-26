import java.util.Arrays;

class Solution {
    
    static int[] dx = {1, 0, -1};
    static int[] dy = {0, 1, -1};
    
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int input = 1;
        int layer = n;
        Position position = Position.init();  
        int positionIndex = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < layer; j++) {
                position = position.move(positionIndex);
                triangle[position.x][position.y] = input++;
            }
            layer--;
            positionIndex = positionIndex < 2 ? positionIndex + 1 : 0;
        }
        
        return flattenArrays(triangle);
    }
    
    private int[] flattenArrays(int[][] triangle) {
        
        return Arrays.stream(triangle)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i != 0)
                .toArray();
    }
    
    static class Position {
        int x;
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Position move(int direction) {
            return new Position(this.x + dx[direction], this.y + dy[direction]);
        }
        
        public static Position init() {
            return new Position(-1, 0);
        }
        
        @Override
        public String toString() {
            return "Position(x=" + this.x + ", y=" + this.y + ")";
        }  
    }
}