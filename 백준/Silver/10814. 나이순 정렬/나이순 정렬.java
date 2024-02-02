import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : org.example.알고리즘.나이순정렬 fileName       : Main author         : ipeac date :
 * 2024-02-02 description    : =========================================================== DATE
 * AUTHOR             NOTE ----------------------------------------------------------- 2024-02-02
 * ipeac       최초 생성
 */
public class Main {
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            List<Person> people = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] split = br.readLine().split(" ");
                Person person = new Person(Integer.parseInt(split[0]), split[1]);
                people.add(person);
            }
            
            people.sort(Person::compareTo);
            people.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static class Person implements Comparable<Person> {
        
        private static int nextId = 1;
        private final int id;
        private int age;
        private String name;
        
        public Person(int age, String name) {
            this.id = nextId++;
            this.age = age;
            this.name = name;
        }
        
        public int getAge() {
            return age;
        }
        
        public String getName() {
            return name;
        }
        
        @Override
        public int compareTo(Person o) {
            if (this.age == o.age) {
                return Integer.compare(this.id, o.id);
            }
            return Integer.compare(this.age, o.age);
        }
        
        @Override
        public String toString() {
            return age + " " + name;
        }
    }
}