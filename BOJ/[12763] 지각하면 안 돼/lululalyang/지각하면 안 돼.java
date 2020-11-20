import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _12763 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 건물의 개수
		String[] s = br.readLine().split(" ");
		int T = Integer.parseInt(s[0]); // 출석까지 남은 시간
		int M = Integer.parseInt(s[1]); // 현재 가지고 있는 돈
		int L = Integer.parseInt(br.readLine());
		
		ArrayList<Edge>[] adj = new ArrayList[N+1];
		for(int i=1; i<N+1; i++)
			adj[i] = new ArrayList<>();
		
		for(int l=0; l<L; l++) {
			String[] d = br.readLine().split(" ");
			int a = Integer.parseInt(d[0]);
			int b = Integer.parseInt(d[1]);
			int time = Integer.parseInt(d[2]);
			int price = Integer.parseInt(d[3]);
			
			adj[a].add(new Edge(b, time, price));
			adj[b].add(new Edge(a, time, price));
		}
		
		int[] dpPrice = new int[N+1]; // 해당 노드까지 오는데 쓴 최저 돈 
		Arrays.fill(dpPrice, Integer.MAX_VALUE);
		dpPrice[1] = 0; //출발지는 0원 사용
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0, 0)); //노드, 시간, 택시비
		
		int result = Integer.MAX_VALUE;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for(Edge next : adj[now.v]) {
				
				if(dpPrice[next.v] >= now.price+next.price) { // 최저 가격이 갱신될 수 있을 때
					if(now.time+next.time<=T && dpPrice[now.v]+next.price<=M) { // 주어진 시간 이내 && 가진돈 이내
						dpPrice[next.v] = now.price + next.price; 
						pq.add(new Edge(next.v, (now.time+next.time), dpPrice[next.v]));
						if(next.v == N)
							result = dpPrice[next.v];
					}
				}else if(now.time+next.time <= T) { // 최저가격은 갱신할 수 없지만 이길로 가도 시간안에 도착할 때
					if(now.price+next.price <= M) { // 택시비 써도 남은돈을 넘지 않을 때
						pq.add(new Edge(next.v, (now.time+next.time), (now.price+next.price)));
						if(next.v == N)
							result = now.price + next.price;
					}
				}
			}
		}
		
		if(result != Integer.MAX_VALUE)
			System.out.print(dpPrice[N]);
		else
			System.out.print("-1");
	}

	static class Edge implements Comparable<Edge>{
		int v; // 인접한 노드
		int time; // 걸리는 시간
		int price; // 택시비
		
		Edge(int v, int time, int price){
			this.v = v;
			this.time = time;
			this.price = price;
		}
		
		@Override
		public int compareTo(Edge e) {
			if(this.price > e.price)
				return 1;
			else if(this.price < e.price)
				return -1;
			else
				return 0;
		}
		
	}
}
