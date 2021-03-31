import java.io.*;
import java.util.*;
public class _19237 {
	static int[][] Time; // ���� ���� �ð�
	static int[][] Smell; //  ���� ������
	static ArrayList<Shark> sharks;
	static PriorityMove[] pm;
	static int[] dx = {0, -1, 1, 0, 0}; // �� �� �� ��
	static int[] dy = {0, 0, 0, -1, 1}; // �� �� �� ��
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int K = Integer.parseInt(s[2]);
		
		Time = new int[N][N]; // ���� ���� �ð�
		Smell = new int[N][N]; // �� ������ ���� ������
		
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
			
			int blank = 0; // ����� ����
			int cnt = 0; // ����� ����
			int me = 0; // �� ���� ����
			int mysitCnt = 0; // �� ���� ���� ����
			for(int k=1; k<5; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				if(Valid(rx, ry, N)) { // ���� ���̶��
					int thisSmell = Smell[rx][ry]; 
					if(thisSmell == 0) { // �����
						cnt++; 
						blank = k; 
					}else if(thisSmell == num) { // �� ����
						me = k; 
						mysitCnt++; 
					}
				}
			}
			
			int nextX = -1;
			int nextY = -1;
			int nextD = -1;
			if(cnt == 1) { // ������� �ϳ���� => �� �������� �̵�
				nextX = x + dx[blank];
				nextY = y + dy[blank];
				nextD = blank;
			}else if(cnt > 1){ // ������� �������̶�� => �켱������ ���� 
				int d = now.d; // ��� ���� ����
				int[] pri = pm[num].priority[d];
				for(Integer p : pri) { // �켱���� ����
					int rx = x + dx[p];
					int ry = y + dy[p];
					
					if(Valid(rx, ry, N) && Smell[rx][ry] == 0) {
						nextX = rx;
						nextY = ry;
						nextD = p;
						break;
					}
				}
			}else if(mysitCnt == 1) { // ������� ���µ�, �� ���� ������ ���ڸ���� => �� ��������
				nextX = x + dx[me];
				nextY = y + dy[me];
				nextD = me;
			}else if(mysitCnt > 1) { // ����� ����, �� ���������� ���� ���̶�� => �켱��������
				int d = now.d; // ��� ���� ����
				int[] pri = pm[num].priority[d];
				for(Integer p : pri) {
					int rx = x + dx[p];
					int ry = y + dy[p];
					
					if(Valid(rx, ry, N) && Smell[rx][ry] == num) { // �����������̶��
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
					if(Time[i][j] == 0) { // ���� �� ��������, �� �����ֱ�
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
			if(Time[r][c] == K) {	// �̹� ���� �� ���� ǳ�� => ���Ŀ� �� ���� ū ���� �Ѱܳ���
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
			
			MoveSharks(N, K); // ��� �̵�
			ReduceTime(N); // ���� ���� �ð� ���̱�
			SmellShark(K); // �̵���Ų ��� ���� �����
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
		private int d; // ����
		
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
