package Study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 자두나무 {
	static int[] fallTree;
	static boolean[] posChange;
	static int W, T;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./input/자두나무"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		fallTree = new int[T];
		posChange = new boolean[T];

		int pos = 1;
		int count = 0;
		for (int t = 0; t < T; t++) {
			fallTree[t] = Integer.parseInt(br.readLine());
			if (pos != fallTree[t]) {
				posChange[t] = true;
				pos = pos == 1 ? 2 : 1;
				count++;
			}
		}
		
		if (W >= count) {
			W = count;
			max = T;
		}
		if (W < count) {
			recursive(0, 0, 1, 0);
		}
		System.out.println(max);
	}

	private static void recursive(int idx, int k, int pos, int count) {
		if (max == T) return;
		if (k == W) {
			int sum = 0;
			for (int rest = 0; rest < T - idx; rest++) {
				if (pos == fallTree[idx + rest]) sum++;
			}
			max = Math.max(max, count + sum);
			return;
		}
		if (idx == T) {
			max = Math.max(max, count);
			return;
		}
		
		if (posChange[idx] && k < W) {
			int tPos = pos == 1 ? 2 : 1;
			int i = 0;
			if (tPos == fallTree[idx]) i = 1;
			recursive(idx + 1, k + 1, tPos, count + i);
		}
		int sum = pos == fallTree[idx] ? 1 : 0;
		idx++;
		while (idx < T && !posChange[idx]) {
			if (pos == fallTree[idx]) sum++;
			idx++;
		}
		recursive(idx, k, pos, count + sum);
	}

}
