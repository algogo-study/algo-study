package ssafy.algorithm.week5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Swea13038_교환학생{
    public static void main(String[] args) throws FileNotFoundException {
       Scanner sc = new Scanner(new FileInputStream("input13038.txt"));

   int[] arr=new int[7];
   int t = sc.nextInt();
   for(int tc = 1; tc<=t; tc++) {
      int n = sc.nextInt(); 
      for(int i=0; i<7; i++) {
         arr[i] = sc.nextInt(); 
      }
      int min = Integer.MAX_VALUE;
      for(int i=0; i<7; i++) { // 모든 요일 별 보기
          if(arr[i] == 0) continue;
              int answer = 0;
              int start = i; // 처음에 무슨 요일에서 시작할지
              while(true) {
                  if(arr[start%7] == 1) answer++;
                  start++;
                  if(answer == n) {
                      min = Math.min(min, start-i);
                      break;
                  }
              }
      }
      System.out.println("#"+tc+" "+min);
   }
}
}

