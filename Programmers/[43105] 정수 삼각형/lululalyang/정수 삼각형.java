
public class _43105 {

	public static void main(String[] args) {
		int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		Solution s = new Solution();
		System.out.println(s.solution(triangle));
	}

}

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[n][];
        for(int i=0; i<n; i++) {
        	dp[i] = new int[triangle[i].length];
        }
        
        dp[0][0] = triangle[0][0];
        for(int i=1; i<n; i++) {
        	for(int j=0; j<dp[i].length; j++) {
        		if(j == 0)
        			dp[i][j] = dp[i-1][j] + triangle[i][j];
        		else if(j == (dp[i].length-1))
        			dp[i][j] = dp[i-1][j-1] + triangle[i][j];
        		else {
        			dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
        		}
        		
        		if(i == (n-1)) {
        			answer = Math.max(dp[i][j], answer);
        		}
        	}
        }
        
        return answer;
    }
}