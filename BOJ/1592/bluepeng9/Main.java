import java.io.*;

public class Main
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int m = Integer.parseInt(in[1]);
        int l = Integer.parseInt(in[2]);


        int[] rec = new int[n];

        int cur = 0;
        rec[0] = 1;
        int count = 0;

        while (true) {
            if (rec[cur] == m) {
                break;
            }
            if (rec[cur] % 2 == 0) {
                cur = (n + cur - l) % n;
            } else {
                cur = (cur + l) % n;
            }
            rec[cur] += 1;
            count += 1;
        }
        System.out.println(count);
    }

}
