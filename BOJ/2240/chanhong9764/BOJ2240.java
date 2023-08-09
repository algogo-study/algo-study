import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		// 0 - 움직임 횟수, 1 - 초, 2 - 위치
		int[][][] jadoo = new int[W + 1][T + 1][2];
		int[] fruit = new int[T + 1];

		for (int i = 1; i <= T; i++) {
			fruit[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i <= W; i++) {
			for (int j = 1; j <= T; j++) {
				if(i == 0 && j == 0) {
					if(fruit[j] == 1) jadoo[i][j][0] = 1;
					else jadoo[i][j][1] = 1;
				} else if(i == 0) {
					if(fruit[j] == 1) {
						jadoo[i][j][0] = jadoo[i][j - 1][0] + 1;
						jadoo[i][j][1] = jadoo[i][j - 1][1];
					} else {
						jadoo[i][j][0] = jadoo[i][j - 1][0];
						jadoo[i][j][1] = jadoo[i][j - 1][1] + 1;
					}
				} else if (j == 0) {
					jadoo[i][j][0] = jadoo[i][j][1] = Math.max(jadoo[i - 1][j][1], jadoo[i - 1][j][0]);
				} else {
					if(fruit[j] == 1) {
						jadoo[i][j][0] = Math.max(jadoo[i][j - 1][0], jadoo[i - 1][j - 1][1]) + 1;
						jadoo[i][j][1] = Math.max(jadoo[i][j - 1][1], jadoo[i - 1][j - 1][0]);
					} else {
						jadoo[i][j][0] = Math.max(jadoo[i][j - 1][0], jadoo[i - 1][j - 1][1]);
						jadoo[i][j][1] = Math.max(jadoo[i][j - 1][1], jadoo[i - 1][j - 1][0]) + 1;
					}
				}
			}
		}
		int ans = 0;
		for(int i = 0; i <= W; i++) {
			for(int j = 1; j <= T; j++) {
				for(int k = 0; k < 2; k++) {
					ans = Math.max(ans, jadoo[i][j][k]);
					//System.out.print(jadoo[i][k][j] + " ");
				}
				//System.out.println();
			}
			//System.out.println();
		}
		System.out.println(ans);
	}
}
