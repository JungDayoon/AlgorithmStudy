import java.io.*;
import java.util.*;
public class _1939 {
	static ArrayList<Edge>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)	adj[i] = new ArrayList<>();
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			int w = Integer.parseInt(s[2]);
			
			max = Math.max(max, w);
			min = Math.min(min, w);
			adj[u].add(new Edge(v, w));
			adj[v].add(new Edge(u, w));
		}
		
		s = br.readLine().split(" ");
		int A = Integer.parseInt(s[0]);
		int B = Integer.parseInt(s[1]); // A -> B
		System.out.print(solution(N, A, B, max, min));
	}
	
	private static int solution(int N, int A, int B, int max, int min) {
		int ans = -1;
		int l = min;
		int r = max;
		while(l <= r) {
			int m = (l + r) / 2;
			
			boolean[] visited = new boolean[N+1];
			if(CanMove(m, A, B, visited)) { // 갈 수 있으면
				ans = m;
				l = m+1;
			}else { // 못가면
				r = m-1;
			}
		}
		
		return ans;
	}
	
	private static boolean CanMove(int w, int now, int B, boolean[] visited) {
		if(now == B)	return true;
		
		visited[now] = true;
		boolean flag = false;
		for(Edge next : adj[now]) {
			if(!visited[next.v] && next.w >= w) {
				flag = CanMove(w, next.v, B, visited);
				if(flag)	break;
			}
		}
		
		return flag;
	}
	
	private static class Edge{
		int v;
		int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}
	}
}
