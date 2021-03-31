import java.io.*;
import java.util.*;
public class _1916 {
	static ArrayList<Node>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)	adj[i] = new ArrayList<>();
		
		String[] s;
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			int w = Integer.parseInt(s[2]);
			
			adj[u].add(new Node(v, w));
		}
		
		s = br.readLine().split(" ");
		int start = Integer.parseInt(s[0]);
		int end = Integer.parseInt(s[1]);
		
		System.out.print(Dijkstra(start, end, N));
	}
	
	private static int Dijkstra(int start, int end, int N) {
		int[] d = new int[N+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(d[now.v] < now.w)	continue;
			for(Node next : adj[now.v]) {
				if(d[next.v] > d[now.v] + next.w) {
					d[next.v] = d[now.v] + next.w;
					pq.add(new Node(next.v, d[next.v]));
				}
			}
		}
		
		return d[end];
	}
	private static class Node implements Comparable<Node>{
		private int v;
		private int w;
		
		Node(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.w - n.w;
		}
	}
}
