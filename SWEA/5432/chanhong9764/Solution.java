import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Solution
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            String input = br.readLine();
            int answer = 0;
            int count = 0;
            
            for(int i = 0; i < input.length() - 1; i++) {
            	if (input.charAt(i) == '(' && input.charAt(i + 1) == ')') {
                    answer += count;
                    i += 1; 
                } else if (input.charAt(i) == '(' && input.charAt(i + 1) == '(') {
                    count += 1;
                } else {
                    answer += 1;
                    count -= 1;
                }
            }
            answer += count;
            System.out.println("#" + tc + " " + answer);
        }
	}
}