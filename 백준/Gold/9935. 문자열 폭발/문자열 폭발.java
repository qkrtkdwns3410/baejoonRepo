import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        String explosionString = br.readLine();

        String result = explodeString(inputString, explosionString);
        System.out.println(result.isEmpty() ? "FRULA" : result);
    }

    private static String explodeString(String inputString, String explosionString) {
        Stack<Character> stack = new Stack<>();
        int explosionLength = explosionString.length();

        for (int i = 0; i < inputString.length(); i++) {
            stack.push(inputString.charAt(i));

            // If the current character sequence matches the explosion string, remove it from the stack
            if (stack.size() >= explosionLength) {
                boolean isExplosion = true;
                for (int j = 0; j < explosionLength; j++) {
                    if (stack.get(stack.size() - explosionLength + j) != explosionString.charAt(j)) {
                        isExplosion = false;
                        break;
                    }
                }

                if (isExplosion) {
                    for (int j = 0; j < explosionLength; j++) {
                        stack.pop();
                    }
                }
            }
        }

        // Reconstruct the remaining string
        StringBuilder remainingString = new StringBuilder();
        for (char c : stack) {
            remainingString.append(c);
        }

        return remainingString.toString();
    }
}