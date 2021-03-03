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
		Level = new int[N+1]; // �� ����� ���� // �ε��� 1 ~ N
		P = new int[N+1]; // �� ����� �θ��� // �ε��� 1 ~ N
		
		String[] s;
		for(int i=0; i<N-1; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			g[u].add(v);
			g[v].add(u);
		}
		
		dfs(1, 1, 0); // ���� ���, ����, �θ���
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine()); // �� ��� �� ����
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
		
		while(uLevel > vLevel) { // u�� ������ �� ũ�ٸ� => ���� �����ֱ� ���� u�� �θ���� �񱳴�� �ٲ۴�
			u = P[u];
			uLevel--;
		}
		while(uLevel < vLevel) { // v�� ������ �� ũ�ٸ� => v�� �θ����
			v = P[v];
			vLevel--;
		}
		// ���� �����ְ�
		while(u != v) { // �� ��尡 ���� ���� �� => ���� ������ ���� �θ����
			u = P[u];
			v = P[v];
		}
		
		return u; // u, v���� ���� => ���� u,v�� �ּ� ���� ����
	}
	private static void dfs(int now, int level, int parent) {
		Level[now] = level;
		P[now] = parent;
		
		for(int next : g[now]) {
			if(next != parent) { // �ڽĳ�常
				dfs(next, level+1, now);
			}
		}
	}
}
