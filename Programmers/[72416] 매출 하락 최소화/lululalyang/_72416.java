import java.io.*;
import java.util.*;

public class _72416 {
	static ArrayList<Integer>[] adj;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] sales = new int[n];
		String[] s = br.readLine().split(" ");
		for(int i=0; i<n; i++)
			sales[i] = Integer.parseInt(s[i]);
		
		int[][] links = new int[n-1][2];
		for(int i=0; i<n-1; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<2; j++)
				links[i][j] = Integer.parseInt(s[j]);
		}
		
		System.out.print(solution(sales, links));
	}
	
	private static int solution(int[] sales, int[][] links) {
		int n = sales.length;
		dp = new int[n+1][2];
		adj = new ArrayList[n+1];
		for(int i=1; i<n+1; i++)	adj[i] = new ArrayList<>();
		
		for(int i=0; i<n-1; i++) {
			int u = links[i][0]; // 팀장
			int v = links[i][1]; // 팀원
			
			adj[u].add(v);
		}
		
		int state1 = dfs(1, 1, sales);
		int state0 = dfs(1, 0, sales);
		
		return Math.min(state1, state0);
	}
	
	private static int dfs(int now, int state, int[] sales) { // sales는 (인덱스-1)!!
		if(dp[now][state] != 0)		return dp[now][state];
		
		if(state == 1) { // now가 참석 => 자식노드는 참여해도되고 안해도됨
			dp[now][state] += sales[now-1];
			for(Integer next : adj[now]) {
				int state0 = dfs(next, 0, sales);
				int state1 = dfs(next, 1, sales);
				
				dp[now][state] += Math.min(state1, state0);
			}
		}else { // now 참석X => 자식 노드 중 하나는 꼭 참여해야 함 (1명부터 자식노드 전체까지  참여했을 때의 매출액 최솟값)
			int subN = adj[now].size(); // 자식노드 개수
			if(subN == 0) { // 자식노드가 없는데, now가 참석하지 않는다면 매출액 최솟값 0
				return 0;
			}
			
			 ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
			for(int i=1; i<=subN; i++) {
				ArrayList<Integer> tmp = new ArrayList<>();
				Comb(subN, i, 0, tmp, comb);
			}
			
			int min = Integer.MAX_VALUE;
			for(ArrayList<Integer> attend : comb) {
				int sum = 0;
				for(int i=0; i<subN; i++) {
					int subNode = adj[now].get(i);
					if(attend.contains(i)) { // 참석
						sum += dfs(subNode, 1, sales);
					}else { // 비참석
						sum += dfs(subNode, 0, sales);
					}
				}
				
				min = Math.min(sum, min);
			}
			
//			for(Integer next1: adj[now]) {	
//				int attend = dfs(next1, 1, sales); // next1이 참여
//				int sum = attend;
//				for(Integer next2 : adj[now]) {
//					if(next1 != next2) { // 나머지는 비참여
//						sum += dfs(next2, 0, sales);
//					}
//				}
//				
//				min = Math.min(sum, min);
//			}
			dp[now][state] = min;
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
}
