import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class _12978 {

	public static void main(String[] args) {
		Solution s = new Solution();
		
		int N = 6;
		int K = 4;
		//int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
		System.out.println(s.solution(N, road, K));
	}

	static class Solution {
	    public int solution(int N, int[][] road, int K) {
	       int answer = 0;
	       
	       ArrayList<Edge>[] adj = new ArrayList[N+1];
	       for(int i=1; i<N+1; i++)
	    	   adj[i] = new ArrayList<>();
	       
	       for(int i=0; i<road.length; i++) {
	    	   int a = road[i][0];
	    	   int b = road[i][1];
	    	   int w = road[i][2];
	    	   adj[a].add(new Edge(b, w));
	    	   adj[b].add(new Edge(a, w));
	       }
	       
	       int[] D = new int[N+1];
	       Arrays.fill(D, Integer.MAX_VALUE);
	       D[1] = 0;
	       
	       boolean[] visited = new boolean[N+1];
	       PriorityQueue<Edge> pq = new PriorityQueue<>();
	       pq.add(new Edge(1, 0));
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
	       
	       for(int d : D) {
	    	   if(d<=K)
	    		   answer++;
	       }
	       return answer;
	    }
	}
	
	static class Edge implements Comparable<Edge>{
		int v;
		int w; 
		
		Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
		public int compareTo(Edge e) {
			if(this.w > e.w)
				return 1;
			else if(this.w < e.w)
				return -1;
			else
				return 0;
		}
	}
}

