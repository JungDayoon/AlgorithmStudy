import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class _1953 {
	static int[][] tunnel = {{0, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 1}, {1, 0, 0, 1}}; //�ͳ� ������� �����Ҽ��ִ��� �Ҽ������� 1, ���ϸ�0
	static ArrayList<Integer>[] dir = new ArrayList[4];	
	static int[] dx = {-1, 0, 1, 0}; // �� �� �� ��
	static int[] dy = {0, 1, 0, -1}; // �� �� �� ��
	static int locCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] result = new int[T];
		initialdir();
		
		for(int t=0; t<T; t++) {
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); // ���� �ͳ� ���� ����
			int M = Integer.parseInt(s[1]); // ���� �ͳ� ���� ����
			int R = Integer.parseInt(s[2]); // ��Ȧ �Ѳ���ġ ����
			int C = Integer.parseInt(s[3]); // ��Ȧ �Ѳ���ġ ����
			int L = Integer.parseInt(s[4]); // Ż�� �� �ҿ�� �ð�
			locCnt = 1;

			int[][] map = new int[N][M];
			boolean[][] visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				String[] d = br.readLine().split(" ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(d[j]);
				}
			}
			
			visited[R][C] = true;
			bfs(map, visited, R, C, L);
			
			result[t] = locCnt;
		}//�׽�Ʈ���̽� �Ϸ�
		
		for(int t=0; t<T; t++)
			System.out.println("#"+(t+1)+" "+result[t]);
	}
	
	static void initialdir()
	{
		for(int i=0; i<4; i++) {
			dir[i] = new ArrayList<Integer>();
		}
		dir[0].add(1); dir[0].add(2); dir[0].add(5); dir[0].add(6);
		dir[1].add(1); dir[1].add(3); dir[1].add(6); dir[1].add(7);
		dir[2].add(1); dir[2].add(2); dir[2].add(4); dir[2].add(7);
		dir[3].add(1); dir[3].add(3); dir[3].add(4); dir[3].add(5);
		
	}
	
	static void bfs(int[][] map, boolean[][] visited, int R, int C, int L) {
		LinkedList<int []> q = new LinkedList<int []>();
		q.add(new int[] {R, C, 1}); //1�� ��������� �ð�
		int r, c, currTime;
		while(q.size() != 0) {
			int[] tmp = q.poll();
			r = tmp[0];
			c = tmp[1];
			currTime = tmp[2];
			if(currTime == L)	continue;
			
			//currTime�ϳ��� �÷����鼭 ť�� �ְ�, ť����� 0�̰ų�, currTime�� L�̸� break
			for(int m=0; m<4; m++) {
				int rx = r + dx[m];
				int ry = c + dy[m];
				if(rx>=0 && rx<map.length && ry>=0 && ry<map[0].length) {
					if(tunnel[map[r][c]][m]==1 && dir[m].contains(map[rx][ry])) {
						if(!visited[rx][ry]) {
							visited[rx][ry] = true;
							locCnt++;
							q.add(new int[] {rx, ry, (currTime+1)});
						}
					}
				}
			}
		}
	}
	
}

