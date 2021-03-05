import java.io.*;
import java.util.*;

public class _19542 {
	static ArrayList<Integer>[] g;
	static int N, S, D;
	static boolean flag = false;
	static int[] subDepth; // subDepth[v] : v 아래의 최대 depth
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		S = Integer.parseInt(s[1]);
		D = Integer.parseInt(s[2]);
		g = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)	g[i] = new ArrayList<>();
		subDepth = new int[N+1];
		
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			
			g[u].add(v);
			g[v].add(u);
		}
		
		boolean[] visited = new boolean[N+1];
		subDepth_DFS(S, visited);
		
		visited = new boolean[N+1];
		System.out.print(Dist_DFS(S, 0, visited) * 2);
	}
	
	private static int Dist_DFS(int now, int dist, boolean[] visited) {
		int res = 0;
		visited[now] = true;
		
		boolean flag = true;
		for(Integer next : g[now]) {
			if(!visited[next] && subDepth[next]>=D) {
				res += Dist_DFS(next, 1, visited);
				flag = false;
			}
		}
		
		return res + dist;
	}
	
	private static void subDepth_DFS(int now, boolean[] visited) {
		visited[now] = true;
		subDepth[now] = 0;
		for(Integer next : g[now]) {
			if(!visited[next]) {
				subDepth_DFS(next, visited);
				subDepth[now] = Math.max(subDepth[now], subDepth[next]+1);
			}
		}
	}
	
}
