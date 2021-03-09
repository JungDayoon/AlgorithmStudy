import java.io.*;

public class _60059 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int[][] key = new int[M][M];
		int[][] lock = new int[N][N];
		String[] s;
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++)
				key[i][j] = Integer.parseInt(s[j]);
		}
		
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++)
				lock[i][j] = Integer.parseInt(s[j]);
		}
		
		System.out.print(solution(key, lock));
	}
	
	private static boolean solution(int[][] key, int[][] lock) {
		boolean ans = false;
		int N = lock.length;
		int M = key.length;
		int LockN = N + 2*M -2;
		int[][] Lock = new int[LockN][LockN];
		int lx = 0;
		int ly = 0;
		int holeCnt = 0;
		for(int i=M-1; i<M-1+N; i++) {
			ly = 0;
			for(int j=M-1; j<M-1+N; j++) {
				if(lock[lx][ly] == 0)	holeCnt++; // �ڹ����� Ȩ ����
				Lock[i][j] = lock[lx][ly++];
			}
			lx++;
		}

		for(int k=0; k<4; k++) { // 4���� ����ȸ���� key ��η� Ȯ��
			boolean flag = false;
			
			for(int i=0; i<=N+M-2; i++) {
				for(int j=0; j<=N+M-2; j++) { // [i][j]�� ������
					if(ChkOpen(i, j, M, N, key, Lock, holeCnt)) {
						flag = true; // ����
						break;
					}
				}
				if(flag)
					break;
			}
			
			if(flag) {
				ans = true;
				break; // �վ����� �׸�
			}
			rotateKey(key);
		} // ���ص� �ȵǸ� false
		
		return ans;
	}
	
	private static boolean Valid(int x, int y, int M, int N) {
		return (x>=(M-1) && x<(M+N-1) && y>=(M-1) && y<(M+N-1)); 
	}
	
	private static boolean ChkOpen(int x, int y, int M, int N, int[][] key, int[][] Lock, int holeCnt) {		
		int kx = 0;
		int ky = 0;
		int cnt = 0; // �ڹ����� Ȩ�� ���谡 �� �¾Ƶ�����
		boolean flag = true;
		for(int i=x; i<x+M; i++) {
			ky = 0;
			for(int j=y; j<y+M; j++) {
				if(Valid(i, j, M, N)) {
					int l = Lock[i][j];
					int k = key[kx][ky];
					
					if(l == k) {
						flag = false; // (lock=1 & key=1) (lock=0 & key=0)
						break;
					}
					if(l==0 && k==1)	cnt++;
				}
				ky++;
			}
			if(!flag) break;
			kx++;
		}
		if(!flag || cnt!=holeCnt)	return false;
		return true;
	}
	
	private static void rotateKey(int[][] key) { // key�� �ð���� 90�� ȸ��
		int M = key.length-1;
		int[][] tmp = new int[M+1][M+1];
		for(int i=0; i<M+1; i++)
			tmp[i] = key[i].clone();
		
		for(int i=0; i<M+1; i++) {
			for(int j=0; j<M+1; j++) {
				int kidx = Math.abs(M-j);
				key[i][j] = tmp[kidx][i];
			}
		}
	}
}
