import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            // 사람의 수 N
            int N = Integer.parseInt(br.readLine());
            char[][] friends = new char[N][N];
            for (int i = 0; i < N; i++) {
                friends[i] = br.readLine()
                               .toCharArray();
            }
            
            int maxFriends = 0;
            for (int i = 0; i < N; i++) {
                int count = 0;
                for (int j = 0; j < N; j++) {
                    if (i == j) {
                        continue;
                    }
                    // 직접 친구를 구할 수 있는 카운트입니다.
                    if (friends[i][j] == 'Y' || hasCommonFriend(friends, i, j)) {
                        count++;
                    }
                    maxFriends = Math.max(maxFriends, count);
                }
                // 간접 친구 구하기
                
            }
            System.out.println(maxFriends);
            
        } catch (
            Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private static boolean hasCommonFriend(char[][] friends, int i, int j) {
        for (int k = 0; k < friends.length; k++) {
            if (friends[i][k] == 'Y' && friends[k][j] == 'Y') {
                // 0 2 와 2 1
                return true;
            }
        }
        return false;
    }
    
}