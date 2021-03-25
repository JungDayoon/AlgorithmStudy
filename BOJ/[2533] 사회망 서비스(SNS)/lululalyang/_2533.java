import java.io.*;
import java.util.*;

public class _2533 {
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] tree;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		tree = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)	{
			adj[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		
		String[] s;
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		boolean[] visited = new boolean[N+1];
		makeTree(1, visited);
		
		dp = new int[N+1][2];
		int state0 = dfs(1, 0); // 얼리어답터가 아닐 때
		int state1 = dfs(1, 1); // 얼리어답터일 때
		
		System.out.print(Math.min(state0, state1));
	}
	
	private static int dfs(int now, int state) {
		if(dp[now][state] != 0)		return dp[now][state];
		
		if(state == 1) { // now가 얼리어답터면 => 자식노드 얼리어답터여도 되고, 아니여도 됨 => 둘 중 작은 값으로
			for(Integer next : tree[now]) {
				int state0 = dfs(next, 0);
				int state1 = dfs(next, 1);
				dp[now][state] += Math.min(state0, state1);
			}
			dp[now][state]++;
		}else { // now가 얼리어답터가 아니면 => 자식노드 모두 얼리어답터여야함
			for(Integer next : tree[now]) {
				dp[now][state] += dfs(next, 1);
			}
		}
		
		return dp[now][state];
	}
	
	private static void makeTree(int now, boolean[] visited) {
		if(visited[now])	return;
		
		visited[now] = true;
		for(Integer next : adj[now]) {
			if(!visited[next]) {
				tree[now].add(next);
				makeTree(next, visited);
			}
		}
	}
}
