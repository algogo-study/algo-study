package ssafy.algorithm.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 다리만들기 {
	static class Node {
		int y, x, cnt;

		public Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	static int n, num = 1, answer = Integer.MAX_VALUE;
	static int[][] a;
	static boolean[][] v;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		a = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] split = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(split[j]);
			}
		}

		v = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] == 1 && !v[i][j]) {
					Queue<Node> q = new LinkedList<Node>();
					q.offer(new Node(i, j, 0));
					v[i][j] = true;
					a[i][j] = num;
					while (!q.isEmpty()) {
						Node no = q.poll();
						for (int k = 0; k < 4; k++) {
							int ny = no.y + dy[k];
							int nx = no.x + dx[k];
							if (ny < 0 || nx < 0 || ny > n - 1 || nx > n - 1)
								continue;
							if (v[ny][nx])
								continue;
							if (a[ny][nx] == 1) {
								v[ny][nx] = true;
								a[ny][nx] = num;
								q.offer(new Node(ny, nx, 0));
							}
						}
					}
					num++;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] >= 2) {
					v = new boolean[n][n];
					bfs(i, j);
				}
			}
		}

		System.out.println(answer);

	}

	private static void bfs(int y, int x) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(y, x, 0));
		v[y][x] = true;
		while (!q.isEmpty()) {
			Node no = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = no.y + dy[i];
				int nx = no.x + dx[i];

				if (ny < 0 || nx < 0 || ny > n - 1 || nx > n - 1)
					continue;
				if (v[ny][nx])
					continue;
				if (a[ny][nx] != a[y][x]) {
					v[ny][nx] = true;
					if (a[ny][nx] == 0) { // 바다
						q.offer(new Node(ny, nx, no.cnt + 1));
					} else { // 다른 섬
						answer = Math.min(answer, no.cnt);
					}
				}
			}
		}
	}
}