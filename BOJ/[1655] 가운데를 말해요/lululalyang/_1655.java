import java.io.*;
import java.util.*;
public class _1655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> tmp = new ArrayList<>();
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // mid보다 작은 수 // 내림차순
		PriorityQueue<Integer> big = new PriorityQueue<>(); // mid보다 큰 수 // 오름차순
		
		StringBuilder sb = new StringBuilder();
		int mid = Integer.parseInt(br.readLine()); // 첫번째 숫자
		sb.append(mid); 
		
		for(int i=1; i<N; i++) {
			int now = Integer.parseInt(br.readLine());
			
			if(now > mid)	big.add(now);
			else	small.add(now);
			
			if(i%2 != 0) { // 인덱스가 홀수 => 짝수개 => small크기가 big보다 1 작아야함
				if(small.size() != (big.size()-1)) {
					if(small.size() > big.size())  mid = MoveOddIdx(small, big, mid, 0);// small에서 big으로
					else  mid = MoveOddIdx(small, big, mid, 1); // 같거나 big이 많을 때는 big에서 small로
				}
			}else { // 인덱스가 짝수 => 홀수개 => 두 pq의 크기가 같아야 함
				if(small.size() != big.size()) {
					if(small.size() > big.size())  mid = MoveEvenIdx(small, big, mid, 0); // small에서 big으로
					else 	mid = MoveEvenIdx(small, big, mid, 1); // big에서 small으로
				}
			}
			
			sb.append("\n" + mid);
		}
		
		System.out.print(sb.toString());
	}
	
	private static int MoveEvenIdx(PriorityQueue<Integer> small, PriorityQueue<Integer> big, int mid, int flag) {
		if(flag == 0) { // small to big
			while(small.size() != big.size()) {
				big.add(mid);
				mid = small.poll();
			}
		}else { // big to small
			while(small.size() != big.size()) {
				small.add(mid);
				mid = big.poll();
			}
		}
		return mid;
	}
	
	private static int MoveOddIdx(PriorityQueue<Integer> small, PriorityQueue<Integer> big, int mid, int flag) { // 짝수 인덱스일때 pq간 이동 // flag=0이면 s->b, flag=1이면 b->s
		if(flag == 0) { // small to big
			while(small.size() != (big.size()-1)) {
				big.add(mid);
				mid = small.poll();
			}
		}else { // big to small
			while(small.size() != (big.size()-1)) {
				small.add(mid);
				mid = big.poll();
			}
		}
		return mid;
	}
}
