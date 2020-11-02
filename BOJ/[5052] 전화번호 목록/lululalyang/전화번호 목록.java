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

class TrieNode{ //자식노드맵과 현재 노드가 마지막 글자인지 여부에 대한 정보 가짐
	Map<Character, TrieNode> childNodes = new HashMap<>(); //자식노드맵
	boolean isLashChar; //마지막 글자인지 여부
	
	Map<Character, TrieNode> getChildNodes(){ //자식노드 맵 Getter
		return this.childNodes;
	}
	
	boolean isLastChar() { //마지막 글자인지 여부 Getter
		return this.isLashChar;
	}
	
	void setIsLastChar(boolean isLastChar) { //마지막 글자인지 여부 Setter
		this.isLashChar = isLastChar;
	}
}

class Trie{ //빈 문자열을 가지는 루트노드만 존재
	public TrieNode rootNode;
	
	Trie(){ //생성자 => Trie객체가 만들어지면 rootNode를 생성
		this.rootNode = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode thisNode = this.rootNode; //루트노드부터 탐색
		
		for(int i=0; i<word.length(); i++) {
			// computeIfAbsent(key, mappingFunction): 첫번째는 key이고 두번째는 key를 사용하여 차례대로 value를 반환하는 함수형 인터페이스이다
			// map에 key가 있으면 값을 반환, 그렇지 않다면 value를 계산하고 map에 추가한 후 value를 반환한다
			thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			//해당 계층 문자의 자식노드가 존재하지 않을 때만 자식노드를 생성
		}
		thisNode.setIsLastChar(true); //마지막 문자라는 것 표시
	}
	
	public boolean contains(String word) { 
		//특정 단어가 Trie에 존재하는 지 확인 -> 1)루트노드부터 순서대로 알파벳이 일치하는 자식노드들이 존재할 것
		// 2) 해당 단어의 마지막 글자에 해당하는 노드의 isLastChar이 true일 것 (=해당 글자를 마지막으로 하는 단어가 있다는 뜻)
		
		TrieNode thisNode = this.rootNode;
		
		for(int i=0; i<word.length(); i++) {
			char character = word.charAt(i);
			TrieNode  node = thisNode.getChildNodes().get(character);
			
			if(node == null)
				return false;
			
			thisNode = node; //있으면 이노드를 현재노드로 하고 이 노드의 자식노드를 확인
			if(thisNode.isLastChar())
				return true;
		}
		return thisNode.isLastChar(); //조건 2)
	}
	
//	public void delete(String word) {
//		delete(this.rootNode, word, 0);
//	}
//	
//	private void delete(TrieNode thisNode, String word, int index) {
//		char character = word.charAt(index);
//		
//		//root노드의 자식노드를 확인해 아예 없는 단어인 경우 에러 출력
//		if(!thisNode.getChildNodes().containsKey(character))
//			throw new Error("There is no["+word+"] in this Trie.");
//		
//		TrieNode childNode = thisNode.getChildNodes().get(character);
//		index++;
//		
//		if(index == word.length()) {
//			//노드는 존재하지만 insert한 단어가 아닌 경우 에러 출력
//			if(!childNode.isLastChar())	throw new Error("There is no["+word+"] in this Trie.");
//			
//			childNode.setIsLastChar(false);
//			//삭제 대상 언어의 제일 끝으로 자식노드가 없으면 삭제 시작(이 단어를 포함하는 더 긴 단어가 없으면)
//			if(childNode.getChildNodes().isEmpty())
//				thisNode.getChildNodes().remove(character);
//		}else {
//			delete(childNode, word, index); //콜백함수
//			//삭제 중, 자식노드가 없고 현재 노드로 끝나는 다른 단어가 없는 경우 이노드 삭제
//			if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty())
//				thisNode.getChildNodes().remove(character);
//		}
//	}
}