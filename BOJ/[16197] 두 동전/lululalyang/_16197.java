import java.io.*;
public class _16197 {
	static int N;
	static int M;
	static String[][] map;
	static int Min = Integer.MAX_VALUE; // ��ư �ּ� Ƚ��
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]); // ����
		M = Integer.parseInt(s[1]); // ����
		map = new String[N][M];
		boolean flag = false;
		
		int Ax = 0, Ay = 0, Bx = 0, By = 0;
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().split("");
			for(int j=0; j<M; j++) {
				 if(map[i][j].equals("o")) {
					 map[i][j] = ".";
					 if(!flag) {
						 Ax = i;
						 Ay = j;
						 flag = true;
					 }else {
						 Bx = i;
						 By = j;
					 }
				 }
			}
		}
		
		solution(0, Ax, Ay, Bx, By);
		if(Min == Integer.MAX_VALUE)	Min = -1;
		System.out.print(Min); 
	}
	
	private static void solution(int click, int Ax, int Ay, int Bx, int By) {
		if(click == 10) 	return;
		for(int k=0; k<4; k++) {
			int Arx = Ax + dx[k];
			int Ary = Ay + dy[k];
			
			int Brx = Bx + dx[k];
			int Bry = By + dy[k];
			
			boolean flagA = Valid(Arx, Ary); // ���̸� true
			boolean flagB = Valid(Brx, Bry);
			if(!flagA && flagB) { // A��, B��
				Min = Math.min(Min, click+1); // ����
				return;
			}else if(flagA && !flagB) { // A��, B��
				Min = Math.min(Min, click+1); // ����
				return;
			}else if(flagA && flagB) { // A��, B��
				boolean chkA = map[Arx][Ary].equals("."); // ������̸� true
				boolean chkB = map[Brx][Bry].equals(".");
				if(chkA && chkB) // �Ѵ� ������̶��	
					solution(click+1, Arx, Ary, Brx, Bry);
				else if(!chkA && !chkB) // �Ѵ� ���̶��
					continue;
				else if(chkA)  // A����� B��
					solution(click+1, Arx, Ary, Bx, By);
				else // A�� B�����
					solution(click+1, Ax, Ay, Brx, Bry);
			}
			//A��, B���� �н�
		}
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
}
