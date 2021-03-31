import java.io.*;
import java.util.*;
public class _16235 {
	static int[][] A; // �ܿ￡ �Ѹ��� ���
	static int[][] ground; // ���� ���
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<Integer>[][] Treeage; // ����ġ�� ������ ���� 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // �� ũ��
		int M = Integer.parseInt(s[1]); // ���� ����
		int K = Integer.parseInt(s[2]); // K�� ��
		
		Treeage = new ArrayList[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++)	Treeage[i][j] = new ArrayList<>();
		}
		
		ground = new int[N+1][N+1];
		A = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			s = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				A[i][j] = Integer.parseInt(s[j-1]);
			}
			Arrays.fill(ground[i], 5);
		}
		
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			Treeage[Integer.parseInt(s[0])][Integer.parseInt(s[1])].add(Integer.parseInt(s[2]));
		}
		
		System.out.print(solution(N, M, K));
	}
	
	private static void Spring(int N, int[][] dieTree) {
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!Treeage[i][j].isEmpty()) { // ������ ������
					Collections.sort(Treeage[i][j]);
					int die = -1;
					for(int k=0; k<Treeage[i][j].size(); k++) {
						int now = Treeage[i][j].get(k);
						if((ground[i][j]-now) >= 0) { // ���� ������ ���̸�ŭ ����� �ִٸ�
							 ground[i][j] -= now; // ���� ��� �԰�
							 Treeage[i][j].set(k, now+1); // ���� +1
						}else {
							die = k; // die��°���� ���� �״´�
							break;
						}
					}
					
					if(die == -1)	continue; // ���� ������ ������ �Ѿ��
					for(int k=die; k<Treeage[i][j].size(); k++) {
						int dieAge = Treeage[i][j].get(k);
						dieTree[i][j] += (dieAge/2); // ���� ���� ����� /2��ŭ ������ ���� �������
						Treeage[i][j].remove(k); // ���� �״´�
						k--;
					}
				}
			}
		}
	}
	
	private static void Summer(int N, int[][] dieTree) { // ���� ���� ��и�ŭ ���� �������
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				ground[i][j] += dieTree[i][j];
			}
		}
	}
	
	private static void Autumn(int N) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!Treeage[i][j].isEmpty()) { // ������ �ִٸ�
					for(Integer age : Treeage[i][j]) {
						if(age%5 == 0) { // 5�� ������
							for(int k=0; k<8; k++) {
								int rx = i + dx[k];
								int ry = j + dy[k];
								
								if(Valid(N, rx, ry)) {
									Treeage[rx][ry].add(1); // ���� 1�� ������ �����
								}
							}
						}
					}
				}
			}
		}
	}
	
	private static boolean Valid(int N, int r, int c) {
		return (r>=1 && r<=N && c>=1 && c<=N);
	}
	
	private static void Winter(int N) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				ground[i][j] += A[i][j];
			}
		}
	}
	
	private static int CountTree(int N) {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				cnt += Treeage[i][j].size();
			}
		}
		
		return cnt;
	}
	
	private static int solution(int N, int M, int K) {
		int res = 0; // ��Ƴ��� ������ ��
		int year = 0;
		
		while(year < K) {
			int[][] dieTree = new int[N+1][N+1]; // ���� ������ ���
			Spring(N, dieTree); // ��
			Summer(N, dieTree); // ����
			Autumn(N); // ����
			Winter(N); // �ܿ�
			year++;
		}
		
		res = CountTree(N);
		return res;
	}
}
