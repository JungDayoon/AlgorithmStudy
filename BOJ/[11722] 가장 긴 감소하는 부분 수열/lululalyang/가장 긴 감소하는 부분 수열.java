import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _11722 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N];
		int[] input = new int[N];
		
		for(int i=0; i<N; i++) //dp배열 초기화
			dp[i] = 1;
		
		String[] s = br.readLine().split(" ");
		input[0] = Integer.parseInt(s[0]);
		for(int i=1; i<N; i++) {
			input[i] = Integer.parseInt(s[i]);
			for(int j=0; j<i; j++) {
				if(input[j] > input[i]) {
					dp[i] = ((dp[j]+1) > dp[i])? (dp[j]+1) : dp[i];
				}
			}
		}
		
		Arrays.sort(dp);
		System.out.print(dp[N-1]);
	}

}
