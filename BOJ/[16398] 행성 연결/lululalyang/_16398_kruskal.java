import java.io.*;
import java.util.*;

public class _16398_kruskal {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=i+1; j<N; j++) {
				pq.add(new Edge(i, j, Integer.parseInt(s[j])));
			}
		}
		
		System.out.println(Kruskal(N, pq));
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
	
	private static long Kruskal(int N, PriorityQueue<Edge> pq) {
		long res = 0;
		int[] p = new int[N];
		
		make_set(p);
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int n1 = now.n1;
			int n2 = now.n2;
			int w = now.w;
			
			int root1 = find(n1, p);
			int root2 = find(n2, p);
			if(root1 == root2) continue; // 루트노드 같으면 사이클 형성
			
			union(root1, root2, p);
			res += w;
		}
		
		return res;
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
