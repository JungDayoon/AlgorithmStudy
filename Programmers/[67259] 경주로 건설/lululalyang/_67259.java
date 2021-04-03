import java.io.*;
import java.util.*;
public class _67259 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		System.out.print(solution(board));
	}
	
	private static int solution(int[][] board) {
		int res = Integer.MAX_VALUE;
		int N = board.length;
		
		Queue<Cost> q = new LinkedList<>(); // r, c, �̶����� �� ���, ��������
		q.add(new Cost(0, 0, 0, -1)); 
		board[0][0] = 1; // Ž������ ����
		
		while(!q.isEmpty()) {
			Cost now = q.poll();
			int x = now.x;
			int y = now.y;
			int cost = now.cost;
			int prevD = now.prevD;
			
			if(x==(N-1) && y==(N-1)) {
				res = Math.min(res, cost);
				continue;
			}
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(N, rx, ry) && board[rx][ry]!=1) { // ���� �ƴ� ��
					int rcost = 0;
					if(prevD==-1 || prevD==k)	rcost = cost + 100; // ù �̵��̰ų�, �������ζ��
					else if(prevD != k)	rcost = cost + 600; // ���� ����� �ٸ���, �ڳ� => 100+500
					
					if(board[rx][ry] == 0) { // ó�� ���� ���̶��
						board[rx][ry] = rcost;
						q.add(new Cost(rx, ry, rcost, k));
					}else if(board[rx][ry] >= rcost) { // �湮�ߴ� ���̶��, ����� ���۰ų� ���� ����� �� q�� �߰����ش�
						board[rx][ry] = rcost;
						q.add(new Cost(rx, ry, rcost, k));
					}
				}
			}
		}
		
		return res;
	}
	
	private static boolean Valid(int N, int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	private static class Cost   {
		private int x;
		private int y;
		private int cost;
		private int prevD;
		
		Cost(int x, int y, int cost, int prevD){
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.prevD = prevD;
		}
	}
}
