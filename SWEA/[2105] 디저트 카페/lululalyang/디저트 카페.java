import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _2105 {
	static int dx[] = {1, 1, -1, -1}; //0: ������ �Ʒ�, 1: ���� �Ʒ�
	static int dy[] = {1, -1, -1, 1}; //2: ���� ��, 3: ������ ��
	static int maxDessert = 0;
	static int sx, sy;
	static ArrayList<Integer> d = new ArrayList<Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			maxDessert = 0;
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i=0; i<N; i++) {
				String[] s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<N-1; j++) {
					d.add(map[i][j]);
					sx = i;
					sy = j;
					calculationCnt(i, j, 0, 1, 0, map);
					d.clear();
				}
			}
			if(maxDessert == 0)
				System.out.println("#"+(t+1)+" -1");
			else
				System.out.println("#"+(t+1)+" "+maxDessert);
		}//�׽�Ʈ ���̽� ������
	}
	
	static void calculationCnt(int x, int y, int dir, int edge1, int edge2, int[][] map) { //dir�� ����(0\, 1/, 2\, 3/), edge1�� \�� ����, edge2�� /�� ����
		if(dir == 3) { // ������ ���� �̵�
			for(int i=0; i<edge2; i++) { // ������ ���� edge1��ŭ �̵�
				x += dx[dir];
				y += dy[dir];
				
				if(x==sx && y==sy) { // ���� �ڸ��� ���ƿ�����
					maxDessert = Math.max(maxDessert, d.size());
					d.clear();
					return;
				}
				
				if(x<0 || x>=map.length || y<0 || y>=map[0].length || d.contains(map[x][y])) //map ������ �����ų� ������ �ִ� ����Ʈ�������
					return; 
				
				d.add(map[x][y]);
			}
		}
		
		if(dir == 2) { //���� ���� �̵�
			for(int i=0; i<edge1; i++) {
				x += dx[dir];
				y += dy[dir];
				
				if(x<0 || x>=map.length || y<0 || y>=map[0].length || d.contains(map[x][y])) //map ������ �����ų� ������ �ִ� ����Ʈ�������
					return; 
				
				d.add(map[x][y]);
			}
			calculationCnt(x, y, dir+1, edge1, edge2, map);
		}
		
		int rx = x + dx[dir];
		int ry = y + dy[dir];
		
		if(rx>=0 && rx<map.length && ry>=0 && ry<map[0].length && !d.contains(map[rx][ry])) {
			d.add(map[rx][ry]);
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.addAll(d);
			
			if(dir == 0) { //������ �Ʒ���������
				//1)
				calculationCnt(rx, ry, dir, edge1+1, edge2, map); // \���� �÷��ָ鼭
				d.clear();
				d.addAll(tmp);
				
				
				//2)
				calculationCnt(rx, ry, dir+1, edge1, edge2+1, map); //���� �ٲ��ְ� /���� �÷��ش�  
				d.clear();
				d.addAll(tmp);
				
			}
			
			if(dir == 1) {
				//3)
				calculationCnt(rx, ry, dir, edge1, edge2+1, map); // /���� �÷��ָ鼭
				d.clear();
				d.addAll(tmp);
				
				//4)
				calculationCnt(rx, ry, dir+1, edge1, edge2, map); // ���� �ٲ��ְ�(2��)
				d.clear();
				d.addAll(tmp);
			}
		}
			
		
	}
}
