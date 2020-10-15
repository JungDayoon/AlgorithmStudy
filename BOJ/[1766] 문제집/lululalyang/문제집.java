import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class _1766 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		ArrayList<Integer>[] g = new ArrayList[N+1];
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int[] into = new int[N+1];
		
		for(int i=1; i<=N; i++)
			g[i] = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			String[] d = br.readLine().split(" ");
			int n1 = Integer.parseInt(d[0]);
			int n2 = Integer.parseInt(d[1]);
			g[n1].add(n2);
			into[n2]++;
		}
		
		for(int i=1; i<=N; i++) {
			if(into[i] == 0)	{
				pq.add(i);
				into[i] = -1; // ť�� ���� �� into�� -1�� ������ ǥ��
			}
			
		}
		topologicalSort(g, into, pq, N);
	}
	
	static void topologicalSort(ArrayList<Integer>[] g, int[] into, PriorityQueue<Integer> pq, int N) {
		while(pq.size() != 0) {
			int node = pq.poll();
			System.out.print(node+ " ");
			
			for(Integer n : g[node]) {
				into[n]--; // ���� ���ҿ� ����� ��� �������� �ϳ��� �ٿ��ְ�
			}
			g[node].clear(); //���� ��� ����
			
			checkIntoZero(pq, into);
		}
	}
	
	static void checkIntoZero(PriorityQueue<Integer> pq, int[] into) {
		for(int i=1; i<into.length; i++) {
			if(into[i] == 0) {
				pq.add(i);
				into[i] = -1;
			}
		}
	}
}
