import java.util.*;
import java.io.*;

public class Main_bj_17471_게리맨더링 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] p;
	static int[][] s;
	static boolean [] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		p = new int[N];
		check = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) p[i] = Integer.parseInt(st.nextToken());
		
		s = new int[N][];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			s[i] = new int[num];
			for(int j = 0; j < num; j++) {
				s[i][j] = Integer.parseInt(st.nextToken())-1;
			}
		}
//		//입력확인
//		System.out.println(Arrays.toString(p));
//		for(int [] t: s) {
//			System.out.println(Arrays.toString(t));
//		}
		
		subset(0);
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	}
	
	private static void subset(int cnt) {
		if(cnt == N) {
			if(isAvailable()) {//연결됐다
				int p1 = 0, p2 = 0;
				for(int i = 0; i < N; i++) {
					if(check[i]) p1 += p[i];
					else p2 += p[i];
				}
				ans = Math.min(ans, Math.abs(p1-p2));
			}
			return;
		}
		
		check[cnt] = true;
		subset(cnt+1);
		check[cnt] = false;
		subset(cnt+1);
	}
	
	private static boolean isAvailable() {// DFS
		boolean [] visit = new boolean[N];
		int a = -1;
		for(int i =0; i < N; i++) {
			if(check[i]) {
				a = i;
				break;
			}
		}
		int b = -1;
		for(int i = 0; i< N; i++) {
			if(!check[i]) {
				b = i;
				break;
			}
		}
		if(a == -1 || b == -1) return false;
		
		Deque<Integer>q = new LinkedList<>();
		q.add(a);
		visit[a] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int i = 0; i < s[now].length; i++) {
				if(visit[s[now][i]]) continue; //방문했거나
				if(!check[s[now][i]]) continue; //a구역이 아닌경우
				visit[s[now][i]] = true;
				q.add(s[now][i]);
			}
		}
		
		q.add(b);
		visit[b] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int i = 0; i < s[now].length; i++) {
				if(visit[s[now][i]]) continue;
				if(check[s[now][i]]) continue;
				visit[s[now][i]] = true;
				q.add(s[now][i]);
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(!visit[i]) return false;
		}
		
		return true;
	}
}
