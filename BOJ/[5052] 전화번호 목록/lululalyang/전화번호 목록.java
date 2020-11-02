import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _5052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			Trie trie = new Trie();
			
			int n = Integer.parseInt(br.readLine());
			
			String[] str = new String[n];
			for(int i=0; i<n; i++) 
				str[i] = br.readLine();
			
			int flag = 0;
			Arrays.sort(str, (a, b)->Integer.compare(a.length(), b.length()));
			for(int i=0; i<n; i++) {
				if(!trie.contains(str[i]))
					trie.insert(str[i]);
				else {
					System.out.println("NO");
					flag = 1;
					break;
				}
			}
			if(flag == 0)
				System.out.println("YES");
			
			
		}
	}

}

class TrieNode{ //�ڽĳ��ʰ� ���� ��尡 ������ �������� ���ο� ���� ���� ����
	Map<Character, TrieNode> childNodes = new HashMap<>(); //�ڽĳ���
	boolean isLashChar; //������ �������� ����
	
	Map<Character, TrieNode> getChildNodes(){ //�ڽĳ�� �� Getter
		return this.childNodes;
	}
	
	boolean isLastChar() { //������ �������� ���� Getter
		return this.isLashChar;
	}
	
	void setIsLastChar(boolean isLastChar) { //������ �������� ���� Setter
		this.isLashChar = isLastChar;
	}
}

class Trie{ //�� ���ڿ��� ������ ��Ʈ��常 ����
	public TrieNode rootNode;
	
	Trie(){ //������ => Trie��ü�� ��������� rootNode�� ����
		this.rootNode = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode thisNode = this.rootNode; //��Ʈ������ Ž��
		
		for(int i=0; i<word.length(); i++) {
			// computeIfAbsent(key, mappingFunction): ù��°�� key�̰� �ι�°�� key�� ����Ͽ� ���ʴ�� value�� ��ȯ�ϴ� �Լ��� �������̽��̴�
			// map�� key�� ������ ���� ��ȯ, �׷��� �ʴٸ� value�� ����ϰ� map�� �߰��� �� value�� ��ȯ�Ѵ�
			thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			//�ش� ���� ������ �ڽĳ�尡 �������� ���� ���� �ڽĳ�带 ����
		}
		thisNode.setIsLastChar(true); //������ ���ڶ�� �� ǥ��
	}
	
	public boolean contains(String word) { 
		//Ư�� �ܾ Trie�� �����ϴ� �� Ȯ�� -> 1)��Ʈ������ ������� ���ĺ��� ��ġ�ϴ� �ڽĳ����� ������ ��
		// 2) �ش� �ܾ��� ������ ���ڿ� �ش��ϴ� ����� isLastChar�� true�� �� (=�ش� ���ڸ� ���������� �ϴ� �ܾ �ִٴ� ��)
		
		TrieNode thisNode = this.rootNode;
		
		for(int i=0; i<word.length(); i++) {
			char character = word.charAt(i);
			TrieNode  node = thisNode.getChildNodes().get(character);
			
			if(node == null)
				return false;
			
			thisNode = node; //������ �̳�带 ������� �ϰ� �� ����� �ڽĳ�带 Ȯ��
			if(thisNode.isLastChar())
				return true;
		}
		return thisNode.isLastChar(); //���� 2)
	}
	
//	public void delete(String word) {
//		delete(this.rootNode, word, 0);
//	}
//	
//	private void delete(TrieNode thisNode, String word, int index) {
//		char character = word.charAt(index);
//		
//		//root����� �ڽĳ�带 Ȯ���� �ƿ� ���� �ܾ��� ��� ���� ���
//		if(!thisNode.getChildNodes().containsKey(character))
//			throw new Error("There is no["+word+"] in this Trie.");
//		
//		TrieNode childNode = thisNode.getChildNodes().get(character);
//		index++;
//		
//		if(index == word.length()) {
//			//���� ���������� insert�� �ܾ �ƴ� ��� ���� ���
//			if(!childNode.isLastChar())	throw new Error("There is no["+word+"] in this Trie.");
//			
//			childNode.setIsLastChar(false);
//			//���� ��� ����� ���� ������ �ڽĳ�尡 ������ ���� ����(�� �ܾ �����ϴ� �� �� �ܾ ������)
//			if(childNode.getChildNodes().isEmpty())
//				thisNode.getChildNodes().remove(character);
//		}else {
//			delete(childNode, word, index); //�ݹ��Լ�
//			//���� ��, �ڽĳ�尡 ���� ���� ���� ������ �ٸ� �ܾ ���� ��� �̳�� ����
//			if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty())
//				thisNode.getChildNodes().remove(character);
//		}
//	}
}