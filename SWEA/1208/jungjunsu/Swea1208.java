package exercise.src.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Swea1208
{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr;
		int dumpNum = 0;
		int max = 1;
		int min = 1000;
		int posa = -1;
		int posb = -1;

		for (int tc = 1; tc <= 10; tc++) {
			posa = -1;
			posb = -1;
			dumpNum = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr = new int[100];

			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 정렬로 바꾸기 
			for (int i = 0; i < dumpNum; i++) {
				max = 1;
				min = 1000;
				for (int j = 0; j < 100; j++) {
					if (arr[j] > max) {
						max = arr[j];
						posa = j;
					} 
					if (arr[j] < min) {
						min = arr[j];
						posb = j;
					}
				}
				if ((max - min) < 2) {
					break;
				} else {
					arr[posa]--;
					arr[posb]++;
				}
			}
			max = 1;
			min = 1000;
			for (int j = 0; j < 100; j++) {
				if (arr[j] > max) {
					max = arr[j];
				} 
				if (arr[j] < min) {
					min = arr[j];
				}
			}
			System.out.printf("#%d %d\n", tc, (max - min));
		}
	}
}