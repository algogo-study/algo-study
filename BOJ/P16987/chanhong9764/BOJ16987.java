import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Egg {
    int quality;
    int weight;
    
    public Egg(int quality, int weight) {
        this.quality = quality;
        this.weight = weight;
    }
}

class BOJ16987 {
    static int N;
    static Egg[] eggs;
    static int ans;
    
    static void recursive(int start) {
        if(start == N) {
        	int count = 0;
            for(int i = 0; i < eggs.length; i++) {
            	if(eggs[i].quality <= 0) {
            		count++;
            	}
            }
            ans = Math.max(ans, count);
            return;
        }
        
        // 계란이 깨졌을 경우
        if(eggs[start].quality <= 0) {
        	recursive(start + 1);
        	return;
        }
        // 계란을 깨지 않을 경우 체크
        boolean isCrack = false;
        for(int i = 0; i < N; i++) {
            if(eggs[i].quality > 0 && start != i) {
            	isCrack = true;
            	eggs[start].quality -= eggs[i].weight;
            	eggs[i].quality -= eggs[start].weight;
            	recursive(start + 1);
            	eggs[start].quality += eggs[i].weight;
            	eggs[i].quality += eggs[start].weight;
            }
        }
        // 깨지지 않은 다른 계란이 없을 경우
        if(!isCrack) {
        	recursive(start + 1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        recursive(0);
        
        System.out.println(ans);
    }
}