import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _14496 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int a = Integer.parseInt(s[0]); // 시작
		int b = Integer.parseInt(s[1]); // 끝
		s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // 전체 문자 수
		int M = Integer.parseInt(s[1]); // 치환가능한 문자쌍
		
		ArrayList<Edge>[] adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)
			adj[i] = new ArrayList<Edge>();
		
		for(int m=0; m<M; m++) {
			String[] d = br.readLine().split(" ");
			int x = Integer.parseInt(d[0]);
			int y = Integer.parseInt(d[1]);
			adj[x].add(new Edge(y, 1));
			adj[y].add(new Edge(x, 1)); //치환 쌍이니까 양방향으로 연결!
		}
		
		int[] D = new int[N+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[a] = 0; //시작점은 0
		boolean[] visited = new boolean[N+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(a, 0));
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for(Edge next : adj[now.v]) {
				if(!visited[next.v] && D[next.v]>D[now.v]+next.w) {
					D[next.v] = D[now.v] + next.w;
					pq.add(new Edge(next.v, D[next.v]));
				}
			}
			
			visited[now.v] = true;
		}
		
		if(D[b] == Integer.MAX_VALUE)
			System.out.print("-1");
		else
			System.out.print(D[b]);
	}
	
	static class Edge implements Comparable<Edge>{
		int v;
		int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.w > o.w)
				return 1;
			else if(this.w < o.w)
				return -1;
			else
				return 0;
		}
	}
}
