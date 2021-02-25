import java.io.*;
import java.util.*;

public class _2565 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] line = new int[N][2];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<2; j++)
				line[i][j] = Integer.parseInt(s[j]);
		}
		
		Arrays.sort(line, new Comparator<int[]>() {
			@Override
			public int compare(int[] l1, int[] l2) {
				return l1[0] - l2[0];
			}
		});
		
		System.out.print(LIS(N, line));
	}
	
	private static int LIS(int N, int[][] line) {
		int[] dp = new int[N];
		int maxDp = 0;
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(line[j][1] < line[i][1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			maxDp = Math.max(maxDp, dp[i]);
		}
		
		return N-maxDp;
	}
}
