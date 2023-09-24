import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * packageName    : org.example.알고리즘.두스티커
 * fileName       : Main
 * author         : ipeac
 * date           : 2023-09-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-24        ipeac       최초 생성
 */
public class Main {
    static Square paper;
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            //  h * w 모눈종이
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // 전체 모눈종이
            paper = new Square(h, w);
            
            int max = 0;
            
            // 스티커 n 개
            int n = Integer.parseInt(br.readLine());
            Square[] sticker = new Square[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int stikerH = Integer.parseInt(st.nextToken());
                int stikerW = Integer.parseInt(st.nextToken());
                sticker[i] = new Square(stikerH, stikerW);
            }
            // 스티커중에 2개를 고르는 등의 모든 조합의 경우의 수를 고려해야한다.
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    Square squareA = sticker[i];
                    Square squareB = sticker[j];
                    boolean isOverTotalArea = squareA.isNotValidArea(squareB);
                    if (isOverTotalArea) {
                        continue;
                    }
                    // 왼쪽 위 오른쪽 아래에서 시작하는 경우 두 조합이 가능한지 확인해야함.
                    // 일단 스티커는 90도로 회전이 가능하기에, 해당 경우의 수도 고려해야한다.
                    Square[] rotatedSquaresA = {squareA, squareA.rotate()};
                    Square[] rotatedSquaresB = {squareB, squareB.rotate()};
                    for (Square a : rotatedSquaresA) {
                        for (Square b : rotatedSquaresB) {
                            // 가로 세로가 fit 하고
                            if (a.isFitHorizontally(b) || a.isFitVertically(b)) {
                                // 해당 값이 최대값인지 확인한다.
                                max = Math.max(max, a.calcMax(b));
                            }
                        }
                    }
                    
                }
            }
            bw.write(String.valueOf(max));
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static class Square {
        int h;
        int w;
        
        public Square(int h, int w) {
            if (!(1 <= h && h <= 100 && 1 <= w && w <= 100)) {
                String errMsg = MessageFormat.format("1 <= h && h <= 100 && 1 <= w && w <= 100, h = {0}, w = {1}", h, w);
                throw new IllegalArgumentException(errMsg);
            }
            this.h = h;
            this.w = w;
        }
        
        public boolean isNotValidArea(Square other) {
            Objects.requireNonNull(other, "other must not be null");
            return (this.h * this.w) + (other.h * other.w) > (paper.h * paper.w);
        }
        
        public boolean isFitHorizontally(Square other) {
            Objects.requireNonNull(other, "other must not be null");
            return this.w + other.w <= paper.w && Math.max(this.h, other.h) <= paper.h;
        }
        
        public boolean isFitVertically(Square other) {
            Objects.requireNonNull(other, "other must not be null");
            return this.h + other.h <= paper.h && Math.max(this.w, other.w) <= paper.w;
        }
        
        public int calcMax(Square other) {
            Objects.requireNonNull(other, "other must not be null");
            return (this.h * this.w) + (other.h * other.w);
        }
        
        public Square rotate() {
            return new Square(this.w, this.h);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Square{");
            sb.append("h=")
              .append(h);
            sb.append(", w=")
              .append(w);
            sb.append('}');
            return sb.toString();
        }
    }
}