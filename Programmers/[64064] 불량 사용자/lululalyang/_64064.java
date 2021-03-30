import java.io.*;
import java.util.*;
public class _64064 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] user_id = br.readLine().split(" ");
		String[] banned_id = br.readLine().split(" ");
		
		System.out.print(solution(user_id, banned_id));
	}
	
	private static int solution(String[] user_id, String[] banned_id) {
		int answer = 0;
		
		int N = user_id.length;
		int M = banned_id.length;
		Map<String, ArrayList<String>> bannedID = new HashMap<>();
		for(String ban : banned_id) {
			if(bannedID.containsKey(ban))	continue;
			int len = ban.length();
			ArrayList<String> tmp = new ArrayList<>();
			for(String id : user_id) {
				if(len != id.length())	continue;
				boolean flag = true;
				for(int i=0; i<len; i++) {
					if(ban.charAt(i) == '*')	continue;
					else if(ban.charAt(i) != id.charAt(i)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					tmp.add(id);
				}
			}
			bannedID.put(ban, tmp);
		}
		
		ArrayList<String> idComb = new ArrayList<>();
		answer = CaseOfNum(M, banned_id, 0, bannedID, idComb);
		return answer;	
	}
	
	static Map<String, Boolean> chkSame = new HashMap<>();
	private static int CaseOfNum(int M, String[] banned_id, int idx, Map<String, ArrayList<String>> bannedID, ArrayList<String> idComb) {
		if(idx == M) {
			Collections.sort(idComb);
			String str = "";
			for(String s : idComb) {
				str += s;
			}
			if(chkSame.containsKey(str))	return 0;
			else {
				chkSame.put(str, true);
				return 1;
			}
		}
		
		String id = banned_id[idx];
		ArrayList<String> now = bannedID.get(id);
		int res = 0;
		for(String bannedId : now) {
			if(!idComb.contains(bannedId)) {
				idComb.add(bannedId);
				res += CaseOfNum(M, banned_id, idx+1, bannedID, idComb);
				idComb.remove(idComb.indexOf(bannedId));
			}
		}
		
		return res;
	}
}
