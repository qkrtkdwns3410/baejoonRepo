
import java.text.MessageFormat;

public class Solution {
    
    public long solution(int r1, int r2) {
        if (!(1 <= r1 && r1 < r2 && r2 <= 1_000_000)) {
            throw new IllegalArgumentException(MessageFormat.format("r1은 1보다 크고 r2보다 작아야 합니다. r1: {0}, r2: {1}", r1, r2));
        }
        long answer = 0;
        Circle biggerCircle = new Circle(0, 0, r2);
        Circle smallerCircle = new Circle(0, 0, r1);
        answer += findIntegerPairs(biggerCircle, smallerCircle);
        return answer;
    }
    
    // 1사분면의 값만 구하여 4배를 해준다.
    public long findIntegerPairs(Circle bigger, Circle smaller) {
        long count = 0;
        for (int x = 1; x <= bigger.r; x++) {
            // 두 원의 방정식을 사용하여 y좌표의 최소값과 최대값을 찾음
            int y_max = (int) Math.floor(Math.sqrt(Math.pow(bigger.r, 2) - Math.pow(x, 2)));
            int y_min = (int) Math.ceil(Math.sqrt(Math.pow(smaller.r, 2) - Math.pow(x, 2)));
            // y의 범위가 유효한지 확인 (y_min이 y_max보다 작거나 같은지 확인)
            if (y_min <= y_max) {
                // 유효한 y 범위 내의 정수 점의 개수를 더함
                count += (y_max - y_min + 1);
            }
        }
        return count * 4;  // 4사분면에 대해 반복
    }
    
    public static class Circle {
        private final int x;
        private final int y;
        private final int r;
        
        public Circle(int x, int y, int r) {
            if (!(1 <= r && r <= 1_000_000)) {
                throw new IllegalArgumentException(MessageFormat.format("r은 1보다 크고 1_000_000 작아야 합니다. r: {0}", r));
            }
            this.x = x;
            this.y = y;
            this.r = r;
        }
        
    }
}