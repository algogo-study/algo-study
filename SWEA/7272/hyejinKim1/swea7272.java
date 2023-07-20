package ssafy.study.swea;

import java.util.Scanner;

public class swea7272 {
	static String Answer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			String str1 = sc.next();
			String str2 = sc.next();

			int left = 0;
			int right = 0;
			Answer = "SAME";

			for (int i = 0; i < str1.length(); i++) {
				if (str1.length() != str2.length()) {// 길이 다르면 무조건 DIFF
					Answer = "DIFF";
					break;
				}

				// 왼쪽
				if (str1.charAt(i) == 'B') {
					left = 2;
				} else if (str1.charAt(i) == 'A' || str1.charAt(i) == 'D' || str1.charAt(i) == 'P'
						|| str1.charAt(i) == 'Q' || str1.charAt(i) == 'O' || str1.charAt(i) == 'R') {
					left = 1;
				} else {
					left = 0;
				}

				// 오른쪽
				if (str2.charAt(i) == 'B') {
					right = 2;
				} else if (str2.charAt(i) == 'A' || str2.charAt(i) == 'D' || str2.charAt(i) == 'P'
						|| str2.charAt(i) == 'Q' || str2.charAt(i) == 'O' || str2.charAt(i) == 'R') {
					right = 1;
				} else {
					right = 0;
				}

				if (left != right) {
					Answer = "DIFF";
					break;
				}
			}

			System.out.println("#" + test_case + " " + Answer);
		}
	}
}
