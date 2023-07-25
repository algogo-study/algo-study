import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

class Solution
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int tc = 1; tc <= 10; tc++) {
            int dump = Integer.parseInt(br.readLine());
            
            ArrayList<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            while(st.hasMoreTokens()) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            
            for(int i = 0; i < dump; i++) {
                int max = Collections.max(list);
                int min = Collections.min(list);
                
                if (max - min <= 0) {
                    break;
                }
                list.set(list.indexOf(max), max - 1);
                list.set(list.indexOf(min), min + 1);
            }
            int answer = Collections.max(list) - Collections.min(list);
            System.out.println("#" + tc + " " + answer);
        }
	}
}