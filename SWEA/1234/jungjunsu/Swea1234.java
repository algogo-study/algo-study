package algo.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String [] str;
        int [] arr;
        int N = 0;
        int check = 1;
 
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N];
            str = st.nextToken().split("");
 
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
             
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] == arr[i+1]) {
                    arr[i] = -1;
                    arr[i+1] = -1;
                    i++;
                }
            }
 
            while (check == 1) {
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        if (arr[j] != -1) {
                            if (arr[i] == arr[j]) {
                                arr[i] = -1;
                                arr[j] = -1;
                                check = 0;
                                i++;
                            }
                            break;
                        }
                    }
                }
                if (check != 0) {
                    break;
                } else {
                    check = 1;
                }
            }
             
            System.out.printf("#%d ", tc);
            for (int x : arr) {
                if (x != -1) {
                    System.out.print(x);
                }
            }
            System.out.println();
        }
    }
}