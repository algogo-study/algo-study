import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

class Solution
{
    static ArrayList<Character> zero_group = new ArrayList<>(Arrays.asList('C', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'S', 'T' ,'U', 'V', 'W', 'X', 'Y', 'Z'));
    static ArrayList<Character> one_group = new ArrayList<>(Arrays.asList('A', 'D', 'O', 'P', 'Q', 'R'));
    static ArrayList<Character> two_group = new ArrayList<>(Arrays.asList('B'));
    
    // 같은 문자로 보이는 지 체크
    public static boolean isSame(char first, char second) {
        if (zero_group.contains(first) && zero_group.contains(second)) {
            return true;
        } else if (one_group.contains(first) && one_group.contains(second)) {
            return true;
        } else if (two_group.contains(first) && two_group.contains(second)) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            String first_word = st.nextToken();
            String second_word = st.nextToken();
            // 문자열 끝까지 같은 문자인지 확인 여부
            boolean check = false;
            
            // 문자열 길이 체크
            if (first_word.length() != second_word.length()) {
                System.out.println("#" + tc + " DIFF");
                continue;
            }
            // 문자 하나씩 같은 지 체크
            for (int i = 0; i < first_word.length(); i++) {
                check = isSame(first_word.charAt(i), second_word.charAt(i));
                if (!check) break;
            }
            
            if (check) {
                System.out.println("#" + tc + " SAME");
            } else {
                System.out.println("#" + tc + " DIFF");
            }
        }
    }
}