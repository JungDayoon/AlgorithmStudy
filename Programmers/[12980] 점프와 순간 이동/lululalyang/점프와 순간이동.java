
public class _12980 {

	public static void main(String[] args) {
		int n = 5000;
		Solution s = new Solution();
		System.out.println(s.solution(n));
	}
	
	static public class Solution {
	    public int solution(int n) {
	        int answer = 0;
	        
	        while(n != 0) { //1�� �Ǳ� ������
	        	if(n%2 == 0) { //���� ¦����
	        		n /= 2; // -> �����̵�
	        	}else { //Ȧ����
	        		n -= 1; // ->1ĭ �̵�
	        		answer++; //������ ��뷮 +1
	        	}
	        }
	        
	        return answer;
	    }
	}
}
