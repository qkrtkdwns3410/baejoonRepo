public class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        int answer = 0;
        
        // 철수의 카드에 적힌 숫자들의 GCD 구하기
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
        }
        
        // 영희의 카드에 적힌 숫자들의 GCD 구하기
        for (int i = 1; i < arrayB.length; i++) {
            gcdB = gcd(gcdB, arrayB[i]);
        }
     
        for(int i = 2 ; i <= gcdA ; i++){
            if(gcdA % i == 0 && !isDivide(i, arrayB)){
                System.out.println(i);
                answer = Math.max(answer, i );
            }
        }
        
        for(int i = 2 ; i <= gcdB ; i++){
            if(gcdB % i == 0 && !isDivide(i, arrayA)){
                System.out.println(i);
                answer = Math.max(answer, i );
            }
        }
        
        return answer; // 조건을 만족하는 a가 없는 경우
    }
    
    private boolean isDivide(int i, int[] arr){
        for(int num : arr){
            if(num % i == 0){
                return true;
            }
        }
        
        return false;
    }
    
    // 최대공약수(GCD)를 구하는 메소드
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}