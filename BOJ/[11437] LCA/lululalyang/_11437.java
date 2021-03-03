import java.io.*;
import java.util.*;

public class _11437 {
	static ArrayList<Integer>[] g;
	static int[] Level;
	static int[] P;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		g = new ArrayList[N+1]; // 1 ~ N
		for(int i=1; i<N+1; i++)
			g[i] = new ArrayList<Integer>();
		Level = new int[N+1]; // 각 노드의 레벨 // 인덱스 1 ~ N
		P = new int[N+1]; // 각 노드의 부모노드 // 인덱스 1 ~ N
		
		String[] s;
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			g[u].add(v);
			g[v].add(u);
		}
		
		dfs(1, 1, 0); // 현재 노드, 레벨, 부모노드
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine()); // 두 노드 쌍 개수
		for(int i=0; i<M; i++) {
			if(i != 0)	sb.append("\n");
			
			s = br.readLine().split(" ");
			sb.append(LCA(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
		}
		
		System.out.print(sb.toString());
	}
	
	private static int LCA(int u, int v) {
		int uLevel = Level[u];
		int vLevel = Level[v];
		
		while(uLevel > vLevel) { // u의 레벨이 더 크다면 => 레벨 맞춰주기 위해 u의 부모노드로 비교대상 바꾼다
			u = P[u];
			uLevel--;
		}
		while(uLevel < vLevel) { // v의 레벨이 더 크다면 => v의 부모노드로
			v = P[v];
			vLevel--;
		}
		// 레벨 맞춰주고
		while(u != v) { // 두 노드가 같지 않을 때 => 같을 때까지 각자 부모노드로
			u = P[u];
			v = P[v];
		}
		
		return u; // u, v값이 같다 => 최초 u,v의 최소 공통 조상
	}
	private static void dfs(int now, int level, int parent) {
		Level[now] = level;
		P[now] = parent;
		
		for(int next : g[now]) {
			if(next != parent) { // 자식노드만
				dfs(next, level+1, now);
			}
		}
	}
}
