import java.io.*;
import java.util.*;
public class _1477 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		int N = Integer.parseInt(s[0]); // �ްԼ� ����
		int M = Integer.parseInt(s[1]); // �� �������� �ްԼ� ����
		int L = Integer.parseInt(s[2]); // ��ӵ����� ����
		
		s = br.readLine().split(" ");
		ArrayList<Integer> rest = new ArrayList<>();
		for(int i=0; i<N; i++) {
			rest.add(Integer.parseInt(s[i]));
		}
		
		System.out.print(solution(N, M, L, rest));
	}
	
	private static int solution(int N, int M, int L, ArrayList<Integer> rest) {
		Collections.sort(rest); // �������� ����
		rest.add(L); // ������ �߰�
		N++;
		
		int l = 1;
		int r = 1000;
		int ans = 0;
		while(l <= r) {
			int m = (l+r) / 2;
			
			if(CheckBuild(N, M, m, rest)) { // �����ϸ� => ���� ���δ�
				ans = m;
				r = m-1;
			}else { // �Ұ����ϸ� => ���� �ø���
				l = m+1;
			}
		}
		
		return ans;		
	}
	
	private static boolean CheckBuild(int N, int M, int dist, ArrayList<Integer> rest) { // M���� �ްԼ� ��� �ִ밣�� dist�� ��������
		int now = 0;
		int i = 0;
		while(i < N) {
			int next = rest.get(i);
			if((next-now) <= dist) {
				i++; // �������� ����
				now = next;
			}else {
				if(M>0) { // ���� �� �ִ� �ްԼҰ� ���Ҵٸ�
					M--;
					now += dist;
				}else // dist�������� �ȵǴµ�, ���� �� �ִ� ���� �ްԼҰ� ���ٸ�
					return false;
			}
		}
		return true;
	}
}
