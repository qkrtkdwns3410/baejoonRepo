import java.util.Arrays;

class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {};
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        int minY = Integer.MAX_VALUE;
        int maxY = 0;
        
        for (int x = 0; x < wallpaper.length; x++) {
            for (int y = 0; y < wallpaper[x].length(); y++) {
                char c = wallpaper[x].charAt(y);
                if (c == '#') {
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }
        answer = new int[]{minX, minY, maxX + 1, maxY + 1};
        return answer;
    }
}