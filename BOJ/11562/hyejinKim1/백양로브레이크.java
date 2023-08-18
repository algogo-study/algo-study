package ssafy.algorithm.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//17퍼 실패

public class 백양로브레이크 {
	static int [][] building;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");

		int n = Integer.parseInt(split[0]);
		int m = Integer.parseInt(split[1]);

		building = new int[251][251];
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(i == j) building[i][j]=0;
				else building[i][j] = 251;
			}
		}

		for (int i = 0; i < m; i++) {
			split = br.readLine().split(" ");
			int u = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);
			int b = Integer.parseInt(split[2]);
			
			building[u][v] = 0;
			
			if(b==1) building[v][u] = 0;
			else building[v][u] = 1;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				for(int k=1; k<=n; k++) {
					if(building[i][k] > building[i][j]+building[j][k])
						building[i][k] = building[i][j]+building[j][k];
				}
			}
		}
			
		int k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			split = br.readLine().split(" ");
			int s = Integer.parseInt(split[0]);
			int e = Integer.parseInt(split[1]);
			
			System.out.println(building[s][e]);
		}
			
	}
}