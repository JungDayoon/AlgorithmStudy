import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class _1953 {
	static int[][] tunnel = {{0, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 1}, {1, 0, 0, 1}}; //터널 상우하좌 연결할수있는지 할수있으면 1, 못하면0
	static ArrayList<Integer>[] dir = new ArrayList[4];	
	static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dy = {0, 1, 0, -1}; // 상 우 하 좌
	static int locCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] result = new int[T];
		initialdir();
		
		for(int t=0; t<T; t++) {
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); // 지하 터널 지도 세로
			int M = Integer.parseInt(s[1]); // 지하 터널 지도 가로
			int R = Integer.parseInt(s[2]); // 맨홀 뚜껑위치 세로
			int C = Integer.parseInt(s[3]); // 맨홀 뚜껑위치 가로
			int L = Integer.parseInt(s[4]); // 탈출 후 소요된 시간
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
		}//테스트케이스 완료
		
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
		q.add(new int[] {R, C, 1}); //1은 현재까지의 시간
		int r, c, currTime;
		while(q.size() != 0) {
			int[] tmp = q.poll();
			r = tmp[0];
			c = tmp[1];
			currTime = tmp[2];
			if(currTime == L)	continue;
			
			//currTime하나씩 늘려가면서 큐에 넣고, 큐사이즈가 0이거나, currTime이 L이면 break
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

