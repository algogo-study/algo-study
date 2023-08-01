package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2644 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] relations = new int[N][N];
		int[][] memo = new int[2][N];

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()) - 1;
		int b = Integer.parseInt(st.nextToken()) - 1;
		int cases = Integer.parseInt(br.readLine());
		int count = 0;
		boolean find = true;

		for (int i = 0; i < cases; i++) {
			st = new StringTokenizer(br.readLine());
			relations[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		memo[0][0] = a + 1;
		memo[1][0] = b + 1; 
		int i = 1;
		int j = 1;
		do {
			find = false;
			for (int col = 0; col < N; col++) {
				for (int row = 0; row < N; row++) {
					if (relations[row][col] == 1) {
						if (col == a) {
							a = row;
							memo[0][i++] = a + 1;
							find = true;
						} else if (col == b) {
							b = row;
							memo[1][j++] = b + 1;
							find = true;
						}
					}
				}
			}
		} while(find);

		int a_end = 0;
		int b_end = 0;
		if (memo[0][i-1] != memo[1][j-1]) {
			a_end = -1;
		} else {
			while(true) {
				if (i - 1 < 0 || j - 1 < 0) {
					break;
				}
				if (memo[0][i-1] == memo[1][j-1]) {
					a_end = i-1;
					b_end = j-1;
				}
				i--;
				j--;
			}
		}
		System.out.println((a_end + b_end));

	}

}
