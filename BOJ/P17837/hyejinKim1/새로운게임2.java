import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//미구현

public class 새로운게임2 {

	static int n, m, answer, hcount = 0;
	static Box[][] board;
	static Horse [] ho;
	static int[][] d = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");

		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);

		board = new Box[n][n];

		// 배열 칸마다 Box 객체 만들고 색 저장
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				board[i][j] = new Box(Integer.parseInt(str[j])); 
			}
		}

		
		ho = new Horse[m];
		
		for (int i = 0; i < m; i++) {
			String[] str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]) - 1;
			int c = Integer.parseInt(str[1]) - 1;
			int t = Integer.parseInt(str[2]) - 1;

			ho[i] = new Horse(r, c, t, i);
			board[r][c].addHorse(new Horse(r, c, t, i));
		}

		answer = 1;
		L: while (true) {
			for(int i=0; i<m; i++) {
				Horse horse = ho[i];

				// 움직일 말의 현재 좌표
				int y = horse.r;
				int x = horse.c;
				int t = horse.t; // 방향 0 1 2 3
				int k = horse.k; // 번호 1 2 3 4

				// 이동할 좌표
				int ny = y + d[t][0];
				int nx = x + d[t][1];

				if ( ny < 0 || nx < 0 || ny >= n || nx >= n || board[ny][nx].color == 2 ) { // 범위 벗어나거나 파란색
					int[] n = blue(t, y, x, k);
					ny = n[0];
					nx = n[1];
				} else 
					board[y][x].move(k, ny, nx, false);
				
				System.out.println(ny + " " + nx + " " + t + " " + k);
				board[ny][nx].print();
				
				if (board[ny][nx].h.size() >= 4)
					break L;
				ho[i] = new Horse( ny, nx, t, k );
			}
			if(answer == 1000) {
				answer = -1;
				break L;
			}
			answer ++;
			System.out.println(answer+ " turn");
			
		}

		System.out.println(answer);
	}

	static public int[] blue(int t, int y, int x, int k) {
		// 방향 반대로
		if (t % 2 == 0)
			t += 1;
		else
			t -= 1;

		// 반대로 한 칸 이동
		int ny = y + d[t][0];
		int nx = x + d[t][1];

		// 반대로 이동할 칸이 범위를 벗어나거나 파란색이면 제자리에
		if (ny < 0 || nx < 0 || ny >= n || nx >= n || board[ny][nx].color == 2) {
			ny = y;
			nx = x;
		} else // 파란색이 아니면 이동
			board[y][x].move(k, ny, nx, true);
			
		return new int[] { ny, nx, t };
	}

	static public class Horse {
		int r, c, t, k;

		public Horse(int r, int c, int t, int k) {
			super();
			this.r = r;
			this.c = c;
			this.t = t; // 말 방향
			this.k = k; // 말 번호

		}

		private void back() { // 말 방향 반대로
			if (t % 2 == 0)
				this.t += 1;
			else
				this.t -= 1;
		}
	}

	static public class Box {
		List<Horse> h;
		int color;

		public Box(int color) {
			this.h = new ArrayList<>();
			this.color = color;
		}

		private void print() {
			System.out.println("--------h시작");
			for (Horse horse : h) {
				System.out.println(horse.t + " " + (horse.k+1));
			}
			System.out.println("--------h끝");
		}
		
		private void moveAdd(int ny, int nx, List<Horse> nh) {
			if(board[ny][nx].color == 1) {
				for(int i = nh.size() - 1; i >= 0; i--) {
					board[ny][nx].addHorse(nh.get(i));
				}
			}else
				for(Horse ho: nh) {
					board[ny][nx].addHorse(ho);
				}
		}

		private void move(int k, int ny, int nx, boolean back) { // k번째 위로 쌓인 말들 nh에 저장해 리턴
			List<Horse> nh = new ArrayList<>();
			int s = 0;
			for (int i = 0; i < h.size(); i++) {
				if (h.get(i).k == k) {
					s = i;
					break;
				}
			}
			
			for (int i = s; i < h.size(); i++) {
				Horse horse = h.get(i);
				if (back && i == s) 
					horse.back();
				nh.add(horse);
				h.remove(i);
				i--;
				
				int num = horse.k;
				ho[num] = new Horse(ny, nx, horse.t, num);
			}
			
			moveAdd(ny, nx, nh);
		}
		
		private void addHorse(Horse ho) { // 말 추가하기
				h.add(ho);
		}

	}
}