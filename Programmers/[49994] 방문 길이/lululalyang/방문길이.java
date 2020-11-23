
public class _49994 {

	public static void main(String[] args) {
		//String dirs = "ULURRDLLU";
		 String dirs = "LULLLLLLU";
		
		Solution_49994 s = new Solution_49994(); 
		System.out.print(s.solution(dirs));
	}

}

class Solution_49994 {
    public int solution(String dirs) {
        int answer = 0;
        boolean[][] visited = new boolean[21][21]; //�� �湮����
        int x = 10; // ����x��ǥ
        int y = 10; // ����y��ǥ
        char[] input = dirs.toCharArray();
        
        for(int i=0; i<input.length; i++) {
        	 if(input[i] == 'U') {
        		 if((x-2) >= 0) { //��ǥ ��� ���� ���� ��
        			 if(!visited[x-1][y]) {
        				 visited[x-1][y] = true;
        				 answer++;
        			 }
        			 x -= 2;
        			 //y�� �״��
        		 }
        	 }else if(input[i] == 'L') {
        		 if((y-2) >= 0) {
        			 if(!visited[x][y-1]) {
        				 visited[x][y-1] = true;
        				 answer++;
        			 }
        			 y -= 2;
        			 //x�� �״��
        		 }
        	 }else if(input[i] == 'R') {
        		 if((y+2) < 21) {
        			if(!visited[x][y+1]) {
        				visited[x][y+1] = true;
        				answer++;
        			}
        			y += 2;
        			//x�� �״��
        		 }
        	 }else { //input[i] == 'D'
        		if((x+2) < 21) {
        			if(!visited[x+1][y]) {
        				visited[x+1][y] = true;
        				answer++;
        			}
        			x += 2;
        		} 
        	 }
        }
        
        return answer;
    }
}