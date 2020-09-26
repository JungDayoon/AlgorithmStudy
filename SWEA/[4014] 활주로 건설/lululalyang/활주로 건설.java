import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4014 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] result = new int[T];
		
		for(int t=1; t<=T; t++) {
			String[] s = br.readLine().split(" ");
			int N = Integer.parseInt(s[0]);
			int X = Integer.parseInt(s[1]);
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
				}
			}
			
			int rowCnt = 0; //가로 활주로 개수
			for(int i=0; i<N; i++) {
				boolean[] slope = new boolean[N];
				int flag = 1;
				for(int j=0; j<N-1; j++) {
					if(Math.abs(map[i][j]-map[i][j+1]) >= 2) { //2칸 이상 차이나면 경사로 X
						flag = 0;
						break;
					}else if(map[i][j]-map[i][j+1] == 1) { // 다음이 낮아질 때 
						int endk = 0;
						for(int k=j+1; k<j+X; k++) {
							if((k+1)>=N || map[i][k]!=map[i][k+1]) {
								flag = 0;
								break;
							}
							slope[k] = true;
							endk = k;
						}
						slope[endk+1] = true;
						j=endk;
					}else if(map[i][j]-map[i][j+1] == -1) { // 다음이 높아질 때
						int endk = 0;
						for(int k=j; k>j-X+1; k--) {
							if((k-1)<0 || map[i][k]!=map[i][k-1] || slope[k-1]==true) {
								flag=0;
								break;
							}
							slope[k] = true;
							endk = k;
						}
						slope[endk] = true;
					}
					
					if(flag == 0)
						break;
				}
				
				if(flag == 1) { //활주로 건설 가능
					rowCnt++;
					//System.out.println("가로 "+i+"번째 활주로 가능");
				}
			}
			
			int colCnt = 0; // 세로 활주로 수
			for(int i=0; i<N; i++) {
				boolean[] slope = new boolean[N];
				int flag = 1;
				for(int j=0; j<N-1; j++) {
					if(Math.abs(map[j][i]-map[j+1][i]) >= 2) { //2칸 이상 차이나면 경사로 X
						flag = 0;
						break;
					}else if(map[j][i]-map[j+1][i] == 1) { // 다음이 낮아질 때 
						int endk = 0;
						for(int k=j+1; k<j+X; k++) {
							if((k+1)>=N || map[k][i]!=map[k+1][i]) {
								flag = 0;
								break;
							}
							slope[k] = true;
							endk = k;
						}
						slope[endk+1] = true;
						j=endk;
					}else if(map[j][i]-map[j+1][i] == -1) { // 다음이 높아질 때
						int endk = 0;
						for(int k=j; k>j-X+1; k--) {
							if((k-1)<0 || map[k][i]!=map[k-1][i] || slope[k-1]==true) {
								flag=0;
								break;
							}
							slope[k] = true;
							endk = k;
						}
						slope[endk] = true;
					}
					
					if(flag == 0)
						break;
				}
				
				if(flag == 1) { //활주로 건설 가능
					colCnt++;
				}
			}
			
			result[t-1] = rowCnt + colCnt;
		}

		for(int i=0;i<T; i++) {
			System.out.println("#"+(i+1)+" "+result[i]);
		}
	}

}
