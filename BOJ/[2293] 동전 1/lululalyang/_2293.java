import java.io.*;
import java.util.*;

public class _2293 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		
		int[] coin = new int[n];
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.print(ChkCases(n, k, coin));
	}
	
	private static int ChkCases(int N, int K, int[] coin) { // n: 동전 종류 //k: k원
		int res = 0;
		Arrays.sort(coin);
		int[][] dp = new int[N][K+1];
		dp[0][0] = 1; // 0원 만드는 경우의 수 => 1로 초기화
		
		for(int j=1; j<K+1; j++) { // j원
			for(int i=0; i<N; i++) {
				int nowC = coin[i];
				int value = j - nowC;
				if(value >= 0) {
					int sum = 0;
					for(int k=0; k<=i; k++)
						sum += dp[k][value];
					
					dp[i][j] = sum;
				}
			}
		}		
		
		for(int i=0; i<N; i++) {
			res += dp[i][K];
		}
		
		return res;
	}
}
