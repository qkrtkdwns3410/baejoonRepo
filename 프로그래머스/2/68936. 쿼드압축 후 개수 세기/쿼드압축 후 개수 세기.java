import java.util.*;

class Solution {
    public static int zeroCount;
    public static int oneCount;
    
    public int[] solution(int[][] arr) {
        int[] answer = {};
        
        compress(arr,0,0, arr.length);
        
        return new int[]{zeroCount, oneCount};
    }
    
    private void compress(int[][] board, int x , int y, int size){
        
        if(isSameAll(board,x,y,size)){
            if(board[x][y] == 0 ){
                zeroCount++;
            }else{
                oneCount++;
            }
            
            return;
        }
        
        int halfSize = size / 2 ;
        
        compress(board, x, y , halfSize);
        compress(board, x+halfSize, y , halfSize);
        compress(board, x, y+halfSize, halfSize);
        compress(board, x+halfSize, y+halfSize , halfSize);
    }
    
    private boolean isSameAll(int[][] board,int x, int y, int size){
        int firstNumber = board[x][y];
        
        for(int  i = x ; i < x + size ; i++){
            for(int j = y ; j < y + size ; j++){
                if(board[i][j] != firstNumber){
                    return false;
                }
            }
        }
        
        return true;
    }
}