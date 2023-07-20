import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] people = new int[N];
		int start = 0;
		int answer = 0;
		
		while(true) {
			people[start] += 1;
			
			if (people[start] == M) {
				break;
			}

			if (people[start] % 2 == 1) {
				start = (start + L) % N;
			} else {
				start = (N - L + start) % N;
			}
			answer += 1;
		}
		System.out.println(answer);
	}
}
