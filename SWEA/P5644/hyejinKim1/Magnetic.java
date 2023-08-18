import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Magnetic {
	static int[][] arr;
	static int answer, n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			answer = 0;

			for (int i = 0; i < n; i++) {
				String[] split = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(split[j]);
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == 1)
						search(i, j);
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void search(int y, int x) {
		if (y + 1 < n) {
			if (arr[y + 1][x] == 2) {
				answer++;
				return;
			} else if (arr[y + 1][x] == 0)
				search(y + 1, x);
		}
	}
}