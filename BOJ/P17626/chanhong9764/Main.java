package BOJ.P17626.chanhong9764;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	static int dp[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		
		dp[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			int min_value = Integer.MAX_VALUE;
			for(int j = 1; j <= Math.sqrt(i); j++) {
				min_value = Math.min(min_value, dp[i - j*j]);
			}
			dp[i] = min_value + 1;
		}
		System.out.println(dp[n]);
	}
}