import java.io.*;
public class _67256 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int[] numbers = new int[s.length];
		for(int i=0; i<numbers.length; i++)	numbers[i] = Integer.parseInt(s[i]);
		String hand = br.readLine();
		
		System.out.print(solution(numbers, hand));
	}
	
	private static String solution(int[] numbers, String hand) {
		StringBuilder sb = new StringBuilder();
		Loc left = new Loc(3, 0);
		Loc right = new Loc(3, 2);
		
		int nlen = numbers.length;
		for(int i=0; i<nlen; i++) {
			int now = numbers[i] - 1;
			int chk = now%3;
			if(chk == 0) { // 1,4,7 => �޼�����
				left = NumToLoc(now);
				sb.append("L");
			}else if(chk == 2) { // 3, 6, 9 => ����������
				right = NumToLoc(now);
				sb.append("R");
			}else { // 2, 5, 8, 0 => �Ÿ��� ���� => ���ٸ� ����������, �޼����̿� ����
				Loc nowloc;
				if(chk==-1)	nowloc = new Loc(3, 1);	
				else	nowloc = NumToLoc(now);
				
				int ldist = left.dist(nowloc);
				int rdist = right.dist(nowloc);
				boolean flag; // true�� ������, false�� �޼�
				
				if(ldist > rdist)	flag = true;	// �������� ������
				else if(ldist < rdist)	flag = false; 	// �޼��� ������
				else { // �Ÿ��� ������
					if(hand.equals("right"))  flag = true;	// ���������̸�
					else 	flag = false;// �޼����̸�
				}
				
				if(flag) { // �������̸�
					right = nowloc;
					sb.append("R");
				}else { // �޼��̸�
					left = nowloc;
					sb.append("L");
				}
			}
			
		}
		return sb.toString();
	}
	
	private static Loc NumToLoc(int num) {
		int x = num / 3;
		int y = num % 3;
		
		return new Loc(x, y);
	}
	
	private static class Loc{
		int x;
		int y;
		
		Loc(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int dist(Loc tmp) { // �� ��ġ ������ �Ÿ��� ����
			return Math.abs(this.x - tmp.x) + Math.abs(this.y - tmp.y);
		}
		
		public String toString() {
			return "(" + this.x + ", " + this.y + ")";
		}
	}
}
