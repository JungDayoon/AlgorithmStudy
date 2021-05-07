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
		
		if(!flag) { // 남은 1이 없는 것
			Min = Math.min(Min, cnt); // 그 때의 색종이 개수 갱신
			return;
		}
		
		int[][] tmp = ArrClone(prev);
		for(int size=1; size<=5; size++) { // 색종이 크기
			if(paper[size]>0 && CheckValid(prev, i, j, size)) { // 이 크기의 색종이가 남아있고, 이 위치에 놓을 수 있다면
				for(int a=i; a<i+size; a++) 
					for(int b=j; b<j+size; b++)	tmp[a][b] = 2; // 색종이 놓는다
				paper[size]--; // 하나 사용
				
				backTracking(tmp, i, j, cnt+1);
				
				for(int a=i; a<i+size; a++) 
					for(int b=j; b<j+size; b++)	tmp[a][b] = prev[a][b]; // 색종이 다시 뺀다
				paper[size]++; // 복구
			}
		}
	}
	
	private static boolean CheckValid(int[][] map, int x, int y, int size) { // 색종이 붙일 수 있는지 확인
		int N = x + size;
		int M = y + size;
		for(int i=x; i<N; i++) {
			for(int j=y; j<M; j++) {
				if(!Valid(i, j))	return false; // 경계 밖이면 x
				if(map[i][j] != 1)	return false; // 겹쳐도 x, 0이어도 x
			}
		}
		return true; // 모두 경계 내이고, 1일 때
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
