import java.io.*;
import java.util.*;

public class _2206 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		int[][] map = new int[N][M];
		int[][][] visited = new int[N][M][2];
		for(int i=0; i<N; i++) {
			s = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				Arrays.fill(visited[i][j], -1); // -1�� �湮���� �ʾ��� ��, �� ���� 0�� �ڿ����� (0,0)������ �Ÿ�
			}
		}
		
		System.out.print(BFS(N, M, map, visited));
	}
	
	private static boolean Valid(int x, int y, int N, int M) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	private static int BFS(int N, int M, int[][] map, int[][][] visited) {
		Queue<int[]> q = new LinkedList<>(); // x, y, �� �μ� ����(0: �Ⱥμ�/ 1: �μ�)
		q.add(new int[] {0, 0, 0});
		visited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int breakWall = now[2];
			int dist = visited[x][y][breakWall]; //�� ��ġ������ �Ÿ�
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N, M) && visited[rx][ry][breakWall]==-1) {
					if(map[rx][ry] == 0) { // ������̸�
						q.add(new int[] {rx, ry, breakWall});
						visited[rx][ry][breakWall] = dist+1;
					}else { //���� ������
						if(breakWall == 0) { //�μ����� ������
							q.add(new int[] {rx, ry, breakWall+1});
							visited[rx][ry][breakWall+1] = dist+1; // �μ���
						}
						// �μ��� ������ �� ��ġ�� �� �� ����.
					}
				}				
			}
		}
		
		int no = visited[N-1][M-1][0]; // ���� �μ��� �ʾ��� ��
		int yes = visited[N-1][M-1][1]; // �ν��� ��
		
		if(no==-1 && yes==-1)
			return -1;
		else if(no==-1)
			return yes;
		else if(yes==-1)
			return no;
		else
			return Math.min(no, yes);
	}
}
