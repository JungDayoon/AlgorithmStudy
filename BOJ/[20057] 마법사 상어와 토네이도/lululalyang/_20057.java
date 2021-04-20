import java.io.*;
import java.util.*;
public class _20057 {
	static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1, -2, 0, 2, 0};
	static int[] dy = {-1, 0, 1, 0, -1, -1, 1, 1, 0, -2, 0, 2}; 
	static Set<Integer> perc = new HashSet<>(Arrays.asList(7, 10, 5, 2, 1));
	static Map<Integer, ArrayList<Integer>>[] percLoc = new HashMap[4]; // �� ���⿡ ���� ��� ��ġ
	static int[][] map;
	static Loc now;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		Initialize();
		
		System.out.print(solution(N));
	}
	
	private static int solution(int N) {
		int Out = 0;
		now = new Loc(N/2, N/2);
		
		int dir = 0;
		int movecnt = 1;
		boolean flag = false;
		while(true) {
			Out += Move(dir, movecnt, N);
			if(movecnt == (N-1) && flag) break;
			if(!flag)	flag = true;
			else {
				flag = false;
				movecnt++;
			}
			dir++;
			if(dir == 4)	dir = 0;
		}
		
		Out += Move(0, N-1, N);
		return Out;
	}
	
	private static int Move(int dir, int movecnt, int N) { // dir��������, movecntĭ��ŭ �̵�
		int Minus;
		int Out = 0;
		for(int i=0; i<movecnt; i++) {
			now.x += dx[dir];
			now.y += dy[dir];
			Minus = 0;
			
			int nowAll = map[now.x][now.y]; // ���� ĭ�� �� ��
			for(Integer per : perc) {
				int amount = nowAll * per / 100; // �̵����Ѿ��ϴ¾�
				ArrayList<Integer> loc = percLoc[dir].get(per); // �� �ۼ�Ʈ�� �Ű�����ϴ� ��ġ loc
				for(Integer l : loc) {
					int rx = now.x + dx[l];
					int ry = now.y + dy[l];
					
					if(Valid(rx, ry, N))	map[rx][ry] += amount; // ���������, ���� ���� �����ְ�
					else	Out += amount; // ���� ���̶�� ������ ���� �𷡾翡 �����ش�
					
					Minus += amount; // ���� ��ġ���� ���� �𷡸� ���Ѵ�
				}
			}
			
			int ax = now.x + dx[dir]; // ������ġ
			int ay = now.y + dy[dir];
			if(Valid(ax, ay, N))	map[ax][ay] += (nowAll - Minus);
			else	Out += (nowAll - Minus);
			
			map[now.x][now.y] = 0; // ���� ĭ�� ���� �� ����
		}
		
		return Out;
	} 
	
	private static boolean Valid(int x, int y, int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	private static void Initialize() {
		for(int k=0; k<4; k++)	percLoc[k] = new HashMap<>();
		percLoc[0].put(7, new ArrayList<>(Arrays.asList(3, 1)));
		percLoc[0].put(10, new ArrayList<>(Arrays.asList(4, 5)));
		percLoc[0].put(5, new ArrayList<>(Arrays.asList(9)));
		percLoc[0].put(2, new ArrayList<>(Arrays.asList(8, 10)));
		percLoc[0].put(1, new ArrayList<>(Arrays.asList(7, 6)));
		
		percLoc[1].put(7, new ArrayList<>(Arrays.asList(0, 2)));
		percLoc[1].put(10, new ArrayList<>(Arrays.asList(5, 6)));
		percLoc[1].put(5, new ArrayList<>(Arrays.asList(10)));
		percLoc[1].put(2, new ArrayList<>(Arrays.asList(9, 11)));
		percLoc[1].put(1, new ArrayList<>(Arrays.asList(4, 7)));
		
		percLoc[2].put(7, new ArrayList<>(Arrays.asList(3, 1)));
		percLoc[2].put(10, new ArrayList<>(Arrays.asList(7, 6)));
		percLoc[2].put(5, new ArrayList<>(Arrays.asList(11)));
		percLoc[2].put(2, new ArrayList<>(Arrays.asList(8, 10)));
		percLoc[2].put(1, new ArrayList<>(Arrays.asList(4, 5)));
		
		percLoc[3].put(7, new ArrayList<>(Arrays.asList(0, 2)));
		percLoc[3].put(10, new ArrayList<>(Arrays.asList(4, 7)));
		percLoc[3].put(5, new ArrayList<>(Arrays.asList(8)));
		percLoc[3].put(2, new ArrayList<>(Arrays.asList(9, 11)));
		percLoc[3].put(1, new ArrayList<>(Arrays.asList(5, 6)));
	}
	
	private static class Loc{
		int x;
		int y;
		
		Loc(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
