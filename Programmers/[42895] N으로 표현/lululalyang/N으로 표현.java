import java.util.HashSet;

public class _42895 {

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(5, 31168));
	}
	
	
}

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        HashSet<Integer>[] set = new HashSet[8];
        for(int i=0; i<8; i++) {
        	set[i] = new HashSet<Integer>();
        	int num = N;
        	for(int j=0; j<i; j++) {
        		num = num*10 + N;
        	}
        	set[i].add(num);
        }
        
        if(N == number)
        	return 1;
        
        for(int i=1; i<8; i++) {
        	for(int j=0; j<i; j++) {
        		for(Integer num1: set[j]) {
        			for(Integer num2 : set[i-j-1]) {
        				set[i].add(num1 + num2);
        				set[i].add(num1 - num2);
        				set[i].add(num1 * num2);
        				if(num2 != 0)
        					set[i].add(num1 / num2);
        			}
        			
        		}
        	}
        	
        	if(set[i].contains(number)) {
        		answer = i+1;
        		break;
        	}
        }
        
       return answer;
    }
}