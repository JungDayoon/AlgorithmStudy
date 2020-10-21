import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _17281 {
	static int maxScore = 0;
	static int N;
	static int[][] player;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		player = new int[N][9];
		for(int i=0; i<N; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=0; j<9; j++)
				player[i][j] = Integer.parseInt(s[j]);
		}
		
		//1~8까지 순열구하기 -> 4번째에 1번 넣어주기
		boolean[] visited = new boolean[9]; // 1~8
		int[] output = new int[8];
		
		perm(output, visited, 0, 8, 8);
		
		System.out.println(maxScore);
	}
	
	static int perm(int[] output, boolean[] visited, int done, int n, int r) {
		if(done == r) {
			
			return inning(output);
		}
		
		for(int i=1; i<n+1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[done] = i;
				perm(output, visited, done+1, n, r);
				visited[i] = false;
			}
		}
		return 0;
		
	}
	
	static int inning(int[] output) {
		int[] turn = new int[9];
		for(int i=0; i<9; i++) {
			if(i<=2)
				turn[i] = output[i];
			else if(i==3) //4번째
				turn[i] = 0; //1번 선수
			else
				turn[i] = output[i-1];
		}
		
		int turnidx = 0;
		int thisTurn = turn[turnidx];
		
		int thisScore = 0;
		for(int i=0; i<N; i++) { //매 이닝 돌면서
			//이닝 시작시 주자는 없다. (base 초기화 -1)
			int[] base = new int[] {-1, -1, -1, -1}; //0:home 1:1루 2:2루 3:3루 //-1이면 아무도 없는 것. 0~8까지는 그에 해당하는 player
			int out = 0;
			while(out < 3) {
				int ball = player[i][thisTurn];
				
				if(ball == 0) 
					out++;
				else
					thisScore += Score(ball, base, thisTurn);
				
				if(turnidx == 8) 
					turnidx = 0;
				else
					turnidx++;
				
				thisTurn = turn[turnidx];	
			}
		}
		
		maxScore = Math.max(maxScore, thisScore);
		return 0;
	}
	
	static int Score(int ball, int[] base, int thisTurn) {
		int score = 0;
		
		for(int i=3; i>=0; i--) {
			if(base[i] != -1) { //누가 있다면
				if((i+ball) > 3) { //홈으로 들어오면
					score++; //1점추가
				}else{ //아직 안들어왔는데 아웃이 아니면 이동. (아웃이면 이동안하니까, 아웃이면 이 함수 안옴)
					base[i+ball] = base[i];
				}
				base[i] = -1; //원래자리에 비워주기
			}
		}
		
		if(ball == 4)
			score++; //홈런이니까 타자도 홈
		else
			base[ball] = thisTurn;
		
		
		return score;
	}
}
