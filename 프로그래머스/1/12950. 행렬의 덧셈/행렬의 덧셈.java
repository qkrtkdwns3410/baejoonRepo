import java.util.Arrays;

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        ArrayClass arrayClass1 = new ArrayClass(arr1);
        ArrayClass arrayClass2 = new ArrayClass(arr2);
        
        return arrayClass1.addArr(arrayClass2);
    }
    
    public static class ArrayClass {
        private final int[][] arr;
        private final int size;
        
        public ArrayClass(int[][] arr) {
            this.arr = arr;
            this.size = arr.length;
        }
        
        public int[][] addArr(ArrayClass arrayClass) {
            int[][] temp = new int[this.arr.length][this.arr[0].length];
            int[][] other = arrayClass.getArr();
            
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    int sum = other[i][j] + this.arr[i][j];
                    temp[i][j] = sum;
                }
            }
            
            return temp;
        }
        
        public int[][] getArr() {
            return arr;
        }
    }
    
}