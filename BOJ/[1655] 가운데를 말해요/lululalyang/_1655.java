import java.io.*;
import java.util.*;
public class _1655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> tmp = new ArrayList<>();
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // mid���� ���� �� // ��������
		PriorityQueue<Integer> big = new PriorityQueue<>(); // mid���� ū �� // ��������
		
		StringBuilder sb = new StringBuilder();
		int mid = Integer.parseInt(br.readLine()); // ù��° ����
		sb.append(mid); 
		
		for(int i=1; i<N; i++) {
			int now = Integer.parseInt(br.readLine());
			
			if(now > mid)	big.add(now);
			else	small.add(now);
			
			if(i%2 != 0) { // �ε����� Ȧ�� => ¦���� => smallũ�Ⱑ big���� 1 �۾ƾ���
				if(small.size() != (big.size()-1)) {
					if(small.size() > big.size())  mid = MoveOddIdx(small, big, mid, 0);// small���� big����
					else  mid = MoveOddIdx(small, big, mid, 1); // ���ų� big�� ���� ���� big���� small��
				}
			}else { // �ε����� ¦�� => Ȧ���� => �� pq�� ũ�Ⱑ ���ƾ� ��
				if(small.size() != big.size()) {
					if(small.size() > big.size())  mid = MoveEvenIdx(small, big, mid, 0); // small���� big����
					else 	mid = MoveEvenIdx(small, big, mid, 1); // big���� small����
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
	
	private static int MoveOddIdx(PriorityQueue<Integer> small, PriorityQueue<Integer> big, int mid, int flag) { // ¦�� �ε����϶� pq�� �̵� // flag=0�̸� s->b, flag=1�̸� b->s
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
