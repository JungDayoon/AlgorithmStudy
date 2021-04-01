import java.io.*;
import java.util.*;

public class _16234 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int L = Integer.parseInt(s[1]);
		int R = Integer.parseInt(s[2]);
		
		int[][] A = new int[N][N];
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				A[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		System.out.print(solution(N, L, R, A));
	}
	
	private static boolean Valid(int N, int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}
	
	private static int ChkAdj(int N, int[][] A, int[][] visited, int r, int c, int united, int L, int R) {
		int pop = 0; // ���յ� ������ �� �α���
		int cnt = 0; // ���յ� ������ ����
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {r, c});
		visited[r][c] = united;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int nowPop = A[x][y]; // ���� ������ �α���
			
			pop += A[x][y];
			cnt++;
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(N, rx, ry) && visited[rx][ry]==0) { // �湮���� ���� ��
					int diff = Math.abs(nowPop - A[rx][ry]); // �� ������ �α��� ��
					if(diff>=L && diff<=R) { // �α����� L�̻� R�����̸�
						q.add(new int[] {rx, ry});
						visited[rx][ry] = united;
					}
				}
			}
		}
		
		if(cnt == 1)	return -1; // ���յ� ���� ����
		else	return pop/cnt;
	}
	
	private static void MovePop(int N, int[][] A, Map<Integer, Integer> change, int[][] visited) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int united = visited[i][j];
				if(change.get(united) != null) {
					A[i][j] = change.get(united);
				}
			}
		}
		
		change.clear();
	}
	
	private static int solution(int N, int L, int R, int[][] A) {
		int move = 0;
		while(true) {
			int[][] visited = new int[N][N];
			Map<Integer, Integer> change = new HashMap<>();
			int united = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]==0) { // �湮���� ���� ���̶��
						united++;
						int changeCnt = ChkAdj(N, A, visited, i, j, united, L, R); // ���� ���� Ȯ��
						if(changeCnt != -1) { // ���յ� ������ �ִٸ�
							change.put(united, changeCnt);
						}
					}
				}
			}
			
			if(change.isEmpty())	break;
			move++; // �̵�
			MovePop(N, A, change, visited); // �α� �̵� �� �α��� ����
		}
		
		return move;
	}
}
