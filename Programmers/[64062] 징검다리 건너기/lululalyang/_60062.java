import java.io.*;
import java.util.*;

public class _60062 {
	static ArrayList<ArrayList<Integer>> resF = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int[] weak = new int[s.length];
		for(int i=0; i<weak.length; i++)
			weak[i] = Integer.parseInt(s[i]);
		
		String[] d = br.readLine().split(" ");
		int[] dist = new int[d.length];
		for(int i=0; i<dist.length; i++)
			dist[i] = Integer.parseInt(d[i]);
		
		System.out.print(solution(n, weak, dist));
	}
	
	private static int solution(int n, int[] weak, int[] dist) {
		int ans = -1;
		Integer[] dist_ = Arrays.stream(dist).boxed().toArray(Integer[]::new); // dist 내림차순 정렬
		Arrays.sort(dist_, Collections.reverseOrder());
		
		int F = dist.length;
		boolean flag = false;
		for(int fcnt=1; fcnt<=F; fcnt++) {
			int[] friends = new int[fcnt];
			for(int i=0; i<fcnt; i++)
				friends[i] = dist_[i];
			
			boolean[] visited = new boolean[fcnt];
			ArrayList<Integer> tmp = new ArrayList<>();
			Perm(fcnt, fcnt, friends, visited, tmp);
			
			for(ArrayList<Integer> forder : resF) {
				if(CanCheckAll(forder, weak, n)) {
					ans = fcnt;
					flag = true;
					break;
				}
			}
			if(flag)	break;
			resF.clear();
		}
		return ans;
	}
	
	private static boolean CanCheckAll(ArrayList<Integer> dist, int[] weak, int n) {
		boolean res = false;
		int weakN = weak.length;
		boolean[] visited;
		
		int start, now, next;
		for(int i=0; i<weakN; i++) {
			visited = new boolean[weakN];
			start = i;
			now = weak[i]; // 시작위치
			if(i == (weakN-1))	next = 0; // 다음 취약점 인덱스
			else	next = i+1; 
			for(Integer d : dist) {
				visited[start] = true;
				for(int j=1; j<=d; j++) {
					now++;
					if(now == n)	now = 0;
					if(weak[next] == now) {
						visited[next] = true;
						if(next == (weakN-1))	next = 0;
						else	next++;
					}
				}
				now = weak[next];
				start = next; // 시작 인덱스
				if(next == (weakN-1))	next = 0;
				else	next++;
			}
			
			if(Chkvisited(visited)) {
				res = true;
				break;
			}
		}
		
		return res;
	}
	
	private static boolean Chkvisited(boolean[] visited) {
		for(boolean flag : visited) {
			if(!flag)	return false;
		}
		return true;
	}
	
	private static void Perm(int N, int r, int[] friends, boolean[] visited, ArrayList<Integer> tmp) {
		if(r == 0) {
			resF.add((ArrayList<Integer>)tmp.clone());
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				int now = friends[i];
				visited[i] = true;
				tmp.add(now);
				Perm(N, r-1, friends, visited, tmp);
				
				tmp.remove(tmp.indexOf(now));
				visited[i] = false;
			}
		}
	}
}
