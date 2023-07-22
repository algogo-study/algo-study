
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] board;
    static char[] command;
    static int cx, cy, h, w;

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());

        for (int line = 1; line < tc + 1; line++) {
            String[] split = br.readLine().split(" ");
            h = Integer.parseInt(split[0]);
            w = Integer.parseInt(split[1]);

            board = new char[h][w];

            for (int i = 0; i < h; i++) {
                char[] charArray = br.readLine().toCharArray();
                board[i] = charArray;
            }

            br.readLine();

            command = br.readLine().toCharArray();

            L:
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == '<' ||
                            board[i][j] == '>' ||
                            board[i][j] == 'v' ||
                            board[i][j] == '^'
                    ) {
                        cx = i;
                        cy = j;
                        break L;
                    }
                }
            }

            for (char c : command) {
                exe(c);
            }
            System.out.printf("#%d ", line);
            for (char[] chars : board) {
                System.out.println(chars);
            }

        }
    }

    private static boolean canGo(int x, int y, int dir) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (!(-1 < nx && nx < h &&
                -1 < ny && ny < w
        )) {
            return false;
        }
        return board[nx][ny] == '.';
    }

    private static void exe(char com) {
        if (com == 'R') {
            boolean b = canGo(cx, cy, 0);
            if (!b) {
                board[cx][cy] = '>';
                return;
            }
            board[cx][cy] = '.';
            cy += 1;
            board[cx][cy] = '>';
        } else if (com == 'L') {
            boolean b = canGo(cx, cy, 1);
            if (!b) {
                board[cx][cy] = '<';
                return;
            }
            board[cx][cy] = '.';
            cy -= 1;
            board[cx][cy] = '<';
        } else if (com == 'D') {
            boolean b = canGo(cx, cy, 2);
            if (!b) {
                board[cx][cy] = 'v';
                return;
            }
            board[cx][cy] = '.';
            cx += 1;
            board[cx][cy] = 'v';
        } else if (com == 'U') {
            boolean b = canGo(cx, cy, 3);
            if (!b) {
                board[cx][cy] = '^';
                return;
            }
            board[cx][cy] = '.';
            cx -= 1;
            board[cx][cy] = '^';
        } else if (com == 'S') {
            shoot();
        }
    }

    private static void shoot() {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('>', 0);
        map.put('<', 1);
        map.put('v', 2);
        map.put('^', 3);
        Integer dir = map.get(board[cx][cy]);

        int nx = cx + dx[dir];
        int ny = cy + dy[dir];
        while (true) {

            if (!(-1 < nx && nx < h &&
                    -1 < ny && ny < w
            )) {
                return;
            }
            if (board[nx][ny] == '*') {
                board[nx][ny] = '.';
                return;
            }
            if (board[nx][ny] == '#') {
                return;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }
}

