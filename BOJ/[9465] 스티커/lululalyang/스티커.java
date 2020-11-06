import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _9465 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] input = new int[2][n+1];
			for(int i=0; i<2; i++) {
				String[] s = br.readLine().split(" ");
				for(int j=1; j<n+1; j++) {
					input[i][j] = Integer.parseInt(s[j-1]);
				}
			}
			
			int[][] dp = new int[2][n+1]; // 해당 index까지 얻을 수 있는 최고 점수
			dp[0][1] = input[0][1];
			dp[1][1] = input[1][1];
			for(int i=2; i<n+1; i++) {
				dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + input[0][i];
				dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + input[1][i];
			}
			
			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
	}

}
