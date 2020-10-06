import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class _2117 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] result = new int[T];
		for(int t=0; t<T; t++) {
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]); //������ ũ�� N
			int M = Integer.parseInt(s[1]); //�ϳ��� ���� ������ �� �ִ� ��� M
			int[][] map = new int[N][N];
			ArrayList<int []> house = new ArrayList<int []>();
			
			for(int i=0; i<N; i++) {
				String[] d = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(d[j]);
					if(map[i][j] == 1) {
						house.add(new int[] {i, j});
					}
				}
			}
			
		
			int housecnt = 1;
			int houseMaxcnt = 0;
			for(int K=1; K<=N+1; K++) {
				int cost = K*K + (K-1)*(K-1); //� ���
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						housecnt = check(K, i, j, house);
						if((housecnt*M - cost) >= 0) { //���ذ� �ƴ϶��
							houseMaxcnt = Math.max(houseMaxcnt, housecnt);
						}
					}
				}
			}
			result[t] = houseMaxcnt;
		}
		
		for(int t=0; t<T; t++) {
			System.out.println("#"+(t+1)+" "+result[t]);
		}
	}
	
	static int check(int K, int i, int j, ArrayList<int[]> house) {
		int hcnt = 0;
		for(int[] h : house) {
			if((Math.abs(i-h[0]) + Math.abs(j-h[1])) < K) { //K������ ���� �ȿ� ���ϸ�
				hcnt++;
			}
		}
		
		return hcnt;
	}
}
