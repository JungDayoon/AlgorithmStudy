import java.io.*;
import java.util.*;
public class _5427 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static String[][] map;
	static Queue<int[]> sangho = new LinkedList<>(); // 상호의 위치
	static Queue<int[]> fire = new LinkedList<>(); // 불의 위치
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] s;
		for(int t=0; t<T; t++) {
			if(t != 0)	sb.append("\n");
			s = br.readLine().split(" ");
			int w = Integer.parseInt(s[0]);
			int h = Integer.parseInt(s[1]);
			
			map = new String[h][w];
			visited = new boolean[h][w];
			sangho.clear();
			fire.clear();
			for(int i=0; i<h; i++) {
				map[i] = br.readLine().split("");
				for(int j=0; j<w; j++) {
					String now = map[i][j];
					if(now.equals("@")) {
						sangho.add(new int[] {i, j});
						visited[i][j] = true;
					}
					else if(now.equals("*"))	fire.add(new int[] {i, j});
				}
			}
			
			int res = solution(w, h);
			if(res == -1)	sb.append("IMPOSSIBLE");
			else	sb.append(res);
		}
		System.out.print(sb.toString());
	}
	
	private static int solution(int w, int h) {
		int time = 0;
		boolean flag = false;
		
		while(true) {
			fireSpread(w, h); // 불 먼저 퍼진다
			flag = MoveSangho(w, h);
			time++;
			if(flag)	break; // 탈출했으면 그만
			if(sangho.isEmpty()) { // 탈출 못했는데, 갈 수 있는 곳이 없으면
				time = -1;
				break;
			}
		}
		
		return time;
	}
	
	private static boolean MoveSangho(int w, int h) { // 상호 탈출했으면 true리턴
		int cnt = sangho.size();
		for(int i=0; i<cnt; i++) {
			int[] now = sangho.poll();
			int x = now[0];
			int y = now[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(!Valid(rx, ry, h, w))	return true; // 범위 벗어나면 => 탈출!
				else { // 탈출 못했다면
					if(!visited[rx][ry] && map[rx][ry].equals(".")) { // 방문하지 않은 빈공간이라면
						visited[rx][ry] = true;
						sangho.add(new int[] {rx, ry});
					}
				}
			}
		}
		return false; // 탈출 못했으면 false
	}
	private static void fireSpread(int w, int h) {
		int cnt = fire.size();
		for(int i=0; i<cnt; i++) {
			int[] now = fire.poll();
			int x = now[0];
			int y = now[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				if(Valid(rx, ry, h, w) && map[rx][ry].equals(".")) { // 범위내에 속하고, 빈공간일 경우만 => 불이 퍼진다
					map[rx][ry] = "*"; // 불은 이걸로 방문여부 체크
					fire.add(new int[] {rx, ry});
				}
			}
		}
	}
	
	private static boolean Valid(int x, int y, int h, int w) {
		return (x>=0 && x<h && y>=0 && y<w);
	}
}
