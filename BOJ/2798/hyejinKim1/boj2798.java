package swea.study.boj;

import java.util.Scanner;

public class boj2798 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int result = 0;
		
		
		for (int i = 0; i < n - 2; i++) {

			// 두 번째는 첫 번째 다음부터 n-1 까지
			for (int j = i + 1; j < n - 1; j++) {

				// 세 번째는 두 번째 다음부터 n까지
				for (int k = j + 1; k < n; k++) {

					int sum = arr[i] + arr[j] + arr[k]; // 3개 카드의 합
					
					if (m == sum) { // 세 카드의 합이 m과 같을 때
						result = sum;
					}

					// 세 카드의 합이 이전 합보다 크고 m보다 작을 때
					if (result < sum && sum < m) {
						result = sum;
					}
				}
			}
		}
			System.out.println(result);
	}
}
