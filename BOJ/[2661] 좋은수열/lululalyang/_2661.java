import java.util.*;
public class _2661 {
	static boolean flag = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		ArrayList<Integer> num = new ArrayList<>(Arrays.asList(1));
		solution(num, 1, N);
		for(Integer n : num)	System.out.print(n);
	}
	
	private static void solution(ArrayList<Integer> num, int len, int N) {
		if(len == N) {
			flag = true;
			return;
		}
		
		for(int i=1; i<=3; i++) {
			num.add(i);
			if(ChkValid(num, len+1))	solution(num, len+1, N);
			if(flag)	return;
			num.remove(len);
		}
	}
	
	private static boolean ChkValid(ArrayList<Integer> num, int len) {
		if(num.get(len-1) == num.get(len-2))	return false; // ���� ���ڿ� ���ٸ� ���� ����
		for(int cnt=2; cnt<=len/2; cnt++) {
			int same = 0;
			for(int i=len-1; i>len-1-cnt; i--) {
				if(num.get(i) == num.get(i-cnt))	same++;
			}
			if(same == cnt)	return false; // ���� �κ� ������ ���ӵ� ���
		}
		return true;
	}
}
