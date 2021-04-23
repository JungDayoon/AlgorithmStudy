import java.io.*;
import java.util.*;
public class _19238 {
	static int fuel;
	static int[][] map;
	static Passenger[] pass;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		fuel = Integer.parseInt(s[2]);
		map = new int[N+1][N+1]; // 1~N
		pass = new Passenger[M+1]; // 1~M
		for(int i=1; i<N+1; i++) {
			s = br.readLine().split(" ");
			for(int j=1; j<N+1; j++) {
				if(Integer.parseInt(s[j-1]) == 1)
					map[i][j] = -1; // ��
			}
		}
		
		int tx=0, ty=0;
		s = br.readLine().split(" ");
		tx = Integer.parseInt(s[0]);
		ty = Integer.parseInt(s[1]);
		for(int i=1; i<M+1; i++) {
			s = br.readLine().split(" ");
			pass[i] = new Passenger(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
			map[pass[i].sx][pass[i].sy] = i;
		}
		
		System.out.print(solution(tx, ty, N, M));
	}
	private static int solution(int tx, int ty, int N, int M) {
		int res = 0;
		while(M != 0) {
			int num = FindNearestPass(tx, ty, N);
			if(num == -1) { // �̵� ���� ���� �ٴ�
				res = -1;
				break;
			}
			tx = pass[num].sx;
			ty = pass[num].sy;
			map[tx][ty] = 0;
			
			if(!CanGoToDest(tx, ty, pass[num].ex, pass[num].ey, N)) { // ���������� �� �� ���ٸ�
				res = -1;
				break;
			}
			tx = pass[num].ex;
			ty = pass[num].ey;
			M--;
		}
		
		if(res != -1)	res = fuel;
		return res;
	}
	
	private static boolean CanGoToDest(int tx, int ty, int ex, int ey, int N) { // ���������� ������ �� �ִ���
		boolean res = false;
		int D = -1;
		Queue<Element> q = new LinkedList<>();
		boolean[][] visited = new boolean[N+1][N+1];
		q.add(new Element(tx, ty, 0));
		visited[tx][ty] = true;
		
		while(!q.isEmpty()) {
			Element now = q.poll();
			int x = now.x;
			int y = now.y;
			int dist = now.dist;
			if(x==ex && y==ey) { // ���������
				D = dist;
				break;
			}
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N) && !visited[rx][ry] && map[rx][ry]!=-1) {
					visited[rx][ry] = true;
					q.add(new Element(rx, ry, dist+1));
				}
			}
		}
		
		if(D!=-1 && (fuel-D)>=0) { // ���������� �� �� �������
			res = true;
			fuel += D; // �̵��Ѹ�ŭ �����ش�
		}
		return res;
	}
	
	private static int FindNearestPass(int tx, int ty, int N) {
		int num = 0;
		int D = -1;
		PriorityQueue<Element> q = new PriorityQueue<>();
		boolean[][] visited = new boolean[N+1][N+1];
		q.add(new Element(tx, ty, 0));
		visited[tx][ty] = true;
		
		while(!q.isEmpty()) {
			Element now = q.poll();
			int x = now.x;
			int y = now.y;
			int dist = now.dist;
			
			if(map[x][y]>0) { // �°��̶��
				num = map[x][y];
				D = dist;
				break;
			}
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				if(Valid(rx, ry, N) && !visited[rx][ry] && map[rx][ry]!=-1) { // �湮���� �ʾҰ� ���� �ƴ϶��
					visited[rx][ry] = true;
					q.add(new Element(rx, ry, dist+1));
				}
			}
		}
		
		if((fuel-D)<=0 || num==0)	num = -1; // �� �� ���� (����0�����̰ų�, �ýÿ��� �� �� �ִ� �°��� ���ٰų�)
		else 	fuel -= D; // �� �� �ִ� // �̵��� ��ŭ ���� ���
		
		return num;
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>0 && x<=N && y>0 && y<=N);
	}
	
	private static class Element implements Comparable<Element>{
		private int x;
		private int y;
		private int dist;
		
		Element(int x, int y, int dist){
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Element e) {
			if(this.dist > e.dist)	return 1; // �Ÿ��� �켱 ��������
			else if(this.dist < e.dist)	return -1;
			else {
				if(this.x > e.x)	return 1; // �Ÿ������� ���ȣ�� ��������
				else if(this.x < e.x)	return -1;
				else {
					return this.y - e.y; // ���ȣ ������ ����ȣ�� ��������
				}
			}
		}
	}
	
	private static class Passenger{
		private int sx;
		private int sy;
		private int ex;
		private int ey;
		
		Passenger(int sx, int sy, int ex, int ey){
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
		}
	}
}
