package ssafy.algorithm.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 무선충전 {

	static class Node {
		int y, x, c, p;

		public Node(int y, int x, int c, int p) {
			this.y = y;
			this.x = x;
			this.c = c; // 충전 범위
			this.p = p; // 처리량
		}
	}

	static int m, cnum, ay=1, ax=1, by=10, bx=10;
	static int[] dy = { 0, 0, 1, 0, -1 };
	static int[] dx = { 0, -1, 0, 1, 0 };
	static List<Node> AP;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] split = br.readLine().split(" ");

			m = Integer.parseInt(split[0]); // 이동 횟수
			cnum = Integer.parseInt(split[1]); // 충전기 수

			int[][] user = new int[2][m + 1];

			for (int i = 0; i < 2; i++) {
				split = br.readLine().split(" ");
				for (int j = 1; j < m + 1; j++) {
					user[i][j] = Integer.parseInt(split[j - 1]);
				}
			}

			AP = new ArrayList<>(); // 무선 충전기 정보

			for (int i = 0; i < cnum; i++) {
				split = br.readLine().split(" ");
				int y = Integer.parseInt(split[0]);
				int x = Integer.parseInt(split[1]);
				int c = Integer.parseInt(split[2]);
				int p = Integer.parseInt(split[3]);

				AP.add(new Node(y, x, c, p));
			}
			
			int answer = 0;
			for (int i = 0; i < m + 1; i++) {
				ay += dy[user[0][i]];
				ax += dx[user[0][i]];

				by += dy[user[1][i]];
				bx += dx[user[1][i]];

				int max = 0;
				for (int a = 0; a < cnum; a++) {
					for (int b = 0; b < cnum; b++) {
						int ap = distance(ay, ax, a);
						int bp = distance(by, bx, b);
						
						int sum = 0;
						if (a != b) sum = ap + bp;
						else sum = Math.max(ap, bp);
						
						max = Math.max(max, sum);
					}
				}
				answer += max;
			}
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static int distance(int y, int x, int i) {
		Node bc = AP.get(i);
		if (Math.abs(bc.y - y) + Math.abs(bc.x - x) <= bc.c)
			return bc.p;
		return 0;
	}
}
