import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

//미구현

public class 새로운게임2 {

	static int n, m, answer, hcount = 0;
	static Box[][] board;
	static int[][] d = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	static Queue<Integer[]> q = new ArrayDeque<>();

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
				board[i][j] = new Box(Integer.parseInt(str[j])); // 칸의 색
			}
		}

		// 몇번 좌표, 몇번 말인지
		for (int i = 0; i < m; i++) {
			String[] str = br.readLine().split(" ");
			int r = Integer.parseInt(str[0]) - 1;
			int c = Integer.parseInt(str[1]) - 1;
			int t = Integer.parseInt(str[2]) - 1;

			q.add(new Integer[] { r, c, t, i });

			board[r][c].h.add(new Horse(t, i));
		}
		
		answer = 1;
		L: while (!q.isEmpty()) {
			Integer[] arr = q.poll();

			// 움직일 말의 현재 좌표
			int y = arr[0]; 
			int x = arr[1]; 
			int t = arr[2]; // 방향
			int k = arr[3]; // 번호
			
			System.out.println(y+" "+x+" "+t+" "+k);

			// 이동할 좌표
			int ny = y + d[t][0];
			int nx = x + d[t][1];

			if (ny < 0 || nx < 0 || ny >= n || nx >= n ) {
				int [] n = blue(t, y, x, k);
				ny = n[0];
				nx = n[1];
			} else {
				switch (board[ny][nx].color) {
				case 0: // 흰색 
					board[ny][nx].add(board[y][x].move(k, false));
					break;
				case 1: // 빨간색 - 쌓은 후에 순서 반대로
					board[ny][nx].add(board[y][x].move(k, false));
					board[ny][nx].reverse();
					break;
				case 2: // 파란색 
					int [] n = blue(t, y, x, k);
					ny = n[0];
					nx = n[1];
					break;
				}
			}
			if (board[ny][nx].h.size() >= 4) break L;
			if (answer == 1000) {
				answer = -1;
				break L;
			}

			if(k == 0) answer ++; // 한 턴마다
			q.add(new Integer[] { ny, nx, t, k });
		}

		System.out.println(answer);
	}
	
	static public int [] blue(int t, int y, int x, int k) {
		if(t%2 == 0) t+=1;
		else t-=1;
		
		//반대로 한 칸
		int ny = y + d[t][0];
		int nx = x + d[t][1];
		
		if (ny < 0 || nx < 0 || ny >= n || nx >= n || board[ny][nx].color == 2) {
			ny = y;
			nx = x;
		}
		else // 이동할 칸이 파란색이 아니면 이동 
			board[ny][nx].add(board[y][x].move(k, true));
		return new int[] {ny, nx};
	}

	static public class Horse {
		int t, k;

		public Horse(int t, int k) {
			super();
			this.t = t; // 말 방향
			this.k = k; // 말 번호

		}

		private void back() { // 말 방향 반대로
			if(t%2 == 0) t+=1;
			else t-=1;
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
			for (Horse horse : h) {
				System.out.println(horse.t + " " + horse.k);
			}
			System.out.println("끝");
		}

		private void reverse() { // 빨간색 칸으로 이동하면 쌓인 순서 거꾸로
			List<Horse> nh = new ArrayList<>();
			for (int i = h.size() - 1; i >= 0; i--) {
				nh.add(h.get(i));
			}
			h = nh;
		}

		private List<Horse> move(int k, boolean back) { // k번째 위로 쌓인 말들 nh에 저장해 리턴
			List<Horse> nh = new ArrayList<>();
			int s = 0;
			for (int i=0; i<h.size(); i++) {
				if (h.get(i).k == k) {
					s = i;
					break;
				}
			}
			for(int i=s; i<h.size(); i++) {
				Horse horse = h.get(i);
				if(back) horse.back();
				nh.add(horse);
				h.remove(i);
			}

			return nh;
		}

		private void add(List<Horse> nh) { // 말 추가하기
			for (Horse ho : nh) {
				h.add(ho);
			}
		}

	}
}
