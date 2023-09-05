package Ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class 줄세우기 {
	static int n, m;
	static int [] in;
	static ArrayList<Integer> [] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] split = br.readLine().split(" ");
		
		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);
		
		arr = new ArrayList[n+1];
		in = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<m; i++) {
			split = br.readLine().split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);
			
			arr[a].add(b);
			in[b]++;
		}
		
		sort();
		
	}
	
	private static void sort() {
		ArrayList<Integer> li = new ArrayList<Integer>();
		Deque<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=n; i++) {
			if(in[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int p = q.pop();
			li.add(p);
			
			for(int k: arr[p]) {
				in[k]--;
				if(in[k]==0) q.add(k);
			}
		}
		
		for(int i=0; i<n; i++) {
			System.out.print(li.get(i)+" ");
		}
			
	}

}
