package exercise.src.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Swea5432 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String str = null;
		int cCount = 0;
		int cSum = 0;
		int t = 1;

		for (int tc = 1; tc <= T; tc++) {
			str = br.readLine();
			cCount = 0;
			cSum = 0;
			
			for (int i = 0; i < str.length() - 1; i++) {
				t = 1;

				if (str.charAt(i) == '(') cCount++;
				else cCount--;
				
				if (str.charAt(i) == '(' && str.charAt(i+1) == ')') {
					while (true) {
						if (i + 1 + t >= str.length()) break;
						if (str.charAt(i + 1 + t) == ')') {
							cSum++;
						} else {break;}
						t++;
					}
					cCount--;
					cSum += cCount;
					i++;
				} 
			}
			

			System.out.printf("#%d %d\n", tc, cSum);
		}
	}

}
