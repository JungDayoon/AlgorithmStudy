import java.io.*;
import java.util.*;

public class _13459 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static String[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new String[N][M];
		
		int[] R = new int[2];
		int[] B = new int[2];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().split("");
			for(int j=0; j<M; j++) {
				if(map[i][j].equals("R")) {
					R[0] = i;
					R[1] = j;
					map[i][j] = ".";
				}else if(map[i][j].equals("B")) {
					B[0] = i;
					B[1] = j;
					map[i][j] = ".";
				}
			}
		}
		Queue<TwoMarbles> q = new LinkedList<>();
		q.add(new TwoMarbles(R[0], R[1], B[0], B[1], 0));
		System.out.print(CanEscape(q));
	}
	
	private static int CanEscape(Queue<TwoMarbles> q) {
		int res = 0; 
		while(!q.isEmpty()) {
			TwoMarbles now = q.poll();
			int Rx = now.Rx;
			int Ry = now.Ry;
			int Bx = now.Bx;
			int By = now.By;
			int cnt = now.cnt;
			
			if(cnt == 10)	break; // 11회 이상부터는 탈출 X
			
			boolean flag = false;
			for(int d=0; d<4; d++) {
				Marble R = new Marble(Rx, Ry, false, 0);
				Marble B = new Marble(Bx, By, false, 0);
				
				MoveMarble(R, d);
				MoveMarble(B, d);
				
				if(R.x ==B.x && R.y==B.y) { // 만약 같으면
					int prevX = R.x;
					int prevY = R.y;
					if(map[prevX][prevY].equals("O"))	continue; // 둘 좌표 같은데, 그 위치가 구멍이면 => 둘다 빠진것으로 패스 
					
					AdjustRB(R, B, d); // 둘 위치 조정
					
					if(Rx==R.x && Ry==R.y && Bx==B.x && By==B.y) // 만약 조정한 좌표가 이동전 좌표와 같으면 패스
						continue;
					
					if(R.x==prevX && R.y==prevY) { // B가 바뀐 것
						B.moveCnt -= 1;
						if(map[prevX][prevY].equals("O"))
							B.escape = false;
					}else { // R이 바뀐 것
						R.moveCnt -= 1;
						if(map[prevX][prevY].equals("O"))
							R.escape = false;
					}
				}
				
				if(B.escape || (R.moveCnt==0 && B.moveCnt==0)) { // B가 탈출했거나, 둘다 움직이지 않았을 때는 패스
					continue;
				}else {
					if(R.escape) { // R만 탈출했을 때 (B가 탈출했을 경우는 위 조건문에서 걸린다)
						flag = true;
						break;
					}else { // 둘 다 탈출하지 않았을 때 || 하나만 움직였을 때					
						q.add(new TwoMarbles(R.x, R.y, B.x, B.y, cnt+1));
					}
				}
			}
			
			if(flag) { // R이 탈출 
				res = 1; 
				break;
			}
		}
		
		return res;
	}
	
	private static void AdjustRB(Marble R, Marble B, int dir) {
		if(R.moveCnt > B.moveCnt) { // R이 더 뒤에 있었을 떄
			if(dir==1 || dir==3) {
				R.y -= dy[dir];
			}else { // dir==0 || dir==2
				R.x -= dx[dir];
			}
		}else { // B가 더 뒤에 있었을 때
			if(dir==1 || dir==3) {
				B.y -= dy[dir];
			}else { // dir==0 || dir==2
				B.x -= dx[dir];
			}
		}
	}
	
	private static void MoveMarble(Marble now, int d) {
		while(!map[now.x+dx[d]][now.y+dy[d]].equals("#")) { // 장애물이 아닐 때 까지
			now.x += dx[d]; // 장애물 아니면 이동
			now.y += dy[d];
			
			now.moveCnt++;
			
			if(map[now.x][now.y].equals("O")) { // 구멍이라면
				now.escape = true;
				return;
			}
		}
	}
	
	private static class Marble {
		int x;
		int y;
		boolean escape;
		int moveCnt;
		
		Marble(int x, int y, boolean escape, int moveCnt) {
			this.x = x;
			this.y = y;
			this.escape = escape;
			this.moveCnt = moveCnt;
		}
	}
	
	private static class TwoMarbles {
		int Rx;
		int Ry;
		int Bx;
		int By;
		int cnt;
		
		TwoMarbles(int Rx, int Ry, int Bx, int By, int cnt){
			this.Rx = Rx;
			this.Ry = Ry;
			this.Bx = Bx;
			this.By = By;
			this.cnt = cnt;
		}
	}
}
