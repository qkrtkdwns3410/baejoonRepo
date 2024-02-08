import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *packageName    : org.example.알고리즘.평범한배낭
 * fileName       : Main
 * author         : ipeac
 * date           : 2024-02-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-02-08        ipeac       최초 생성
 */
public class Main {

	//물품의 수
	static int N;

	//준서가 버틸 수 있는 무게
	static int K;

	static Item[] items;

	static int[][] dp;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			// 첫째 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다.
			String[] split = br.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			int K = Integer.parseInt(split[1]);

			items = new Item[N + 1];
			dp = new int[N + 1][K + 1];

			for (int i = 1; i <= N; i++) {
				// 다음 N개의 줄에는 각 물품의 무게 W(1 ≤ W ≤ 100,000)와 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
				split = br.readLine().split(" ");

				int W = Integer.parseInt(split[0]);
				int V = Integer.parseInt(split[1]);

				items[i] = new Item(W, V);
			}

			for (int i = 1; i <= N; i++) {
				for (int w = 1; w <= K; w++) {
					if (items[i].weight <= w) {
						dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items[i].weight] + items[i].value);
					} else {
						dp[i][w] = dp[i - 1][w];
					}
				}
			}

			System.out.println(dp[N][K]);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Item {
		private final int weight;
		private final int value;

		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Item{" +
				"weight=" + weight +
				", value=" + value +
				'}';
		}
	}
}