import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Solution
{
    public static int isDiff(char[] number) {
        int check = -1;
        
        for (int i = 0; i < number.length - 1; i++) {
            if (number[i] == number[i + 1]) {
                check = i;
                break;
            }
        }
        
        return check;
    }
	public static void main(String args[]) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            char[] number = st.nextToken().toCharArray();
			
            int isSame = isDiff(number);
            while(isSame != -1) {
                char[] temp = new char[number.length - 2];
                int index = 0;
                for (int i = 0; i < isSame; i++) {
                    temp[index] = number[i];
                    index += 1;
                }
                for (int i = isSame + 2; i < number.length; i++) {
                    temp[index] = number[i];
                    index += 1;
                }
                number = temp;
                isSame = isDiff(number);
            }
            System.out.print("#" + tc + " ");
            for (int i = 0; i < number.length; i++) {
                System.out.print(number[i]);
            }
            System.out.println();
        }
	}
}