package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class 새로운게임2 {
	static class Element {
		int row, col, direction;
		Stack<Integer> stack = new Stack<Integer>();
		
		public Element(int row, int col, int direction) {
			this.row = row;
			this.col = col;
		}
		
	}
	
	static int N, K;
	static int[][] map;
	static Deque<Element> Q = new ArrayDeque<Element>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(새로운게임2.class.getResourceAsStream("새로운게임2"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < K; i++) {
			
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
		}
		
		
	}

	
	
	// map 확인
	private static void print(int[][] map2) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			} System.out.println();
		}
		
	}

	
}
