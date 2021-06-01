import java.io.*;
public class _2602 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] magic = br.readLine().split(""); // 마법의 두루미
		String[][] bridge = new String[2][];
		for(int i=0; i<2; i++) {
			bridge[i] = br.readLine().split("");
		}
		
		System.out.print(solution(magic, bridge));
	}
	
	private static long solution(String[] magic, String[][] bridge) {
		long ans = 0;
		int mLen = magic.length;
		int bLen = bridge[0].length;
		long[][][] dp = new long[2][mLen][bLen];
		
		for(int b=0; b<bLen; b++) { // 다리 하나씩 확인
			for(int row=0; row<2; row++) { // row=0: 악마다리 // row=1:천사다리
				for(int m=0; m<mLen; m++) {
					if(magic[m].equals(bridge[row][b])) { // 만약 현재 다리의 문자가 해당 인덱스의 마*두의 문자와 같다면
						ComputeCaseOfNum(b, row, m, dp);
					}
				}
			}
		}
		
		for(int row=0; row<2; row++) {
			for(int b=0; b<bLen; b++) {
				ans += dp[row][mLen-1][b];
			}
		}
		
		return ans;
	}
	
	private static void ComputeCaseOfNum(int b, int row, int m, long[][][] dp) {
		if(m == 0) {
			dp[row][m][b] = 1;
			return;
		}
		
		long sum = 0;
		int chk = (row == 0)? 1 : 0;
		for(int i=b-1; i>=0; i--) {
			sum += dp[chk][m-1][i];
		}
		
		dp[row][m][b] += sum;
	}
}
