import java.io.*;
import java.util.*;

public class _6497 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s;
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		while(true) {
			s = br.readLine().split(" ");
			int V = Integer.parseInt(s[0]);
			int E = Integer.parseInt(s[1]);
			
			if(V==0 && E==0)	break; // 종료조건
			
			if(flag)	sb.append("\n"); // 처음이 아니면
			else	flag = true;
			
			int sum = 0;
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for(int i=0; i<E; i++) {
				s = br.readLine().split(" ");
				pq.add(new Edge(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
				sum += Integer.parseInt(s[2]); // 총 전기세
			}
			
			sb.append(Kruskal(pq, V, E, sum));
		}
		
		System.out.print(sb.toString());
	}
	
	private static void make_set(int[] p) {
		for(int i=0; i<p.length; i++)
			p[i] = i;
	}
	
	private static int find(int v, int[] p) {
		if(p[v] == v) return v;
		p[v] = find(p[v], p);
		return p[v];
	}
	
	private static void union(int root1, int root2, int[] p) {
		p[root2] = root1;
	}
	
	private static int Kruskal(PriorityQueue<Edge> pq, int V, int E, int sum) {
		int res = 0;
		int[] p = new int[V];
		make_set(p);
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int n1 = now.n1;
			int n2 = now.n2;
			int w = now.w;
			
			int root1 = find(n1, p);
			int root2 = find(n2, p);
			if(root1 == root2) continue; // 사이클 생성
			
			union(root1, root2, p);
			res += w;
		}
		return (sum-res);
	}
	
	private static class Edge implements Comparable<Edge>{
		int n1;
		int n2; 
		int w;
		
		Edge(int n1, int n2, int w){
			this.n1 = n1;
			this.n2 = n2;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.w >= e.w ? 1 : -1;
		}
	}
}	
