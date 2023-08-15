package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
class Swea13038 {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            int[] arr = new int[7];
            int[] count = new int[7];
            int[] days = new int[7];
            int[] depth = new int[7];
            int N = Integer.parseInt(br.readLine());
            int flags = 0;
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i <  7; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            	for (int j = 0; j <= i; j++) {
            		if (depth[j] != 1) {
            			days[j] += 1;            			
            		}
            		if (arr[i] == 1) {
            			count[j] += 1;
            		}
            		if (count[j] == N) {
            			flags = j + 1;
            			depth[j] = 1;
            		}
            	}
            }

            if (flags > 0) {
            	System.out.printf("#%d %d\n", tc, days[flags-1]);
            } else {
            	int idx = 0;
            	boolean yet = true;
            	while(yet) {
            		for (int i = 0; i < 7; i++) {
            			if (depth[i] == 1) {
            				continue;
            			} else {
            				int day = 0;
                        	while (true){
                        		days[i] += 1;
                        		if (arr[day % 7] == 1) {
                        			count[i] += 1;
                        		}
                        		if (count[i] == N) {
                        			depth[i] = 1;
                        			break;
                        		}
                        		day++;
                        	}
            			}
            		}
            		
            		int sum = 0;
            		for (int j = 0; j < 7; j++) {
            			if (depth[j] == 1) {
            				sum += 1;
            			}
            		} 
            		
            		if (sum == 7) {
            			Arrays.sort(days);
                        System.out.printf("#%d %d\n", tc, days[0]);
            			yet = false;
            		}
            	}
            }

        }
    }
 
}