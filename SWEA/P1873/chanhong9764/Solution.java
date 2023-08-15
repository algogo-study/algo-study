package SWEA.P1873.chanhong9764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Solution
{
    static char[][] map;
    static int posX, posY, H, W;
    static char state;
    
    static boolean isHit(int x, int y) {
        if (map[x][y] == '*') {
            map[x][y] = '.';
            return true;
        } else if (map[x][y] == '#') {
            return true;
        } else {
            return false;
        }
    }
    
    static void shooting() {
        if (state == '^') {
            for(int i = posX - 1; i >= 0; i--) {
                if(isHit(i, posY)) break;
            }
        } else if (state == 'v') {
            for(int i = posX + 1; i < H; i++) {
                if(isHit(i, posY)) break;
            }
        } else if (state == '<') {
            for(int i = posY - 1; i >= 0; i--) {
                if(isHit(posX, i)) break;
            }
        } else {
            for(int i = posY + 1; i < W; i++) {
                if(isHit(posX, i)) break;
            }
        }
    }
    
    static boolean isValidBoundary(int x, int y) {
        if (x >= H || x < 0 || y >= W || y < 0) {
            return false;
        }
        return true;
    }
    
    static void isValidMoving(int x, int y) {
        if (isValidBoundary(x, y)) {
            if (map[x][y] == '.') {
            	posX = x;
                posY = y;
        	}
        }
    }
    
    static void moving(char action) {
        map[posX][posY] = '.';
        if (action == 'U') {
            state = '^';
            isValidMoving(posX - 1, posY);
        } else if (action == 'D') {
            state = 'v';
            isValidMoving(posX + 1, posY);
        } else if (action == 'L') {
            state = '<';
            isValidMoving(posX, posY - 1);
        } else {
            state = '>';
            isValidMoving(posX, posY + 1);
        }
        map[posX][posY] = state;
    }
    
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            
            for(int i = 0; i < H; i++) {
                String input = br.readLine();
                for(int j = 0; j < W; j++) {
                    char temp = input.charAt(j);
                    if (temp == '^' || temp == 'v' || temp == '<' || temp == '>') {
                        posX = i;
                        posY = j;
                        state = temp;
                    }
                    map[i][j] = temp;
                }
            }
            
            int actionCount = Integer.parseInt(br.readLine());
            String action = br.readLine();
            
            for (int i = 0; i < actionCount; i++) {
                char temp = action.charAt(i);
                if (temp == 'U' || temp == 'D' || temp == 'L' || temp == 'R') {
                    moving(temp);
                } else {
                    shooting();
                }
            }
            
            System.out.print("#" + tc + " ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
	}
}