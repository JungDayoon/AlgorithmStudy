import java.io.*;
import java.util.*;
public class _15686 {
	static int Min = Integer.MAX_VALUE; // ������ ġŲ �Ÿ��� �ּڰ�
	static int[][] map;
	static ArrayList<int[]> chicken = new ArrayList<>(); // �� ġŲ���� ��ġ ��ǥ
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if(map[i][j] == 2)	chicken.add(new int[] {i, j});	
			}
		}
		
		solution(N, M);
		System.out.print(Min);
	}

	private static void solution(int N, int M) {
		ArrayList<Integer> tmp = new ArrayList<>();
		int cSize = chicken.size();
		Comb(cSize, cSize-M, 0, tmp);
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			CloseOrOpenStore(tmp, 0); // ����
			ComputeMinDist();
			CloseOrOpenStore(tmp, 2); // �ٽ� ����
			return;
		}
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
	
	private static void ComputeMinDist() { // bfs�� �� �� �ּҰŸ� ���ϱ�
		int N = map.length;
		int distSum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) { // ���̸�
					distSum += bfs(i, j, N);
				}
			}
		}
		
		Min = Math.min(Min, distSum); // ������ ġŲ �Ÿ� ����
	}
	
	private static int bfs(int i, int j, int N) { // (i, j)���� ġŲ �Ÿ��� ���Ѵ�
		int res = -1; // ġŲ�Ÿ�
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j, 0});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int dist = now[2];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N) && !visited[rx][ry]) {
					if(map[rx][ry] == 2) {
						res = dist+1;
						break;
					}
					visited[rx][ry] = true;
					q.add(new int[] {rx, ry, dist+1});
				}
			}
			if(res != -1)	break;
		}
		
		return res;
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	private static void CloseOrOpenStore(ArrayList<Integer> tmp, int flag) {
		for(Integer i : tmp) {
			int[] now = chicken.get(i);
			map[now[0]][now[1]] = flag; // ���� or ����
		}
	}
}
