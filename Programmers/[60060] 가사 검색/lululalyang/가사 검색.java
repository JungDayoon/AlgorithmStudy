import java.util.HashMap;
import java.util.Map;

public class _60060 {

	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		int[] answer = new int[queries.length];
		
		Trie[] trie = new Trie[10001]; // word���̺��� Ʈ���� ������ش�
		
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
			
			if(query.charAt(0) == '?') //������ ?�� �����ϸ� �ڿ��� ���� Ȯ��
				answer[i] = trie[len].backcheck(query);
			else //������ ?�� ���̻��� �տ��� ���� Ȯ��
				answer[i] = trie[len].frontcheck(query);
			
			System.out.println(answer[i]);
		}
		

	}

}

class TrieNode{ //�ڽĳ��� & ���� ����� �ڽĳ���� ����
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
			// �� ����� �ڽĳ��map�� word.charAt(i)��� key�� ������ �ִٸ� �� key�� �ش��ϴ� value�� �� �ڽĳ����� ��ȯ
			// key�� ���ٸ� �� word.charAt(i) ��, c�� key�� TrieNode��ü�� ���� �̰��� �� ����� �ڽĳ��㿡 �߰��ϰ� �߰��� �� ���� ��ȯ
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
			}else { //�̹� ���ڰ� ?���
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
			}else { //�̹� ���ڰ� ?���
				return thisNode.cnt;
			}
		}
		return 0;
	}
}