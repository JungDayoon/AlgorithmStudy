import java.util.*;
import java.io.*;

public class _7570 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.print(N - Solution(br, N));
	}
	
	private static int Solution(BufferedReader br, int N) throws IOException {
		int res = 0;
		int[] dp = new int[N+1]; //dp[i] : �� i���� �����ϴ� �����ϴ� ������ ũ��
		
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(s[i]);
			
			dp[num] = dp[num-1] + 1;
			res = Math.max(res, dp[num]);
		}

		return res;
	}
	
}
