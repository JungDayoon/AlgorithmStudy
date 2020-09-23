import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _4013 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		int[][] M = new int[4][8]; 
		int[] result = new int[T];
		for(int t=1; t<=T; t++) {
			int K = Integer.parseInt(br.readLine()); // 회전 횟 수
			for(int i=0; i<4; i++) { 
				String[] s = br.readLine().split(" ");
				for(int j=0; j<8; j++) {
					M[i][j] = Integer.parseInt(s[j]); //4개의 자석의 자성정보
				}
			}
			
			for(int i=0; i<K; i++) { //회전 횟 수 만큼
				String[] s = br.readLine().split(" ");
				int magNum = Integer.parseInt(s[0]) - 1; //회전시키려는 자석의 번호
				int direction = Integer.parseInt(s[1]); //회전방향
				
				
				if(direction == 1) {	
					RotateClock(M[magNum]);
					int flag = 1;
					
					Bigger(M, flag, magNum);
					flag = 1;
					Smaller(M, flag, magNum);
				}else { //direction == 0
					RotateCounterClock(M[magNum]);
					int flag = 0;
					
					Bigger(M, flag, magNum);
					flag = 0;
					Smaller(M, flag, magNum);
				}
			}
			
			int resultSum = 0;
			int multiple = 1;
			for(int i=0; i<4; i++) {
				resultSum = resultSum + (multiple * M[i][0]);
				multiple*=2;
			}
			
			//System.out.println("#"+t+" "+resultSum);
			result[t-1] = resultSum;
		}//테스트케이스 종료
		
		for(int i=0; i<T; i++) {
			System.out.println("#"+(i+1)+" "+result[i]);
		}
	}
	
	static void Bigger(int[][] M, int flag, int magNum) {
		for(int j=magNum+1; j<4; j++) {
			if(flag == 1) {
				if(M[j][6] == M[j-1][3]) {
					break;
				}else { //인력에 의해 회전해야하는 경우
					RotateCounterClock(M[j]);
					flag = 0;
				}
			}else { //flag == 0: 이전에 반시계로 회전한 경우
				if(M[j][6] == M[j-1][1]) {
					break;
				}else { //인력에 의해 회전해야하는 경우
					RotateClock(M[j]);
					flag = 1;
				}
			}
		}
	}
	
	static void Smaller(int[][] M, int flag, int magNum) {
		for(int j=magNum-1; j>=0; j--) {
			if(flag == 1) {
				if(M[j][2] == M[j+1][7]) {
					break;
				}else {
					RotateCounterClock(M[j]);
					flag = 0;
				}
			}else { //flag == 0
				if(M[j][2] == M[j+1][5]) {
					break;
				}else {
					RotateClock(M[j]);
					flag = 1;
				}
			}
		}
	}
	
	static void RotateClock(int[] m) { // 시계방향
		int[] res = new int[8];
		for(int i=0; i<8; i++) {
			if(i == 7) { 	
				res[0] = m[7];
				break;
			}		
			res[i+1] = m[i];
		}
		for(int i=0; i<8; i++)	m[i] = res[i];
	}
	
	static void RotateCounterClock(int[] m) { // 반시계 방향
		int[] res = new int[8];
		for(int i=0; i<8; i++) {
			if(i == 0) { 	
				res[7] = m[0];
				continue;
			}		
			res[i-1] = m[i];
		}
		for(int i=0; i<8; i++)	m[i] = res[i];
	}
}
