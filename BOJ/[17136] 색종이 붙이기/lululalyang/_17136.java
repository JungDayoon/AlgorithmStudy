import java.io.*;
public class _17136 {
	static int Min = Integer.MAX_VALUE;
	static int[] paper = {0, 5, 5, 5, 5, 5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[10][10];
		String[] s;
		for(int i=0; i<10; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		backTracking(map, 0, 0, 0);
		if(Min == Integer.MAX_VALUE)	Min = -1;
		System.out.print(Min);
	}
	
	private static void backTracking(int[][] prev, int x, int y, int cnt) {
		boolean flag = false;
		int i=0, j=0;
		for(i=x; i<10; i++) {
			for(j=0; j<10; j++) {
				if(prev[i][j] == 1) {
					flag = true;
					break;
				}
			}
			if(flag)	break;
		}
		
		if(!flag) { // ���� 1�� ���� ��
			Min = Math.min(Min, cnt); // �� ���� ������ ���� ����
			return;
		}
		
		int[][] tmp = ArrClone(prev);
		for(int size=1; size<=5; size++) { // ������ ũ��
			if(paper[size]>0 && CheckValid(prev, i, j, size)) { // �� ũ���� �����̰� �����ְ�, �� ��ġ�� ���� �� �ִٸ�
				for(int a=i; a<i+size; a++) 
					for(int b=j; b<j+size; b++)	tmp[a][b] = 2; // ������ ���´�
				paper[size]--; // �ϳ� ���
				
				backTracking(tmp, i, j, cnt+1);
				
				for(int a=i; a<i+size; a++) 
					for(int b=j; b<j+size; b++)	tmp[a][b] = prev[a][b]; // ������ �ٽ� ����
				paper[size]++; // ����
			}
		}
	}
	
	private static boolean CheckValid(int[][] map, int x, int y, int size) { // ������ ���� �� �ִ��� Ȯ��
		int N = x + size;
		int M = y + size;
		for(int i=x; i<N; i++) {
			for(int j=y; j<M; j++) {
				if(!Valid(i, j))	return false; // ��� ���̸� x
				if(map[i][j] != 1)	return false; // ���ĵ� x, 0�̾ x
			}
		}
		return true; // ��� ��� ���̰�, 1�� ��
	}
	
	private static boolean Valid(int x, int y) {
		return (x>=0 && x<10 && y>=0 && y<10);
	}
	
	private static int[][] ArrClone(int[][] A){
		int[][] tmp = new int[10][10];
		for(int i=0; i<10; i++)	tmp[i] = A[i].clone();
		return tmp;
	}
}
