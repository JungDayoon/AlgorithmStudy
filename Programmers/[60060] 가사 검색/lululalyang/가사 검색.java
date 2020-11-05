import java.util.HashMap;
import java.util.Map;

public class _60060 {

	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		int[] answer = new int[queries.length];
		
		Trie[] trie = new Trie[10001]; // word길이별로 트라이 만들어준다
		
		for(String word : words) {
			int len = word.length();
			if(trie[len] == null)
				trie[len] = new Trie();
			trie[len].insert(word);
		}
		
		for(int i=0; i<queries.length; i++) {
			String query = queries[i];
			int len = query.length();
			if(trie[len] == null) {
				answer[i] = 0;
				System.out.println(answer[i]);
				continue;
			}
			
			if(query.charAt(0) == '?') //쿼리가 ?로 시작하면 뒤에서 부터 확인
				answer[i] = trie[len].backcheck(query);
			else //쿼리의 ?가 접미사라면 앞에서 부터 확인
				answer[i] = trie[len].frontcheck(query);
			
			System.out.println(answer[i]);
		}
		

	}

}

class TrieNode{ //자식노드맵 & 현재 노드의 자식노드의 개수
	Map<Character, TrieNode> childNodes = new HashMap();
	int cnt = 0;
	
}

class Trie{ 
	public TrieNode front;
	public TrieNode back;
	
	Trie(){
		this.front = new TrieNode();
		this.back = new TrieNode();		
	}
	
	public void insert(String word) {
		frontinsert(word);
		backinsert(word);
	}
	
	private void frontinsert(String word) {
		TrieNode thisNode = this.front;
		for(int i=0; i<word.length(); i++) {
			thisNode.cnt++;
			thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
			// 이 노드의 자식노드map이 word.charAt(i)라는 key를 가지고 있다면 그 key에 해당하는 value인 그 자식노드맵을 반환
			// key가 없다면 그 word.charAt(i) 즉, c를 key로 TrieNode객체를 만들어서 이것을 위 노드의 자식노드멥에 추가하고 추가된 이 맵을 반환
		}
	}
	
	private void backinsert(String word) {
		TrieNode thisNode = this.back;
		for(int i=word.length()-1; i>=0; i--) {
			thisNode.cnt++;
			thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
		}
	}
	
	public int frontcheck(String query) {
		TrieNode thisNode = this.front;
		
		for(int i=0; i<query.length(); i++) {
			char c = query.charAt(i);
			if(c != '?') {
				TrieNode node = thisNode.childNodes.get(c);
				if(node == null)
					return 0;
				
				thisNode = node;
			}else { //이번 글자가 ?라면
				return thisNode.cnt;
			}
		}
		return 0;
	}
	
	public int backcheck(String query) {
		TrieNode thisNode = this.back;
		
		for(int i=query.length()-1; i>=0; i--) {
			char c = query.charAt(i);
			if(c != '?') {
				TrieNode node = thisNode.childNodes.get(c);
				if(node == null)
					return 0;
				
				thisNode = node;
			}else { //이번 글자가 ?라면
				return thisNode.cnt;
			}
		}
		return 0;
	}
}