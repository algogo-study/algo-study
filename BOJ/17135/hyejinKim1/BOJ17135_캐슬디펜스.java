package ssafy.algorithm.week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ17135_캐슬디펜스 {
	// 완성 못함
	static int n, m, d, num, cnt;
	static int[][] enemy;
	static int[][] check;
	static int[] col_num;
	
	static int[] dy = { 0, -1, 0 };
	static int[] dx = { -1, 0, 1 };
	
	static List <Integer> li = new ArrayList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		d = sc.nextInt();

		enemy = new int[n][m];
		check = new int[n][m];
		col_num = new int[m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				enemy[i][j] = sc.nextInt();
				if (enemy[i][j] == 1) {
					num++;
					col_num[j]++;
				}
			}
		}
		
		if(d == 1) {
			Arrays.sort(col_num);
			for(int i = 1; i <= 3; i++) {
				cnt += col_num[m-i];
			}
			System.out.println(cnt);
		}else {
			// 성 5칸 중에 3개 고르기 - 조합
			recursive(new int[] { 0, 1, 2, 3, 4 }, new int[3], 0, 0);
			
			int answer = 0;
			
			for(int i=0; i<li.size(); i++) {
				answer = Math.max(answer, li.get(i));
			}
			System.out.println(answer);
		}
	}

	//3명의 궁수 위치 구하기 - 조합
	private static void recursive(int[] arr, int[] sel, int idx, int k) {
		// basis part
		//한 턴  끝나면 -1 되돌리기
		// d가 1 이상일때만 이므로 
		if (k == sel.length) {
			cnt = 0;
			for(int y = n-1; y>=0; y--) {
				for(int sel_idx : sel) {
					//적이 있고 이번 턴에 다른 궁수가 쏘지 않았으면 
					if(enemy[y][sel_idx]==1 && check[y][sel_idx] == 0) { 
						check[y][sel_idx] = 1;
						cnt++;
					}
					else if( enemy[y][sel_idx] == 0 && check[y][sel_idx] == 0) { // 0이면 depth만큼 탐색
						search(y, sel_idx, 1);
					}
				}
			}
			li.add(cnt);
			return;
		}
		// inductive part
		for (int i = idx; i < arr.length; i++) {
			sel[k] = arr[i];
			recursive(arr, sel, i + 1, k + 1);
		}
	}
	
	private static void search(int y, int x, int k) {
		if (k == d)
			return;
		if (cnt == num)
			return;

		for(int i=0; i<3; i++){
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny >= 0 && nx >= 0 && nx < m) {
				if (enemy[ny][nx] == 1  && check[ny][nx] == 0) {
					check[ny][nx] = 1;
					cnt++;
					return;
				}else if(enemy[ny][nx] == 1  && check[ny][nx] == 1) {
					return;
				}
				else if (enemy[ny][nx] == 0 && check[ny][nx] == 0)
					search(ny, nx, k + 1);
			}
		}
	}
	
}
