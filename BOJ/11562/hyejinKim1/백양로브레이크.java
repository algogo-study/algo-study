package ssafy.algorithm.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백양로브레이크 {
	static class Node {
		int s, dis;

		public Node(int start, int dis) {
			super();
			this.s = start;
			this.dis = dis;
		}
	}

	static int n, m, MAX = 100000;
	static int[][] building, dist;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");

		n = Integer.parseInt(split[0]); // 건물의 수
		m = Integer.parseInt(split[1]); // 길의 수

		building = new int[n + 1][n + 1];
		dist = new int[n + 1][n + 1];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				
				if (i == j) {
					dist[i][j] = 0;
					building[i][j] = 0; // 길이 존재하면 0
				}
				else {
					dist[i][j] = MAX;
					building[i][j] = MAX;
				}
					
			}
		}

		for (int i = 0; i < m; i++) {
			split = br.readLine().split(" ");
			int u = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);
			int b = Integer.parseInt(split[2]);

			building[u][v] = 0;

			if (b == 1)
				building[v][u] = 0;
			else
				building[v][u] = 1; // v-u로 가려면 양방향으로 바꿔줘야 함
		}

		for (int i = 1; i < n + 1; i++) {
			bfs(i);
		}

		int k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			split = br.readLine().split(" ");
			int s = Integer.parseInt(split[0]);
			int e = Integer.parseInt(split[1]);

			System.out.println(dist[s][e]);
		}

	}

	private static void bfs(int k) {
		v = new boolean[n + 1];
		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1.dis, o2.dis);
		});
		q.offer(new Node(k, 0));

		while (!q.isEmpty()) {
			Node p = q.poll();

			dist[k][p.s] = Math.min(dist[k][p.s], p.dis);
			if (!v[p.s]) {
				v[p.s] = true;
				for (int i = 1; i < n + 1; i++) {
					if(building[p.s][i] != MAX)
						q.add(new Node(i, building[p.s][i]+p.dis));
				}
			}
		}
	}
}