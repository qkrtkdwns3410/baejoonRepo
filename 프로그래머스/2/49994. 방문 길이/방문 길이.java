import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * packageName    : org.example.알고리즘.방문길이
 * fileName       : Solution
 * author         : ipeac
 * date           : 24. 4. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 12.        ipeac       최초 생성
 */
public class Solution {
    public static Map<Character, int[]> LOCATIONS = new HashMap<>();
    public static Set<String> duplications = new HashSet<>();
    
    public int solution(String dirs) {
        initLocations();
        int x = 5, y = 5;
        
        char[] splited = dirs.toCharArray();
        
        for (char dir : splited) {
            int[] way = LOCATIONS.get(dir);
            
            int nx = x + way[0], ny = y + way[1];
            
            if (!isInGraph(nx, ny)) {
                continue;
            }
            
            print(nx,ny);
            
            duplications.add(x + " " + y + " " + nx + " " + ny);
            duplications.add(nx + " " + ny + " " + x + " " + y);
            
            x = nx;
            y = ny;
        }
        
        
        return duplications.size() / 2;
    }
    
    private boolean isInGraph(int x, int y) {
        if (((0 <= x && x <= 10) && (0 <= y && y <= 10))) {
            return true;
        }
        
        return false;
    }
    
    public static void initLocations() {
        LOCATIONS.put('L', new int[]{0, 1});
        LOCATIONS.put('U', new int[]{1, 0});
        LOCATIONS.put('D', new int[]{-1, 0});
        LOCATIONS.put('R', new int[]{0, -1});
    }
    
    public static <T> void print(T... t) {
        for(T tt : t){
            System.out.print(tt+ " ");
        }
        
        System.out.println();
    }
}