import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class _17070 {
	static int[][] checkBydir_x = {{0}, {1}, {0, 1, 1}}; //0:�� //1:�� //2:�� 
	static int[][] checkBydir_y = {{1}, {0}, {1, 1, 0}}; //�̵� ���⿡ ���� (x2, y2)���� Ȯ��������ϴ� ��ǥ������ ����
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
		int py = 1; //�������� ������ ���� ��ǥ
		int pipe = 0; //������ ���. ó���� ����
		dfs(px, py, pipe);
		System.out.println(result);
	}
	
	static void dfs(int px, int py, int pipe) {
		if(px==N-1 && py==N-1) {
			result++;
			return;
		}
		switch(pipe) {
		case 0: // pipe�� ���θ���� �� : ��(0) ��(2) ����
			movePipe(px, py, pipe, 0);
			movePipe(px, py, pipe, 2);
			break;
		case 1: // pipe�� ���θ���� �� : ��(1) ��(2) ����
			movePipe(px, py, pipe, 1);
			movePipe(px, py, pipe, 2);
			break;
		case 2: // pipe�� �밢������� �� : ��(0) ��(1) ��(2) ����
			movePipe(px, py, pipe, 0);
			movePipe(px, py, pipe, 1);
			movePipe(px, py, pipe, 2);
				break;
		}
	}
	
	static void movePipe(int x, int y, int pipe, int moveDir) {
		if(moveDir==0 || moveDir==1) { //���� �Ǵ� ���ι������� �̵��� ��
			int rx = x + checkBydir_x[moveDir][0];
			int ry = y + checkBydir_y[moveDir][0];
			if(rx<N && ry<N && map[rx][ry]==0) { //map�ȿ� �ְ�, ��ĭ�϶�(���� �ƴҶ�)
				dfs(rx, ry, moveDir);
			}
		}else { //moveDir==2 //�밢���������� �̵��� ��
			int flag = 0;
			for(int i=0; i<3; i++) {
				int rx = x + checkBydir_x[moveDir][i];
				int ry = y + checkBydir_y[moveDir][i];
				if(rx>=0 && rx<N && ry>=0 && ry<N && map[rx][ry]==0) { //map�ȿ� �ְ�, ��ĭ�϶�(���� �ƴҶ�)
					flag++;
				}
			}
			if(flag == 3) {
				int rx = x + 1; //�밢��
				int ry = y + 1; //����
				dfs(rx, ry, moveDir);
			}
		}
	}

}
