package ssafy.algorithm.week6.BOJ16234인구이동;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//인구이동

class Country {
	int y;
	int x;

	public Country(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Main { // 인구이동
	static int[][] a;
	static boolean[][] v;
	static int N, L, R;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static List<Country> li;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();

		a = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a[i][j] = sc.nextInt();
			}
		}

		int answer = 0;

		L: while (true) { // 더 이상 인구 이동이 없을 때까지
			boolean flag = true;
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if( !v[i][j] ) {
						li = new ArrayList<>(); // 인구 이동할 나라
						int sum = recursive(i, j);
						
						if (li.size() > 1) {
							int avg = sum / li.size();
							for (Country c : li) {
								a[c.y][c.x] = avg;
								flag = false;
							}
						}
					}
				}
			}
			
			if (flag) {
				System.out.println(answer);
				break L;
			}
			
			answer++;
		}

	}

	private static int recursive(int y, int x) { // bfs

		li.add(new Country(y, x));
		v[y][x] = true;

		int sum = a[y][x];

		Queue<Country> Q = new LinkedList<>();
		Q.offer(new Country(y, x));

		while (!Q.isEmpty()) {
			Country c = Q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = c.y + dy[i];
				int nx = c.x + dx[i];

				if (ny < 0 || nx < 0 || nx > N - 1 || ny > N - 1)
					continue;
				if (v[ny][nx])
					continue;
				if (Math.abs(a[c.y][c.x] - a[ny][nx]) >= L && Math.abs(a[c.y][c.x] - a[ny][nx]) <= R) {
					li.add(new Country(ny, nx));
					Q.offer(new Country(ny, nx));
					sum += a[ny][nx];
					v[ny][nx] = true;
				}
			}
		}
		return sum;
	}
}