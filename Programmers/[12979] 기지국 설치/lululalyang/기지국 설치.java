
public class _12979 {

	public static void main(String[] args) {
		int n = 11;
		int[] stations = {4, 11};
		int w = 1;
		
		Solution s = new Solution();
		System.out.println(s.solution(n, stations, w));
	}
	
	static class Solution {
	    public int solution(int n, int[] stations, int w) {
	        int answer = 0;
	        
	        int start = 1;
	        int range = 1 + 2*w; //������ �ϳ��� ���� ���� �Ÿ�
	        for(int i=0; i<stations.length; i++) {
	        	int sectionLen = stations[i]-w - start;
	        	
	        	if(sectionLen<0) {
	        		start = stations[i] + w + 1;
	        		continue;
	        	}
	        	
	        	if(sectionLen%range == 0) { // �������� ������
	        		answer += (sectionLen/range);
	        	}else {
	        		answer += (sectionLen/range + 1);
	        	}
	        	
	        	start = stations[i] + w + 1;
	        }
	        
	        if(start < n) { //������ ����
	        	int sectionLen = n - start + 1;
	        	
	        	if(sectionLen%range == 0)
	        		answer += (sectionLen/range);
	        	else
	        		answer += (sectionLen/range + 1);
	        }else if(start == n)
	        	answer += 1;
	        
	        return answer;
	    }
	}
	
}
