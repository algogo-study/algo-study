package Study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class 줄기세포배양 {
	static int N, M, K;
	static Deque<Cell> ready, open, close;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("./input/줄기세포배양"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= 1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			// ready 큐 : 세포 비활성 open : 세포 활성
			ready = new ArrayDeque<Cell>();
			open = new ArrayDeque<Cell>();
			close = new ArrayDeque<Cell>();

			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < M; col++) {
					int v = Integer.parseInt(st.nextToken());
					if (v != 0) {
						ready.addLast(new Cell(row, col, v, 0));
					}
				}
			}
			
			int time = 0;
			while (time++ <= K) {
				int rSize = ready.size();
				int oSize = open.size();
				int cSize = close.size();

				for (int rQ = 0; rQ < rSize; rQ++) {
					Cell readyCell = ready.pollFirst();
					readyCell .span += 1;
					if (readyCell.span == readyCell.vitality) {
						open.addLast(readyCell);
						continue;
					}
					ready.addLast(readyCell);
				}
				
				for (int oQ = 0; oQ < oSize; oQ++) {
					Cell liveCell = open.pollFirst();
					for (int i = 0; i < 4; i++) {
						int r = liveCell.row + dr[i];
						int c = liveCell.col + dc[i];
						compareVital = 0;
						if (checkEmptySpace(r, c)) {
							if (compareVital < liveCell.vitality) {
								ready.addLast(new Cell(r, c, liveCell.vitality, 0));
							}
						}
					}
					liveCell.span -= 1;
					if (liveCell.span == 0) {
						close.addLast(liveCell);
						continue;
					}
					open.addLast(liveCell);
				}
			}
			
			System.out.println(open.size() + close.size());
			
		}
	}

	static int compareVital;
	private static boolean checkEmptySpace(int r, int c) {
		int rSize = ready.size();
		int oSize = open.size();
		int cSize = close.size();

		for (int rQ = 0; rQ < rSize; rQ++) {
			Cell readyCell = ready.getFirst();
			if (readyCell.row == r && readyCell.col == c) {
				ready.addLast(readyCell);
				return false;
			}
			ready.addLast(readyCell);
		}
		
		for (int oQ = 0; oQ < oSize; oQ++) {
			Cell openCell =  open.getFirst();
			if (openCell.row == r && openCell.col == c) {
				if (openCell.vitality != openCell.span) {
					open.addLast(openCell);
					return false;
				}
				if (openCell.vitality == openCell.span) {
					compareVital = openCell.vitality;
					open.addLast(openCell);
					return true;
				}
			}
			open.addLast(openCell);
		}
		
		for (int cQ = 0; cQ < cSize; cQ++) {
			Cell closeCell = close.getFirst();
			if (closeCell.row == r && closeCell.col == c) {
				close.addLast(closeCell);
				return false;
			}
			close.addLast(closeCell);
		}
		
		return true;
	}

	// 세포의 좌표, 생명력 클래스
	private static class Cell {
		int row, col, vitality, span;
		
		Cell(int r, int c, int v, int s) {
			this.row = r;
			this.col = c;
			this.vitality = v;
			this.span = s;
		}
	}

}
