import java.io.*;
import java.util.*;

public class _16398_prim {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Edge>> g = new ArrayList<ArrayList<Edge>>();
		for(int i=0; i<N; i++)
			g.add(new ArrayList<>());
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				if(i!=j) {
					g.get(i).add(new Edge(j, Integer.parseInt(s[j])));
				}
			}
		}
		
		System.out.print(Prim(g, N));
	}
	
	private static long Prim(ArrayList<ArrayList<Edge>> g, int N) {
		long res = 0;
		int cnt = 0;
		boolean[] visited = new boolean[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0)); // ������ ���� 0
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int v = e.v;
			int w = e.w;
			
			if(visited[v])	continue; //�̹� �湮�� �����̸� ���� ���� Ȯ��
			visited[v] = true; //�׷��� ������ �湮���� true �� ����� ���� Ȯ��
			res += w;
			
			Iterator<Edge> itr = g.get(v).iterator(); 
			while(itr.hasNext()) {
				Edge now = itr.next();
				if(!visited[now.v])
					pq.add(new Edge(now.v, now.w));
			}
			
			if(++cnt == N) // ��� ���� Ž���� �����ٸ�
				break;
		}
		
		return res;
	}
	
	private static class Edge implements Comparable<Edge>{
		int v;
		int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return (this.w >= e.w)? 1 : -1; 
		}
	}
}
