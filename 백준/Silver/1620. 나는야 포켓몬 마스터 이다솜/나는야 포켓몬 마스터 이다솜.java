import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * packageName    : 박상준.주차5.나는야포켓몬마스터이다솜
 * fileName       : Main
 * author         : ipeac
 * date           : 24. 6. 29.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 29.        ipeac       최초 생성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Map<String, String> pokemons = new HashMap<>();
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int pokeNum = 1;
        
        while (N-- > 0) {
            String value = br.readLine();
            pokemons.put(String.valueOf(pokeNum), value);
            pokemons.put(value, String.valueOf(pokeNum));
            pokeNum++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (M-- > 0) {
            String findValue = br.readLine();
            sb.append(pokemons.get(findValue)).append("\n");
        }
        
        System.out.println(sb);
    }
}