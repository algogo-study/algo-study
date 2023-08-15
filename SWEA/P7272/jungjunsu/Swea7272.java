package algo.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Swea7272 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String [] comp_str = {"ADOPQR", "B", "CEFGHIJKLMNSTUVWXYZ"};
		int T = Integer.parseInt(br.readLine());

		for (int x = 1; x <= T; x++) {
			st = new StringTokenizer(br.readLine());
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			
			if (str1.length() != str2.length()) {
				System.out.printf("#%d DIFF\n", x);
			} else {
				for (int i = 0; i < str1.length(); i++) {
					char c1 = str1.charAt(i);
					char c2 = str2.charAt(i);

					for (int a = 0; a < comp_str.length; a++) {
						for (int b = 0; b < comp_str[a].length(); b++) {
							if (c1 == comp_str[a].charAt(b)) {
								c1 = (char) a;
							}
							if (c2 == comp_str[a].charAt(b)) {
								c2 = (char) a;
							}
						}
						if (c1 == c2) {
							break;
						}
					}
					
					if (c1 != c2) {
						System.out.printf("#%d DIFF\n", x);
						break;
					} else if (i == str1.length() - 1) {
						System.out.printf("#%d SAME\n", x);
						break;
					}
				}
			}
			
		}
		
		
	}
}
