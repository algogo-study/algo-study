package exercise.src.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swea1873 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		char[][] map;
		char[] userInput;
		int[] pos;
		int[][] dm = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int T = Integer.parseInt(br.readLine());
		int H = 0;
		int W = 0;
		int N = 0;
		String str;

		for (int tc = 1; tc <= T; tc++) {
			pos = new int[2];
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char [H][W];

			for (int row = 0; row < H; row++) {
				st = new StringTokenizer(br.readLine());
				str = st.nextToken();
				for (int col = 0; col < W; col++) {
					map[row][col] = str.charAt(col);
					if (map[row][col] == '<' || map[row][col] == '>' || map[row][col] == '^' || map[row][col] == 'v') {
						pos[0] = row;
						pos[1] = col;
					}
				}
			}
			
			N = Integer.parseInt(br.readLine());
			userInput = new char[N];
			st = new StringTokenizer(br.readLine());
			str = st.nextToken();
			for (int i = 0; i < N; i++) {
				userInput[i] = str.charAt(i);
			}
			
			for (int i = 0; i < N; i++) {
				userAction(map, pos, H, W, dm, userInput[i]);
			}
			
			System.out.printf("#%d ", tc);
			for (char[] h : map) {
				for (char c : h) {
					System.out.print(c+"");
				}
				System.out.println();
			}
		}
	}
	
	static void userAction(char[][] map, int[] pos, int H, int W, int[][] dm, char userInput) {
		switch (userInput) {
			case ('U'):
				map[pos[0]][pos[1]] = '^';
				if (pos[0] - 1 < H && pos[0] - 1 >= 0) {
					if (map[pos[0] - 1][pos[1]] == '.') {
						map[pos[0]][pos[1]] = '.';
						map[pos[0] - 1][pos[1]] = '^';
						pos[0] -= 1;
					}
				}
				break;
			case ('D'):
				map[pos[0]][pos[1]] = 'v';
				if (pos[0] + 1 < H && pos[0] + 1 >= 0) {
					if (map[pos[0] + 1][pos[1]] == '.') {
						map[pos[0]][pos[1]] = '.';
						map[pos[0] + 1][pos[1]] = 'v';
						pos[0] += 1;
					}
				}
				break;
			case ('L'):
				map[pos[0]][pos[1]] = '<';
				if (pos[1] - 1 < W && pos[1] - 1 >= 0) {
					if (map[pos[0]][pos[1] - 1] == '.') {
						map[pos[0]][pos[1]] = '.';
						map[pos[0]][pos[1] - 1] = '<';
						pos[1] -= 1;
					}
				}
				break;
			case ('R'):
				map[pos[0]][pos[1]] = '>';
				if (pos[1] + 1 < W && pos[1] + 1 >= 0) {
					if (map[pos[0]][pos[1] + 1] == '.') {
						map[pos[0]][pos[1]] = '.';
						map[pos[0]][pos[1] + 1] = '>';
						pos[1] += 1;
					}
				}
				break;
			case ('S'):
				int i = 0;
				int range = 0;
				switch (map[pos[0]][pos[1]]) {
					case ('^'):
						i = 0;
						range = pos[0] - 0;
						break;
					case ('v'):
						i = 1;
						range = H - 1 - pos[0];
						break;
					case ('<'):
						i = 2;
						range = pos[1] - 0;
						break;
					case ('>'):
						i = 3;
						range = W - 1 - pos[1];
						break;
				}

				for (int j = 1; j <= range; j++) {
					if (map[pos[0] + j * dm[i][0]][pos[1] + j * dm[i][1]] == '*') {
						map[pos[0] + j * dm[i][0]][pos[1] + j * dm[i][1]] = '.';
						break;
					} else if (map[pos[0] + j * dm[i][0]][pos[1] + j * dm[i][1]] == '#') {
						break;
					}
				}
		}
	}
	
}

