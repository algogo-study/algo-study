import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int[] number;
	static int answer;
	static int M;
	
	public static void backtracking(int start, int n, int r) {
		if (r == 0) {
			int temp = 0;
			for(int i = 0; i < n; i++) {
				if (visited[i]) {
					temp += number[i];
				}
			}
			if (temp <= M && answer < temp) {
				answer = temp;
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			visited[i] = true;
			backtracking(i + 1, n, r - 1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		number = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		backtracking(0, N, 3);
		System.out.println(answer);
	}
}