import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/* *
 *
 *
 *
 *
 *
 *  */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));) {
            int n = Integer.parseInt(br.readLine());
            do {
                int answer = solution(n);
                bw.write(answer + "\n");
                n = Integer.parseInt(br.readLine());
            } while (n != 0);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 에라토스테네스의 체 활용
    public static int solution(int n) {
        if (n == 1 || n == 2 || n == 3 || n == 5 || n == 7) {
            return 1;
        }
        int answer = 0;
        Number brNumber = new Number(n);
        answer = brNumber.countPrimeNumber(n + 1, 2 * n);
        return answer;
    }
    
    public static class Number {
        private final int value;
        
        public Number(int value) {
            if (!(1 <= value && value <= 123456)) {
                String errMsg = MessageFormat.format("value는 1보다 크거나 같고 123456보다 작거나 같아야 합니다. value: {0}", value);
                throw new IllegalArgumentException(errMsg);
            }
            this.value = value;
        }
        
        // 입력된 자연수  n에 대해 n 보다 크고  2n 보다 작거나 같은 소수는 적어도 하나 존재함.
        // 사이의 소수에 대해 검색하면 된다.
        // 아레토스 테네스의 체가 필요하다. 일단 2 3 5 7 은 남기고 해당 숫자의 배수는 전부 제외한다.
        public int countPrimeNumber(int start, int end) {
            if (!(0 < start && start <= end)) {
                String errMsg = MessageFormat.format("start는 0보다 크고 start보다 작거나 같아야 합니다. start: {0}, end: {1}", start, end);
                throw new IllegalArgumentException(errMsg);
            }
            int count = 0;
            
            for (int i = start; i <= end; i++) {
                if (isPrimeNumber(i)) {
                    count++;
                }
            }
            return count;
        }
        
        private boolean isPrimeNumber(int number) {
            Set<Integer> primes = new HashSet<>() {
                {
                    add(2);
                    add(3);
                    add(5);
                    add(7);
                }
            };
            if (number == 1) {
                return false;
            }
            if (primes.contains(number)) {
                return true;
            }
            if (number % 2 == 0 || number % 3 == 0 || number % 5 == 0 || number % 7 == 0) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
        
    }
    
}