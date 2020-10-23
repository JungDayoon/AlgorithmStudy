import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

public class _11559 {
	static int turn = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[][] puyo = new String[12][6]; 
		boolean[][] visited;
		
		for(int i=0; i<12; i++) {
			String[] s = br.readLine().split("");
			for(int j=0; j<6; j++) {
				puyo[i][j] = s[j];
			}
		}
		
		int check = 1;
		while(check != 0) { 
			visited = new boolean[12][6];
			check = PuyoPuyo(puyo, visited); //뿌요가 터졌으면 1, 안터졌으면 0
			if(check == 1)
				turn++;
		}
		
		System.out.println(turn);
	} 
	
	static int PuyoPuyo(String[][] puyo, boolean[][] visited) {
		int YorN = 0; //터졌으면 1, 안터졌으면 0
		Queue<String[]> q = new LinkedList<>();
		
		for(int i=11; i>=0; i--) { //왼쪽 맨아래부터 확인
			for(int j=0; j<6; j++) {
				if(!puyo[i][j].equals(".")) { //"."이 아니면 == 뿌요가 존재하면
					q.add(new String[] {Integer.toString(i), Integer.toString(j), puyo[i][j]});
					visited[i][j] = true;
					YorN = Math.max(Pop(q, puyo, visited), YorN); //1이랑 0 값이니까 큰값으로 하나라도 터졌으면 1
				}
			}
		}
		
		if(YorN == 0) 
			return 0;
		else { //YorN==1 : 터졌으면 중력받은거 처리해주기
			Gravity(puyo);
			return 1;
		}
	}
	
	static void Gravity(String[][] puyo) {
		ArrayList<String> S = new ArrayList<String>();
		for(int j=0; j<6; j++) {
			S.clear();
			for(int i=11; i>=0; i--) {
				if(!puyo[i][j].equals(".")) { //문자라면
					S.add(puyo[i][j]);
					puyo[i][j] = ".";
				}
			}
			
			int idx = 11;
			for(String s : S) {
				puyo[idx--][j] = s;
			}
		}
	}
	
	static int Pop(Queue<String[]> q, String[][] puyo, boolean[][] visited) {
		ArrayList<int[]> bomb = new ArrayList<>();
		
		while(q.size() != 0) {
			String[] tmp = q.poll();
			int x = Integer.parseInt(tmp[0]);
			int y = Integer.parseInt(tmp[1]);
			String S = tmp[2];
			bomb.add(new int[] {x, y});
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(rx>=0 && rx<12 && ry>=0 && ry<6 && puyo[rx][ry].equals(S) && !visited[rx][ry]) {
					visited[rx][ry] = true;
					q.add(new String[] {Integer.toString(rx), Integer.toString(ry), S});
					//puyoN++;
				}
			}
		}
		
		if(bomb.size() >= 4) {
			for(int[] tmp : bomb) {
				puyo[tmp[0]][tmp[1]] = ".";
			}
			return 1;
		}else
			return 0;
	}
}
