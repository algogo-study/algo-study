package ssafy.algorithm.study.week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//상호의 배틀필드
public class swea1873 {
	static int x;
	static int y;
	static int dx;
	static int dy;
	static int direction;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("input1873.txt"));

		int t = sc.nextInt();

		for (int test_case = 1; test_case <= t; test_case++) {
			int h = sc.nextInt();
			int w = sc.nextInt();

			char[][] map = new char[h][w];

			x = 0;
			y = 0;
			dx = 0;
			dy = 0;
			direction = 0;

			for (int i = 0; i < h; i++) {
				String line = sc.next();
				int temp1 = line.indexOf("^");
				int temp2 = line.indexOf("v");
				int temp3 = line.indexOf("<");
				int temp4 = line.indexOf(">");

				if (temp1 != -1) {
					direction = 1;
					y = i;
					x = temp1;
				} else if (temp2 != -1) {
					direction = 2;
					y = i;
					x = temp2;
				} else if (temp3 != -1) {
					direction = 3;
					y = i;
					x = temp3;
				} else if (temp4 != -1) {
					direction = 4;
					y = i;
					x = temp4;
				}
				map[i] = line.toCharArray();
			}

			int n = sc.nextInt(); // 입력의 개수
			String StrLine = sc.next(); // 입력

			for (int i = 0; i < n; i++) {
				switch (StrLine.charAt(i)) {
				case 'U':
					direction = 1;
					if (y > 0 && map[y - 1][x] == '.') {
						map[y][x] = '.';
						map[--y][x] = '^';
					} else if (map[y][x] != '^') {
						map[y][x] = '^';
					}
					break;

				case 'D':
					direction = 2;
					if (y < h - 1 && map[y + 1][x] == '.') {
						map[y][x] = '.';
						map[++y][x] = 'v';
					} else if (map[y][x] != 'v') {
						map[y][x] = 'v';
					}
					break;

				case 'L':
					direction = 3;
					if (x > 0 && map[y][x - 1] == '.') {
						map[y][x] = '.';
						map[y][--x] = '<';
					} else if (map[y][x] != '<') {
						map[y][x] = '<';
					}
					break;

				case 'R':
					direction = 4;
					if (x < w - 1 && map[y][x + 1] == '.') {
						map[y][x] = '.';
						map[y][++x] = '>';
					} else if (map[y][x] != '>') {
						map[y][x] = '>';
					}
					break;

				case 'S':
					switch (direction) {
					case 1:
						dy = -1;
						dx = 0;
						break;
					case 2:
						dy = 1;
						dx = 0;
						break;
					case 3:
						dy = 0;
						dx = -1;
						break;
					case 4:
						dy = 0;
						dx = 1;
						break;
					}
					int iy = y;
					int ix = x;

					while (true) {
						iy += dy;
						ix += dx;

						if (ix >= 0 && iy >= 0 && ix < w && iy < h) {
							if (map[iy][ix] == '*') {
								map[iy][ix] = '.';
								break;
							} else if (map[iy][ix] == '#') {
								break;
							}
						} else {
							break;
						}
					}
					break;
				}
			}

			System.out.print("#" + test_case + " ");
			for (int m = 0; m < h; m++) {
				for (int j = 0; j < w; j++) {
					System.out.print(map[m][j]);
				}
				System.out.println();
			}
		}
	}
}
