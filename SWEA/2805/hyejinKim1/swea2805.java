package ssafy.study.swea;

import java.util.Scanner;

public class swea2805 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int n = sc.nextInt();
			int[][] map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				String str = sc.next();
				int[] line = new int[n];
				for (int j = 0; j < n; j++) {
					line[j] = str.charAt(j) - '0';
				}
				map[i] = line;
			}
			

			int ans = 0;
			int start = n / 2;
			int end = n / 2;
			for (int i = 0; i < n; i++) {
				for (int j = start; j <= end; j++) {
					ans += map[i][j];
				}
				if (i < n / 2) { // 행의 절반 이전
					start -= 1; 
					end += 1; 
				} else { // 행의 절반 이후
					start += 1; 
					end -= 1; 
				}
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}

}
