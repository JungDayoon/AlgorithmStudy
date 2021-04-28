import java.util.*;
public class _42577 {

	public static void main(String[] args) {
		String[] p_book = {"123","456","789"};
		
		System.out.print(solution(p_book));
	}

	private static boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, new Comparator<String>() { // ���� �� ��������
        	@Override
        	public int compare(String s1, String s2) {
        		return s1.length() - s2.length();
        	}
        }); 
        
        Trie trie = new Trie();
        for(String now : phone_book) {
        	if(!trie.contains(now)) { // ���ٸ�
        		trie.insert(now);
        	}else {
        		answer = false; // ���λ簡 �ִ� ��
        		break;
        	}
        }
        return answer;
    }
	
	private static class Trie{
		private TrieNode rootNode;
		
		Trie(){
			rootNode = new TrieNode();
		}
		
		void insert(String word) {
			TrieNode thisNode = this.rootNode;
			
			for(int i=0; i<word.length(); i++) {
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			}
			thisNode.setIsLastChar(true);
		}
		
		boolean contains(String word) { // true�� ���λ簡 �ִ� ��
			TrieNode thisNode = this.rootNode;
			
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				TrieNode node = thisNode.getChildNodes().get(c);
				
				if(node == null)	return false;
				
				thisNode = node;
				if(thisNode.isLastChar)	return true; // �ٵ� ���� �� ��尡 ������ ����� => �긦 ���λ�� �ϴ� ��
			}
			
			return thisNode.isLastChar();
		}
	}
	private static class TrieNode{
		private Map<Character, TrieNode> childNodes = new HashMap<>();
		private boolean isLastChar;
		
		Map<Character, TrieNode> getChildNodes(){
			return this.childNodes;
		}
		
		boolean isLastChar() {
			return this.isLastChar;
		}
		
		void setIsLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
	}
}
