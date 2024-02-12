import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 *packageName    : org.example.알고리즘.최소힙
 * fileName       : Main
 * author         : ipeac
 * date           : 2024-02-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-02-12        ipeac       최초 생성
 */
public class Main {
	static int N;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			PriorityQueue<Integer> queue = new PriorityQueue<>();
			N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(br.readLine());
				if (x == 0) {
					if (queue.isEmpty()) {
						System.out.println(0);
					} else {
						System.out.println(queue.poll());
					}
				} else {
					queue.add(x);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}