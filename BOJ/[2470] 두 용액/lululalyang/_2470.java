import java.io.*;
import java.util.*;
public class _2470 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(s[i]));
		}
		
		solution(N, list);
	}
	
	private static void solution(int N, ArrayList<Integer> list) {
		Collections.sort(list); // �������� ����
		
		int min = Integer.MAX_VALUE;
		int ans1 = 0;
		int ans2 = 0;
		
		int l = 0;
		int r = N-1;
		
		while(l < r) {
			int sum = list.get(l) + list.get(r); // ȥ�տ���� ��
			if(min > Math.abs(sum)) { // ���� 0�� �� �����ٸ�
				min = Math.abs(sum);
				ans1 = list.get(l);
				ans2 = list.get(r);
			}
			
			if(sum > 0)		r--; // ���� ��� => ���� �ٿ��� �� => r--
			else if(sum < 0)	l++; // ���� ���� => �� �÷��� �� => l++
			else	break; // ���� 0 => ������ ���
		}
		
		if(ans1 < ans2)		System.out.print(ans1 + " " + ans2);
		else	System.out.print(ans2 + " " + ans1);
	}
	
}
