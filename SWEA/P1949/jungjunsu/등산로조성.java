package Study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

class 등산로조성 {
	static int N, K;
	static int[][] map;
	static boolean[][] v;
	static int maxHeight;
	static int max;
	static Deque<Point> Q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("./input/등산로조성"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			Q = new ArrayDeque<Point>();
			maxHeight = Integer.MIN_VALUE;
			max = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[r][c]);
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (maxHeight == map[r][c]) Q.add(new Point(r, c));
				}
			}
			
			int size = Q.size();
			for (int i = 0; i < size; i++) {
				v = new boolean[N][N];
				Point p = Q.pollFirst();
				int memo = K;
				for (int W = 1; W <= memo; W++) {
					K = W;
					recursive(p.r, p.c, 0, 1, new int[20], 0, 0);
				}
				K = memo;
				
			}
			sb.append("#"+tc+" "+max+"\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.print(sb.toString());
		
	}
	
	static int[] dr = {0 , 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	private static void recursive(int r, int c, int use, int roadLength, int[] test, int ti, int depth) {
		v[r][c] = true;
		max = Math.max(max, roadLength);

		for (int i = 0; i < 4; i++) {
			if (depth == 0) {v = new boolean[N][N]; v[r][c] = true;}
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < N && nr >= 0 && nc < N && nc >= 0 && !v[nr][nc]) {
				if (map[nr][nc] < map[r][c]) {
					recursive(nr, nc, use, roadLength + 1, test, ti + 2, depth + 1);
				}
				else if (use != 1 && (map[nr][nc] - K < map[r][c])) {
					map[nr][nc] -= K;
					recursive(nr, nc, 1, roadLength + 1, test, ti + 2, depth + 1);
					map[nr][nc] += K;
				}
			}
		}
		v[r][c] = false;
	}

	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
