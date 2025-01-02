import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * packageName    : 박상준.연습.사칙연산13420
 * fileName       : Main
 * author         : ipeac
 * date           : 25. 1. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 25. 1. 2.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            final int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < T; i++) {
                String[] input = br.readLine().split(" ");
                long a = Long.parseLong(input[0]);
                
                String operator = input[1];
                
                long b = Long.parseLong(input[2]);
                
                String equal = input[3];
                
                long c = Long.parseLong(input[4]);
                
                sb.append(mainLogic(a, operator, b, equal, c));
            }
            
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String mainLogic(long a, String operator, long b, String equal, long c) {
        long result = 0;
        
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        
        if (result == c) {
            return "correct\n";
        } else {
            return "wrong answer\n";
        }
    }
}
