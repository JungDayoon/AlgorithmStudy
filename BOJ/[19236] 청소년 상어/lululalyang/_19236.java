import java.io.*;
import java.util.*;
public class _19236 {
	static int Max = 0; // 상어가 먹을 수 있는 물고기 번호의 합 최대값
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1}; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[] fish = new Fish[17];
		int[][] map = new int[4][4];
		String[] s;
		for(int i=0; i<4; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<=6; j+=2) {
				int num = Integer.parseInt(s[j]);
				int dir = Integer.parseInt(s[j+1]);
				fish[num] = new Fish(i, j/2, dir);
				map[i][j/2] = num;
			}
		}
		
		int sum = map[0][0]; // 잡아먹히는 물고기 번호
		boolean[] live = new boolean[17];
		Arrays.fill(live, true);
		live[sum] = false;
		
		BackTracking(0, 0, fish[sum].dir, sum, fish, live);
		System.out.print(Max);
	}
	
	private static void BackTracking(int sx, int sy, int sd, int sum, Fish[] prev, boolean[] live) { // (sx, sy): 상어의 위치 // sd:상어의 방향 // sum:이때가지 먹은 물고기 번호의 합
		int[][] map = new int[4][4]; // 현재 살아있는 물고기만 있다
		Fish[] fish = new Fish[17];
		for(int i=1; i<17; i++) {
			if(live[i]) { // 살아있으면
				fish[i] = prev[i].clone();
				map[fish[i].x][fish[i].y] = i;
			}
		}
		
		MoveFish(sx, sy, map, fish, live); // 물고기 이동
		
		ArrayList<Integer> CanGo = new ArrayList<>();
		int rx = sx + dx[sd];
		int ry = sy + dy[sd];
		while(Valid(rx, ry)) {
			int fishNum = map[rx][ry];
			if(fishNum>0 && live[fishNum]) 	CanGo.add(fishNum); // 살아있는 물고기
			rx += dx[sd];
			ry += dy[sd];
		}
		
		if(CanGo.isEmpty()) { // 갈 수 있는 칸이 없다면
			Max = Math.max(Max, sum);
			return;
		}
		
		for(Integer i : CanGo) { // i:잡아먹을 수 있는 물고기 번호
			sx = fish[i].x;
			sy = fish[i].y;
			sd = fish[i].dir;
			live[i] = false;
			
			BackTracking(sx, sy, sd, sum+i, fish, live);
			
			live[i] = true;
		}
	}
	
//	private static void prevToFish(Fish[] prev) {
//		for(int i=1; i<17; i++) {
//			fish[i].x = prev[i].x;
//			fish[i].y = prev[i].y;
//			fish[i].dir = prev[i].dir;
//		} 	
//	}
	
	private static void MoveFish(int sx, int sy, int[][] map, Fish[] fish, boolean[] live) { // 물고기 이동 // (sx, sy): 상어의 위치
		for(int i=1; i<=16; i++) { // i가 현재 물고기 번호
			if(!live[i])	continue; // 이미 죽은 물고기라면 패스
			int x = fish[i].x;
			int y = fish[i].y;
			int dir = fish[i].dir; // 현재 물고기의 방향
			
			
			boolean flag = false;
			int rx = 0, ry = 0;
			for(int k=0; k<8; k++) {
				rx = x + dx[dir];
				ry = y + dy[dir];
				
				if(rx!=sx || ry!=sy) { // 상어자리가 아니고
					if(Valid(rx, ry)) { // 범위 내라면 => 갈 수 있는 칸( 빈칸이나 물고기 칸)
						flag = true;
						break;
					}
				}
				dir = (dir+1) > 8? 1: dir+1;
			}
			
			if(flag) { // 갈 수 있는 칸 찾으면 => 빈칸 or 물고기칸 // 
				int num = map[rx][ry];
				if(num > 0) { // 물고기 칸 이라면 -> 그 물고기를 현재 물고기 자리로
					fish[num].x = x;
					fish[num].y = y;
					map[x][y] = num;
				}else { // 빈칸이라면
					map[x][y] = 0; // 원래 자리 비워주기
				}
				// 빈칸이든 물고기칸이든 현재 물고기의 위치와 방향을 바꿔준다
				fish[i].x = rx; 
				fish[i].y = ry;
				fish[i].dir = dir; // 회전했을수도 있으니까
				map[rx][ry] = i;
			}
		}
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<4 && y>=0 && y<4);
	}
	
	private static class Fish{
		int x;
		int y; 
		int dir;
		
		Fish(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		public String toString() {
			return "("+x+","+y+") - "+dir;
		}
		
		public Fish clone() {
			return new Fish(this.x, this.y, this.dir);
		}
	}
}
