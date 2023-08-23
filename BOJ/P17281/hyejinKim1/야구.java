import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 야구 {
	static int[][] a;
	static boolean[] v = new boolean[9];
	static int[] player = new int[9];
	static int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static int n, answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		a = new int[n][9];
		for (int i = 0; i < n; i++) {
			String[] split = br.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				a[i][j] = Integer.parseInt(split[j]);
			}
		}

		recursive(0);

		System.out.println(answer);
		br.close();
	}

	public static void game(int[] p) {
		int pnum = 0; // 타자 번호
		int score = 0; // 점수
		int out = 0; // 아웃된 횟수
		for (int i = 0; i < n; i++) {
			int [] l = new int[9]; // 위치
			while(true) {
				int k = a[i][p[pnum]-1]; // 해당 타자의 결과
				
				switch (k) {
				case 0: // 아웃
					out++;
					break;
				case 1: // 안타
					score += go(l, pnum, 1);
					break;
				case 2: // 2루타
					score += go(l, pnum, 2);
					break;
				case 3: // 3루타
					score += go(l, pnum, 3);
					break;
				case 4: // 홈런
					score += go(l, pnum, 4);
					break;
				}
				
				pnum++;
				if(pnum == 9) pnum = 0; // 9번 다 쳐도 끝나지 않으면
				
				if (out == 3) {// 3아웃 발생하면 이닝 끝
					out = 0;
					break;
				}
			}
		}
		answer = Math.max(answer, score);
	}
	
	public static int go(int [] l, int pnum, int n) {
		int score = 0;
		for(int i=0; i<9; i++) {
			if(l[i] > 0 || i == pnum) {
				if((l[i] += n) >= 4) {
					l[i] = 0;
					score++;
				}
			}
		}
		return score;
	}

	public static void recursive(int idx) { // 순열
		if (idx == player.length && player[3] == 1) {
			game(player);
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (!v[i]) {
				v[i] = true;
				player[idx] = arr[i];
				recursive(idx + 1);
				v[i] = false;
			}
		}
	}
}