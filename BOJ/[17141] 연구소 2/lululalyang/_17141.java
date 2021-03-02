import java.io.*;
import java.util.*;

public class _17141 {
	static int[][] map;
	static int res = Integer.MAX_VALUE;
	static ArrayList<VirusPos> AllVirusPos = new ArrayList<>();
	static int VirusPosCnt;
	static int N, M;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 1) { // 벽이면
					map[i][j] = -1;
				}else if(map[i][j] == 2) { // 바이러스 자리면
					AllVirusPos.add(new VirusPos(i, j)); // 바이러스공간 좌표 저장
					map[i][j] = 0; // 빈공간으로
				}
			}
		}
		
		VirusPosCnt = AllVirusPos.size();
		ArrayList<Integer> tmp = new ArrayList<>();
		SelectVirusPos(VirusPosCnt, M, 0, tmp);
		if(res != Integer.MAX_VALUE)
			System.out.print(res);
		else
			System.out.print("-1");
	}
	
	private static void SelectVirusPos(int n, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			// tmp에 있는 인덱스에 해당하는 자리에 바이러스 놓고 확인
			ComputeTime(tmp);
			return;
		}
		
		for(int i=start; i<n; i++) {
			tmp.add(i);
			SelectVirusPos(n, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
	
	private static void CloneArr(int[][] a, int[][] b) { // b to a
		for(int i=0; i<N; i++) {
			a[i] = b[i].clone();
		}
	}
	
	private static void ComputeTime(ArrayList<Integer> tmp) {
		int[][] visited = new int[N][N];
		CloneArr(visited, map);
		
		Queue<int[]> q = new LinkedList<>();
		Iterator<Integer> itr = tmp.iterator();
		while(itr.hasNext()) {
			int idx = itr.next();
			VirusPos now = AllVirusPos.get(idx);
			visited[now.x][now.y] = 1; // 바이러스 놓기
			q.add(new int[] {now.x, now.y, 0});
		}
		
		int max = 0;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int time = now[2];
			max = Math.max(max, time);
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry) && visited[rx][ry]==0) {
					q.add(new int[] {rx, ry, time+1});
					visited[rx][ry] = time+1;
				}
			}
		}
		
		if(ChkAllInfected(visited))
			res = Math.min(res, max);
	}
	
	private static boolean ChkAllInfected(int[][] visited) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	private static class VirusPos{
		int x;
		int y;
		
		VirusPos(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return x + " " + y;
		}
	}
}
