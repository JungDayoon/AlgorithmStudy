import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class _2580 {
	static int flag = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[10][10];
		LinkedList<int []> zero = new LinkedList();
		int zeroN = 0;
		
		for(int i=1; i<10; i++) {
			String[] s = br.readLine().split(" ");
			for(int j=1; j<10; j++) {
				map[i][j] = Integer.parseInt(s[j-1]);
				if(map[i][j] == 0) zeroN++;
			}
		}
		
		sudoku(map, zeroN);
	}
	
	static void sudoku(int[][] map, int zeroN) {
		if(zeroN == 0) {
			for(int i=1; i<10; i++) {
				for(int j=1; j<10; j++) {
					System.out.print(map[i][j]+" ");
				}
				if(i!=9)
					System.out.println();
			}
			System.exit(0);
		}
		else {
			for(int i=1; i<10; i++) {
				for(int j=1; j<10; j++) {
					if(map[i][j] == 0) {
						LinkedList<Integer> tmp = check(map, i, j);
						if(tmp == null)		return;
						else {
							Iterator<Integer> iter = tmp.iterator();
							while(iter.hasNext()) {
								map[i][j] = iter.next();
								sudoku(map, zeroN-1);
								map[i][j] = 0;
							}
							return;
						}
					}
				}
			}
		}
	}
	
	static LinkedList<Integer> check(int[][] map, int x, int y)
	{
		boolean[] c = new boolean[10]; //1~9�߿� �� �ڸ��� �� �� �ִ� �� ����=>F (�ִ� ���� T, ���� ���� F)
		
		for(int i=1; i<10; i++) {
			if(map[x][i] != 0) c[map[x][i]] = true; //�̹� �ִ� ��
			if(map[i][y] != 0) c[map[i][y]] = true; //�̹� �ִ� ��
		}
		
		int modx = x % 3;
		int mody = y % 3;
		if(modx == 1) {
			for(int i=x; i<=x+2; i++) {
				if(mody == 0) {
					for(int j=y-2; j<=y; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}else if(mody == 1) {
					for(int j=y; j<=y+2; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}else { // mody == 2
					for(int j=y-1; j<=y+1; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}
			}
		}else if(modx == 2) {
			for(int i=x-1; i<=x+1; i++) {
				if(mody == 0) {
					for(int j=y-2; j<=y; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}else if(mody ==1) {
					for(int j=y; j<=y+2; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}else { //mody == 2
					for(int j=y-1; j<=y+1; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}
			}
		}else { //modx == 0
			for(int i=x-2; i<=x; i++) {
				if(mody == 0) {
					for(int j=y-2; j<=y; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}else if(mody == 1) {
					for(int j=y; j<=y+2; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}else { //mody==2
					for(int j=y-1; j<=y+1; j++) {
						if(map[i][j] != 0) c[map[i][j]] = true;
					}
				}
			}
		}
		
		LinkedList<Integer> possN = new LinkedList<Integer>(); 
		for(int i=1; i<10; i++) {
			if(c[i] == false) //������ ����
				possN.add(i);
		}
		if(possN.size() == 0)	return null; //������ ���� ���� ��
		else	return possN;
		
	}
	
}
