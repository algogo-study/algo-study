package exercise.src.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Swea4615
{
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] arr;
		int T = Integer.parseInt(br.readLine());
		int N = 0;
		int M = 0;
		int [] count = new int[2];
		int[][] dm = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			count = new int[2];

			initPos(arr, N);
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken()) - 1;
				int row = Integer.parseInt(st.nextToken()) - 1;
				int type = Integer.parseInt(st.nextToken());
				
				if (checkPos(arr, row, col, type, N)) {
					updatePos(arr, dm, N, row, col, type);
				} else {
					continue;
				}
				
			}
			
			calcMap(arr, N, count);
			System.out.printf("#%d %d %d\n", tc, count[0], count[1]);
		}
	}
	
	static void initPos(int[][] arr, int N) {
		int a = N/2;
		int b = N/2 - 1;
		arr[a][a] = 2;
		arr[b][b] = 2;
		arr[a][b] = 1;
		arr[b][a] = 1;
	}

	static boolean checkPos(int[][] arr, int row, int col, int type, int N) {
		if (row < N && row >= 0 && col < N && col >= 0) {
			return true;
		}
		return false;
	}
	
	static void updatePos(int[][] arr, int[][] dm, int N, int row, int col, int type) {
		int[][] diff;
		int cr = 0;
		int cc = 0;
		int count = 0;
		arr[row][col] = type;

		for (int dr = 0; dr < 8; dr++) {
			diff = new int[N-1][N-1];
			count = 0;
			for (int w = 1; w < N; w++) {
				cr = row + w*dm[dr][0];
				cc = col + w*dm[dr][1];
	
				if (cr < N && cr >= 0 && cc < N && cc >= 0) {
					if (arr[cr][cc] != type && arr[cr][cc] != 0) {
						diff[w-1][0] = cr;
						diff[w-1][1] = cc;
						count++;
					} else {
						if (arr[cr][cc] == type) {
							for (int i = 0; i < count; i++) {
								arr[diff[i][0]][diff[i][1]] = type;
							}
							}
						break;
					}
				} else {
					break;
				}
			}
		}
	}
	
	static void calcMap(int[][] arr, int N, int[] count) {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (arr[row][col] == 1) {
					count[0] += 1;
				} else if (arr[row][col] == 2){
					count[1] += 1;
				}
			}
		}
	}
}