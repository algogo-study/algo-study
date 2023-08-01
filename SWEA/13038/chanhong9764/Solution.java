import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws IOException {
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringTokenizer st;
 		int T = Integer.parseInt(br.readLine());
 		
 		for(int tc = 1; tc <= T; tc++) {
 			int n = Integer.parseInt(br.readLine());
 			st = new StringTokenizer(br.readLine());
 			Queue<Integer> q = new LinkedList<>();
 			
 			int[] week = new int[7];
 			int answer = Integer.MAX_VALUE;
 			
 			for(int i = 0; i < 7; i++) {
 				int input = Integer.parseInt(st.nextToken());
 				if(input == 1) q.add(i);
 				week[i] = input;
 			}
 			
 			while (!q.isEmpty()) {
 				int index = q.poll();
 				int count = 0;
 				int days = 0;
 				
 				while(count != n) {
 	 				if (week[index++ % 7] == 1) {
 	 					count++;
 	 				}
 	 				days++;
 	 			}
 				answer = Math.min(answer, days);
 			}
 			
 			System.out.println("#" + tc + " " + answer);
 		}
	}
}
