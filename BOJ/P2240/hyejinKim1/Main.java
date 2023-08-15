package ssafy.algorithm.week6.BOJ2240자두나무;

import java.util.Arrays;
import java.util.Scanner;

class Main { // 자두나무
	static int T, W, cnt;
	static int [] a;
	static int [][] answer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int W = sc.nextInt();
		a = new int[T];
		
		for(int i=0; i<T; i++) {
			a[i] = sc.nextInt();
		}
		
		// 자두가 1에서 떨어질 때
		// 자두가 2에서 떨어질 때
		// 자두는 1에서 시작 - 0->1 1->2 2-> 1
		// 지금까지 움직인 횟수  = n
		// n%2 == 0 이면 1번 나무, 1이면 2번 나무
		answer = new int[T][W+1]; // 떨어진 횟수 / 움직인 횟수 
		
	
		for(int i=0; i<W+1; i++) {
			if((a[0] == 1 && i%2 == 0) || (a[0] == 2 && i%2 == 1)) answer[0][i] = 1;
			else answer[0][i] = 0;
		}
		
		
		for(int i=1; i<T; i++) {
			//0번 움직이면 1번에서 떨어지는 것만 먹음
			if(a[i] == 1) {
				answer[i][0] = answer[i-1][0]+1;
			}else {
				answer[i][0] = answer[i-1][0];
			}
			
			//1번 이상 움직이면
			for(int j = 1; j<W+1; j++) { // 움직인 횟수
				if((a[i] == 1 && j%2 == 0) || (a[i] == 2 && j%2 == 1)) { // 자두를 받아먹음
					answer[i][j] = Math.max(answer[i-1][j-1],answer[i-1][j])+1;
				}else // 자두를 못받음
					answer[i][j] = Math.max(answer[i-1][j-1],answer[i-1][j]);
			}
		}

		int max = 0;
		for(int i=0; i<W+1; i++) {
			max = Math.max(max, answer[T-1][i]);
		}
		
		System.out.println(max);
		
	}
}

