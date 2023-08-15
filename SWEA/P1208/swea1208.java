package ssafy.algorithm.week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class swea1208 {
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new FileInputStream("input1208.txt"));

		for(int test_case = 1; test_case <= 10; test_case++) {
			int dump = sc.nextInt();
			
			int [] arr = new int[100];
			for(int i=0; i<100; i++) {
				arr[i] = sc.nextInt();
			}
			
			for(int i=0; i<dump; i++) {
				Arrays.sort(arr);
				if(arr[99]-arr[0] <= 1) {
					break;
				}
				arr[0]++;
				arr[99]--;
			}
			Arrays.sort(arr);
			int answer = arr[99]-arr[0];
			System.out.println("#"+test_case+" "+answer);
		}
	}
}
