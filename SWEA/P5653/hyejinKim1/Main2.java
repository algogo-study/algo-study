package ssafy.algorithm.week6.SWEA5653줄기세포배양;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
//미구현

class Node {
	int y;
	int x;
	int h; // 생명력
//	boolean active; // 활성화 여부

	public Node(int y, int x, int h) {
		this.y = y;
		this.x = x;
		this.h = h;
//		this.active = active;
	}
}

/*
 * 
 * 배양은 무한히 하므로 배열의 크기 크게 주변을 탐색하므로 bfs로 구현 퍼뜨려야 하는 칸을 리스트에 저장해두고 정렬 동시에 퍼지는 상황에선
 * 생명력이 큰 게 우선
 */

class Main2 {

	static int N, M, K, start;
	static int[][] a; // 생명력 배열
	static int[][] activate; // 활성화 검사 배열

	static boolean[][] v;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static PriorityQueue<Node> spreadQ;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileInputStream("input5653.txt"));

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();

			start = K + 50;
			int len = 800;
			a = new int[len][len];
			activate = new int[len][len];
			v = new boolean[len][len];

			for (int i = start; i < start + N; i++) {
				for (int j = start; j < start + M; j++) {
					a[i][j] = sc.nextInt();
					activate[i][j] = a[i][j];
				}
			}
			int answer = 0;

			spreadQ = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					return Integer.compare(n2.h, n1.h);
				}
			});

			for (int k = 1; k <= K; k++) {
				for (int i = 0; i < start + N; i++) {
					for (int j = 0; j < start + M; j++) {
						if (activate[i][j] == k && !v[i][j]) { // 활성화되면 퍼뜨리기
							v[i][j] = true;
							for (int d = 0; d < 4; d++) { // 사방
								int ny = i + dy[d];
								int nx = j + dx[d];

								if (ny < 0 || nx < 0 || ny > start + N - 1 || nx > start + M - 1)
									continue;
								if (v[ny][nx])
									continue;
								if (a[ny][nx] == 0) { // 줄기세포가 번식하지 않은 곳으로
									spreadQ.add(new Node(ny, nx, a[i][j]));
								}
							}
						}
					}
				}
				spread(k);
			}

//			for (int i = 0; i < start + N; i++) {
//				System.out.println(Arrays.toString(activate[i]));
//			}

			for (int i = 0; i < start + N; i++) {
				for (int j = 0; j < start + M; j++) {
					if(activate[i][j]+a[i][j] >= K) answer++;
				}
			}

			System.out.println("#" + tc + " " + answer);

		}
	}

	private static void spread(int h) {
		while (!spreadQ.isEmpty()) {
			Node n = spreadQ.poll();
			int y = n.y;
			int x = n.x;
			if (a[y][x] == 0) {
				a[y][x] = n.h;
				activate[y][x] = h + n.h + 1;
			}
		}
	}
}

//	private static void bfs(int y, int x, int h) {
//		// 큐에 넣고 생명력 순서대로 정렬
//		// 큐 2개?? - 활성화된 노드 담을 큐
//		// 퍼뜨릴 큐 - 생명력 큰 순서대로 담을 큐
//
//		for (int i = 0; i < 4; i++) {
//			int ny = y + dy[i];
//			int nx = x + dx[i];
//
//			if (ny < 0 || nx < 0 || ny > N - 1 || nx > M - 1)
//				continue;
//			if (v[ny][nx])
//				continue;
//			if (a[ny][nx] == 0) {
//				spreadQ.add(new Node(y, x, h));
//			}
//		}

//		Q.add(new Node(y, x, a[y][x]));
//		
//		while (!Q.isEmpty()) {
//
//			Node n = Q.poll();

// k = 1
// a[i][j] = 1인 것 활성화하고 주변에 퍼뜨리기
// 검사할 때 활성화배열이 k와 같으면 죽기
//
//			
//			for (int k = 1; k <= K; k++) {
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						if (activate[i][j] == k) // 비활성화
//							activate[i][j] = -1;
//						if (a[i][j] == k && activate[i][j] == 0) {
//							activate[i][j] = 2 * k;
//							recursive(i, j, k);
//						}
//					}
//				}
//			}

//
//	private static void recursive(int y, int x, int h) { // y, x: 좌표
//		v[y][x] = true;
//
//		for (int i = 0; i < 4; i++) {
//			int ny = y + dy[i];
//			int nx = x + dx[i];
//
//			if (ny < 0 || nx < 0 || ny > N - 1 || nx > M - 1)
//				continue;
//			if (v[ny][nx])
//				continue;
//			if (a[ny][nx] == 0 && !v[ny][nx]) {
//				a[ny][nx] = h + a[y][x];
//				recursive(ny, nx, h);
//			}
//		}
//	}