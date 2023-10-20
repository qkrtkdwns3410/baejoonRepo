
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum Way {
    BOTTOM(0), RIGHT(1), TOP(2), LEFT(3), NO_WAY(100),
    ;
    private final int value;
    
    Way(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}


/*
 * startX 가 동일하다면,
 *1) start Y < target Y
 * 아래로 튕기는건 불가능하다.
 * 2) start Y > targetY
 *  위로 튕기는건 불가능하다.
 * startY 가 동일하다면,
 * 1) startX < target X
 * 오른쪽으로 튕기는건 불가능하다.
 * 2) startX > target X
 * 왼쪽으로 튕기는건 불가능하다.
 * */
class Solution {
    static int m, n;
    
    public List<Integer> solution(int m, int n, int startX, int startY, int[][] balls) {
        Solution.m = m;
        Solution.n = n;
        List<Integer> answer = new ArrayList<>();
        Ball startBall = new Ball(startX, startY);
        for (int[] ball : balls) {
            Ball targetBall = new Ball(ball[0], ball[1]);
            int distance = startBall.calcShortestDistance(targetBall);
            answer.add(distance);
        }
        
        return answer;
    }
}

class Ball {
    private final int x;
    private final int y;
    
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // 아래 오른쪽 위 왼쪽
    private Ball createSymmetryBall(int way) {
        // m 가로  n  세로
        Ball symmetryBall = null;
        switch (way) {
            case 0:
                symmetryBall = new Ball(this.x, this.y * -1);
                break;
            case 1:
                symmetryBall = new Ball(this.x + (Solution.m - this.x) * 2, this.y);
                break;
            case 2:
                symmetryBall = new Ball(this.x, this.y + (Solution.n - this.y) * 2);
                break;
            case 3:
                symmetryBall = new Ball(this.x * -1, this.y);
                break;
            default:
                break;
        }
        return symmetryBall;
    }
    
    private int calcDistance(Ball targetBall) {
        return (int) (Math.pow(this.x - targetBall.x, 2) + Math.pow(this.y - targetBall.y, 2));
    }
    
    public int calcShortestDistance(Ball targetBall) {
        Objects.requireNonNull(targetBall, "targetBall must not be null");
        
        int distance = Integer.MAX_VALUE;
        
        int startX = this.x;
        int startY = this.y;
        
        int targetX = targetBall.x;
        int targetY = targetBall.y;
        
        if (startX == targetX) {
            if (startY < targetY) {
                // 위 제외하고 나머지는 가능하다.
                // 대칭방향으로 start 를 대칭시켜서 직선거리를 구해야한다.
                // 0 -> 아래 1 -> 오른쪽 2 -> 위 3 -> 왼쪽
                distance = calcShortestDistance(targetBall, Way.TOP);
            } else {
                // 아래로 튕기기 불가능
                distance = calcShortestDistance(targetBall, Way.BOTTOM);
            }
        } else if (startY == targetY) {
            if (startX < targetX) {
                // 오른쪽 튕기기 불가능
                distance = calcShortestDistance(targetBall, Way.RIGHT);
            } else {
                // 왼쪽 튕기기 불가능
                distance = calcShortestDistance(targetBall, Way.LEFT);
            }
        } else {
            // 다 튕길수있음
            distance = calcShortestDistance(targetBall, Way.NO_WAY);
        }
        
        return distance;
    }
    
    private int calcShortestDistance(Ball targetBall, Way excludedWay) {
        Objects.requireNonNull(targetBall, "targetBall must not be null");
        Objects.requireNonNull(excludedWay, "excludedWay must not be null");
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if (i == excludedWay.getValue()) {
                // 튕기기 불가능한 경우
                continue;
            }
            Ball symmetryBall = createSymmetryBall(i);
            distance = Math.min(distance, symmetryBall.calcDistance(targetBall));
        }
        
        return distance;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ball{");
        sb
            .append("x=")
            .append(x);
        sb
            .append(", y=")
            .append(y);
        sb.append('}');
        return sb.toString();
    }
}