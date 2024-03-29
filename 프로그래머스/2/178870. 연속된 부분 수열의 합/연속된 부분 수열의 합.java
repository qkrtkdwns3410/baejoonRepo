import java.util.* ;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        System.out.println();
        
        int left = 0 ;
        int right = 0 ;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        
        while(right < sequence.length){
            //부분 수열의 합이 k 보다 작은 경우, end 증가 sum에 값을 더합니다.
            if(sum < k){
                sum += sequence[right++];
            }
            
            //만약  k보다 크
            while(k<= sum){
                if(sum == k){
                    int length = right - left - 1;
                    //최소 길이보다 작은 경우 기록한다.
                    if(length < minLength){
                        answer[0] = left;
                        answer[1] = right -1;
                        minLength = length;
                    }
                }
                
                //만약 sum 이 k보다 크면 , 왼쪽을 하나 땡김
                sum -= sequence[left++];
            }
        }
        
        
        return answer;
    }
}