import java.util.LinkedList;

public class _49993 {
	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		
		Solution s = new Solution();
		System.out.println(s.solution(skill, skill_trees));
	}
	
	static class Solution {
	    public int solution(String skill, String[] skill_trees) {
	        int answer = 0;
	        LinkedList<Character> skillList = new LinkedList<>();
	        char[] skillArr = skill.toCharArray();
	        
	        for(char c : skillArr)
	        	skillList.add(c);
	        
	        
	        for(int i=0; i<skill_trees.length; i++) {
	        	int flag = 0;
	        	char[] tmp = skill_trees[i].toCharArray();
	        	LinkedList<Character> tmpList = (LinkedList<Character>) skillList.clone();
	        	for(char c : tmp) {
	        		if(tmpList.contains(c)) { // skill�� �����ϴ� ����̸�
	        			if(c == tmpList.get(0)) {
	        				tmpList.remove(0);
	        			}else { // ù ���ڰ� �ƴϸ� -> �Ұ����� ��ųƮ��
	        				flag = 1;
	        				break;
	        			}
	        		}
	        		//�������� �ʴ� ����̸� -> �׳� �Ѿ��
	        	}
	        	
	        	if(flag == 0)
	        		answer++;
	        }
	        
	        return answer;
	    }
	}
}
