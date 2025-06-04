import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        final int r = Integer.parseInt(st.nextToken());
        final int c = Integer.parseInt(st.nextToken());
        
        // 크로스워드 판
        char[][] corssword = new char[r][c];
        
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                corssword[i][j] = line.charAt(j);
            }
        }
        
        List<String> words = new ArrayList<>();
        
        // 가로 단어 찾기
        for (int i = 0; i < r; i++) {
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < c; j++) {
                if (corssword[i][j] == '#') {
                    if (word.length() > 1) {
                        words.add(word.toString());
                    }
                    word.setLength(0); // 단어 초기화
                } else {
                    word.append(corssword[i][j]);
                }
            }
            if (word.length() > 1) {
                words.add(word.toString());
            }
        }
        
        // 세로 단어 찾기
        for (int j = 0; j < c; j++) {
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < r; i++) {
                if (corssword[i][j] == '#') {
                    if (word.length() > 1) {
                        words.add(word.toString());
                    }
                    word.setLength(0); // 단어 초기화
                } else {
                    word.append(corssword[i][j]);
                }
            }
            if (word.length() > 1) {
                words.add(word.toString());
            }
        }
        
        words.sort(String::compareTo);
        
        System.out.println(words.get(0));
    }
}
