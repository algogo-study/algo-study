package exercise.src.practice;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 종이접기 {

    public static void main(String[] args) throws IOException {
        //System.setIn(Main.class.getResourceAsStream("./input/종이접기"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        String[] inputStr = br.readLine().split(" ");
        int hole = Integer.parseInt(br.readLine());
        int pow = (int) Math.pow(2, k);
        int[][] map = new int[2][2];

        int[] pattern = new int[4];
        int[][] locate = {{0, 1}, {0, 0}, {1, 0}, {1, 1}};
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] pos = new int[4][2];
        int[] fold = new int[2];
        int patternIdx = -1;
        int foldIdx = -1;
        for (int i = 0; i < pow; i++) {
            switch (inputStr[inputStr.length - 1 - i].charAt(0)) {
                case ('R'):
                    pattern[++patternIdx] = 0;
                    pattern[++patternIdx] = 3;
                    fold[++foldIdx] = 1;
                    break;
                case ('L'):
                    pattern[++patternIdx] = 1;
                    pattern[++patternIdx] = 2;
                    fold[++foldIdx] = 0;
                    break;
                case ('D'):
                    pattern[++patternIdx] = 2;
                    pattern[++patternIdx] = 3;
                    fold[++foldIdx] = 3;
                    break;
                case ('U'):
                    pattern[++patternIdx] = 0;
                    pattern[++patternIdx] = 1;
                    fold[++foldIdx] = 2;
                    break;
            }
            if (foldIdx == 1) {
                if ((fold[1] == 3 || fold[1] == 2) && (fold[0] == 3 || fold[0] == 2)) {
                    foldIdx--;
                    patternIdx -= 2;
                }
                else if ((fold[1] == 0 || fold[1] == 1) && (fold[0] == 0 || fold[0] == 1)) {
                    foldIdx--;
                    patternIdx -= 2;
                }
                else break;
            }
        }
        Arrays.sort(pattern);
        int firstLocate = 0;
        for (int i = 0; i < 3; i++) {
            if (pattern[i] == pattern[i+1]) {
                firstLocate = pattern[i];
                break;
            }
        }

        pos[0][0] = locate[firstLocate][0];
        pos[0][1] = locate[firstLocate][1];
        map[pos[0][0]][pos[0][1]] = hole;

        pos[1][0] = pos[0][0] + direction[fold[0]][0];
        pos[1][1] = pos[0][1] + direction[fold[0]][1];
        map[pos[1][0]][pos[1][1]] = fold[0] < 2 ? (5 - map[pos[0][0]][pos[0][1]]) % 4 : (map[pos[0][0]][pos[0][1]] + 2) % 4;

        pos[2][0] = pos[0][0] + direction[fold[1]][0];
        pos[2][1] = pos[0][1] + direction[fold[1]][1];
        map[pos[2][0]][pos[2][1]] = fold[1] < 2 ? (5 - map[pos[0][0]][pos[0][1]]) % 4 : (map[pos[0][0]][pos[0][1]] + 2) % 4;

        pos[3][0] = pos[1][0] + direction[fold[1]][0];
        pos[3][1] = pos[1][1] + direction[fold[1]][1];
        map[pos[3][0]][pos[3][1]] = fold[1] < 2 ? (5 - map[pos[1][0]][pos[1][1]]) % 4 : (map[pos[1][0]][pos[1][1]] + 2) % 4;

        for (int row = 0; row < pow; row++) {
            for (int col = 0; col < pow / 2; col++) {
                bw.write(map[row%2][0] + " " + map[row%2][1]);
                if (col != pow / 2 - 1)
                    bw.write(" ");
            }
            if (row != pow - 1)
                bw.write("\n");
        }

        bw.flush();
    }


}