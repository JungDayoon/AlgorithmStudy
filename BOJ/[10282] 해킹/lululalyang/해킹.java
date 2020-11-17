import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _10282 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) { //테스트케이스 시작
			String[] S = br.readLine().split(" ");
			int N = Integer.parseInt(S[0]); //컴퓨터 개수
			int D = Integer.parseInt(S[1]); //의존성 개수
			int C = Integer.parseInt(S[2]); //해킹당한 컴퓨터 번호
			
			ArrayList<Edge>[] adj= new ArrayList[N+1];
			for(int i=1; i<N+1; i++) //초기화
				adj[i] = new ArrayList<Edge>();
			
			for(int d=0; d<D; d++) {
				String[] ss = br.readLine().split(" ");
				int a = Integer.parseInt(ss[0]);
				int b = Integer.parseInt(ss[1]);
				int s = Integer.parseInt(ss[2]);
				
				adj[b].add(new Edge(a, s)); // b->a (weight: s)
			}
			
			int[] result = new int[N+1]; //각 출발지에서의 거리를 담을 배열
			ArrayList<Integer> com = new ArrayList<>();
			com.add(C);
			Arrays.fill(result, Integer.MAX_VALUE);
			result[C] = 0;
			boolean[] visited = new boolean[N+1]; //방문여부
			
			
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(C, 0));
			while(!pq.isEmpty()) {
				Edge now = pq.poll();
				
				for(Edge next : adj[now.v]) {
					if(result[next.v] > (result[now.v] + next.w)) {
						result[next.v] = result[now.v] + next.w;
						pq.add(new Edge(next.v, result[next.v]));
						
						if(!com.contains(next.v))
							com.add(next.v);
					}
				}
				visited[now.v] = true;
			}
		
			Collections.sort(com, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if(result[o1] < result[o2])
						return 1;
					else if(result[o1] > result[o2])
						return -1;
					else
						return 0;
				}		
			});

			System.out.println(com.size() + " " + result[com.get(0)]);	
		} //테스트케이스 끝
	}
	
	static class Edge implements Comparable<Edge> { //Comparable!!!
		int v;
		int w;
		
		Edge(int v, int w){
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {

			return Integer.compare(this.w, o.w);
		}	
	}
}
