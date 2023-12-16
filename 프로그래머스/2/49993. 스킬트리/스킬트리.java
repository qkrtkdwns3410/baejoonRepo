/*
 *
 *
 *
 *
 * */

class Solution {
    
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (String skillTree : skill_trees) {
            System.out.println("skillTree = " + skillTree);
            int skillIndex = 0;
            boolean isPossible = true;
            for (int i = 0; i < skillTree.length(); i++) {
                int skillOrder = skill.indexOf(skillTree.charAt(i));
                //필수 스킬트리에 존재하는 스킬인지 확인
                // -1 이라면 필수 스킬트리에 존재하지 않는 스킬이기에 자유롭게 배울 수 있는 스킬
                if (skillOrder == -1) {
                    continue;
                }
                
                //하지만 필수 스킬트리에 존재하는 스킬이라면
                // 지금 진행중인 skillIndex 와 동일해야 정상적인 스킬트리를 진행중임을 알 수 있다.
                if (skillOrder == skillIndex) {
                    skillIndex++;
                } else {
                    //만약 스킬트리와 동일하지 않은 인덱스에 위치한다면 불가능한 스킬트리이다.
                    isPossible = false;
                    break;
                }
            }
            
            //만약 isPossible 이 true 라면 정상적인 스킬트리이다.
            if (isPossible) {
                answer++;
            }
        }
        
        return answer;
    }
}