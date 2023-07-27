package ssafy.algorithm.study.week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class boj16926 {
    public static int n;
    public static int m;
    public static int[][] arr;
    public static int[][] new_arr;

    static void rotate(){
    	int check = Math.min(n, m) / 2; // 돌려야 할 사각형 개수
    	
    	for(int cnt = 0; cnt < check; cnt++){
    		int n_max = n - cnt - 1;
    		int m_max = m - cnt - 1;

    		int tmp = arr[cnt][cnt];
    		// 위쪽 : 왼 <- 오
    		for(int i = cnt; i < m_max; i++){
    			arr[cnt][i] = arr[cnt][i + 1];
    		}
    		// 오른쪽 : 아래 <- 위
    		for(int i = cnt; i < n_max; i++){
    			arr[i][m_max] = arr[i + 1][m_max];
    		}
    		// 아래쪽 : 왼 -> 오
    		for(int i = m_max; i > cnt; i--){
    			arr[n_max][i] = arr[n_max][i - 1];
    		}
    		// 왼쪽 : 위 -> 아래
    		for(int i = n_max; i > cnt; i--){
    			arr[i][cnt] = arr[i - 1][cnt];
    		}
    		arr[cnt+1][cnt] = tmp;
    	}
    }
public static void main(String[] args) throws FileNotFoundException {
    // TODO Auto-generated method stub
    Scanner sc = new Scanner(new FileInputStream("input16926.txt"));
    n = sc.nextInt();
    m = sc.nextInt();
    int r = sc.nextInt();

    arr = new int[n][m];
    new_arr = new int[n][m];

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            arr[y][x] = sc.nextInt();
        }
    }

    for (int k = 0; k < r; k++) {
        rotate();
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            System.out.print(arr[i][j] + " ");
        }
        System.out.println();
    }
}
}
