package ssafy.algorithm.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//상어가 먹을 수 있는 물고기를 리스트에 담기

public class 아기상어 {
	static class Node {
		int y, x, cnt;

		public Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

	static int n, answer, eat, size;
	static int[][] a;
	static boolean[][] v;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		Queue<Node> q = new LinkedList<>();
		a = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] split = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(split[j]);
				if (a[i][j] == 9) {
					q.add(new Node(i, j, 0));
					a[i][j] = 0;
				}

			}
		}

		size = 2;
		eat = 0;
		answer = 0;

		L: while (true) {
			ArrayList<Node> li = new ArrayList<>();
			int[][] d = new int[n][n];

			while (!q.isEmpty()) {
				Node p = q.poll();

				for (int k = 0; k < 4; k++) {
					int ny = p.y + dy[k];
					int nx = p.x + dx[k];

					if (ny < 0 || nx < 0 || ny > n - 1 || nx > n - 1)
						continue;
					if (d[ny][nx] == 0 && a[ny][nx] <= size) {
						d[ny][nx] = d[p.y][p.x] + 1;
						q.add(new Node(ny, nx, d[ny][nx]));
						if (1 <= a[ny][nx] && a[ny][nx] <= 6 && a[ny][nx] < size) 
							li.add(new Node(ny, nx, d[ny][nx]));	
					}

				}
			}

			if (li.size() == 0)
				break L;

			Node no = li.get(0);
			for (int i = 1; i < li.size(); i++) {
				if (no.cnt > li.get(i).cnt) 
					no = li.get(i);
				else if (no.cnt == li.get(i).cnt) {
					if (no.y > li.get(i).y) 
						no = li.get(i);
					else if (no.y == li.get(i).y)
						if (no.x > li.get(i).x) no = li.get(i);
				}
			}
			
			eat++;
			a[no.y][no.x] = 0;
			answer += no.cnt;
			
			if (size == eat) {
				size++;
				eat = 0;
			}
			q.add(no);
		}
		System.out.println(answer);
	}
}