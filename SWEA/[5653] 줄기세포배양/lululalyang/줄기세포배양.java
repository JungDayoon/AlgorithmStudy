import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class _5653 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int[][] map;
	static int[][] exist; //0���� 1���� 2����
	static int[][] state; 
	static int alive = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			alive = 0;
			
			map = new int[351][351]; //����� �ִ�ũ�� 150+50+150 (+1�� �߰���)
			exist = new int[351][351];
			state = new int[351][351]; //�� ������ ���� ����
			
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); // ���� ũ��
			int M = Integer.parseInt(s[1]); // ���� ũ��
			int K = Integer.parseInt(s[2]); // ��� �ð�
			
			for(int i=175-(N/2); i<175-(N/2)+N; i++) { //ó���� ����� �� ����� �ٱ⼼�� ���д�
				String[] d = br.readLine().split(" ");
				int k=0;
				for(int j=175-(M/2); j<175-(M/2)+M; j++) {
					map[i][j] = Integer.parseInt(d[k++]);
					state[i][j] = map[i][j];
					if(map[i][j] != 0) {
						exist[i][j] = 1; //0���� 1���� 2����
						alive++;
					}
				}
			}
			
			for(int k=0; k<K; k++) { //K�ð� ����
				Breeding();
			}
			
			System.out.println("#"+(t+1)+" "+alive);
		}
	}
	
	static ArrayList<int[]> zero = new ArrayList<>();
	static void Breeding() { //map:����� ��ġ //exist:���� ���� ���� (0���� 1���� 2����)//state:������ ����
		
		zero.clear();
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(exist[i][j] == 1) { //���� �����ϸ� -> ����--
					if(state[i][j] == 0){ //����0�̸� �����ؾߵǴϱ� 
						zero.add(new int[] {i, j});
					}
					
					state[i][j]--;
					
					if((-1)*state[i][j] == map[i][j]) { // state == -map �̶�� => ��������! ������ ������ ����
						exist[i][j] = 2; //��������
						alive--;
					}
				}
			}
		}
		
		for(int[] z : zero) {
			int x = z[0];
			int y = z[1];
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(rx<0 || ry<0 || rx>=map.length || ry>=map[0].length) //���� �����
					continue;
				
				if(exist[rx][ry] == 0) // �����Ϸ��� ���� ���� ���ٸ� -> ����! aliveŰ���ֱ� !!!
				{ 
					exist[rx][ry] = 1;
					map[rx][ry] = map[x][y];
					state[rx][ry] = map[x][y];
					
					alive++;
					
				}else // exist[rx][ry]==1 || exist[rx][ry]==2 //����ְų� �׾��ְų� ��ư �̹� �����ִٸ�
				{ 
					if(map[rx][ry] == state[rx][ry]) { // ����¼�ġ�� ���°� ���ٸ� -> ��� �����!
						if(map[rx][ry] < map[x][y]) { // ���� �����Ϸ��� ������ ����� ��ġ�� �� ũ�ٸ�
							map[rx][ry] = map[x][y];
							state[rx][ry] = map[x][y]; //ū �ְ� ������ �ڸ� �����Ѵ� ->�����ִ� ���������� �� aliveŰ�������ϱ� �����൵ ��
						} // else -> ���� ���� ������ ������� �� �۰ų� ������ �״�� ����
					} // else -> ��� ���� ������ �ƴ϶�� -> ���� �ִ� ���� -> ���� ���Ѵ�
				}
				
			}
		}
	}
}
