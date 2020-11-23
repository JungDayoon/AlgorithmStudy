
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
        boolean[][] visited = new boolean[21][21]; //길 방문여부
        int x = 10; // 현재x좌표
        int y = 10; // 현재y좌표
        char[] input = dirs.toCharArray();
        
        for(int i=0; i<input.length; i++) {
        	 if(input[i] == 'U') {
        		 if((x-2) >= 0) { //좌표 경계 내에 있을 때
        			 if(!visited[x-1][y]) {
        				 visited[x-1][y] = true;
        				 answer++;
        			 }
        			 x -= 2;
        			 //y는 그대로
        		 }
        	 }else if(input[i] == 'L') {
        		 if((y-2) >= 0) {
        			 if(!visited[x][y-1]) {
        				 visited[x][y-1] = true;
        				 answer++;
        			 }
        			 y -= 2;
        			 //x는 그대로
        		 }
        	 }else if(input[i] == 'R') {
        		 if((y+2) < 21) {
        			if(!visited[x][y+1]) {
        				visited[x][y+1] = true;
        				answer++;
        			}
        			y += 2;
        			//x는 그대로
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