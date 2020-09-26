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
			
			int rowCnt = 0; //���� Ȱ�ַ� ����
			for(int i=0; i<N; i++) {
				boolean[] slope = new boolean[N];
				int flag = 1;
				for(int j=0; j<N-1; j++) {
					if(Math.abs(map[i][j]-map[i][j+1]) >= 2) { //2ĭ �̻� ���̳��� ���� X
						flag = 0;
						break;
					}else if(map[i][j]-map[i][j+1] == 1) { // ������ ������ �� 
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
					}else if(map[i][j]-map[i][j+1] == -1) { // ������ ������ ��
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
				
				if(flag == 1) { //Ȱ�ַ� �Ǽ� ����
					rowCnt++;
					//System.out.println("���� "+i+"��° Ȱ�ַ� ����");
				}
			}
			
			int colCnt = 0; // ���� Ȱ�ַ� ��
			for(int i=0; i<N; i++) {
				boolean[] slope = new boolean[N];
				int flag = 1;
				for(int j=0; j<N-1; j++) {
					if(Math.abs(map[j][i]-map[j+1][i]) >= 2) { //2ĭ �̻� ���̳��� ���� X
						flag = 0;
						break;
					}else if(map[j][i]-map[j+1][i] == 1) { // ������ ������ �� 
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
					}else if(map[j][i]-map[j+1][i] == -1) { // ������ ������ ��
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
				
				if(flag == 1) { //Ȱ�ַ� �Ǽ� ����
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
