import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {  
    public int solution(String[] friends, String[] gifts) {
        Map<String, Map<String, Integer>> giftMap = new HashMap<>();
        Map<String, Integer> givenGifts = new HashMap<>();
        Map<String, Integer> receivedGifts = new HashMap<>();
        
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            String giver = split[0];
            String receiver = split[1];
            
            giftMap.putIfAbsent(giver, new HashMap<>());
            giftMap.get(giver).put(receiver, giftMap.get(giver).getOrDefault(receiver, 0) + 1);
            
            givenGifts.put(giver, givenGifts.getOrDefault(giver, 0) + 1);
            receivedGifts.put(receiver, receivedGifts.getOrDefault(receiver, 0) + 1);
        }
        
        // 선물 지수 계산
        Map<String, Integer> giftIndexs = new HashMap<>();
        for (String friend : friends) {
            // 준 선물  - 받은 선물
            giftIndexs.put(friend, givenGifts.getOrDefault(friend, 0) - receivedGifts.getOrDefault(friend, 0));
        }
        
        Map<String,Integer> nextMonthGifts = new HashMap<>();
        
        for (int i = 0 ;i< friends.length; i++) {
            for (int j = i + 1 ; j < friends.length; j++) {
                String giver = friends[i];
                String receiver = friends[j];
                
                // 선물을 주고 받은 기록이 있다면  - 선물을 더 많이 준 사람 - 다음 달에 선물 하나 GET
                // 선물을 주고 받은 기록없거나 주고 받은 수 동일 - 선물 지수가 더 큰 사람 선물 하나 받는다.
                // 두 사람의 선물 지수도 같다면  - 다음 달에 선물을 주고받지 않는다.
                int giverToReceiver = giftMap.getOrDefault(giver, new HashMap<>()).getOrDefault(receiver, 0);
                int receiverToGiver = giftMap.getOrDefault(receiver, new HashMap<>()).getOrDefault(giver, 0);
                
                if (giverToReceiver > receiverToGiver) {
                    nextMonthGifts.merge(giver, 1, Integer::sum);
                } else if (giverToReceiver < receiverToGiver) {
                    nextMonthGifts.merge(receiver, 1, Integer::sum);
                } else {
                    //선물을 주고 받은 기록 X or 주고 받은 수 동일
                    //선물 지수가 더 큰 사람 선물하나 받음.
                    if(giftIndexs.get(giver) > giftIndexs.get(receiver)) {
                        nextMonthGifts.merge(giver, 1, Integer::sum);
                    } else if (giftIndexs.get(giver) < giftIndexs.get(receiver)) {
                        nextMonthGifts.merge(receiver, 1, Integer::sum);
                    }
                }
            }
        }
        
        int giftKing = 0;
        for (int value : nextMonthGifts.values()) {
            if(giftKing < value) {
                giftKing = value;
            }
        }
        
        return giftKing;
    }
}
