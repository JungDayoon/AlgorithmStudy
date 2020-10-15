import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class _1516 {
	static int[] doneTimes; //각 건물이 완성되는 데 걸리는 시간
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		doneTimes = new int[N+1];
		int[] BuildingT = new int[N+1]; //각 건물을 짓는데 걸리는 시간
		int[] into = new int[N+1]; //진입차수
		ArrayList<Integer>[] g = new ArrayList[N+1];
		for(int i=1; i<=N; i++)
			g[i] = new ArrayList<Integer>(); //그래프 초기화
		
		for(int i=1; i<=N; i++) {
			String[] s = br.readLine().split(" ");
			BuildingT[i] = Integer.parseInt(s[0]);
			doneTimes[i] = Integer.parseInt(s[0]);
			for(int j=1; j<s.length; j++) {
				if(s[j].equals("-1")) break;
				int n = Integer.parseInt(s[j]);
				g[n].add(i);
				into[i]++;
			}
		}
		
		Queue<Integer> q= new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(into[i] == 0) {
				q.add(i); // 진입차수가 0인 애들 q에 넣어준다.
				into[i] = -1; //q에 넣어줬으면 into는 -1로 표시
			}
		}
		
		topologicalSort(BuildingT, into, g, q);
		for(int t=1; t<=N; t++) {
			System.out.println(doneTimes[t]);
		}
	}
	
	static void topologicalSort(int[] BuildingT, int[] into, ArrayList<Integer>[] g, Queue<Integer> q) {
		while(q.size() != 0) {
			int poll = q.poll();
			
			for(Integer node : g[poll]) {
				if(doneTimes[node] < doneTimes[poll] + BuildingT[node]) {
					doneTimes[node] = doneTimes[poll] + BuildingT[node];
				}
				into[node]--;
			}
			
			g[poll].clear();
			checkIntoZero(into, q);
		}
	}
	
	static void checkIntoZero(int[] into, Queue<Integer> q) {
		for(int i=1; i<into.length; i++) {
			if(into[i] == 0){
				q.add(i);
				into[i] = -1;
			}
		}
	}
}
