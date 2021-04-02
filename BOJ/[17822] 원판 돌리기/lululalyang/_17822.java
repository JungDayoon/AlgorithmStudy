import java.io.*;
import java.util.*;
public class _17822 {
	static int[][] circles;
	static int[][] rotate;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // ���� ����
		int M = Integer.parseInt(s[1]); // ���� �� ���� ����
		int T = Integer.parseInt(s[2]); // ȸ�� Ƚ��
		
		circles = new int[N+1][M];
		for(int i=1; i<N+1; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				circles[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		rotate = new int[T][3];
		for(int i=0; i<T; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<3; j++)	rotate[i][j] = Integer.parseInt(s[j]);
		}
		
		System.out.print(solution(N, M, T));
	}
	
	private static boolean Valid(int N, int M, int r, int c) {
		return (r>=1 && r<=N && c>=0 && c<M);
	}
	
	private static void RotateCircles(int x, int d, int k, int M) {
		int[] now = circles[x];
		int[] res = new int[M];
		
		if(d == 0) { // �ð����
			for(int i=0; i<M; i++) {
				if((i+k) >= M)	res[i+k-M] = now[i];
				else	res[i+k] = now[i];
			}
		}else { // �ݽð����
			for(int i=0; i<M; i++) {
				if((i-k) < 0) res[i-k+M] = now[i];
				else	res[i-k] = now[i];
			}
		}
		
		circles[x] = res;
	}
	
	private static boolean ChkRemain(int N, int M) {
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				if(circles[i][j] != 0)	return true; // ���� ���ڰ� �������� ��
			}
		}
		
		return false; // �� 0�̸� ���� ���ڰ� ���� ��
 	}
	
	private static int ChkAdjSameNum(int x, int y, int N, int M) { // ������ ���� �� ã��
		int cnt = 0; // ������ ���� �� ����
		int num = circles[x][y];
		Queue<int[]> q = new LinkedList<>(); // (r,c),���� ��
		for(int k=0; k<4; k++) { // �����¿� ������ ���� Ȯ��
			int rx = x + dx[k];
			int ry = y + dy[k];
			
			if(Valid(N, M, rx, ry) && circles[rx][ry]==num) {
				q.add(new int[] {rx, ry});
				circles[rx][ry] = 0;
				cnt++;
			}
		}
		if(y==0 && circles[x][M-1]==num) {
			q.add(new int[] {x, M-1});
			circles[x][M-1] = 0;
			cnt++;
		}else if(y==(M-1) && circles[x][0]==num) {
			q.add(new int[] {x, 0});
			circles[x][0] = 0;
			cnt++;
		}
		
		if(cnt != 0) { // 0�� �ƴ϶�� (x,y)�� �ٷ� �������ִ� ���� ���� �ִ� ��
			circles[x][y] = 0; // (x,y)�� ������ �� ��� Ȯ�������Ƿ� q�� add()���� ����
			cnt++;
		}
		
		while(!q.isEmpty()) { // cnt�� �̹� ���������Ƿ� while�� �ȿ����� ������ ���� �ʾƵ� �ȴ�
			int[] now = q.poll();
			x = now[0];
			y = now[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(N, M, rx, ry) && circles[rx][ry]==num) {
					q.add(new int[] {rx, ry});
					circles[rx][ry] = 0;
				}
			}
			
			if(y==0 && circles[x][M-1]==num) {
				q.add(new int[] {x, M-1});
				circles[x][M-1] = 0;
			}else if(y==(M-1) && circles[x][0]==num) {
				q.add(new int[] {x, 0});
				circles[x][0] = 0;
			}
		}
		
		return cnt;
	}
	
	private static void AdjByAvg(float avg, int N, int M) {
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				int now = circles[i][j];
				if(now != 0) {
					if(now > avg)	circles[i][j]--;
					else if(now < avg) circles[i][j]++;
				}
			}
		}
	}
	
	private static int RemainNumSum(int N, int M) {
		int sum = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				sum += circles[i][j];
			}
		}
		return sum;
	}
	
	private static int solution(int N, int M, int T) {
		int res = 0;
		for(int t=0; t<T; t++) {
			if(t != 0) { // ù ȸ���� �ƴ϶��
				if(!ChkRemain(N, M)) { // ���ڰ� ��������������
					res = -1;
				}
			}
			
			int x = rotate[t][0]; // x��� ��ȣ�� ���� // 2 <= x <= N
			int d = rotate[t][1]; // d=0: �ð� // d=1: �ݽð�
			int k = rotate[t][2]; // ȸ�� ĭ ����
			
			for(int i=2; i<=N; i++) {
				if((i%x) == 0)	RotateCircles(i, d, k, M); // x����� �����̶�� => d�������� kĭ ȸ��
			}
			
			int sum = 0; // ������ ���� ���� ���� ���� ������ ������ ��
			int cnt = 0; // �� ����
			int Adjcnt = 0; // ������ ���� ���� ������ ���, ������ 0
			for(int i=1; i<=N; i++) {
				for(int j=0; j<M; j++) {
					if(circles[i][j] != 0) { // ���ڰ� �����Ѵٸ�
						Adjcnt += ChkAdjSameNum(i, j, N, M);
						sum += circles[i][j];
						cnt++;
					}
				}
			}
			
			if(Adjcnt == 0) { // ������ ���� ���� ���ٸ�
				float avg = (float)sum/cnt; // ���� ���� ���
				AdjByAvg(avg, N, M);
			}
		}
		
		if(res == -1)	return 0;
		else {
			res = RemainNumSum(N, M);
		}
		return res;
	}
}
