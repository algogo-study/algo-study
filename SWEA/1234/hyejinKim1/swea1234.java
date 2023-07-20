package ssafy.study.swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class swea1234 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("input1234.txt"));
		for (int tese_case = 1; tese_case <= 10; tese_case++) {
			int n = sc.nextInt(); // 글자 수
			ArrayList<String> List = new ArrayList<>();
			
			String a = sc.next();

			for (int i = 0; i < n; i++)
				List.add(a.substring(i, i + 1));

			int count = 0;

			// 처음부터 값이 같으면 삭제하고 다시 처음부터 순차 탐색
			while (true) {
				if (count == List.size() - 1)
					break;
				if (List.get(count).equals(List.get(count + 1))) {
					List.remove(count);
					List.remove(count);
					count = 0;
				} else
					count++;
			}

			System.out.printf("#%d ", tese_case);
			for (String c : List)
				System.out.print(c);
			System.out.println();
		}
	}
}
