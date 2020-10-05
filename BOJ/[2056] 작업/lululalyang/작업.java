import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class _2056 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		LinkedList<Integer>[] adj = new LinkedList[N+1];
		LinkedList<Integer> q = new LinkedList(); //위상정렬 시 쓰이는 큐
		int[] into = new int[N+1]; //진입 차수
		int[] work = new int[N+1]; //작업 수행 시간
		int[] time = new int[N+1];
		boolean[] visited = new boolean[N+1];
		for(int i=1; i<N+1; i++) {
			String[] s = br.readLine().split(" ");
			work[i] = Integer.parseInt(s[0]);
			adj[i] = new LinkedList<Integer>();
			for(int j=0; j<Integer.parseInt(s[1]); j++) {
				adj[Integer.parseInt(s[j+2])].add(i);
				into[i]++;
			}
		}
		
		for(int i=1; i<N+1; i++) {
			if(into[i] == 0)	q.add(i);
			time[i] = work[i];
		}
		
		while(q.size() != 0) {
			int node = q.poll();
			visited[node] = true;
			
			for(int i : adj[node]) {
				if(time[i] < time[node] + work[i]) {
					time[i] = time[node] + work[i];
				}
				into[i]--;
				if(into[i] == 0)	q.add(i);
			}
		}
		
		
		int max = 0;
		for(int i=1; i<N+1; i++) {
			max = Math.max(max, time[i]);
		}
		
		System.out.print(max);
	}
	
}
