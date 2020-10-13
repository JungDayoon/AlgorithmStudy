import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _2383 {
	static ArrayList<ArrayList<Integer>> C = new ArrayList<ArrayList<Integer>>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			int minTime = 2000000000;
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] stairs = new int[2][3]; // 계단의 x, y좌표, 높이
			int sIndex = 0;
			ArrayList<int []> q = new ArrayList<>();
			for(int i=0; i<N; i++) {
				String[] s = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(s[j]);
					if(map[i][j] > 1) { //계단이면
						stairs[sIndex][0] = i;
						stairs[sIndex][1] = j;
						stairs[sIndex++][2] = map[i][j];
					}else if(map[i][j] == 1) { //사람이면
						q.add(new int[] {i, j}); //위치 큐에 저장
					}
				}
			}
			
			for(int i=0; i<=q.size()/2; i++) {
				ArrayList<Integer> tmp = new ArrayList<>();
				comb(q.size(), i, 0, tmp);
				ArrayList<Integer> C2 = new ArrayList<Integer>();
				for(ArrayList<Integer> a : C) {
					for(int j=0; j<q.size(); j++) {
						if(!a.contains(j))
							C2.add(j);
					}
					
					minTime = Math.min(minTime, distToStairs(a, C2, stairs, q));
					minTime = Math.min(minTime, distToStairs(C2, a, stairs, q));
					C2.clear();
				}
				C.clear();
			}
			
			System.out.println("#"+t+" "+minTime);
			//result[t] = minTime;
		}
	}
	
	static int move(ArrayList<Integer> dist, ArrayList<Integer> s, int dN, int N) {
		for(int i=0; i<N; i++) {
			if(dist.get(i)!=-100) { //현재 존재하면 (-100이 아니면)
				if(dist.get(i) == -1) { //거리값이 -1이면 내려갈 수 있다
					if(s.size() < 3) { //계단에 3명보다 적게 있으면 계단에 오를 수 있다
						s.add(dist.get(i));
						dist.set(i, -100);
						dN--;
					}
					continue;
				}
					
				dist.set(i, dist.get(i)-1); //계단까지 한칸씩 이동 => 거리 1씩 줄여줌
			}
		}
		return dN;
	}
	
	static int remove(ArrayList<Integer> s, int stairL, int finish) {
		for(int i=0; i<s.size(); i++)	
			s.set(i, s.get(i)-1);
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.addAll(s);
		for(int i=0; i<s.size(); i++) {
			if(s.get(i) == stairL) {
				tmp.remove(tmp.indexOf(s.get(i)));
				finish++;
			}
		}
		s.clear();
		s.addAll(tmp);
		return finish;
	}
	
	static int calculation(ArrayList<Integer> dist1, ArrayList<Integer> dist2, int[][] stairs, int peopleN) {
		int finish = 0;
		int time = 0;
		ArrayList<Integer> s1 = new ArrayList<>(); // 계단 A
		ArrayList<Integer> s2 = new ArrayList<>(); // 계단 B
		int sl1 = (-1) * stairs[0][2] -1; // A 계단 높이 * (-1) -1 // 계단 도착 뒤 1분후에 내려갈 수 있으므로
		int sl2 = (-1) * stairs[1][2] -1; // B 계단 높이 * (-1) -1 // 계단 도착 뒤 1분후에 내려갈 수 있으므로
		int reald1N = dist1.size();
		int reald2N = dist2.size();
		
		int d1N = reald1N;
		int d2N = reald2N;
		while(finish != peopleN) {
			time++;
			
			if(d1N != 0)
				d1N = move(dist1, s1, d1N, reald1N); //계단까지 거리 줄여주고, 계단에 3명보다 적으면 계단에 올린다
			if(d2N != 0)
				d2N = move(dist2, s2, d2N, reald2N);
			
			if(s1.size() != 0)
				finish = remove(s1, sl1, finish); // 계단에 있는 애들 한칸씩 내려주고
			if(s2.size() != 0)
				finish = remove(s2, sl2, finish); // 만약 그 계단을 다 내려왔다면 계단에서 없애준다
		}
		
		return time;
	}
	
	static int distToStairs(ArrayList<Integer> C1, ArrayList<Integer> C2, int[][] stairs, ArrayList<int []> q) { //q는 사람 x,y좌표
		ArrayList<Integer> dist1 = new ArrayList<Integer>(); // C1 ~ A 계단 거리
		ArrayList<Integer> dist2 = new ArrayList<Integer>(); // C2 ~ B 계단 거리
			
		for(Integer p1 : C1) {
			int[] pLoc = q.get(p1);
 			dist1.add(Math.abs((pLoc[0]-stairs[0][0])) + Math.abs((pLoc[1]-stairs[0][1]))); //사람위치와  A 계단 사이 거리		
		}
		
		for(Integer p2 : C2) {
			int[] pLoc = q.get(p2);
			dist2.add(Math.abs((pLoc[0]-stairs[1][0])) + Math.abs((pLoc[1]-stairs[1][1]))); //사람위치와  B 계단 사이 거리		
		}
		
		return calculation(dist1, dist2, stairs, q.size());
	}
	
	static void comb(int N, int r, int start, ArrayList<Integer> tmp) { //조합으로 가능한 사람조합 리스트로
		if(r == 0) {
			C.add((ArrayList<Integer>) tmp.clone());
			return;
		}
		for(int i=start; i<N; i++) {
			tmp.add(i);
			comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
}
