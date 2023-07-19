package algo.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2798 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int [] arr;
		int N = 0;
		int M = 0;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(dfs(arr, 0, 0, 0, 0, M));
	}
	
	private static int dfs(int [] arr, int depth, int start_index, int sum, int max, int M) {
		if (depth >= 3) {
			if (max < sum) {
				max = sum;
			}
			return max;
		} else {
			for (int i = start_index; i < arr.length; i++) {
				if (sum + arr[i] <= M) {
					sum += arr[i];
					max = dfs(arr, depth + 1, i + 1, sum, max, M);
					sum -= arr[i];
				}
			}
		}

		return max;
	}
	
	
}
