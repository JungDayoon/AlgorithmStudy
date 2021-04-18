import java.io.*;
import java.util.*;
public class _16236 {
	static int[][] map;
	static Shark shark;
	static int[] dx = {-1, 0, 0, 1}; // ��, ��, ��, ��
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
					map[i][j] = 0; // ���������
				}
			}
		}
		
		System.out.print(solution(N));
	}
	
	private static int solution(int N) {
		int time = 0;
		boolean[][] visited;
		PriorityQueue<Element> q = new PriorityQueue<>(); // ��ġ ��ǥ, �̵��Ÿ�
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
				
				if(map[x][y]<shark.size && map[x][y]>0) { // �� ���� �� �ִ� �������
					fx = x;
					fy = y;
					fd = d;
					break;
				}
				
				for(int k=0; k<4; k++) {
					int rx = x + dx[k];
					int ry = y + dy[k];
					
					if(Valid(rx, ry, N) && map[rx][ry]<=shark.size && !visited[rx][ry]) { // ���� ���̰�, ���ũ��� ���ų� ���� �����ĭ, �Ǵ� ��ĭ
						q.add(new Element(rx, ry, d+1));
						visited[rx][ry] = true;
					}
				}
			}
			
			if(fx == -1) { // ���� �� �ִ� ����⸦ ã�� ���ߴٸ�
				break;
			}else { // ����� ã�Ҵٸ�
				time += fd;
				map[fx][fy] = 0; // ����� ���ְ�
				shark.x = fx;
				shark.y = fy; // ��� �ű��
				
				shark.eatCnt++;
				if(shark.eatCnt == shark.size) { // ��Ƹ��� ������ ũ��� ���ٸ�
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
			if(this.d > e.d)	return 1; // �Ÿ� ����� ��
			else if(this.d < e.d)	return -1;
			else {
				if(this.x > e.x)	return 1; // ���� ����
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
