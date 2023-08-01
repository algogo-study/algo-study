package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17626 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] cArr = new int[4];
		int[] memo = new int[n + 1];

		rc(n, 0, cArr, memo);
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			if (cArr[i] == 1) {
				idx = i;
				break;
			}
		}
		System.out.println(idx + 1);
	}

	static void rc(int n, int count, int[] cArr, int[] memo) {
		if ((count == 4) || cArr[count] == 1) {
			return;
		}
		if (memo[n] == 1) {
			return;
		} else {
			double rt = Math.sqrt(n);
			if (rt > (int)rt) {
				
			} else {memo[n] = 1; cArr[count] = 1; return;}
			for (int i = 1; i <= Math.sqrt(n); i++) {
				if (n - i*i > 0) {
					rc(n - i*i, count + 1, cArr, memo);
				}
			}
		}
		
	}
	
	static boolean checkSquare(int n, int[] memo) {
		for (long i = n/2 + 1; i >= 0; i--) {
			if (i * i == n) {
				memo[n] = 1;
				return true;
			}
		}
		
		return false;
	}
}
