package ssafy.algorithm.week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class swea5432 {
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new FileInputStream("input5432.txt"));
		int t = sc.nextInt();
		
		for(int test_case = 1; test_case <= t; test_case++) {
            int res = 0;
            int cnt = 0;
            
            String line = sc.next();
            for(int i = 0; i < line.length() - 1; i++) {
                if(line.charAt(i) == '(' && line.charAt(i + 1) == ')') { 
                    res += cnt;
                    i++;
                }
                else if(line.charAt(i) == '(') cnt++; // 막대 추가
                else { cnt--; res++; } // 막대 끝
            }
            System.out.println("#" + t + " " + ++res);
		}	
	}
}
