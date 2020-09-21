import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _5644 {
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int M;
		int A;
		int[] userA;
		int[] userB;
		int[][] BC;
		String[] s;
		int[][] dMove = {{0,0}, {0,-1}, {1,0}, {0,1}, {-1,0}};
		for(int t=1; t<=T; t++) { //테스트케이스 수 만큼
			
			s = br.readLine().split(" ");
			
			M = Integer.parseInt(s[0]); //총 이동 시간
			A = Integer.parseInt(s[1]); //BC 개수
			
			s = br.readLine().split(" ");
			userA = new int[M];
			for(int i=0; i<M; i++) {
				userA[i] = Integer.parseInt(s[i]); //사용자 A의 이동동선
			}
			s = br.readLine().split(" ");
			userB = new int[M];
			for(int i=0; i<M; i++) {
				userB[i] = Integer.parseInt(s[i]); //사용자 B의 이동동선
			}
			
			BC = new int[A][4]; //0:x / 1:y / 2:C / 3:P 
			for(int i=0; i<A; i++) {
				s = br.readLine().split(" ");
				BC[i][0] = Integer.parseInt(s[0]);
				BC[i][1] = Integer.parseInt(s[1]);
				BC[i][2] = Integer.parseInt(s[2]);
				BC[i][3] = Integer.parseInt(s[3]);
			}
			
			int A_x = 1; int A_y = 1; //A, B의 현재 위치좌표
			int B_x = 10; int B_y = 10;
			int MaxSum = 0;
			
			for(int i=0; i<=M; i++) { //이동할때마다
				int ABSum = 0;
				for(int j=0; j<A; j++) {
					int A_D = Math.abs(A_x - BC[j][0]) + Math.abs(A_y - BC[j][1]);
					int ASum = 0;
					int BSum = 0;
					for(int k=0; k<A; k++) {
						int B_D = Math.abs(B_x - BC[k][0]) + Math.abs(B_y - BC[k][1]);
						if(A_D <= BC[j][2] && B_D <= BC[k][2] && j==k) {
							ASum = BC[j][3] / 2;
							BSum = BC[j][3] / 2;
						}else if(A_D <= BC[j][2] && B_D <= BC[k][2]){
							ASum = BC[j][3];
							BSum = BC[k][3];
						}else  if(A_D <= BC[j][2] && B_D > BC[k][2]) {	
							ASum = BC[j][3];
							BSum = 0;
						}else if(A_D > BC[j][2] && B_D <= BC[k][2])	{
							BSum = BC[k][3];
							ASum = 0;
						}
						ABSum = Math.max(ABSum, ASum+BSum);	
					}
				}
				
				MaxSum = MaxSum + ABSum;
				if(i==M) break;
				
				//userA,B 위치 이동
				if((A_x+dMove[userA[i]][0])>0 && (A_x+dMove[userA[i]][0])<=10)	 A_x += dMove[userA[i]][0];
				if((A_y+dMove[userA[i]][1])>0 && (A_y+dMove[userA[i]][1])<=10)	 A_y += dMove[userA[i]][1];
				if((B_x+dMove[userB[i]][0])>0 && (B_x+dMove[userB[i]][0])<=10)	 B_x += dMove[userB[i]][0];
				if((B_y+dMove[userB[i]][1])>0 && (B_y+dMove[userB[i]][1])<=10)	 B_y += dMove[userB[i]][1];
			}
			System.out.println("#"+t+" "+MaxSum);
		}
		br.close();
	}
}
