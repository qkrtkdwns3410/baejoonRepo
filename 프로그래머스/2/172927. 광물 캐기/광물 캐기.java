
import java.text.MessageFormat;
import java.util.*;


class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        List<Fatigue> fatigues = new ArrayList<>();
        int[][] scores = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        
        int pickSize = Arrays.stream(picks)
                             .sum();
        for (int i = 0; i < minerals.length; i += 5) {
            if (pickSize == 0) {
                break;
            }
            int diamondFatigue = 0;
            int ironFatigue = 0;
            int stoneFatigue = 0;
            for (int j = i; j < i + 5; j++) {
                if (j >= minerals.length) {
                    break;
                }
                int index = Stone.findByName(minerals[j])
                                 .getIndex();
                diamondFatigue += scores[0][index];
                ironFatigue += scores[1][index];
                stoneFatigue += scores[2][index];
            }
            fatigues.add(new Fatigue(diamondFatigue, ironFatigue, stoneFatigue));
            pickSize--;
        }
        // 일단 다이아 - >아이언 -> 스톤 순으로 도구를 사용한다.
        Collections.sort(fatigues);
        
        for (Fatigue fatigue : fatigues) {
            int diamond = fatigue.getDiamond();
            int iron = fatigue.getIron();
            int stone = fatigue.getStone();
            
            if (picks[0] > 0) {
                answer += diamond;
                picks[0]--;
                continue;
            }
            if (picks[1] > 0) {
                answer += iron;
                picks[1]--;
                continue;
            }
            if (picks[2] > 0) {
                answer += stone;
                picks[2]--;
                continue;
            }
        }
        return answer;
    }
}

enum Stone {
    DIAMOND("diamond", 0), IRON("iron", 1), STONE("stone", 2),
    
    ;
    private final String name;
    private final int index;
    
    Stone(String name, int index) {
        this.name = name;
        this.index = index;
    }
    
    public static Stone findByName(String name) {
        for (Stone value : values()) {
            if (Objects.equals(value.name, name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format("해당하는 이름({0})의 광물이 없습니다.", name));
    }
    
    public int getIndex() {
        return index;
    }
}

class Fatigue implements Comparable<Fatigue> {
    private final int diamond;
    private final int iron;
    private final int stone;
    
    public Fatigue(int diamond, int iron, int stone) {
        this.diamond = diamond;
        this.iron = iron;
        this.stone = stone;
    }
    
    @Override
    public int compareTo(Fatigue o) {
        return o.stone - this.stone;
    }
    
    public int getDiamond() {
        return diamond;
    }
    
    public int getIron() {
        return iron;
    }
    
    public int getStone() {
        return stone;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fatigue{");
        
        sb.append("diamond=")
          .append(diamond);
        sb.append(", iron=")
          .append(iron);
        sb.append(", stone=")
          .append(stone);
        sb.append('}');
        return sb.toString();
    }
}