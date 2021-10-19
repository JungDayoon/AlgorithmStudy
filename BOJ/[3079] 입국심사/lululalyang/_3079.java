import java.io.*;
public class _3079 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // 심사대 N개
		int M = Integer.parseInt(s[1]); // M명
		
		int[] T = new int[N];
		int max = 0;
		for(int i=0; i<N; i++) {
			T[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, T[i]);
		}
		
		System.out.print(solution(N, M, T, max));
	}
	
	private static long solution(int N, int M, int[] T, int max) {
		long ans = 0;
		long l = 1; 
		long r = (long)max * M; // 가장 오래걸리는 심사대에서 M명 처리할 때 => 최대 시간
		while(l <= r) {
			long m = (l+r) / 2;
			
			if(AllDone(N, M, T, m)) { // m시간으로 처리할 수 있으면 => 더 줄인다
				ans = m;
				r = m-1;
			}else { // 처리할 수 없으면 => 더 늘린다
				l = m+1;
			}
		}
			
		return ans;
	}
	
	private static boolean AllDone(int N, int M, int[] T, long m) { // m시간 안에 모두 심사할 수 있는지의 여부
		long done = 0;
		for(int i=0; i<N; i++) {
			done += (m / T[i]); // i번째 심사대가 m시간동안 심사할 수 있는 명 수
			if(done >= M)	return true; // M명 이상 심사했으면 가능
		}
		return false;
	}
}
