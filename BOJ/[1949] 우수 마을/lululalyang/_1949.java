import java.io.*;
import java.util.*;
public class _1949 {
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] tree;
	static int[] citizen;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		tree = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			adj[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		
		citizen = new int[N+1];
		String[] s = br.readLine().split(" ");
		for(int i=1; i<N+1; i++)	citizen[i] = Integer.parseInt(s[i-1]);
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
		int state0 = dfs(1, 0);
		int state1 = dfs(1, 1);
		
		System.out.print(Math.max(state0, state1));
	}
	
	private static int dfs(int now, int state) {
		if(dp[now][state] != 0)	return dp[now][state];
		
		if(state == 0) { // now�� ��������� �ƴ� ��
			int subCnt = tree[now].size(); // �ڽĳ�� ��
			ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int i=1; i<=subCnt; i++) { // 1����� subCnt�����
				Comb(subCnt, i, 0, tmp, comb);
			}
			
			int max = 0; // ������� �ֹ� �� ������ �ִ��
			for(ArrayList<Integer> great : comb) { // great: ��������� �ڽĳ���� �ε��� ����Ʈ
				int sum = 0;
				for(int i=0; i<subCnt; i++) {
					if(great.contains(i)) { // ��������̸�
						sum += dfs(tree[now].get(i), 1);
					}else { // ��������� �ƴϸ�
						sum += dfs(tree[now].get(i), 0);
					}
				}
				max = Math.max(max, sum);
			}
			
			int notgreat = 0;
			for(Integer next : tree[now]) { // ��� �ڽĳ�尡 ��������� �ƴ� ��
				notgreat += dfs(next, 0);
			}
			
			dp[now][state] = Math.max(max, notgreat);
			
		}else { // now�� ������� �� ��
			int sum = citizen[now];
			for(Integer next : tree[now]) {
				sum += dfs(next, 0); // �ڽĳ��� ��������� �ƴϿ��� ��
			}
			dp[now][state] = sum;
		}
		
		return dp[now][state];
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> comb) {
		if(r == 0) {
			comb.add((ArrayList<Integer>)tmp.clone());
			return;
		}
		
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp, comb);
			tmp.remove(tmp.indexOf(i));
		}
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
