package ssafy.algorithm.week5;

import java.util.Scanner;

public class BOJ17626_FourSquares {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] arr = new int[n + 1];

		for (int i = 1; i <= n; i++) { // n번
			int min = Integer.MAX_VALUE;

			int j = 1;
			while(j*j <= i) {
				min = Math.min(min, arr[i-j*j]);
				j ++;
			}
			
			arr[i] = min + 1;
		}

		System.out.println(arr[n]);
	}
}
