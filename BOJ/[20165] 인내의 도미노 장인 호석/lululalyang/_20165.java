import java.io.*;
import java.util.*;
public class _20165 {
	static int[][] height;
	static String[][] state;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int R = Integer.parseInt(s[2]);
		
		height = new int[N+1][M+1]; // ���̳��� ����
		state = new String[N+1][M+1]; // ���̳밡 ���ִ���("S"), �Ѿ�������("F")
		for(int i=1; i<N+1; i++) {
			s = br.readLine().split(" ");
			for(int j=1; j<M+1; j++) {
				height[i][j] = Integer.parseInt(s[j-1]);
			}
			Arrays.fill(state[i], "S");
		}
		
		int res = solution(N, M, R, br);
		StringBuilder sb = new StringBuilder();
		sb.append(res);
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				if(j == 1)	sb.append("\n" + state[i][j]);
				else	sb.append(" " + state[i][j]);
			}
		}
		
		System.out.print(sb.toString());
	}
	
	private static int solution(int N, int M, int R, BufferedReader br) throws IOException {
		int score = 0;
		String[] s;
		while(R != 0) {
			// 1. ����
			s = br.readLine().split(" ");
			int ax = Integer.parseInt(s[0]); // ���� x��ǥ
			int ay = Integer.parseInt(s[1]); // ���� y��ǥ
			int adir = DirToInt(s[2]); // ���� ����
			score += AttackDomino(ax, ay, adir, N, M);
			
			// 2. ����
			s = br.readLine().split(" ");
			state[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = "S"; // �ٽ� �����.
			
			R--;
		}
		
		return score;
	}
	
	private static int AttackDomino(int ax, int ay, int adir, int N, int M) {
		int cnt = 0; // �Ѿ��� ���̳� ����
		Queue<int[]> q = new LinkedList<>(); // x, y, �Ѿ��� ���̳��� ����
		q.add(new int[] {ax, ay});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int h = height[x][y];
			
			if(state[x][y].equals("F"))	continue;
			state[x][y] = "F"; // �Ѿ�߸���
			cnt++;
			for(int i=0; i<h-1; i++) { // h-1����ŭ
				x += dx[adir];
				y += dy[adir];
				
				if(Valid(x, y, N, M)) {
					if(state[x][y].equals("S"))	q.add(new int[] {x, y}); // �ȳѾ�����������
				}
			}
		}
		
		return cnt;
	}
	
	private static int DirToInt(String dir) { // ���⿡ �´� �ε����� ����
		switch(dir) {
		case "N": return 0;
		case "E": return 1;
		case "S": return 2;
		case "W": return 3;
		}
		return -1;
	}
	
	private static boolean Valid(int x, int y, int N, int M) {
		return (x>0 && x<=N && y>0 && y<=M);
	}
}
