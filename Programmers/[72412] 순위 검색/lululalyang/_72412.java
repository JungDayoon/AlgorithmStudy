import java.io.*;
import java.util.*;

public class _72412 {
	static ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String[] info = new String[N];
		String[] query = new String[M];
		
		for(int i=0; i<N; i++)	info[i] = br.readLine();
		for(int i=0; i<M; i++)	query[i] = br.readLine();
		
		int[] res = solution(info, query);
		for(int i=0; i<M; i++)
			System.out.println(res[i]);
	}
	
	private static int[] solution(String[] info, String[] query) {
		int N = info.length; // 사람 수
		int M = query.length; // 쿼리 수
		int[] ans = new int[M];
		for(int i=1; i<=4; i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			Comb(4, i, 0, tmp);
		}
		
		Map<String, ArrayList<Integer>> map = new HashMap<>();
		String[] s;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			s = info[i].split(" ");
			for(int j=0; j<4; j++)	sb.append(s[j]);
			int score = Integer.parseInt(s[4]);
			
			if(map.containsKey(sb.toString())) {
				ArrayList<Integer> tmp = map.remove(sb.toString());
				tmp.add(score);
				map.put(sb.toString(), tmp);
			}else {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(score);
				map.put(sb.toString(), tmp);
			}
			
			for(ArrayList<Integer> dash : comb) {
				sb.setLength(0); // sb초기화
				for(int j=0; j<4; j++) {
					if(dash.contains(j)) {
						sb.append("-");
					}else{ 
						sb.append(s[j]);
					}
				}
				
				if(map.containsKey(sb.toString())) {
					ArrayList<Integer> tmp = map.remove(sb.toString());
					tmp.add(score);
					map.put(sb.toString(), tmp);
				}else {
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(score);
					map.put(sb.toString(), tmp);
				}
			}
			sb.setLength(0);
		}
		for(String str : map.keySet()) {
			Collections.sort(map.get(str));
		}
		
		for(int i=0; i<M; i++) {
			sb.setLength(0);
			s = query[i].split(" ");
			for(int j=0; j<7; j+=2) {
				sb.append(s[j]);
			}
			
			ArrayList<Integer> find = map.get(sb.toString());
			if(find == null)	continue;
			
			int X = Integer.parseInt(s[7]);
			ans[i] = findCnt(find, X);
		}
		return ans;
	}
	
	private static int findCnt(ArrayList<Integer> list, int score) {
		int cnt = 0;
		
		int l = 0;
		int r = list.size() - 1;
		int mid;
		
		while(l <= r) {
			mid = (l + r) / 2;
			
			if(list.get(mid) < score) {
				l = mid + 1;
			}else { // list.get(mid) >= score
				r = mid - 1;
			}
		}
		
		cnt = list.size() - l;
		return cnt;
	}
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			comb.add((ArrayList<Integer>)tmp.clone());
			return;
		}
		
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}

}
