import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Solution {
	static String[][] map;
	static int N;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static boolean isValid(int x, int y) {
		if (x > N || x < 1 || y > N || y < 1) {
			return false;
		}
		return true;
	}

	static void othello(int x, int y, String dol) {
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < N; j++) {
				int nx = x + dx[i] * j;
				int ny = y + dy[i] * j;

				if (!isValid(nx, ny) || map[nx][ny] == null) {
					break;
				}
				if (dol.equals(map[nx][ny])) {
					for (int k = 0; k < j; k++) {
						map[x + dx[i] * k][y + dy[i] * k] = dol;
					}
					break;
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			map = new String[N + 1][N + 1];
			int init = N / 2;
			for (int i = init; i <= init + 1; i++) {
				for (int j = init; j <= init + 1; j++) {
					if (i == j) {
						map[i][j] = "W";
					} else {
						map[i][j] = "B";
					}
				}
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				String dol = Integer.parseInt(st.nextToken()) == 1 ? "B" : "W";
				othello(x, y, dol);
			}

			int white = 0;
			int black = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == "W") {
						white += 1;
					} else if (map[i][j] == "B") {
						black += 1;
					}
				}
			}
			System.out.println("#" + tc + " " + black + " " + white);
		}
	}
}