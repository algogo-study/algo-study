package Ex;

import java.util.ArrayList;
import java.util.Scanner;

public class 두로봇 {
	static int n, r1, r2, ans = 0, max = 0;
	static ArrayList [] adj;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		r1 =  sc.nextInt()-1;
		r2 = sc.nextInt()-1;
		adj = new ArrayList [n];
		
		for(int i=0; i<n; i++) {
			adj[i] = new ArrayList<Integer[]>();
		}
		
		for(int i = 0; i <n-1; i++) {
			int u = sc.nextInt()-1;
			int v = sc.nextInt()-1;
			int w = sc.nextInt();
			
			adj[u].add(new Integer[]{v, w});
			adj[v].add(new Integer[]{u, w});
		}
		
		dfs(r1, new boolean [n]);
	
		System.out.println(ans-max);
		
	}
	
	private static boolean dfs(int start, boolean [] v) {
		if(start == r2) return true;
		v[start] = true;
		ArrayList<Integer[]> li = adj[start];
		for(Integer [] arr: li) {
			if(!v[arr[0]]) {
				if(dfs(arr[0], v)) {
					ans += arr[1];
					max = Math.max(max, arr[1]);
					return true;
				}
			}
		}
		return false;
	}
}
