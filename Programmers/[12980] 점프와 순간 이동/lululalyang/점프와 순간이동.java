
public class _12980 {

	public static void main(String[] args) {
		int n = 5000;
		Solution s = new Solution();
		System.out.println(s.solution(n));
	}
	
	static public class Solution {
	    public int solution(int n) {
	        int answer = 0;
	        
	        while(n != 0) { //1이 되기 전까지
	        	if(n%2 == 0) { //지금 짝수면
	        		n /= 2; // -> 순간이동
	        	}else { //홀수면
	        		n -= 1; // ->1칸 이동
	        		answer++; //건전지 사용량 +1
	        	}
	        }
	        
	        return answer;
	    }
	}
}
