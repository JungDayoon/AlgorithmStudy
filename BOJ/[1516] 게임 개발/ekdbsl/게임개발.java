import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static int N;
//	static List<Integer>[][] map;
	static ArrayList<Integer>[] adj;
	static int[] indegree;
	static int[] time;
	static int[] resTime;
	static PriorityQueue<Game> pq = new PriorityQueue<>();
	
	static class Game implements Comparable<Game>{
		int num, time;
		Game(int num, int time){
			this.num = num;
			this.time = time;
		}
		
		public int compareTo(Game o) {
			return this.time - o.time;
		}
	}
	
	public static void input() throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	      
	      N = Integer.parseInt(st.nextToken());
	      adj = new ArrayList[N];
	      indegree = new int[N];
	      time = new int[N];
	      resTime = new int[N];
	      
	      for(int i = 0; i < N; i++) {
	         adj[i] = new ArrayList<>();
	      }
	      
	      for(int i = 0; i < N; i++) {
	         st = new StringTokenizer(br.readLine(), " ");
	         time[i] = Integer.parseInt(st.nextToken());
	         while(true)
	         {
	        	 int tmp = Integer.parseInt(st.nextToken());
	        	 if(tmp == -1) 
	        		 break;
	        	 adj[tmp-1].add(i);
	        	 indegree[i] += 1;
	         }
//	         list.add(new Fireball(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
	      }
	 }
	
	public static void main(String[] args) throws Exception
	{
		input();
		for(int i = 0; i<N; i++)
		{
			if(indegree[i] == 0)
			{
				pq.offer(new Game(i, time[i]));
				resTime[i] = time[i];
			}
		}
		
		while(!pq.isEmpty()) {
			Game g = pq.poll();
			
			for(int i = 0; i<adj[g.num].size(); i++)
			{
				indegree[adj[g.num].get(i)] -= 1;
				
				if(indegree[adj[g.num].get(i)] == 0)
				{
					resTime[adj[g.num].get(i)] = time[adj[g.num].get(i)] + g.time;
					pq.offer(new Game(adj[g.num].get(i), resTime[adj[g.num].get(i)]));
					
				}
			}
		}
		
		for(int i = 0; i<N; i++)
		{
			System.out.println(resTime[i]);
		}
	}
}
