import java.util.TreeSet;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        TreeSet<Score> treeSet = new TreeSet<>();
        for (int i = 0; i < score.length; i++) {
            treeSet.add(new Score(i, score[i]));
            if (treeSet.size() > k) {
                treeSet.pollLast();
            }
            answer[i] = treeSet.last().getScore();
        }
        return answer;
    }
    
    public static class Score implements Comparable<Score> {
        private int index;
        private int score;
        
        public Score(int index, int score) {
            this.index = index;
            this.score = score;
        }
        
        public int getIndex() {
            return index;
        }
        
        public int getScore() {
            return score;
        }
        
        @Override
        public String toString() {
            return "Score{" +
                    "index=" + index +
                    ", score=" + score +
                    '}';
        }
        
        @Override
        public int compareTo(Score o) {
            if (this.score != o.score) {
                return Integer.compare(o.score, this.score); // 스코어는 내림차순으로
            }
            return Integer.compare(this.index, o.index); // 인덱스는 오름차순으로
        }
    }
}