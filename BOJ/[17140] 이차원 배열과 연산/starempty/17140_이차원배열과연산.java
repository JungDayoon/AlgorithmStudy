import java.util.*;

public class Main_bj_17140_이차원배열과연산 {
	public static class node implements Comparable<node>{
		int n, cnt;
		public node(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(node o) {
			int dif = this.cnt - o.cnt;
			return dif==0?this.n-o.n:dif;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt()-1;
		int c = sc.nextInt()-1;
		int k = sc.nextInt();
//		System.out.println(r+" "+c+" "+k);
		int[][] arr = new int[100][100];
		int[] count = new int[101];
		int row = 3, col = 3;
		for(int i = 0; i < col; i++) {
			for(int j = 0; j < row; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
//		//입력확인
//		for(int[] a: arr) System.out.println(Arrays.toString(a));
		int t;
		for(t = 0; t < 101; t++) {
			if(arr[r][c] == k) break;
			
			LinkedList<node> list = new LinkedList<>();
			if(col >= row) {//R연산
				int rsize = 0;
				for(int x = 0; x < col; x++) {
					for(int y = 0; y < row; y++) {
						count[arr[x][y]]++;
						arr[x][y] = 0;
					}
					for(int i = 1; i < count.length; i++) {
						if(count[i] != 0) list.add(new node(i, count[i]));
					}
					
					Collections.sort(list);
					
					int idx = 0;
					for(node n: list) {
						arr[x][idx++] = n.n;
						arr[x][idx++] = n.cnt;
					}
					rsize = Math.max(rsize, idx);
					
					list.clear();
					Arrays.fill(count, 0); //count배열 초기화
				}
				row = rsize;
			}
			else {//C연산
				int csize = 0;
				for(int x = 0; x < row; x++) {
					for(int y = 0; y < col; y++) {
						count[arr[y][x]]++;
						arr[y][x] = 0;
					}
					for(int i = 1; i < count.length; i++) {
						if(count[i] != 0) list.add(new node(i, count[i]));
					}
					Collections.sort(list);
					
					int idx = 0;
					for(node n: list) {
						arr[idx++][x] = n.n;
						arr[idx++][x] = n.cnt;
					}
					csize = Math.max(csize, idx);
					
					list.clear();
					Arrays.fill(count, 0); //count 배열 초기화
				}
				col = csize;
			}
		}
		System.out.println(arr[r][c]==k?t:-1);
		sc.close();
	}
}
