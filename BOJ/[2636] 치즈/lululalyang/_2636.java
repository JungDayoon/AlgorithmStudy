import java.io.*;
import java.util.*;
public class _2636 {
	static int[][] cheese;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // ����
		int M = Integer.parseInt(s[1]); // ����
		cheese = new int[N][M];
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				cheese[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		int[] res = solution(N, M);
		System.out.print(res[0] + "\n" + res[1]);
	}
	
	private static int[] solution(int N, int M) {
		int time = 0;
		int cnt = 0;
		
		while(ChkRemain(N, M)) {
			boolean[][] melt = new boolean[N][M];
			cnt = ChkMelt(N, M, melt);
			
			for(int i=1; i<N-1; i++) {
				for(int j=1; j<M-1; j++) {
					if(cheese[i][j]==1 && melt[i][j])	cheese[i][j] = 0;
				}
			}
			time++;
		}
		
		return new int[] {time, cnt};
	}
	
	private static int ChkMelt(int N, int M, boolean[][] melt) {
		int cnt = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];

			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N, M) && !melt[rx][ry]) {
					melt[rx][ry] = true; // �湮���� üũ
					if(cheese[rx][ry]==0)  q.add(new int[] {rx, ry}); // ������̸�
					else	cnt++; // ġ���
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean Valid(int x, int y, int N, int M) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	private static boolean ChkRemain(int N, int M) {
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<M-1; j++) {
				if(cheese[i][j] == 1)	return true; // ġ�� ���������� true
			}
		}
		return false; // ������ false
	}
}
