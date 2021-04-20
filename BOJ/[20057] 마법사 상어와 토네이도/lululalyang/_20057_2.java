import java.io.*;
import java.util.*;
public class _20057_2 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] map;
	static int nowx, nowy;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		System.out.print(solution(N));
	}
	
	private static int solution(int N) {
		int Out = 0;
		nowx = nowy = N/2;
		
		int dir = 0;
		int movecnt = 1;
		boolean flag = false;
		while(true) {
			Out += Move(dir, movecnt, N);
			if(movecnt == (N-1) && flag) break;
			if(!flag)	flag = true;
			else {
				flag = false;
				movecnt++;
			}
			dir++;
			if(dir == 4)	dir = 0;
		}
		
		Out += Move(0, N-1, N);
		return Out;
	}
	
	private static int Move(int dir, int movecnt, int N) {
		int Out = 0;
		for(int i=0; i<movecnt; i++) {
			nowx += dx[dir];
			nowy += dy[dir];
			
			int nowAll = map[nowx][nowy]; // 현재 칸의 모래의 양
			int rightdir = (dir-1) < 0 ? 3 : dir-1;
			int leftdir = (dir+1) > 3 ? 0 : dir+1;
			
			Out += MoveByPerc(nowAll, dir, rightdir, leftdir, N);
		}
		
		return Out;
	}
	
	private static int MoveByPerc(int nowAll, int dir, int right, int left, int N) {
		int Out = 0, rx, ry;
		int minus = 0;
		int amount = nowAll * 7 / 100; //7퍼센트
		rx = nowx + dx[right];
		ry = nowy + dy[right];
		Out += Chk(rx, ry, N, amount);
		rx = nowx + dx[left];
		ry = nowy + dy[left];
		Out += Chk(rx, ry, N, amount);
		minus += (amount * 2);
		
		amount = nowAll * 10 / 100; // 10퍼센트
		rx = nowx + dx[dir] + dx[right];
		ry = nowy + dy[dir] + dy[right];
		Out += Chk(rx, ry, N, amount);
		rx = nowx + dx[dir] + dx[left];
		ry = nowy + dy[dir] + dy[left];
		Out += Chk(rx, ry, N, amount);
		minus += (amount * 2);
		
		amount = nowAll * 2 / 100; // 2퍼센트
		rx = nowx + dx[right]*2;
		ry = nowy + dy[right]*2; 
		Out += Chk(rx, ry, N, amount);
		rx = nowx + dx[left]*2;
		ry = nowy + dy[left]*2;
		Out += Chk(rx, ry, N, amount);
		minus += (amount * 2);
		
		amount = nowAll * 5 / 100; // 5퍼센트
		rx = nowx + dx[dir]*2;
		ry = nowy + dy[dir]*2;
		Out += Chk(rx, ry, N, amount);
		minus += amount;
		
		amount = nowAll / 100; // 1퍼센트
		rx = nowx + dx[right] - dx[dir];
		ry = nowy + dy[right] - dy[dir];
		Out += Chk(rx, ry, N, amount);
		rx = nowx + dx[left] - dx[dir];
		ry = nowy + dy[left] - dy[dir];
		Out += Chk(rx, ry, N, amount);
		minus += (amount * 2);
		
		amount = nowAll - minus; // 알파
		rx = nowx + dx[dir];
		ry = nowy + dy[dir]; 
		Out += Chk(rx, ry, N, amount);
		
		map[nowx][nowy] = 0;
		return Out;
	}
	
	private static int Chk(int rx, int ry, int N, int amount) {
		int res = 0;
		if(Valid(rx, ry, N))	map[rx][ry] += amount;
		else res = amount;
		return res;
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
