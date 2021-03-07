import java.io.*;
import java.util.*;

public class _42890 {
	static ArrayList<ArrayList<Integer>> idxSet = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> keySet = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int rCnt = 4;
		String[][] relation = new String[rCnt][];
		for(int i=0; i<rCnt; i++) {
			relation[i] = br.readLine().split(" ");
		}
		
		System.out.print(solution(relation));
	}
	
	private static boolean AdjustKeySet(ArrayList<Integer> tmp) {
		for(ArrayList<Integer> now : keySet) {
			if(tmp.containsAll(now))
				return false;
		}
		return true;
	}
	
	private static int solution(String[][] relation){
		int N = relation.length; // 튜플 개수
		int M = relation[0].length; // 속성 개수
		
		for(int r=1; r<=M; r++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			Comb(M, r, 0, tmp);
		}
		
		for(ArrayList<Integer> set : idxSet) {
			int[] idx = ListToArr(set);
			if(ChkSame(N, M, relation, idx)) { // 이 속성으로 구별이 된다면
				if(AdjustKeySet(set)) // 최소성을 만족할 때만
					keySet.add(set);
			}
		}
		return keySet.size();
	}

	private static int[] ListToArr(ArrayList<Integer> now) {
		int[] res = new int[now.size()];
		for(int i=0; i<res.length; i++) {
			res[i] = now.get(i);
		}
		return res;
	}
	
	private static void Comb(int N, int r, int start, ArrayList<Integer> tmp) {
		if(r == 0) {
			idxSet.add((ArrayList<Integer>)tmp.clone());
			return;
		}
		
		for(int i=start; i<N; i++) {
			tmp.add(i);
			Comb(N, r-1, i+1, tmp);
			tmp.remove(tmp.indexOf(i));
		}
	}
	
	private static boolean ChkSame(int N, int M, String[][] relation, int[] attArr) {
		for(int i=0; i<N-1; i++) {
			ArrayList<String> nowList = new ArrayList<>();
			for(int k=0; k<attArr.length; k++)	nowList.add(relation[i][attArr[k]]);
		
			for(int j=i+1; j<N; j++) {
				int same = 0;
				for(int k=0; k<attArr.length; k++) {
					if(relation[j][attArr[k]].equals(nowList.get(k)))	same++;	 
				}
				if(same == attArr.length)
					return false;
			}
		}
		
		return true;
	}
	
	
}
