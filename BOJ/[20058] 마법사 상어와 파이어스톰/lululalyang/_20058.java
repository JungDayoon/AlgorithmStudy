import java.io.*;
import java.util.*;
public class _20058 {
	static int[][] map;
	static int[] L;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int Q = Integer.parseInt(s[1]);
		N = (int) Math.pow(2, N); // 실제 가로세로 길이로
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		L = new int[Q];
		s = br.readLine().split(" ");
		for(int i=0; i<Q; i++)	L[i] = Integer.parseInt(s[i]);
		
		solution1(N, Q);
		System.out.println(Sum(N));
		System.out.print(solution2(N));
	}
	
	private static int solution2(int N) { // 가장 큰 덩어리의 칸 개수
		int max = 0;
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]!=0 && !visited[i][j]) {
					max = Math.max(bfs(i, j, visited, N), max);
				}
			}
		}
		
		return max;
	}
	
	private static int bfs(int i, int j, boolean[][] visited, int N) {
		int cnt = 0;
		visited[i][j] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			cnt++;
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N) && map[rx][ry]!=0 && !visited[rx][ry]) {
					q.add(new int[] {rx, ry});
					visited[rx][ry] = true;
				}
			}
		}
		
		return cnt;
	}
	
	private static int Sum(int N) {
		int remain = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				remain += map[i][j];	
		}
		return remain;
	}
	
	private static void solution1(int N, int Q){
		for(int q=0; q<Q; q++) {
			int n = (int)Math.pow(2, L[q]); // 부분격자의 가로세로 길이
			for(int i=0; i<N; i+=n) { // 각 부분격자 회전
				for(int j=0; j<N; j+=n) { // (i,j)가 시작점, n이 변 길이
					RotateOnePart(i, j, n);					
				}
			}
			
			ChkAdjThree(N);
		}
	}
	
	private static void ChkAdjThree(int N) {
		boolean[][] chk = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = 0;
				for(int k=0; k<4; k++) {
					int rx = i + dx[k];
					int ry = j + dy[k];
					if(Valid(rx, ry, N) && map[rx][ry]!=0)	cnt++; // 얼음칸 개수 확인
				}
				
				if(cnt < 3)	chk[i][j] = true;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(chk[i][j] && map[i][j]!=0)	
					map[i][j]--; // 얼음 1 녹인다
			}
		}
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	private static void RotateOnePart(int i, int j, int n) {
		int thisn = n;
		for(int x=i, y=j; x<i+(n/2) && y<j+(n/2); x++, y++) { // (x, y)가 시작
			RotateOneLine(x, y, thisn);
			thisn -= 2;
		}
	}
	
	private static void RotateOneLine(int x, int y, int n) {
		int[][] tmp = new int[4][n];
		int idx = 0;
		for(int j=y; j<y+n; j++)	tmp[0][idx++] = map[x][j];
		idx = 0;
		for(int i=x; i<x+n; i++)	tmp[1][idx++] = map[i][y+n-1];
		idx = 0;
		for(int j=y+n-1; j>=y; j--)	tmp[2][idx++] = map[x+n-1][j];
		idx = 0;
		for(int i=x+n-1; i>=x; i--)	tmp[3][idx++] = map[i][y];
		// tmp로 옮겨두고
		idx = 0;
		for(int j=y; j<y+n; j++)	map[x][j] = tmp[3][idx++];
		idx = 0;
		for(int i=x; i<x+n; i++)	map[i][y+n-1] = tmp[0][idx++];
		idx = 0;
		for(int j=y+n-1; j>=y; j--)	map[x+n-1][j] = tmp[1][idx++];
		idx = 0;
		for(int i=x+n-1; i>=x; i--)	map[i][y] = tmp[2][idx++];
	}
}
