import java.io.*;
import java.util.*;
public class _16236 {
	static int[][] map;
	static Shark shark;
	static int[] dx = {-1, 0, 0, 1}; // 상, 좌, 우, 하
	static int[] dy = {0, -1, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] s;
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
					map[i][j] = 0; // 빈공간으로
				}
			}
		}
		
		System.out.print(solution(N));
	}
	
	private static int solution(int N) {
		int time = 0;
		boolean[][] visited;
		PriorityQueue<Element> q = new PriorityQueue<>(); // 위치 좌표, 이동거리
		while(true) {
			q.clear();
			visited = new boolean[N][N];
			q.add(new Element(shark.x, shark.y, 0));
			visited[shark.x][shark.y] = true;
			
			int fx=-1, fy=-1, fd=-1;
			while(!q.isEmpty()) { // bfs
				Element now = q.poll();
				int x = now.x;
				int y = now.y;
				int d = now.d;
				
				if(map[x][y]<shark.size && map[x][y]>0) { // 상어가 먹을 수 있는 물고기라면
					fx = x;
					fy = y;
					fd = d;
					break;
				}
				
				for(int k=0; k<4; k++) {
					int rx = x + dx[k];
					int ry = y + dy[k];
					
					if(Valid(rx, ry, N) && map[rx][ry]<=shark.size && !visited[rx][ry]) { // 범위 내이고, 상어크기와 같거나 작은 물고기칸, 또는 빈칸
						q.add(new Element(rx, ry, d+1));
						visited[rx][ry] = true;
					}
				}
			}
			
			if(fx == -1) { // 먹을 수 있는 물고기를 찾지 못했다면
				break;
			}else { // 물고기 찾았다면
				time += fd;
				map[fx][fy] = 0; // 물고기 없애고
				shark.x = fx;
				shark.y = fy; // 상어 옮긴다
				
				shark.eatCnt++;
				if(shark.eatCnt == shark.size) { // 잡아먹은 개수가 크기와 같다면
					shark.size++;
					shark.eatCnt = 0;
				}
			}
		}
		
		return time;
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	private static class Element implements Comparable<Element>{
		private int x;
		private int y;
		private int d;
		
		Element(int x, int y, int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		@Override
		public int compareTo(Element e) {
			if(this.d > e.d)	return 1; // 거리 가까운 순
			else if(this.d < e.d)	return -1;
			else {
				if(this.x > e.x)	return 1; // 위쪽 먼저
				else if(this.x < e.x)	return -1; 
				else {
					return this.y - e.y;
				}
			}
		}
	}
	private static class Shark{
		private int x;
		private int y; 
		private int size;
		private int eatCnt;
		
		Shark(int x, int y, int size, int eatCnt){
			this.x = x;
			this.y = y;
			this.size = size;
			this.eatCnt = eatCnt;
		}
	}
}
