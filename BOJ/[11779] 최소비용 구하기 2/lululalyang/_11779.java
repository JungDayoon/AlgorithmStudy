import java.io.*;
import java.util.*;
public class _11779 {
	static ArrayList<Edge>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		adj = new ArrayList[n+1]; // 1~n
		for(int i=1; i<n+1; i++)	adj[i] = new ArrayList<>();
		
		String[] s;
		for(int i=0; i<m; i++) {
			s = br.readLine().split(" ");
			adj[Integer.parseInt(s[0])].add(new Edge(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
		}
		
		s = br.readLine().split(" ");
		int start = Integer.parseInt(s[0]);
		int arrive = Integer.parseInt(s[1]);
		
		StringBuilder res = solution(n, m, start, arrive);
		System.out.print(res.toString());
	}
	
	private static StringBuilder solution(int n, int m, int start, int arrive) {
		int[] d = new int[n+1]; // 최소비용
		int[] p = new int[n+1]; // 부모 노드
		
		Arrays.fill(d, Integer.MAX_VALUE);
		for(int i=1; i<n+1; i++)	p[i] = i;
		
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(start, 0));
		d[start] = 0;
		
		while(!q.isEmpty()) {
			Edge now = q.poll();
			int v = now.v;
			int w = now.w;
			
			if(d[v] < w)	continue;
			for(Edge next : adj[v]) {
				if(d[next.v] > (w + next.w)) {
					d[next.v] = w + next.w;
					p[next.v] = v;
					q.add(new Edge(next.v, d[next.v]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(d[arrive] + "\n"); // 도착까지의 비용
		
		ArrayList<Integer> ans = new ArrayList<>();
		dfs(arrive, p, ans);
		int cnt = ans.size();
		sb.append(cnt + "\n"); // 도시의 개수
		for(int i=0; i<cnt; i++) {
			if(i==0)	sb.append(ans.get(i));
			else	sb.append(" "+ans.get(i));
		}
		
		return sb;
	}
	
	private static void dfs(int v, int[] p, ArrayList<Integer> ans) {
		if(p[v] == v) {
			ans.add(v);
			return;
		}
		dfs(p[v], p, ans);
		ans.add(v);
	}
	
	private static class Edge implements Comparable<Edge>{
		private int v;
		private int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.w - e.w;
		}
	}
}
