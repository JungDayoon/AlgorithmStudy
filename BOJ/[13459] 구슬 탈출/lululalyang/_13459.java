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
			
			if(cnt == 10)	break; // 11ȸ �̻���ʹ� Ż�� X
			
			boolean flag = false;
			for(int d=0; d<4; d++) {
				Marble R = new Marble(Rx, Ry, false, 0);
				Marble B = new Marble(Bx, By, false, 0);
				
				MoveMarble(R, d);
				MoveMarble(B, d);
				
				if(R.x ==B.x && R.y==B.y) { // ���� ������
					int prevX = R.x;
					int prevY = R.y;
					if(map[prevX][prevY].equals("O"))	continue; // �� ��ǥ ������, �� ��ġ�� �����̸� => �Ѵ� ���������� �н� 
					
					AdjustRB(R, B, d); // �� ��ġ ����
					
					if(Rx==R.x && Ry==R.y && Bx==B.x && By==B.y) // ���� ������ ��ǥ�� �̵��� ��ǥ�� ������ �н�
						continue;
					
					if(R.x==prevX && R.y==prevY) { // B�� �ٲ� ��
						B.moveCnt -= 1;
						if(map[prevX][prevY].equals("O"))
							B.escape = false;
					}else { // R�� �ٲ� ��
						R.moveCnt -= 1;
						if(map[prevX][prevY].equals("O"))
							R.escape = false;
					}
				}
				
				if(B.escape || (R.moveCnt==0 && B.moveCnt==0)) { // B�� Ż���߰ų�, �Ѵ� �������� �ʾ��� ���� �н�
					continue;
				}else {
					if(R.escape) { // R�� Ż������ �� (B�� Ż������ ���� �� ���ǹ����� �ɸ���)
						flag = true;
						break;
					}else { // �� �� Ż������ �ʾ��� �� || �ϳ��� �������� ��					
						q.add(new TwoMarbles(R.x, R.y, B.x, B.y, cnt+1));
					}
				}
			}
			
			if(flag) { // R�� Ż�� 
				res = 1; 
				break;
			}
		}
		
		return res;
	}
	
	private static void AdjustRB(Marble R, Marble B, int dir) {
		if(R.moveCnt > B.moveCnt) { // R�� �� �ڿ� �־��� ��
			if(dir==1 || dir==3) {
				R.y -= dy[dir];
			}else { // dir==0 || dir==2
				R.x -= dx[dir];
			}
		}else { // B�� �� �ڿ� �־��� ��
			if(dir==1 || dir==3) {
				B.y -= dy[dir];
			}else { // dir==0 || dir==2
				B.x -= dx[dir];
			}
		}
	}
	
	private static void MoveMarble(Marble now, int d) {
		while(!map[now.x+dx[d]][now.y+dy[d]].equals("#")) { // ��ֹ��� �ƴ� �� ����
			now.x += dx[d]; // ��ֹ� �ƴϸ� �̵�
			now.y += dy[d];
			
			now.moveCnt++;
			
			if(map[now.x][now.y].equals("O")) { // �����̶��
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
