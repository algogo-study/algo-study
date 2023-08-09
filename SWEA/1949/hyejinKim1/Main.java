package ssafy.algorithm.week6.SWEA1949등산로조성;

import java.util.Scanner;
// 등산로 조성
public class Main {
	static int[][] a; // 등산 지형
	static boolean[][] v; // 방문 배열
	static int N, K, answer;
	static boolean flag;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			N = sc.nextInt();
			K = sc.nextInt();
			
			a = new int[N][N];

			int max = 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					a[i][j] = sc.nextInt();
					max = Math.max(max, a[i][j]); // 가장 높은 봉우리의 높이 저장
				}
			}
			
			answer = 0;
			v = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(a[i][j] == max) dfs(i, j, 1); // 가장 높은 봉우리에서 시작
				}	
			}
			
			System.out.println("#"+tc+" "+answer);
		}
	}
	
	public static void dfs(int y, int x, int len) {
		v[y][x] = true;
		answer = Math.max(answer, len);
		
		for(int i=0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			if (ny < 0 || nx < 0 || nx > N - 1 || ny > N - 1)
				continue;
			if (v[ny][nx])
				continue;
			if(a[y][x] > a[ny][nx]) { // 더 낮은 지형으로 이동
				dfs(ny,nx, len+1);
			}
			else if(!flag){ // 더 높고 지형을 깎은 적 없으면 깎고 이동
				for(int k = 1; k<=K; k++) {
					a[ny][nx] -= k;
					flag = true;
					if(a[y][x] > a[ny][nx])
						dfs(ny,nx, len+1);
					flag = false;
					a[ny][nx] += k;
				}
			}
		}
		v[y][x] = false;
	}
}
