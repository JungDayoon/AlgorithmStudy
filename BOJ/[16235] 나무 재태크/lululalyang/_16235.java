import java.io.*;
import java.util.*;
public class _16235 {
	static int[][] A; // 겨울에 뿌리는 양분
	static int[][] ground; // 땅의 양분
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayList<Integer>[][] Treeage; // 각위치의 나무의 나이 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); // 땅 크기
		int M = Integer.parseInt(s[1]); // 나무 개수
		int K = Integer.parseInt(s[2]); // K년 후
		
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
				if(!Treeage[i][j].isEmpty()) { // 나무가 있으면
					Collections.sort(Treeage[i][j]);
					int die = -1;
					for(int k=0; k<Treeage[i][j].size(); k++) {
						int now = Treeage[i][j].get(k);
						if((ground[i][j]-now) >= 0) { // 땅에 나무의 나이만큼 양분이 있다면
							 ground[i][j] -= now; // 땅의 양분 먹고
							 Treeage[i][j].set(k, now+1); // 나이 +1
						}else {
							die = k; // die번째부터 나무 죽는다
							break;
						}
					}
					
					if(die == -1)	continue; // 죽은 나무가 없으면 넘어간다
					for(int k=die; k<Treeage[i][j].size(); k++) {
						int dieAge = Treeage[i][j].get(k);
						dieTree[i][j] += (dieAge/2); // 죽은 나무 양분의 /2만큼 여름에 땅의 양분으로
						Treeage[i][j].remove(k); // 나무 죽는다
						k--;
					}
				}
			}
		}
	}
	
	private static void Summer(int N, int[][] dieTree) { // 죽은 나무 양분만큼 땅의 양분으로
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				ground[i][j] += dieTree[i][j];
			}
		}
	}
	
	private static void Autumn(int N) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!Treeage[i][j].isEmpty()) { // 나무가 있다면
					for(Integer age : Treeage[i][j]) {
						if(age%5 == 0) { // 5의 배수라면
							for(int k=0; k<8; k++) {
								int rx = i + dx[k];
								int ry = j + dy[k];
								
								if(Valid(N, rx, ry)) {
									Treeage[rx][ry].add(1); // 나이 1인 나무가 생긴다
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
		int res = 0; // 살아남은 나무의 수
		int year = 0;
		
		while(year < K) {
			int[][] dieTree = new int[N+1][N+1]; // 죽은 나무의 양분
			Spring(N, dieTree); // 봄
			Summer(N, dieTree); // 여름
			Autumn(N); // 가을
			Winter(N); // 겨울
			year++;
		}
		
		res = CountTree(N);
		return res;
	}
}
