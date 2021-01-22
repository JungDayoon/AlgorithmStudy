import java.util.*;
import java.io.*;

public class _2225 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int K = Integer.parseInt(s[1]);
		
		int[][] caseNum = new int[K+1][N+1];
		
		findSumN_cnt(caseNum);
		System.out.print(caseNum[K][N] % 1000000000);
	}
	
	private static void findSumN_cnt(int[][] caseNum) {
		Arrays.fill(caseNum[1], 1); // 숫자하나로 어떠한 숫자 만드는 경우의 수는 1
		int flag = 0;
		
		for(int i=2; i<caseNum.length; i++) {
			caseNum[i][0] = 1; // i개로 0 만드는 경우의 수는 1
			for(int j=1; j<caseNum[0].length; j++) {
				caseNum[i][j] = caseNum[i][j-1]%1000000000 + caseNum[i-1][j]%1000000000;
			}
		}
	}
}
