import java.io.*;
import java.util.*;
public class Main {
	static class node implements Comparable<node>{
		int v, t, m;
		public node(int v, int m, int t) {
			this.v = v;
			this.t = t;
			this.m = m;
		}
		@Override
		public int compareTo(node o) {
			return Integer.compare(this.m, o.m); //기본적으로 비용이 저렴한 것 먼저 선택
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(br.readLine());
		
		ArrayList<node>[] arr = new ArrayList[n+1];
		for(int i = 0; i < n+1; i++) {
			arr[i] = new ArrayList<>();
		}
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[x].add(new node(y, w, t));
			arr[y].add(new node(x, w, t));
		}
		//입력완료
		
		int[] dist = new int[n+1];
		int[] time = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(time, Integer.MAX_VALUE);
		dist[1] = 0;
		time[1] = 0;
		
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(1, dist[1], time[1]));
		
		while(!pq.isEmpty()) {
			//방문하지 않은 정점 중 최소가중치 선택
			node cur = pq.poll();
			
			for(node next: arr[cur.v]) {
				if(cur.m+next.m > M) continue; //비용 초과 시 불가능
				if(cur.t+next.t > T) continue; //시간 초과 시 불가능
				if(dist[next.v] > cur.m+next.m) { //갱신 가능 조건(더 적은 비용)
					dist[next.v] = cur.m+next.m;
					time[next.v] = cur.t+next.t;
					pq.offer(new node(next.v, dist[next.v], time[next.v]));
				}
				else { //적은 비용은 아니지만 비용,시간 내 지점 가보기
					pq.offer(new node(next.v, cur.m+next.m, cur.t+next.t));
				}
			}
		}
		if(dist[n] <= M) System.out.println(dist[n]);
		else System.out.println(-1);
		br.close();
	}
}
