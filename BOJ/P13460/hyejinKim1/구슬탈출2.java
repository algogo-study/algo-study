import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class 구슬탈출2 {

	static class Ball {
		int ry, rx, by, bx, cnt;

		public Ball(int ry, int rx, int by, int bx, int cnt) {
			super();
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.cnt = cnt;
		}
	}

	static int n, m, y, x, answer = -1;
	static String[][] board;
	static boolean[][][][] v;
	static Ball blue, red;
	static boolean rEnd, bEnd;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");

		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);

		board = new String[n][m];
		v = new boolean[n][m][n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = str.substring(j, j + 1);
				if (board[i][j].equals("R")) {
					red = new Ball(i, j, 0, 0, 0);
				} else if (board[i][j].equals("B")) {
					blue = new Ball(0, 0, i, j, 0);
				} else if (board[i][j].equals("O")) {
					y = i;
					x = j;
				}
			}
		}
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		Queue<Ball> q = new ArrayDeque<>();
		q.offer(new Ball(red.ry, red.rx, blue.by, blue.bx, 1));
		v[red.ry][red.rx][blue.by][blue.bx] = true;

		while (!q.isEmpty()) {
			Ball ball = q.poll();

			if (ball.cnt > 10) return;
			
				
			for (int i = 0; i < 4; i++) {
				int nry = ball.ry;
				int nrx = ball.rx;
				int nby = ball.by;
				int nbx = ball.bx;

				rEnd = false;
				bEnd = false;

				// 구슬 이동
				int [] r = move(nry, nrx, i, 'r');
				nry = r[0];
				nrx = r[1];
				int [] b = move(nby, nbx, i, 'b');
				nby = b[0];
				nbx = b[1];

				if (bEnd) continue;
				
				if (rEnd && !bEnd) {
					answer = ball.cnt;
					return;
				}

				// red, blue가 붙어있을 때 원래의 좌표 확인하고 옮겨주기
				if (nry == nby && nrx == nbx) {
					if (i == 0) { // 위
						if (ball.ry > ball.by) 
							nry -= dy[i]; 
						else 
							nby -= dy[i];
					} else if (i == 1) { // 오른쪽
						if (ball.rx < ball.bx)
							nrx -= dx[i];
						else
							nbx -= dx[i];
					} else if (i == 2) { // 아래
						if (ball.ry < ball.by)
							nry -= dy[i];
						else
							nby -= dy[i];
					} else { // 왼쪽
						if (ball.rx > ball.bx)
							nrx -= dx[i];
						else
							nbx -= dx[i];
					}
				}

				if (!v[nry][nrx][nby][nbx]) {
					v[nry][nrx][nby][nbx] = true;
					q.add(new Ball(nry, nrx, nby, nbx, ball.cnt + 1));
				}
			}
		}
	}
	
	public static int[] move(int ny, int nx, int i, char c) {
		// 구슬  #까지 이동
		while (!board[ny + dy[i]][nx + dx[i]].equals("#")) {
			ny += dy[i];
			nx += dx[i];
			
			if (ny == y && nx == x) {// 구멍에 빠지면
				if(c == 'r') rEnd = true;
				else bEnd = true;
				break;
			}
		}
		
		return new int[] {ny , nx};
	}
}
