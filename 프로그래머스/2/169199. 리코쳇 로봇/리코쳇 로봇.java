import java.text.MessageFormat;
import java.util.*;

/*
* board	result
["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."]	7
[".D.R", "....", ".G..", "...D"]	-1
*  */


class Solution {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static int boardWidth;
    static int boardHeight;
    char[][] newBoard;
    
    public int solution(String[] board) {
        boardWidth = board[0].length();
        boardHeight = board.length;
        int answer = 0;
        if (!(3 <= board.length && board.length <= 100)) {
            throw new IllegalArgumentException(MessageFormat.format("board.length must be between {0} and {1} || board.length -> {2}", 3, 100));
        }
        newBoard = new char[boardHeight][boardWidth];
        // 로봇의 포지션을 구한다,.
        Position robotPosition = null;
        Position targetPosition = null;
        for (int i = 0; i < board.length; i++) {
            char[] charArray = board[i].toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                if (charArray[j] == 'R') {
                    robotPosition = new Position(i, j, 0);
                } else if (charArray[j] == 'G') {
                    targetPosition = new Position(i, j, 0);
                }
                newBoard[i][j] = charArray[j];
            }
        }
        // 로봇을 bfs 로 좌우 상하로 끝까지 직진하도록 한다. ( 이미 방문한 위치는 방문하지 않도록 한다 )
        // 만약 큐가 비게되면, 해당 케이스는 -1 이다.
        
        Set<Position> visited = new HashSet<>();
        Queue<Position> positionQueue = new LinkedList<>();
        robotPosition.addInto(visited);
        positionQueue.add(robotPosition);
        
        while (!positionQueue.isEmpty()) {
            Position polled = positionQueue.poll();
            int x = polled.getX();
            int y = polled.getY();
            if (polled.isSameXY(targetPosition)) {
                answer = polled.getCount();
                break;
            }
            for (int i = 0; i < 4; i++) {
                Position nextPosition = getNextPosition(i, polled);
                if (!visited.contains(nextPosition)) {
                    nextPosition.addInto(visited);
                    positionQueue.add(nextPosition);
                }
            }
            
        }
        
        return answer == 0 ? -1 : answer;
    }
    
    private Position getNextPosition(int direction, Position now) {
        // 하, 우, 상 , 좌
        int x = now.getX();
        int y = now.getY();
        int nx = dx[direction];
        int ny = dy[direction];
        // 그래프안에 존재하고, D를 만나지 않는 경우 계속 x y 값을 증대시켜야한다.
        while ((0 <= x + nx && x + nx < boardHeight) && (0 <= y + ny && y + ny < boardWidth) && (newBoard[x + nx][y + ny] != 'D')) {
            x += nx;
            y += ny;
        }
        return new Position(x, y, now.getCountPlusOne());
    }
    
    private void bfs() {
        // boolean[][] visited = new boolean[boardWidth][boardWidth];
        // Queue<Position> positionQueue = new LinkedList<>();
        
    }
}

class Position {
    private final int x;
    private final int y;
    private int count;
    
    public Position(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
    
    public void addInto(Set<Position> visitArr) {
        Objects.requireNonNull(visitArr, "visitedArr is not null");
        visitArr.add(this);
    }
    
    public int getCountPlusOne() {
        return this.count + 1;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean isSameXY(Position other) {
        return isSameX(other) && isSameY(other);
    }
    
    private boolean isSameX(Position other) {
        return this.x == other.x;
    }
    
    private boolean isSameY(Position other) {
        return this.y == other.y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        
        Position position = (Position) o;
        
        if (x != position.x) return false;
        return y == position.y;
    }
    
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Position{");
        sb
            .append("x=")
            .append(x);
        sb
            .append(", y=")
            .append(y);
        sb
            .append(", count=")
            .append(count);
        sb.append('}');
        return sb.toString();
    }
}