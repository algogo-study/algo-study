package ssafy.algorithm.week6.BOJ16987계란으로계란치기;

import java.util.Scanner;

//계란으로 계란깨기

public class Main { // 계란으로 계란깨기
    static int [][] egg;
    static int n, answer;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

    egg = new int [n][2]; // 내구도 - 무게
    for(int i=0; i<n; i++) {
        egg[i][0] = sc.nextInt();
        egg[i][1] = sc.nextInt();
    }

    recursive(0);
    
    System.out.println(answer);
}


public static void recursive(int idx) { // idx: 들고있는 계란 인덱스
    if(idx == n) {// 인덱스가 n 이상인 계란 없음
        int count = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<2; j++) {
                if(egg[i][j] <=0) count++;
            }
        }
        
        answer = Math.max(answer, count);
        return; 
    }
    
    if(egg[idx][0] <= 0) {// 잡고 있는 계란이 깨지면 다음 계란
        recursive(idx+1); 
        return;
    }

    boolean flag = true;
    for(int i=0; i<n; i++) {
        if(idx != i && egg[i][0] >0) {
            flag = false;
            egg[idx][0] -= egg[i][1];
            egg[i][0] -= egg[idx][1];
            recursive(idx+1);
            egg[idx][0] += egg[i][1];
            egg[i][0] += egg[idx][1];
        }    
    }        
    if(flag) recursive(n);    
}
}