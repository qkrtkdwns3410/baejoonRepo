import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    private final static String checkWord = "driip";
    
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        final String input = br.readLine();
        
        // 길이 미만이면 전부 안귀여움
        if (input.length() < checkWord.length()) {
            System.out.println("not cute");
            return;
        }
        
        //마지막만 체크한다.
        if(Objects.equals(checkWord, input.substring(input.length() - checkWord.length()))) {
            System.out.println("cute");
        } else {
            System.out.println("not cute");
        }
        
    }
}
