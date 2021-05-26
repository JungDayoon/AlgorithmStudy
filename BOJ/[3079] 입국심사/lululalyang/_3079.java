import java.io.*;
public class _3079 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // �ɻ�� N��
		int M = Integer.parseInt(s[1]); // M��
		
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
		long r = (long)max * M; // ���� �����ɸ��� �ɻ�뿡�� M�� ó���� �� => �ִ� �ð�
		while(l <= r) {
			long m = (l+r) / 2;
			
			if(AllDone(N, M, T, m)) { // m�ð����� ó���� �� ������ => �� ���δ�
				ans = m;
				r = m-1;
			}else { // ó���� �� ������ => �� �ø���
				l = m+1;
			}
		}
			
		return ans;
	}
	
	private static boolean AllDone(int N, int M, int[] T, long m) { // m�ð� �ȿ� ��� �ɻ��� �� �ִ����� ����
		long done = 0;
		for(int i=0; i<N; i++) {
			done += (m / T[i]); // i��° �ɻ�밡 m�ð����� �ɻ��� �� �ִ� �� ��
			if(done >= M)	return true; // M�� �̻� �ɻ������� ����
		}
		return false;
	}
}
