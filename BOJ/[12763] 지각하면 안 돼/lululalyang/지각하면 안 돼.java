import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _12763 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // �ǹ��� ����
		String[] s = br.readLine().split(" ");
		int T = Integer.parseInt(s[0]); // �⼮���� ���� �ð�
		int M = Integer.parseInt(s[1]); // ���� ������ �ִ� ��
		int L = Integer.parseInt(br.readLine());
		
		ArrayList<Edge>[] adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)
			adj[i] = new ArrayList<>();
		
		for(int l=0; l<L; l++) {
			String[] d = br.readLine().split(" ");
			int a = Integer.parseInt(d[0]);
			int b = Integer.parseInt(d[1]);
			int time = Integer.parseInt(d[2]);
			int price = Integer.parseInt(d[3]);
			
			adj[a].add(new Edge(b, time, price));
			adj[b].add(new Edge(a, time, price));
		}
		
		int[] dpPrice = new int[N+1]; // �ش� ������ ���µ� �� ���� �� 
		Arrays.fill(dpPrice, Integer.MAX_VALUE);
		dpPrice[1] = 0; //������� 0�� ���
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0, 0)); //���, �ð�, �ýú�
		
		int result = Integer.MAX_VALUE;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for(Edge next : adj[now.v]) {
				
				if(dpPrice[next.v] >= now.price+next.price) { // ���� ������ ���ŵ� �� ���� ��
					if(now.time+next.time<=T && dpPrice[now.v]+next.price<=M) { // �־��� �ð� �̳� && ������ �̳�
						dpPrice[next.v] = now.price + next.price; 
						pq.add(new Edge(next.v, (now.time+next.time), dpPrice[next.v]));
						if(next.v == N)
							result = dpPrice[next.v];
					}
				}else if(now.time+next.time <= T) { // ���������� ������ �� ������ �̱�� ���� �ð��ȿ� ������ ��
					if(now.price+next.price <= M) { // �ýú� �ᵵ �������� ���� ���� ��
						pq.add(new Edge(next.v, (now.time+next.time), (now.price+next.price)));
						if(next.v == N)
							result = now.price + next.price;
					}
				}
			}
		}
		
		if(result != Integer.MAX_VALUE)
			System.out.print(dpPrice[N]);
		else
			System.out.print("-1");
	}

	static class Edge implements Comparable<Edge>{
		int v; // ������ ���
		int time; // �ɸ��� �ð�
		int price; // �ýú�
		
		Edge(int v, int time, int price){
			this.v = v;
			this.time = time;
			this.price = price;
		}
		
		@Override
		public int compareTo(Edge e) {
			if(this.price > e.price)
				return 1;
			else if(this.price < e.price)
				return -1;
			else
				return 0;
		}
		
	}
}
