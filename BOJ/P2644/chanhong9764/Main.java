package BOJ.P2644.chanhong9764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class Main {
    static int p1, p2;
    static int n;
    static boolean isFind;
    static boolean[] visited;
    static List<Integer>[] relation;
    
    static void find(int start, int count) {
        visited[start] = true;
        
        if(start == p2) {
            System.out.println(count);
            isFind = true;
            return;
        }
        
        for(int i = 0; i < relation[start].size(); i++) {
        	int value = relation[start].get(i);
        	if (!visited[value]) {
        		find(value, count + 1);
        	}
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        relation = new ArrayList[n + 1];
        st = new StringTokenizer(br.readLine());
        
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        
        int m = Integer.parseInt(br.readLine());
        
        for(int i = 1; i < n + 1; i++) {
        	relation[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            relation[parent].add(child);
            relation[child].add(parent);
        }
        
        visited = new boolean[n + 1];
        
        find(p1, 0);
        if(!isFind) {
            System.out.println(-1);
        }
    }
}
