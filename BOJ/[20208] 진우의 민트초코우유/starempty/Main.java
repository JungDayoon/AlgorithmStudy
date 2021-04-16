import java.io.*;
import java.util.*;

public class Main {
	static List<int[]> li;
	static int n,m,h,hx,hy,size,max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		li = new ArrayList<>(); //민초 리스트
		hx = 0; hy = 0; //진우 집
		int tmp;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				tmp = Integer.parseInt(st.nextToken());
				if(tmp != 0) {
					if(tmp == 1) {
						hx = i; hy = j;
					}else {
						li.add(new int[] {i, j});
					}
				}
			}
		}
		
		size = li.size();
		arr = new int[size];
		visit = new boolean[size];
		max = 0;
		dfs(0);
		System.out.println(max);
	}
	
	static boolean[] visit;
	static int[] arr;
	private static void dfs(int cnt) {//민트초코가 최대10개이기때문에 가능한 순열을 모두 만들어본다...
		if(cnt == size) {
			int nm = m;
			int x = hx, y = hy;
			int eat = 0;
			for(int i = 0; i < size; i++) {
				int[] cur = li.get(arr[i]);
				int cost = Math.abs(x-cur[0])+Math.abs(y-cur[1]);
				int toHome = Math.abs(cur[0]-hx)+Math.abs(cur[1]-hy);
				
				if(nm >= cost) {
					eat++; //갈 수 있으면 일단 ㄱ
					nm -= cost;
					nm += h;
					if(nm >= toHome) { //집으로 갈 수 있으면 일단 ㄱ
						max = Integer.max(max, eat);
					}
					x = cur[0]; y = cur[1]; //다른 민초에게도 가보기
				}else { //거리가 안닿으면 그만하기
					return;
				}
			}
			return;
		}
		for(int i = 0; i < size; i++) { //기본적인 순열 코드
			if(visit[i]) continue;
			visit[i] = true;
			arr[cnt] = i;
			dfs(cnt+1);
			visit[i] = false;
		}
	}
}