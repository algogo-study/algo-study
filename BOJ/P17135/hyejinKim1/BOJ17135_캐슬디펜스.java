package ssafy.algorithm.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class BOJ17135_캐슬디펜스{
	static int n, m, d, num, cnt;
	static int[][] enemy;
	static int[] col_num;

	static int[] dy = {0, 0, -1, 0 };
	static int[] dx = {0, -1, 0, 1 };

	static List<Integer> li = new ArrayList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		d = sc.nextInt();

		enemy = new int[n + 1][m];
		col_num = new int[m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				enemy[i][j] = sc.nextInt();
				if (enemy[i][j] == 1) {
					num++;
					col_num[j]++;
				}
			}
		}

		int answer = 0;

		if (d == 1) { // 바로 앞에만 보기 때문에 열의 합 가장 큰 3개
			Arrays.sort(col_num);
			for (int i = 1; i <= 3; i++) {
				answer += col_num[m - i];
			}
		} else {

			int[] arr = new int[m];
			for (int i = 0; i < arr.length; i++) {

			}

			recursive(new int[3], 0, 0);

			for (int i = 0; i < li.size(); i++) { // 모든 조합 중에 최대 값 고르기
				answer = Math.max(answer, li.get(i));
			}
		}

		System.out.println(answer);
	}

	// 3명의 궁수 위치 구하기 - 조합
	private static void recursive(int[] sel, int idx, int k) {
		// basis part
		if (k == sel.length) {
			boolean[][] kill = new boolean[n + 1][m];
			cnt = 0;
			for (int y = n; y > 0; y--) {
				for (int sel_idx : sel) {
					search(kill, y, sel_idx, 0);
				}
			}
			li.add(cnt);
			return;
		}
		// inductive part
		for (int i = idx; i < m; i++) {
			sel[k] = i;
			recursive(sel, i + 1, k + 1);
		}
	}
	
	//현재- 좌 - 상 - 우 순서로 탐색
	private static void search(boolean[][] kill, int y, int x, int k) {
		if (k == d)
			return;
		if (cnt == num)
			return;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && nx >= 0 && nx < m) {
				if (enemy[ny][nx] == 1 && !kill[ny][nx]) {
					kill[ny][nx] = true;
					cnt++;
//					System.out.println("search" + ny+ nx);
					return;
				} else if (kill[ny][nx]) // 다른 궁수와 같은 적 쏜 경우
					return;
				else if (enemy[ny][nx] == 0)
					search(kill, ny, nx, k + 1);
			}
		}
	}

}
