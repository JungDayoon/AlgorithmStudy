import java.util.*;
public class _12987 {

	public static void main(String[] args) {
		int[] A = {2,2,2,2};
		int[] B = {1,1,1,1};
		
		System.out.print(solution(A, B));
	}
	
	private static int solution(int[] A, int[] B) {
		int ans = 0;
		PriorityQueue<Integer> Apq = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> Bpq = new PriorityQueue<>(Collections.reverseOrder());
		
		int N = A.length;
		for(int i=0; i<N; i++) {
			Apq.add(A[i]);
			Bpq.add(B[i]);
		}
		
		while(!Apq.isEmpty()) {
			int nowA = Apq.poll();
			int nowB = Bpq.peek();
			
			if(nowA > nowB)	continue;			
			if(nowA < nowB) { // B�¸�
				ans++;
				Bpq.poll();
			}
			// ������ B�� �״��, A�� ���� ���� Ȯ���Ѵ�
		}
		
		return ans;
	}
}
