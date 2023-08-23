import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ13460 {
	static class Dol {
		int rx;
		int ry;
		int bx;
		int by;
		int dist;

		public Dol(int rx, int ry, int bx, int by, int dist) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.dist = dist;
		}
	}

	static int N, M;
	static char[][] map;
	static Dol dol = new Dol(0, 0, 0, 0, 0);
	static int holeX, holeY;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int ans = Integer.MAX_VALUE;
	static int rnx, rny, bnx, bny;
	static boolean isRedHole, isRedValid, isBlueHole, isBlueValid;

	static void movingRed(int direction, int rx, int ry) {
		int dist = 1;

		while (true) {
			rnx = rx + dx[direction] * dist;
			rny = ry + dy[direction] * dist;

			if (rnx >= N - 1 || rnx < 1 || rny >= M - 1 || rny < 1 || map[rnx][rny] == '#' || map[rnx][rny] == 'B') {
				rnx -= dx[direction];
				rny -= dy[direction];
				break;
			}
			if (rnx == holeX && rny == holeY) {
				isRedHole = true;
			}
			isRedValid = true;
			dist += 1;
		}
		if (isRedHole) {
			map[rx][ry] = '.';
		} else {
			if (isRedValid) {
				map[rx][ry] = '.';
				map[rnx][rny] = 'R';
			}
		}
	}

	static void movingBlue(int direction, int bx, int by) {
		int dist = 1;
		while (true) {
			bnx = bx + dx[direction] * dist;
			bny = by + dy[direction] * dist;

			if (bnx >= N - 1 || bnx < 1 || bny >= M - 1 || bny < 1 || map[bnx][bny] == '#' || map[bnx][bny] == 'R') {
				bnx -= dx[direction];
				bny -= dy[direction];
				break;
			}
			if (bnx == holeX && bny == holeY) {
				isBlueHole = true;
			}
			isBlueValid = true;
			dist += 1;
		}
		if (isBlueHole) {
			map[bx][by] = '.';
		} else {
			if (isBlueValid) {
				map[bx][by] = '.';
				map[bnx][bny] = 'B';
			}
		}
	}

	static void bfs() {
		ArrayDeque<Dol> q = new ArrayDeque<>();
		q.add(dol);

		while (!q.isEmpty()) {
			Dol t = q.poll();

			// 공이 11회 이상이면 X
			if (t.dist >= 10) {
				continue;
			}
			// R, B 설정
			map[t.rx][t.ry] = 'R';
			map[t.bx][t.by] = 'B';

			boolean isValidMoving = false;

			for (int i = 0; i < 4; i++) {
				// 공이 움직였다면
				isRedValid = false;
				isBlueValid = false;
				// 공이 홀에 빠졌다면
				isRedHole = false;
				isBlueHole = false;

				if ((i == 0 && t.rx < t.bx) || (i == 1 && t.rx > t.bx) || (i == 1 && t.rx == t.bx) || (i == 2 && t.ry < t.by) || (i == 2 && t.ry == t.by) || (i == 3 && t.ry > t.by)) {
					// 레드
					movingRed(i, t.rx, t.ry);
					// 블루
					movingBlue(i, t.bx, t.by);
				} else if ((i == 0 && t.rx > t.bx) || (i == 0 && t.rx == t.bx) || (i == 1 && t.rx < t.bx) || (i == 2 && t.ry > t.by) || (i == 3 && t.ry < t.by) || (i == 3 && t.ry == t.by)) {
					// 블루
					movingBlue(i, t.bx, t.by);
					// 레드
					movingRed(i, t.rx, t.ry);
				} 
				// Red가 움직였다면
				if (isRedValid) {
					map[t.rx][t.ry] = 'R';
					map[rnx][rny] = '.';
				}
				// Blue가 움직였다면
				if (isBlueValid) {
					map[t.bx][t.by] = 'B';
					map[bnx][bny] = '.';
				}

				// 정답이 되는 경우
				if (isRedHole && !isBlueHole) {
					isValidMoving = true;
					continue;
				}

				// 정답이 될 수 없는 경우
				if (isRedHole && isBlueHole)
					continue;
				if (!isRedHole && isBlueHole)
					continue;

				// 움직인 값을 Q에 저장
				if (isRedValid && isBlueValid) {
					q.add(new Dol(rnx, rny, bnx, bny, t.dist + 1));
				} else if (isRedValid && !isBlueValid) {
					q.add(new Dol(rnx, rny, t.bx, t.by, t.dist + 1));
				} else if (!isRedValid && isBlueValid) {
					q.add(new Dol(t.rx, t.ry, bnx, bny, t.dist + 1));
				} else {
					q.add(new Dol(t.rx, t.ry, t.bx, t.by, t.dist + 1));
				}

			}
			// 답이 된 경우가 있다면
			if (isValidMoving) {
				// 최솟값 갱신
				ans = Math.min(ans, t.dist + 1);
			}
			// R, B 초기화
			map[t.rx][t.ry] = '.';
			map[t.bx][t.by] = '.';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				char temp = input.charAt(j);
				if (temp == 'R') {
					dol.rx = i;
					dol.ry = j;
					map[i][j] = '.';
					continue;
				} else if (temp == 'B') {
					dol.bx = i;
					dol.by = j;
					map[i][j] = '.';
					continue;
				}
				if (temp == 'O') {
					holeX = i;
					holeY = j;
					map[i][j] = '.';
					continue;
				}
				map[i][j] = input.charAt(j);
			}
		}
		bfs();
		
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
}