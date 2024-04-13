import java.util.Stack;

class Solution {
    boolean solution(String s) {
        char[] chars = s.toCharArray();
        
        Stack<Character> stacks = new Stack<>();
        
        for (char c : chars) {
            if (c == '(') {
                stacks.add(c);
            } else {
                if (stacks.isEmpty() || stacks.pop() == c) {
                    return false;
                }
            }
        }
        
        return stacks.isEmpty();
    }
    
    public static <T> void print(T... t) {
        for (T t1 : t) {
            System.out.print(t1 + " ");
        }
        System.out.println();
    }
}