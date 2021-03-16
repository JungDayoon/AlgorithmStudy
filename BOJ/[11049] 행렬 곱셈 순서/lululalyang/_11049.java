import java.io.*;
public class _11049 {
	static int[][] dp;
	static Matrix[] m;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][N];
		m = new Matrix[N];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			m[i] = new Matrix(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		}
		
		System.out.print(solution(0, N-1));
	}
	
	private static int solution(int s, int l) {
		if(dp[s][l] != 0)	return dp[s][l];
		if((s+1) == l) { // s다음이 l이면
			dp[s][l] = m[s].r * m[s].c * m[l].c;
			return dp[s][l];
		}else if(s == l) { // 같으면 행렬이 1개 -> 곱셈연산 0번
			return 0;
		}
		
		dp[s][l] = Integer.MAX_VALUE;
		for(int i=s; i<l; i++) {
			dp[s][l] = Math.min(dp[s][l], solution(s, i) + solution(i+1, l) + m[s].r*m[i].c*m[l].c);
		}
		
		return dp[s][l];
	}
	
	private static class Matrix{
		int r;
		int c;
		
		Matrix(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
