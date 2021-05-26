import java.io.*;
import java.util.Arrays;
public class _2631 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] kids = new int[N];
		for(int i=0; i<N; i++) {
			kids[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.print(solution(N, kids));
	}
	
	private static int solution(int N, int[] kids) {
		int lis = 0;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		for(int i=1; i<N; i++) {
			int now = kids[i];
			for(int j=0; j<i; j++) {
				if(kids[j] < now) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					lis = Math.max(lis, dp[i]);
				}
			}
		}
			
		return (N - lis);
	}
}
