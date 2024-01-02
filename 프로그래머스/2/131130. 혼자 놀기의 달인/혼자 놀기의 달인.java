
class Solution {
    
    public int solution(int[] cards) {
        int maxScore = 0;
        
        for (int start = 1; start <= cards.length; start++) {
            boolean[] visited = new boolean[cards.length + 1];
            int groupSize1 = getGroupSize(start, cards, visited);
            int groupSize2 = 0;
            
            for (int i = 1; i <= cards.length; i++) {
                if (!visited[i]) {
                    groupSize2 = getGroupSize(i, cards, visited);
            maxScore = Math.max(maxScore, groupSize1 * groupSize2);
                }
            }
            
        }
        return maxScore;
    }
    
    private int getGroupSize(int start, int[] cards, boolean[] visited) {
        int groupSize = 0;
        int current = start;
        
        while (!visited[current]) {
            visited[current] = true;
            groupSize++;
            current = cards[current - 1];
        }
        
        return groupSize;
    }
}