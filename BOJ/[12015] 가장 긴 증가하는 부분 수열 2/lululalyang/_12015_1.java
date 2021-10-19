import java.io.*;
import java.util.*;
public class _12015_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		
		System.out.print(solution(N, s));
	}

	private static int solution(int N, String[] s) {
		ArrayList<Integer> LIS = new ArrayList<>();
		LIS.add(Integer.parseInt(s[0]));
		for(int i=1; i<N; i++) {
			int now = Integer.parseInt(s[i]);
			if(LIS.get(LIS.size()-1) < now) {
				LIS.add(now);
			}else { // now�� LIS�� ���� ū ������ �۰ų� ������
				int l = 0;
				int r = LIS.size()-1;
				
				while(l < r) { // lower boundã�� (now���� ũ�ų� ���� �� �� ���� ���� ���� ��ġ)
					int m = (l+r)/2;
					
					if(now <= LIS.get(m))	r = m;
					else	l = m+1;
				}		
				LIS.set(l, now); // ���簪���� �ٲ۴�
			}
		}
		
		return LIS.size();
	}
}
