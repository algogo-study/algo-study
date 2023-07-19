package algo.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Swea2805 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] str;
		int [][] arr;
		int T = Integer.parseInt(br.readLine());
		int N;
		int range;
		int sum = 0;

		
		for (int tc = 1; tc <= T; tc++) {
			sum = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int row = 0; row < N; row++) {
				str = br.readLine().split("");
				for (int col = 0; col < N; col++) {
					arr[row][col] = Integer.parseInt(str[col]);
				}
			}
			
			for (int r = 0; r < N; r++) {
				sum += arr[r][N/2];
				if (r < (N/2 + 1)) {
					range = r % (N/2 + 1);
				} else {
					range = N - (r + 1);
				}
				for (int d = 1; d <= range; d++) {
					sum += arr[r][N/2 - d];
					sum += arr[r][N/2 + d];
				}
			}
			System.out.printf("#%d %d\n", tc, sum);
		}
	}
}
