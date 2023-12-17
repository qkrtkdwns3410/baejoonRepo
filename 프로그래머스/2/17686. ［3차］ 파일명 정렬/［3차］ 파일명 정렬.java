
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    
    public List<String> solution(String[] files) {
        List<FileInfo> answer = new ArrayList<>();
        for (String word : files) {
            FileInfo fileInfo = FileInfo.split(word);
            //head number tail 로 분리해보자
            answer.add(fileInfo);
        }
        
        answer.sort((file1, file2) -> {
            int headCompare = file1.getHead().toLowerCase().compareTo(file2.getHead().toLowerCase());
            if (headCompare != 0) {
                return headCompare;
            }
            int number1 = Integer.parseInt(file1.getNumber());
            int number2 = Integer.parseInt(file2.getNumber());
            return Integer.compare(number1, number2);
        });
        
        return answer.stream().map(FileInfo::toString).collect(Collectors.toList());
    }
    
    
    static class FileInfo {
        private final String head;
        private final String number;
        private final String tail;
        
        public String getHead() {
            return head;
        }
        
        public String getNumber() {
            return number;
        }
        
        public static FileInfo split(String word) {
            int index = 0;
            StringBuilder head = new StringBuilder();
            StringBuilder number = new StringBuilder();
            StringBuilder tail = new StringBuilder();
            
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (Character.isDigit(c)) {
                    break;
                }
                head.append(c);
                index++;
            }
            
            for (int i = index; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!Character.isDigit(c)) {
                    break;
                }
                number.append(c);
                index++;
            }
            
            for (int i = index; i < word.length(); i++) {
                char c = word.charAt(i);
                tail.append(c);
            }
            
            return new FileInfo(head.toString(), number.toString(), tail.toString());
        }
        
        
        public FileInfo(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            return String.valueOf(sb.append(head).append(number).append(tail));
        }
    }
}