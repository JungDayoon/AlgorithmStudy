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
		int state0 = dfs(1, 0); // �󸮾���Ͱ� �ƴ� ��
		int state1 = dfs(1, 1); // �󸮾������ ��
		
		System.out.print(Math.min(state0, state1));
	}
	
	private static int dfs(int now, int state) {
		if(dp[now][state] != 0)		return dp[now][state];
		
		if(state == 1) { // now�� �󸮾���͸� => �ڽĳ�� �󸮾���Ϳ��� �ǰ�, �ƴϿ��� �� => �� �� ���� ������
			for(Integer next : tree[now]) {
				int state0 = dfs(next, 0);
				int state1 = dfs(next, 1);
				dp[now][state] += Math.min(state0, state1);
			}
			dp[now][state]++;
		}else { // now�� �󸮾���Ͱ� �ƴϸ� => �ڽĳ�� ��� �󸮾���Ϳ�����
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
