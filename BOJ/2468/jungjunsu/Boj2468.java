import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int max = 0;
		int maxHeight = 0;

		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] > maxHeight) {maxHeight = map[row][col];}
			}
		}
		
		for (int rainHeight = 0; rainHeight < maxHeight; rainHeight++) {
			int safeCount = 0;
			boolean[][] memo = new boolean [N][N];

			for (int col = 0; col < N; col++) {
				boolean toggle = true;
				for (int row = 0; row < N; row++) {
					if (map[row][col] > rainHeight) {
						if (toggle) {safeCount++; toggle = false;}
						else {memo[row][col] = true;}
					} else {toggle = true;}
				}
			}

			for (int row = 0; row < N; row++) {
				boolean toggle = false;
				for (int col = 0; col < N; col++) {
					if (map[row][col] > rainHeight) {
						if (!(memo[row][col])) {
							if (col == 0) {toggle = true;}
							else if (toggle){safeCount--;}
							else {toggle = true;}
						} else {
							toggle = true;
							if ((col - 1 >= 0) && !(memo[row][col - 1]) && (map[row][col - 1] > rainHeight)) {safeCount--;}
						  }
					} else {toggle = false;}
				}
			}
			
			if (max < safeCount) {max = safeCount;}
		}
		System.out.println(max);
	}

}
