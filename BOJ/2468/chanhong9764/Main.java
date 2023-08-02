import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pos {
	int x;
	int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[][] map;
	static int[] sel;
	static int m, d, n, answer;
	static boolean[] visited;

	static void relocation(int[][] tempMap) {
		for (int i = n - 1; i > 0; i--) {
			for (int j = 0; j < m; j++) {
				tempMap[i][j] = tempMap[i - 1][j];
			}
		}
		for (int i = 0; i < m; i++) {
			tempMap[0][i] = 0;
		}
	}

	static boolean isValidBoundary(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= m) {
			return false;
		}
		return true;
	}

	static int shooting(int[] pos) {
		int count = 0;
		int[][] tempMap = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tempMap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			boolean[] isShoot = new boolean[3];
			for (int l = 0; l < d; l++) {
				for (int j = 0; j < m; j++) {
					if (isValidBoundary(n - 1 - l, j)) {
						if (tempMap[n - 1 - l][j] == 1) {
							for (int k = 0; k < 3; k++) {
								if (!isShoot[k]) {
 									int dist = Math.abs(l + 1) + Math.abs(j - pos[k]);
									if (dist <= d) {
										isShoot[k] = true;
										tempMap[n - 1 - l][j] = 0;
										break;
									}
								}
							}
						}
					}
				}
			}
			for(int j = 0; j < 3; j++) {
				if(isShoot[j]) {
					count++;
				}
			}
			relocation(tempMap);
		}
		return count;
	}

	static void combination(int index, int r) {
		if (r == 3) {
			int idx = 0;
			int[] pos = new int[3];
			for (int i = 0; i < m; i++) {
				if (visited[i]) {
					pos[idx++] = i;
				}
			}
			answer = Math.max(answer, shooting(pos));
			return;
		}

		for (int i = index; i < m; i++) {
			visited[i] = true;
			combination(i + 1, r + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combination(0, 0);
		System.out.println(answer);
	}
}