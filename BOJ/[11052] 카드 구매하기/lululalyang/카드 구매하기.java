import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] setPrice = new int[N+1];
		String[] s = br.readLine().split(" ");
		for(int i=1; i<N+1; i++) {
			setPrice[i] = Integer.parseInt(s[i-1]);
		}
		
		int[] dp = new int[N+1];
		dp[1] = setPrice[1];
		for(int i=2; i<N+1; i++) {
			int max = 0;
			for(int j=1; j<i; j++) {
				if(max < (dp[j]+setPrice[i-j]))
					max = dp[j]+setPrice[i-j];
			}
			if(max < setPrice[i])
				dp[i] = setPrice[i];
			else
				dp[i] = max;
		}
		
		System.out.print(dp[N]);
	}

}
