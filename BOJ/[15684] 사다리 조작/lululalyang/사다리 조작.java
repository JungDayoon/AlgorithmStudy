import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _15684 {
	static ArrayList<ArrayList<Integer>> c = new ArrayList<ArrayList<Integer>>();
	static int N;
	static int M;
	static int H;
	static int flag = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]); //세로선 개수
		M = Integer.parseInt(s[1]); //가로선 개수
		H = Integer.parseInt(s[2]); //가로선을 놓을 수 있는 위치의 개수
		
		int[][] ladder = new int[H+2][N+1];
		
		for(int j=1; j<ladder[0].length; j++)
			ladder[H+1][j] = j;
		
		for(int i=0; i<M; i++) {
			String[] d = br.readLine().split(" ");
			int a = Integer.parseInt(d[0]);
			int b = Integer.parseInt(d[1]);
			
			ladder[a][b] = b+1;
			ladder[a][b+1] = b;
		}
		
		if(checkDone(ladder)) {
			System.out.println("0");
			System.exit(0);
		}else {
			for(int ladderN=1; ladderN<4; ladderN++) {
				Buildladder(ladderN, ladder, 0, 1, 1);
				
				if(flag == 1) { //되면 true 안되면 false
					System.out.println(ladderN);
					System.exit(0);
				}
			}//만약 이 사다리개수로 된다면 출력해주고 끝내기
		}
		
		System.out.println("-1"); //3개보다 많은 사다리가 필요할 경우
		
	}
	
	static void Buildladder(int ladderN, int[][] ladder, int done, int x, int y) { 
		if(done == ladderN) {
			if(checkDone(ladder)) {
				flag = 1;
			}
			return;
		}else {
			if(y == N) {
				return;
			}
			
			if(ladder[x][y] == 0) {
				if(ladder[x][y+1] == 0) {
					ladder[x][y] = y+1;
					ladder[x][y+1] = y;
					int rx, ry;
					if((x+1) == (H+1)) { 
						rx = 1;
						ry = y + 1;
					}else { 
						rx = x+1;
						ry = y;
					}
					
					Buildladder(ladderN, ladder, done+1, rx, ry);
					if(flag == 1)
						return;
					
					ladder[x][y] = 0;
					ladder[x][y+1] = 0;
					Buildladder(ladderN, ladder, done, rx, ry);
					
				}else {
					int rx, ry;
					if((x+1) == (H+1)) { 
						rx = 1;
						ry = y + 1;
					}else { 
						rx = x+1;
						ry = y;
					}
					Buildladder(ladderN, ladder, done, rx, ry);
				}
				
			}else { //이미 사다리가 있다면 -> 놓지 못한다
				int rx, ry;
				if((x+1) == (H+1)) {
					rx = 1;
					ry = y + 1;
				}
				else {
					rx = x+1;
					ry = y;
				}
				Buildladder(ladderN, ladder, done, rx, ry);
				
			}
			
		}
		
	}
	
	static boolean checkDone(int[][] ladder) {
		for(int lNum=1; lNum<ladder[0].length; lNum++) {
			int i = lNum;
			int j=0;
			while(j != ladder.length-1) {
				if(ladder[j+1][i] != 0) {
					i = ladder[j+1][i];
				}
				j++;
			}
			if(i != lNum)
				return false;
		}
		
		return true;
	}
}
