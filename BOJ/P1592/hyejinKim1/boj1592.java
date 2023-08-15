package swea.study.boj;

import java.util.Arrays;
import java.util.Scanner;

class boj1592 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int l = sc.nextInt();
		
		int [] arr = new int[n]; // 공 몇 번 받는지 배열
		
		Arrays.fill(arr, 0);
		arr[0]++;
		
		int num = 0; // 공 던지는 횟수
		int idx = 0; // 공 던지는 사람
		
		 while(true) {
	            if(arr[idx] == m) break;

	            if(arr[idx]%2==1) idx = idx + l; // 홀수이면 시계방향으로 L만큼
	            else idx = idx - l; // 짝수이면 반시계방향으로 L만큼

	            //시계 방향이면서 배열을 벗어날 때 Index + L;
	            if(idx>n-1) idx %= n;

	            //반시계 방향이면서 배열을 벗어날 때 Index - L;
	            else if(idx<0) idx += n;

	            arr[idx]++;
	            num++;
	        }
		 System.out.println(num);
	}
}
