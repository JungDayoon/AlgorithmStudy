import java.io.*;
import java.util.*;
public class _13334 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<BasedEnd> pq = new PriorityQueue<>();
		String[] s;
		for(int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			if(a < b)	pq.add(new BasedEnd(a, b)); // ���� ���� ����������
			else	pq.add(new BasedEnd(b, a)); 
		}
		int d = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> startPoint = new PriorityQueue<>();
		int Max = 0;
		while(!pq.isEmpty()) {
			BasedEnd now = pq.poll();
			if((now.end-now.start) > d)	continue; // �������� ������ ���� �Ÿ��� d���� ũ�� �� �Ÿ��� ���Ե� �� ����
			int thisStart = now.end - d; // �̹� ö���� ������
			startPoint.add(now.start); // �������� �߰�
			
			while(startPoint.peek() < thisStart) { // startPoint �� �� ���� ���� �̹� ö���� ���������� Ŭ ������
				startPoint.remove();
			}
			
			Max = Math.max(Max, startPoint.size());
		}
		System.out.print(Max);
	}
	
	private static class BasedEnd implements Comparable<BasedEnd>{
		private int start;
		private int end;
		
		BasedEnd(int start, int end){
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(BasedEnd b) {
			if(this.end > b.end)	return 1;
			else if(this.end < b.end)	return -1;
			else { // ������ ���ٸ�
				return this.start - b.start;
			}
		}
	}
}
