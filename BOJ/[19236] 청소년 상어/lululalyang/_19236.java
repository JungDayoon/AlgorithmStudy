import java.io.*;
import java.util.*;
public class _19236 {
	static int Max = 0; // �� ���� �� �ִ� ����� ��ȣ�� �� �ִ밪
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
		
		int sum = map[0][0]; // ��Ƹ����� ����� ��ȣ
		boolean[] live = new boolean[17];
		Arrays.fill(live, true);
		live[sum] = false;
		
		BackTracking(0, 0, fish[sum].dir, sum, fish, live);
		System.out.print(Max);
	}
	
	private static void BackTracking(int sx, int sy, int sd, int sum, Fish[] prev, boolean[] live) { // (sx, sy): ����� ��ġ // sd:����� ���� // sum:�̶����� ���� ����� ��ȣ�� ��
		int[][] map = new int[4][4]; // ���� ����ִ� ����⸸ �ִ�
		Fish[] fish = new Fish[17];
		for(int i=1; i<17; i++) {
			if(live[i]) { // ���������
				fish[i] = prev[i].clone();
				map[fish[i].x][fish[i].y] = i;
			}
		}
		
		MoveFish(sx, sy, map, fish, live); // ����� �̵�
		
		ArrayList<Integer> CanGo = new ArrayList<>();
		int rx = sx + dx[sd];
		int ry = sy + dy[sd];
		while(Valid(rx, ry)) {
			int fishNum = map[rx][ry];
			if(fishNum>0 && live[fishNum]) 	CanGo.add(fishNum); // ����ִ� �����
			rx += dx[sd];
			ry += dy[sd];
		}
		
		if(CanGo.isEmpty()) { // �� �� �ִ� ĭ�� ���ٸ�
			Max = Math.max(Max, sum);
			return;
		}
		
		for(Integer i : CanGo) { // i:��Ƹ��� �� �ִ� ����� ��ȣ
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
	
	private static void MoveFish(int sx, int sy, int[][] map, Fish[] fish, boolean[] live) { // ����� �̵� // (sx, sy): ����� ��ġ
		for(int i=1; i<=16; i++) { // i�� ���� ����� ��ȣ
			if(!live[i])	continue; // �̹� ���� ������� �н�
			int x = fish[i].x;
			int y = fish[i].y;
			int dir = fish[i].dir; // ���� ������� ����
			
			
			boolean flag = false;
			int rx = 0, ry = 0;
			for(int k=0; k<8; k++) {
				rx = x + dx[dir];
				ry = y + dy[dir];
				
				if(rx!=sx || ry!=sy) { // ����ڸ��� �ƴϰ�
					if(Valid(rx, ry)) { // ���� ����� => �� �� �ִ� ĭ( ��ĭ�̳� ����� ĭ)
						flag = true;
						break;
					}
				}
				dir = (dir+1) > 8? 1: dir+1;
			}
			
			if(flag) { // �� �� �ִ� ĭ ã���� => ��ĭ or �����ĭ // 
				int num = map[rx][ry];
				if(num > 0) { // ����� ĭ �̶�� -> �� ����⸦ ���� ����� �ڸ���
					fish[num].x = x;
					fish[num].y = y;
					map[x][y] = num;
				}else { // ��ĭ�̶��
					map[x][y] = 0; // ���� �ڸ� ����ֱ�
				}
				// ��ĭ�̵� �����ĭ�̵� ���� ������� ��ġ�� ������ �ٲ��ش�
				fish[i].x = rx; 
				fish[i].y = ry;
				fish[i].dir = dir; // ȸ���������� �����ϱ�
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
