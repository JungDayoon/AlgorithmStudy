import java.io.*;
import java.util.*;
public class _1365 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(s[i]);
		}
			
		System.out.print(solution(N, num));
	}
	
	private static int solution(int N, int[] num) {
		ArrayList<Integer> LIS = new ArrayList<>();
		LIS.add(num[0]);
		
		for(int i=1; i<N; i++) {
			int now = num[i];
			if(LIS.get(LIS.size()-1) < now)	LIS.add(now);
			else { // now가 작거나 같으면
				int l = 0;
				int r = LIS.size()-1;
				
				while(l < r) {
					int m = (l+r) / 2;
					
					if(now <= LIS.get(m))	r = m;
					else	l = m+1;
				}
				
				LIS.set(l, now);
			}
		}
		
		return (N - LIS.size());
	}
}
