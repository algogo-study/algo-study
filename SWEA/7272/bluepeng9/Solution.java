import java.io.*;
import java.util.*;
 
public class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
    public static void main(String[] args) throws Exception {
         
        int tc = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> map = new HashMap<>();
         
        String one = "CEFGHIJKLMNSTUVWXYZ";
        String zero = "ADOPQR";
         
        for (int i = 0; i < one.length(); i++) {
            char v = one.charAt(i);
            map.put(v, 1);
        }
         
        for (int i = 0; i < zero.length(); i++) {
            char v = zero.charAt(i);
            map.put(v, 0);
        }
         
        map.put('B', 2);
         
        L: for (int i = 1; i < tc + 1; i++) {
             
             
            String[] s = br.readLine().split(" ");
             
            if (s[0].length() != s[1].length()) {
                System.out.printf("#%d DIFF\n", i);
                continue;
            }
             
            for (int j = 0; j < s[0].length(); j++) {
                char v1 = s[0].charAt(j);
                char v2 = s[1].charAt(j);
                if (map.get(v1) != map.get(v2)) {
                    System.out.printf("#%d DIFF\n", i);
                    continue L;
                }
            }
            System.out.printf("#%d SAME\n", i);
        }
         
    }
}