import java.io.*;
import java.util.*;
public class _19237 {
	static int[][] Time; // 냄새 남은 시간
	static int[][] Smell; //  누구 냄샌지
	static ArrayList<Shark> sharks;
	static PriorityMove[] pm;
	static int[] dx = {0, -1, 1, 0, 0}; // 상 하 좌 우
	static int[] dy = {0, 0, 0, -1, 1}; // 상 하 좌 우
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int K = Integer.parseInt(s[2]);
		
		Time = new int[N][N]; // 냄새 남은 시간
		Smell = new int[N][N]; // 그 냄새가 누구 냄샌지
		
		sharks = new ArrayList<>();
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				int shark = Integer.parseInt(s[j]);
				if(shark != 0) {
					Smell[i][j] = shark;
					Time[i][j] = K;
					sharks.add(new Shark(shark, i, j));
				}
			}
		}
		
		Collections.sort(sharks);
		s = br.readLine().split(" ");
		for(int i=0; i<M; i++) {
			int d = Integer.parseInt(s[i]);
			sharks.get(i).d = d;
		}
		
		pm = new PriorityMove[M+1]; // 1~M
		for(int i=1; i<=M; i++) {
			pm[i] = new PriorityMove();
			int[][] now = pm[i].priority;
			for(int j=1; j<=4; j++) {
				s = br.readLine().split(" ");
				for(int k=0; k<4; k++) {
					now[j][k] = Integer.parseInt(s[k]);
				}
			}
		}
		
		System.out.print(solution(N, K));
	}
	
	private static void MoveSharks(int N, int K) {		
		for(int i=0; i<sharks.size(); i++) {
			Shark now = sharks.get(i);
			int num = now.num;
			int x = now.r;
			int y = now.c;
			
			int blank = 0; // 빈공간 방향
			int cnt = 0; // 빈공간 개수
			int me = 0; // 내 냄새 방향
			int mysitCnt = 0; // 내 냄새 공간 개수
			for(int k=1; k<5; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				if(Valid(rx, ry, N)) { // 격자 안이라면
					int thisSmell = Smell[rx][ry]; 
					if(thisSmell == 0) { // 빈공간
						cnt++; 
						blank = k; 
					}else if(thisSmell == num) { // 내 공간
						me = k; 
						mysitCnt++; 
					}
				}
			}
			
			int nextX = -1;
			int nextY = -1;
			int nextD = -1;
			if(cnt == 1) { // 빈공간이 하나라면 => 그 공간으로 이동
				nextX = x + dx[blank];
				nextY = y + dy[blank];
				nextD = blank;
			}else if(cnt > 1){ // 빈공간이 여러곳이라면 => 우선순위에 따라 
				int d = now.d; // 상어 이전 방향
				int[] pri = pm[num].priority[d];
				for(Integer p : pri) { // 우선순위 방향
					int rx = x + dx[p];
					int ry = y + dy[p];
					
					if(Valid(rx, ry, N) && Smell[rx][ry] == 0) {
						nextX = rx;
						nextY = ry;
						nextD = p;
						break;
					}
				}
			}else if(mysitCnt == 1) { // 빈공간은 없는데, 내 냄새 공간이 한자리라면 => 그 공간으로
				nextX = x + dx[me];
				nextY = y + dy[me];
				nextD = me;
			}else if(mysitCnt > 1) { // 빈공간 없고, 내 냄새공간이 여러 곳이라면 => 우선순위따라
				int d = now.d; // 상어 이전 방향
				int[] pri = pm[num].priority[d];
				for(Integer p : pri) {
					int rx = x + dx[p];
					int ry = y + dy[p];
					
					if(Valid(rx, ry, N) && Smell[rx][ry] == num) { // 내냄새공간이라면
						nextX = rx;
						nextY = ry;
						nextD = p;
						break;
					}
				}
			}
			
			now.r = nextX;
			now.c = nextY;
			now.d = nextD;
		}
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	private static void ReduceTime(int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(Time[i][j] > 0) {
					Time[i][j]--;
					if(Time[i][j] == 0) { // 냄새 다 날라갔으면, 상어도 지워주기
						Smell[i][j] = 0;
					}
				}
			}
		}
	}
	
	private static void SmellShark(int K) {
		for(int i=0; i<sharks.size(); i++) {
			Shark now = sharks.get(i);
			int r = now.r;
			int c = now.c;
			int num = now.num;
			if(Time[r][c] == K) {	// 이미 이전 상어가 냄새 풍김 => 이후에 온 숫자 큰 상어는 쫓겨난다
				sharks.remove(i);
				i--;
			}else {
				Time[r][c] = K;
				Smell[r][c] = num;
			}
		}
	}
	
	private static int solution(int N, int K) {
		int time = 0;
		
		while(sharks.size() != 1) {
			if(time == 1000) {
				time = -1;
				break;
			}
			
			MoveSharks(N, K); // 상어 이동
			ReduceTime(N); // 냄새 남은 시간 줄이기
			SmellShark(K); // 이동시킨 상어 냄새 남기기
			time++;
		}
		
		return time;
	}
	
	private static class PriorityMove{
		private int[][] priority;
		
		PriorityMove(){
			priority = new int[5][4];
		}
	}
	
	private static class Shark implements Comparable<Shark>{
		private int num;
		private int r;
		private int c;
		private int d; // 방향
		
		Shark(int num, int r, int c){
			this.num = num;
			this.r = r;
			this.c = c;
		}
		
		@Override
		public int compareTo(Shark s) {
			return this.num - s.num;
		}
		
		public String toString() {
			return num + ": (" + r + "," + c + ") " + d;
		}
	}
}
