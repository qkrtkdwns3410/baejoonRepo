import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : 박상준.주차7.베스트셀러
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 7. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 7. 14.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // value 기준으로 오름차순
        Map<String, Integer> books = new HashMap<>();
        
        // 하루동안 팔린 책의 개수 N
        int n = Integer.parseInt(br.readLine());
        
        while (n-- > 0) {
            String book = br.readLine();
            
            books.put(book, books.getOrDefault(book, 0) + 1);
        }
        
        String bestSeller = books.entrySet().stream()
                                    .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                                                    .thenComparing(Map.Entry.comparingByKey())
                                    )
                                    .findFirst()
                                    .get()
                                    .getKey();
        
        System.out.println(bestSeller);
    }
}