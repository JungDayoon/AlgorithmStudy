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
		
		//1~8���� �������ϱ� -> 4��°�� 1�� �־��ֱ�
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
			else if(i==3) //4��°
				turn[i] = 0; //1�� ����
			else
				turn[i] = output[i-1];
		}
		
		int turnidx = 0;
		int thisTurn = turn[turnidx];
		
		int thisScore = 0;
		for(int i=0; i<N; i++) { //�� �̴� ���鼭
			//�̴� ���۽� ���ڴ� ����. (base �ʱ�ȭ -1)
			int[] base = new int[] {-1, -1, -1, -1}; //0:home 1:1�� 2:2�� 3:3�� //-1�̸� �ƹ��� ���� ��. 0~8������ �׿� �ش��ϴ� player
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
			if(base[i] != -1) { //���� �ִٸ�
				if((i+ball) > 3) { //Ȩ���� ������
					score++; //1���߰�
				}else{ //���� �ȵ��Դµ� �ƿ��� �ƴϸ� �̵�. (�ƿ��̸� �̵����ϴϱ�, �ƿ��̸� �� �Լ� �ȿ�)
					base[i+ball] = base[i];
				}
				base[i] = -1; //�����ڸ��� ����ֱ�
			}
		}
		
		if(ball == 4)
			score++; //Ȩ���̴ϱ� Ÿ�ڵ� Ȩ
		else
			base[ball] = thisTurn;
		
		
		return score;
	}
}
