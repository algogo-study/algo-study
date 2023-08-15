package ssafy.algorithm.week5;

import java.util.Scanner;

class Main {
	static int[][] arr;
	static boolean[][] v;
	static int n;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
				min = Math.min(min, arr[i][j]);
				max = Math.max(max, arr[i][j]);
			}
		}

		int answer = 0;

		// 최저 높이에서 최대 높이까지 반복
		// 경계값보다 높은 값이 있으면 이어진 곳 탐색하면서 visit 배열에 true
		for (int height = min - 1; height < max; height++) {
			v = new boolean[n][n];
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!v[i][j] && arr[i][j] > height) {
						cnt++;
						dfs(i, j, height);
					}

				}
			}
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);
	}

	// 안전 영역 탐색
	static void dfs(int x, int y, int height) {
		v[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1)
				continue;
			if (v[nx][ny])
				continue;
			if (arr[nx][ny] > height) {
				dfs(nx, ny, height);
			}
		}
		return;
	}
}
