package algo.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Boj1592 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int [] arr;
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int count = 0;
		int index = 0;

		arr = new int[N];
		while (count >= 0) {
			arr[index] += 1;

			for (int x : arr) {
				if (x == M) {
					System.out.println(count);
					count = -2;
					break;
				}
			}
			
			if (arr[index] % 2 == 0) {
				index = index >= L ? index - L : N + (index - L);
			} else {
				index = (index + L) % N;
			}
			count++;
		}
		
		
	}
}
