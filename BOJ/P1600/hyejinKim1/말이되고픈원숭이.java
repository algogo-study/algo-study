package Ex;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class 말이되고픈원숭이 {
	static int k, w, h, ans = -1;
	static int[][] arr;
	static boolean[][][] v;
	static int[] dx = { 0, 0, 1, -1, -2, 2, -1, 1, -2, 2, -1, 1 };
	static int[] dy = { 1, -1, 0, 0, -1, -1, -2, -2, 1, 1, 2, 2 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		k = sc.nextInt();
		w = sc.nextInt();
		h = sc.nextInt();

		arr = new int[h][w];
		v = new boolean[h][w][k + 1];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		if (h == 1 && w == 1)
			ans = 0;
		else
			bfs(0, 0);

		System.out.println(ans);
	}

	private static void bfs(int x, int y) {
		Deque<Integer[]> q = new ArrayDeque<>();

		q.add(new Integer[] { x, y, 0, 0 });
		v[x][y][0] = true;

		while (!q.isEmpty()) {
			Integer[] p = q.poll();

			for (int i = 0; i < 12; i++) {

				int nx = p[0] + dx[i];
				int ny = p[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= h || ny >= w)
					continue;
				if (arr[nx][ny] == 1)
					continue;

				if (i < 4) {
					if (v[nx][ny][p[3]])
						continue;
					q.add(new Integer[] { nx, ny, p[2] + 1, p[3] });
					v[nx][ny][p[3]] = true;
				} else {
					if (p[3] == k)
						continue;
					if (v[nx][ny][p[3] + 1])
						continue;
					q.add(new Integer[] { nx, ny, p[2] + 1, p[3] + 1 });
					v[nx][ny][p[3] + 1] = true;
				}

				if (nx == h - 1 && ny == w - 1) {
					ans = p[2] + 1;
					return;
				}
			}
		}
	}

}
