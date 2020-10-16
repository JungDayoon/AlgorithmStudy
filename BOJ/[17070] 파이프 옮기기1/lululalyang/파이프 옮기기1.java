import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _17070 {
	static int[][] checkBydir_x = {{0}, {1}, {0, 1, 1}}; //0:→ //1:↓ //2:↘ 
	static int[][] checkBydir_y = {{1}, {0}, {1, 1, 0}}; //이동 방향에 따라 (x2, y2)기준 확인해줘야하는 좌표까지의 차이
	static int result = 0;
	static int N;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		int px = 0;
		int py = 1; //파이프의 오른쪽 끝의 좌표
		int pipe = 0; //파이프 모양. 처음엔 가로
		dfs(px, py, pipe);
		System.out.println(result);
	}
	
	static void dfs(int px, int py, int pipe) {
		if(px==N-1 && py==N-1) {
			result++;
			return;
		}
		switch(pipe) {
		case 0: // pipe가 가로모양일 때 : →(0) ↘(2) 가능
			movePipe(px, py, pipe, 0);
			movePipe(px, py, pipe, 2);
			break;
		case 1: // pipe가 세로모양일 때 : ↓(1) ↘(2) 가능
			movePipe(px, py, pipe, 1);
			movePipe(px, py, pipe, 2);
			break;
		case 2: // pipe가 대각선모양일 때 : →(0) ↓(1) ↘(2) 가능
			movePipe(px, py, pipe, 0);
			movePipe(px, py, pipe, 1);
			movePipe(px, py, pipe, 2);
				break;
		}
	}
	
	static void movePipe(int x, int y, int pipe, int moveDir) {
		if(moveDir==0 || moveDir==1) { //가로 또는 세로방향으로 이동할 때
			int rx = x + checkBydir_x[moveDir][0];
			int ry = y + checkBydir_y[moveDir][0];
			if(rx<N && ry<N && map[rx][ry]==0) { //map안에 있고, 빈칸일때(벽이 아닐때)
				dfs(rx, ry, moveDir);
			}
		}else { //moveDir==2 //대각선방향으로 이동할 때
			int flag = 0;
			for(int i=0; i<3; i++) {
				int rx = x + checkBydir_x[moveDir][i];
				int ry = y + checkBydir_y[moveDir][i];
				if(rx>=0 && rx<N && ry>=0 && ry<N && map[rx][ry]==0) { //map안에 있고, 빈칸일때(벽이 아닐때)
					flag++;
				}
			}
			if(flag == 3) {
				int rx = x + 1; //대각선
				int ry = y + 1; //방향
				dfs(rx, ry, moveDir);
			}
		}
	}

}
