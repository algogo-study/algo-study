package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Boj17135 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		int[][] map = new int[N+1][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		int[] max = new int[1];
		rc(map, max, N, M, D, 0, 0);
		System.out.println(max[0]);
	}

	static void rc(int[][] map, int[] max, int N, int M, int D, int depth, int s_index) {
		if (depth == 3) {
			playGame(map, max, N, M, D);
		} else {
			for (int i = s_index; i < M; i++) {
				map[N][i] = 1;
				rc(map, max, N, M, D, depth + 1, i + 1);
				map[N][i] = 0;
			}
		}
	}
	
	static void playGame(int[][] map, int[] max, int N, int M, int D) {
		int[][] temp = new int[N+1][M];
		int lineCount = 0;
		int killCount = 0;

		// 베열 복사
		for (int i = 0; i < N+1; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		/* 미구현부분
		while(lineCount != N) {
			for (int i = 0; i < M; i++) {
				if (temp[N][i] == 1) {
					for (int row = N - 1 - lineCount; row >= 0; row--) {
						int[] kArr = new int[M];
						for (int col = 0; col < M; col++) {
							if ((temp[row][col] == 1) && (D >= Math.abs(N - lineCount - row) + Math.abs(i - col))) {
								kArr[col] = 1;
							}
						}
						
					}
					
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (temp[r][c] == -1) {
						temp[r][c] = 0;
					}
				}
			}
			lineCount++;
		} */
		
		if (max[0] < killCount) {
			max[0] = killCount;
		}
		
	}

}
