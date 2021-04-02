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
		int N = Integer.parseInt(s[0]); // 원판 개수
		int M = Integer.parseInt(s[1]); // 원판 위 숫자 개수
		int T = Integer.parseInt(s[2]); // 회전 횟수
		
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
		
		if(d == 0) { // 시계방향
			for(int i=0; i<M; i++) {
				if((i+k) >= M)	res[i+k-M] = now[i];
				else	res[i+k] = now[i];
			}
		}else { // 반시계방향
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
				if(circles[i][j] != 0)	return true; // 아직 숫자가 남아있을 때
			}
		}
		
		return false; // 다 0이면 남은 숫자가 없는 것
 	}
	
	private static int ChkAdjSameNum(int x, int y, int N, int M) { // 인접한 같은 수 찾기
		int cnt = 0; // 인접한 같은 수 개수
		int num = circles[x][y];
		Queue<int[]> q = new LinkedList<>(); // (r,c),이전 수
		for(int k=0; k<4; k++) { // 상하좌우 인접한 숫자 확인
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
		
		if(cnt != 0) { // 0이 아니라면 (x,y)와 바로 인접해있는 같은 수가 있는 것
			circles[x][y] = 0; // (x,y)는 인접한 수 모두 확인했으므로 q에 add()하지 않음
			cnt++;
		}
		
		while(!q.isEmpty()) { // cnt가 이미 정해졌으므로 while문 안에서는 개수를 세지 않아도 된다
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
			if(t != 0) { // 첫 회전이 아니라면
				if(!ChkRemain(N, M)) { // 숫자가 남아있지않으면
					res = -1;
				}
			}
			
			int x = rotate[t][0]; // x배수 번호의 원판 // 2 <= x <= N
			int d = rotate[t][1]; // d=0: 시계 // d=1: 반시계
			int k = rotate[t][2]; // 회전 칸 개수
			
			for(int i=2; i<=N; i++) {
				if((i%x) == 0)	RotateCircles(i, d, k, M); // x배수의 원판이라면 => d방향으로 k칸 회전
			}
			
			int sum = 0; // 인접한 같은 수가 없을 때의 나머지 숫자의 합
			int cnt = 0; // 그 개수
			int Adjcnt = 0; // 인접한 같은 수가 있으면 양수, 없으면 0
			for(int i=1; i<=N; i++) {
				for(int j=0; j<M; j++) {
					if(circles[i][j] != 0) { // 숫자가 존재한다면
						Adjcnt += ChkAdjSameNum(i, j, N, M);
						sum += circles[i][j];
						cnt++;
					}
				}
			}
			
			if(Adjcnt == 0) { // 인접한 같은 수가 없다면
				float avg = (float)sum/cnt; // 남은 수의 평균
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
