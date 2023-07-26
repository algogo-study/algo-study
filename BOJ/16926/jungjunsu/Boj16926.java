package exercise.src.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16926 {
	public static void main(String[] args) throws Exception{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int N = Integer.parseInt(st.nextToken());
	int M = Integer.parseInt(st.nextToken());
	int R = Integer.parseInt(st.nextToken());
	int[][] map = new int[N][M];
	int[][] dm = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	
		for (int i = 0; i < R; i++) {
			map = arrRotation(map, dm, N, M);
		}
	
		for (int[] y : map) {
			for (int i = 0; i < M; i++) {
				System.out.print(y[i]);
				if (i != M-1) System.out.print(" ");
			}
			System.out.println();
		}
	}

	static int[][] arrRotation(int[][] map, int[][] dm, int N, int M) {
		int[][] tMap = new int[N][M];
		int dPos = 0;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (col > row%(N/2 + N%2) + row/(N/2 + N%2) * (N/2 - 1 - 2*(row%(N/2 + N%2))) - row/(N/2 + N%2)
					&&
					col < M - 1 - (row%(N/2 + N%2) + row/(N/2 + N%2) * (N/2 - 1 - 2*(row%(N/2 + N%2)))) + (row/(N/2 + N%2)^1)) {
					if (row < N/2 + N%2) {
						dPos = 0;
					} else {
						dPos = 1;
					}
				} else if (col < M/2 + M%2) {
					dPos = 2;
				} else {
					dPos = 3;
				}
				tMap[row + dm[dPos][0]][col + dm[dPos][1]] = map[row][col];
			}
		}

		return (tMap);
	}
}