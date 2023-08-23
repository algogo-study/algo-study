import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 종이접기 {
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static String[] s;
	static int[][] p;
	static int k, h, w;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		k = Integer.parseInt(br.readLine());

		String[] str = br.readLine().split(" ");

		s = new String[2 * k];
		for (int i = 0; i < 2 * k; i++) {
			s[i] = str[i];
		}

		h = Integer.parseInt(br.readLine());

		// 한 변 길이
		w = (int) Math.pow(2, k);
		p = new int[w][w];

		// 배열 -1로 채우기
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				p[i][j] = -1;
			}
		}

		fold();
		fill();

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(p[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void fold() {
		int y = 0, x = 0, ry = w, rx = w;

		// 종이를 모두 접었을 때 보이는 윗 면 구하기
		for (int i = 0; i < 2 * k; i++) {
			if (s[i].equals("D")) {
				ry /= 2;
				y += ry;
			} else if (s[i].equals("U")) {
				ry /= 2;
			} else if (s[i].equals("R")) {
				rx /= 2;
				x += rx;
			} else if (s[i].equals("L")) {
				rx /= 2;
			}
		}

		// 가장 윗 면에 뚫리는 부분 저장
		p[y][x] = h;
	}

	// 패턴에 따라 배열 모두 채우기
	// 0 1
	// 2 3
	private static void fill() { 
		while (true) {
			boolean flag = true;

			for (int i = 0; i < w; i++) {
				for (int j = 0; j < w; j++) {
					if (p[i][j] == -1) {
						flag = false;
						for (int m = 0; m < 4; m++) {
							int ny = i + dy[m];
							int nx = j + dx[m];

							if (ny < 0 || nx < 0 || ny >= w || nx >= w)
								continue;
							if (p[ny][nx] != -1) {
								if (dy[m] == 0 && (dx[m] == 1 || dx[m] == -1))
									p[i][j] = (p[ny][nx] % 2 == 0 ? p[ny][nx] + 1 : p[ny][nx] - 1);
								else if ((dy[m] == 1 || dy[m] == -1) && dx[m] == 0)
									p[i][j] = (p[ny][nx] < 2 ? p[ny][nx] + 2 : p[ny][nx] - 2);
								break;
							}
						}
					}
				}
			}
			if (flag)
				break;
		}
	}

}