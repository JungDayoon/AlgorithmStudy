import java.io.*;
import java.util.*;
public class _1445 {
	static String[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		int sx = -1, sy = -1; // 출발지
		int ex = -1, ey = -1; // 꽃 // 도착지
		map = new String[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().split("");
			for(int j=0; j<M; j++) {
				if(map[i][j].equals("S")) {
					sx = i;
					sy = j;
				}else if(map[i][j].equals("F")) {
					ex = i;
					ey = j;
				}
			}
		}
		
		int[] res = solution(N, M, sx, sy, ex, ey);
		System.out.print(res[0] + " " + res[1]);
	}
	
	private static int[] solution(int N, int M, int sx, int sy, int ex, int ey) {
		int[][] trash = new int[N][M];
		int[][] trashAdj = new int[N][M];
		
		for(int i=0; i<N; i++) {
			Arrays.fill(trash[i], Integer.MAX_VALUE);
			Arrays.fill(trashAdj[i], Integer.MAX_VALUE);
		}
		trash[sx][sy] = 0; // 출발점은 쓰레기 0개
		trashAdj[sx][sy] = 0; // 출발지 근처 쓰레기 개수
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		pq.add(new Element(sx, sy, 0, 0));
		while(!pq.isEmpty()) {
			Element now = pq.poll();
			int x = now.x;
			int y = now.y;
			int trashCnt = now.trashCnt;
			int adjCnt = now.adjCnt;
			
			if(map[x][y].equals("F")) { // 꽃 // 도착지점이라면
				if(trash[x][y] > trashCnt) { // 더 적은 쓰레기를 거쳐왔다면
					trash[x][y] = trashCnt;
					trashAdj[x][y] = adjCnt;
				}else if(trash[x][y] == trashCnt) { // 쓰레기 지난 횟수가 같다면
					trashAdj[x][y] = Math.min(trashAdj[x][y], adjCnt); // 스쳐지나간 횟수가 작도록
				}
				continue;
			}
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N, M)) {
					int rtrashCnt = trashCnt;
					int radjCnt = adjCnt;
					if(map[rx][ry].equals("g"))		rtrashCnt++; // 그 자리가 쓰레기라면 +1
					else if(map[rx][ry].equals(".") && CheckAdjTrash(rx, ry, N, M))	
						radjCnt++; // 근처 쓰레기있으면 +1 // 비어있을 때만 인접한 칸에 쓰레기 있으면 쓰레기 옆 지나는 것!!
					
					if(trash[rx][ry] > rtrashCnt) {
						trash[rx][ry] = rtrashCnt;
						trashAdj[rx][ry] = radjCnt;
						pq.add(new Element(rx, ry, rtrashCnt, radjCnt));
					}else if(trash[rx][ry]==rtrashCnt && trashAdj[rx][ry]>radjCnt) { // 쓰레기 개수는 같은데, 인접쓰레기 개수가 적다면
						trashAdj[rx][ry] = radjCnt;
						pq.add(new Element(rx, ry, rtrashCnt, radjCnt));
					}
				}
				
			}
		}
		
		return new int[] {trash[ex][ey], trashAdj[ex][ey]};
	}
	
	private static boolean Valid(int x, int y, int N, int M) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	private static boolean CheckAdjTrash(int x, int y, int N, int M) { // (x,y)근처에 있는 쓰레기 여부
		for(int k=0; k<4; k++) {
			int rx = x + dx[k];
			int ry = y + dy[k];
			
			if(Valid(rx, ry, N, M) && map[rx][ry].equals("g"))	return true;
		}
		return false;
	}
	
	private static class Element implements Comparable<Element>{
		private int x;
		private int y;
		private int trashCnt;
		private int adjCnt;
		
		Element(int x, int y, int trashCnt, int adjCnt) {
			this.x = x;
			this.y = y;
			this.trashCnt = trashCnt;
			this.adjCnt = adjCnt;
		}
		
		@Override
		public int compareTo(Element e) { // 오름차순
			if(this.trashCnt > e.trashCnt)	return 1;
			else if(this.trashCnt < e.trashCnt)	return -1;
			else { // 지나간 쓰레기개수 같으면
				return this.adjCnt - e.adjCnt; // 인접 쓰레기칸 개수로 오름차순
			}
		}
	}
}
