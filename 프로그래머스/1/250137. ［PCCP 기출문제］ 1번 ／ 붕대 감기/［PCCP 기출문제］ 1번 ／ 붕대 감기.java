class Solution {
    public int solution(int[] bandage, int maxHealth, int[][] attacks) {
        //붕대의 기술 시전 시간, 초당회복량 ,추가회복량
        int bandageTime = bandage[0];
        int bandageRecoveryPerSeconds = bandage[1];
        int bandageAddRecovery = bandage[2];
        
        int consecutiveTime = 0;
        
        int currentHealth = maxHealth;
        
        int lastAttackTime = attacks[attacks.length - 1][0];
        int attackIndex = 0;
        
        for (int time = 1; time <= lastAttackTime; time++) {
            //현재 시간이 공격 시간이면 공격
            if (attacks[attackIndex][0] == time) {
                currentHealth -= attacks[attackIndex][1];
                if (currentHealth <= 0) {
                    return -1;
                }
                
                consecutiveTime = 0;
                attackIndex++;
            } else {
                //공격이 없는 경우
                consecutiveTime++;
                //1초마다  bandageRecoveryPerSeconds 회복
                
                currentHealth += bandageRecoveryPerSeconds;
                if(currentHealth > maxHealth) {
                    currentHealth = maxHealth;
                }
                
                // t 초 연속으로 붕대 감는 데 성공 y 만큼의 추가 회복
                if (consecutiveTime == bandageTime) {
                    currentHealth += bandageAddRecovery;
                    if(currentHealth > maxHealth) {
                        currentHealth = maxHealth;
                    }
                    consecutiveTime = 0;
                }
            }
        }
        
        return currentHealth;
    }
}
