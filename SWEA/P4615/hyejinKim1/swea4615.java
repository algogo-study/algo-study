package ssafy.algorithm.study.week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//재미있는 오셀로 게임
class swea4615 {
    static int black_num = 0;
    static int white_num = 0;
    static int n;
    static int [][] arr;
    
    public static void othello(int x, int y, int color) {
        for (int ix = -1; ix <= 1; ix++) {    // 주변 돌 탐색
            for (int iy = -1; iy <= 1; iy++) { 
                if (ix == 0 && iy == 0)
                    continue;
 
                int xx = x + ix;    // 주변 돌 좌표
                int yy = y + iy;
 
                boolean check = false;    // 같은 돌인지 체크
                while (xx >= 0 && xx < n && yy >= 0 && yy < n && arr[xx][yy] != 0) {
                    if(arr[xx][yy] == color) {    // 같은 돌일 경우 
                        check = true;
                        break;
                    }
                    xx += ix;    // 다른 돌이면 자신의 돌이 나올 때까지 탐색 
                    yy += iy;
                }

                while(check) {
                    if(xx == x && yy == y) break;
                    arr[xx][yy] = color;    // 같은 색 돌로 교체
                    xx -= ix;
                    yy -= iy;
                }
            }
        }
    }
	
    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(new FileInputStream("input4615.txt"));
        int t = sc.nextInt();

        for(int test_case = 1; test_case <= t; test_case++) {
            n = sc.nextInt();
            int m = sc.nextInt();

            arr = new int[n][n];

            arr[n/2-1][n/2-1] = 2;
            arr[n/2][n/2 - 1] = 1;
            arr[n/2-1][n/2] = 1;
            arr[n/2][n/2] = 2;

            for(int i = 0; i < m; i++) {
                int x = sc.nextInt()-1;
                int y = sc.nextInt()-1;
                int color = sc.nextInt();

                arr[x][y] = color;
                othello(x, y, color);
            }
            
            for(int i=0; i<n; i++) {
            	for(int j=0; j<n; j++) {
            		if(arr[i][j] == 1) {
            			black_num++;
            		}else if(arr[i][j] == 2) {
            			white_num++;
            		}
            	}
            }
                System.out.println("#"+test_case+" "+black_num+" "+white_num);
        }
    }
}
