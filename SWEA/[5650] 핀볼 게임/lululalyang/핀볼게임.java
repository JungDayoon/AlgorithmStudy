import java.io.*;
import java.util.*;

public class _5650 {
	static int maxScore;
	static ArrayList<Integer>[] DirbyBlock = new ArrayList[5]; //�� 1~4 //5�� ����
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static int N;
	static HashMap<Integer, ArrayList<int[]>> wormhole;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		initializeList();
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++) {
			maxScore = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			wormhole = new HashMap<Integer, ArrayList<int[]>>();
			for(int i=0; i<N; i++) {
				String[] s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if(map[i][j]>=6 && map[i][j]<=10) { //��Ȧ�̶��
						int whNum = map[i][j];
						if(wormhole.containsKey(whNum)) { //�����ϴ� ��Ȧ�̶��
							ArrayList<int[]> tmp = wormhole.remove(whNum);
							tmp.add(new int[] {i, j});
							wormhole.put(whNum, (ArrayList<int[]>) tmp.clone());
						}else { //���ο� ��Ȧ�̶��
							ArrayList<int[]> tmp = new ArrayList<>();
							tmp.add(new int[] {i, j});
							wormhole.put(whNum, (ArrayList<int[]>)tmp.clone());
						}
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 0) {
						for(int k=0; k<=3; k++)
							dropPinball(i, j, k);
					}						
				}
			}
			
			sb.append("#"+t+" "+maxScore+"\n");
		}// �׽�Ʈ���̽� ��
		
		System.out.print(sb.toString());
	}
	
	
	
	private static void dropPinball(int x, int y, int dir) {
		int rx = x;
		int ry = y;
		int score = 0;
		rx = x + dx[dir];
		ry = y + dy[dir];
		while(true) {
			if(rx>=0 && rx<N && ry>=0 && ry<N) { //���� ��
				
				int mapvalue = map[rx][ry];
				if(x==rx && y==ry)	break; // ���� �ڸ��� ���ƿ��� ���� ��
				if(mapvalue == -1) break; // ��Ȧ ������ ���� ��
				
				if(mapvalue == 0) {
					rx = rx + dx[dir];
					ry = ry + dy[dir];
					continue; //������̸� ���
				}
				else { //���̳� ��Ȧ�� �ִ� ����
					if(mapvalue>=1 && mapvalue<=5) { //�� -> ����ٲ��ְ�
						dir = changeDir(dir, mapvalue);
						score++;
					}else { //��Ȧ -> ��ġ �ٲ��ְ�
						ArrayList<int[]> tmp = wormhole.get(mapvalue);
						if(tmp.get(0)[0]==rx && tmp.get(0)[1]==ry) {
							rx = tmp.get(1)[0];
							ry = tmp.get(1)[1];
						}else { // if(tmp.get(1)[0]==rx && tmp.get(1)[1]==ry){
							rx = tmp.get(0)[0];
							ry = tmp.get(0)[1];
						}
					}
				}
			}else { //���϶�
				score++;
				if(rx == -1)	dir = 2;
				else if(rx == N)	dir = 0;
				else if(ry == -1)	dir = 1;
				else if(ry == N)	dir = 3;
			}	
			
			rx = rx + dx[dir];
			ry = ry + dy[dir];
		}
		maxScore = Math.max(maxScore, score);	
	}
	
	private static int changeDir(int existDir, int block) { // existDir: ��������, block: �����
		int changeDir = -1;
		if(block==5 || block==0) { // block�� �׸��̰ų� ���϶�
			switch(existDir) {
			case 0: changeDir = 2;	break;
			case 1: changeDir = 3;	break;
			case 2: changeDir = 0;	break;
			case 3: changeDir = 1;	break;
			}
		}else { // ���� 1~4�����϶�
			int next = DirbyBlock[block].indexOf(existDir) + 1;
			if(next != 4)
				changeDir = DirbyBlock[block].get(next);
			else
				changeDir = DirbyBlock[block].get(0);
		}
		return changeDir;
	}
	
	private static void initializeList() {
		DirbyBlock[1] = new ArrayList<>(Arrays.asList(0, 2, 1, 3));
		DirbyBlock[2] = new ArrayList<>(Arrays.asList(0, 1, 3, 2));
		DirbyBlock[3] = new ArrayList<>(Arrays.asList(0, 3, 1, 2));
		DirbyBlock[4] = new ArrayList<>(Arrays.asList(0, 2, 3, 1));
		//5�� 0<->2, 1<->3
	}
}
