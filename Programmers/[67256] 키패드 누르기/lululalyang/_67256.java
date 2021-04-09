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
			if(chk == 0) { // 1,4,7 => 왼손으로
				left = NumToLoc(now);
				sb.append("L");
			}else if(chk == 2) { // 3, 6, 9 => 오른손으로
				right = NumToLoc(now);
				sb.append("R");
			}else { // 2, 5, 8, 0 => 거리에 따라 => 같다면 오른손잡이, 왼손잡이에 따라
				Loc nowloc;
				if(chk==-1)	nowloc = new Loc(3, 1);	
				else	nowloc = NumToLoc(now);
				
				int ldist = left.dist(nowloc);
				int rdist = right.dist(nowloc);
				boolean flag; // true면 오른손, false면 왼손
				
				if(ldist > rdist)	flag = true;	// 오른손이 가까우면
				else if(ldist < rdist)	flag = false; 	// 왼손이 가까우면
				else { // 거리가 같으면
					if(hand.equals("right"))  flag = true;	// 오른손잡이면
					else 	flag = false;// 왼손잡이면
				}
				
				if(flag) { // 오른손이면
					right = nowloc;
					sb.append("R");
				}else { // 왼손이면
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
		
		public int dist(Loc tmp) { // 두 위치 사이의 거리를 리턴
			return Math.abs(this.x - tmp.x) + Math.abs(this.y - tmp.y);
		}
		
		public String toString() {
			return "(" + this.x + ", " + this.y + ")";
		}
	}
}
