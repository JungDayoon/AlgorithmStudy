import java.io.*;
import java.util.*;

public class _1774 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		Vertex[] V = new Vertex[N+1];
		
		for(int i=1; i<=N; i++) {
			s = br.readLine().split(" ");
			V[i] = new Vertex(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		}
		
		double[][] Dist = new double[N+1][N+1];
		PriorityQueue<Edge> pq1 = new PriorityQueue<>();
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			int n1 = Integer.parseInt(s[0]);
			int n2 = Integer.parseInt(s[1]);
			pq1.add(new Edge(n1, n2, ComputeDist(V[n1], V[n2])));
			if(n1 < n2)
				Dist[n1][n2] = -1; // 이미 있는 간선은 -1로
			else
				Dist[n2][n1] = -1;
		}
		
		PriorityQueue<Edge> pq2 = new PriorityQueue<>();
		for(int i=1; i<N+1; i++) {
			for(int j=i+1; j<N+1; j++) {
				if(Dist[i][j] != -1) {
					pq2.add(new Edge(i, j, ComputeDist(V[i], V[j])));
				}
			}
		}
		
		System.out.printf("%.2f", Kruskal(N, V, pq1, pq2));
	}
	
	private static void make_set(int[] p) {
		for(int i=1; i<p.length; i++)
			p[i] = i;
	}
	
	private static int find(int v, int[] p) {
		if(p[v] == v)	return v;
		p[v] = find(p[v], p);
		return p[v];
	}
	
	private static void union(int root1, int root2, int[] p) {
		p[root2] = root1;
	}
	
	private static double Loop(PriorityQueue<Edge> pq, int[] p, double res, boolean flag) {
		
		while(!pq.isEmpty()){
			Edge now = pq.poll();
			int n1 = now.n1;
			int n2 = now.n2;
			double w = now.w;
			
			int root1 = find(n1, p);
			int root2 = find(n2, p);
			if(root1 == root2)	continue;
			
			union(root1, root2, p);
			if(flag)
				res += w;
		}
		
		return res;
	}
	
	private static double Kruskal(int N, Vertex[] V, PriorityQueue<Edge> pq1, PriorityQueue<Edge> pq2) {
		double res = 0.0;
		int[] p = new int[N+1];
		make_set(p);
		
		res = Loop(pq1, p, 0.0, false);
		res = Loop(pq2, p, 0.0, true);
		
		return res;
	}
	
	private static double ComputeDist(Vertex v1, Vertex v2) {
		return Math.sqrt(Math.pow(Math.abs(v1.x-v2.x), 2) + Math.pow(Math.abs(v1.y-v2.y), 2));
	}
	
	private static class Edge implements Comparable<Edge>{
		int n1;
		int n2;
		double w;
		
		Edge(int n1, int n2, double w){
			this.n1 = n1;
			this.n2 = n2;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.w >= e.w? 1 : -1;
		}
	}
	
	private static class Vertex{
		int x;
		int y;
		
		Vertex(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}