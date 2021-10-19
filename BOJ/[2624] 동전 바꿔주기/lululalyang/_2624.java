import java.io.*;
import java.util.*;
public class _2624 {
	static int[][] coin_cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // ������ �ݾ�
		int k = Integer.parseInt(br.readLine()); // ������ ���� ��
		coin_cnt = new int[k][2]; // [i][0]: ���� ��ġ // [i][1]: ���� ����
		
		String[] s;
		for(int i=0; i<k; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<2; j++)	coin_cnt[i][j] = Integer.parseInt(s[j]);
		}
		
		System.out.print(solution(T, k));
	}
	
	private static int solution(int T, int k) {
		int res = 0;
		sortArr(); // �������� ����
		
		int[][] dp = new int[k][T+1];
		for(int i=0; i<k; i++) {
			for(int j=1; j<T+1; j++) { // 1�� ~ T��
				ComputeNumOfCase(j, i, dp);
			}
		}
		
		for(int i=0; i<k; i++)	res += dp[i][T];
		return res;
	}
	
	private static void ComputeNumOfCase(int T, int i, int[][] dp) {
		int coin = coin_cnt[i][0]; // ���� ���� ��ġ
		int cnt = coin_cnt[i][1]; // �� ������ ����
		
		int now = 0;
		for(int x=1; x<=cnt; x++) {
			int tmp = T - coin*x;
			if(tmp < 0)	break;
			else if(tmp == 0)	now++;
			else {
				for(int y=i-1; y>=0; y--) {
					now += dp[y][tmp];
				}
			}
		}
		
		dp[i][T] = now;
	}
	
	private static void sortArr() {
		Arrays.sort(coin_cnt, new Comparator<int[]>(){
			@Override
			public int compare(int[] A, int[] B) {
				return A[0] - B[0];
			}
		});
	}
}
