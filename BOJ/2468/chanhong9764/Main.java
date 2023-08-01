import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] region;
	static int max_value, min_value, n;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static boolean isValidBoudary(int x, int y) { 
		if(x < 0 || x >= n || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
	
	static void dfs(int x, int y, int height) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(isValidBoudary(nx, ny) && !visited[nx][ny]) {
				if (region[nx][ny] > height) {
					dfs(nx, ny, height);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		region = new int[n][n];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				max_value = Math.max(temp, max_value);
				min_value = Math.min(temp, min_value);
				region[i][j] = temp;
			}
		}
		int answer = 0;
		for(int i = min_value - 1; i < max_value; i++) {
			visited = new boolean[n][n];
			int count = 0;
			for(int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (region[j][k] > i && !visited[j][k]) {
						dfs(j, k, i);
						count++;
					}
				}
			}
			answer = Math.max(answer, count);
		}
		System.out.println(answer);
	}
}
