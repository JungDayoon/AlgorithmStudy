import java.io.*;
import java.util.*;

public class _1238 {
	static ArrayList<Edge>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // �л���, ���� ��
		int M = Integer.parseInt(s[1]); // ���� ��
		int X = Integer.parseInt(s[2]); // ��Ƽ�� ������ X����
		
		adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)	adj[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");			
			adj[Integer.parseInt(s[0])].add(new Edge(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
		}
		
		System.out.print(solution(N, X));
	}
	
	private static int solution(int N, int X) {
		int maxTime = 0;
		int[][] dist = new int[N+1][N+1];
		for(int i=1; i<N+1; i++) { // i�� �������
			int[] d = dist[i];
			Arrays.fill(d, Integer.MAX_VALUE);
			d[i] = 0; // �������
			Queue<Edge> q = new LinkedList<>();
			q.add(new Edge(i, 0));
			
			while(!q.isEmpty()) {
				Edge now = q.poll();
				int v = now.v;
				int t = now.t;
				
				if(t > d[v])	continue;
				
				for(Edge next : adj[v]) {
					if(d[next.v] > (t + next.t)) {
						d[next.v] = t + next.t;
						q.add(new Edge(next.v, d[next.v]));
					}
				}
			}
		}
		
		for(int i=1; i<N+1; i++) {
			int time = dist[i][X] + dist[X][i];
			maxTime = Math.max(maxTime, time);
		}
		
		return maxTime;
	}
	
	private static class Edge implements Comparable<Edge>{
		private int v; // ����ȣ
		private int t; // �ɸ��� �ð�
		
		Edge(int v, int t){
			this.v = v;
			this.t = t;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.t - e.t;
		}
	}
}
