import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] oldMap;
    static int[][] newMap;

    static void rotate(int startRow, int endRow, int startCol, int endCol) {
        if (endRow - startRow <= 0 || endCol - startCol <= 0) {
            return;
        }
        
        for(int i = 0; i < endRow - startRow; i++) {
            // 왼쪽 위에서 아래로
            newMap[startRow + i + 1][startCol] = oldMap[startRow + i][startCol];
            // 오른쪽 아래에서 위로
            newMap[endRow - i - 1][endCol] = oldMap[endRow - i][endCol];
        }
        for(int i = 0; i < endCol - startCol; i++) {
            // 오른쪽 위에서 왼쪽 위로
            newMap[startRow][endCol - i - 1] = oldMap[startRow][endCol - i]; 
            // 왼쪽 아래에서 오른쪽 아래로
            newMap[endRow][startCol + i + 1] = oldMap[endRow][startCol + i];
        }
        rotate(startRow + 1, endRow - 1, startCol + 1, endCol - 1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        
        oldMap = new int[N][M];
        newMap = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                oldMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i < R; i++) {
            rotate(0, N - 1, 0, M - 1);
            for (int j = 0; j < N; j++) {
            	for (int k = 0; k < M; k++) {
            		oldMap[j][k] = newMap[j][k];
            	}
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(newMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}