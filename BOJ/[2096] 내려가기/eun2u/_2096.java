import java.io.*;

public class _2096 {
	static int N,maxResult=0, minResult=987654321;
	static int[][] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
		N = Integer.parseInt(br.readLine());

		String[] s;

		num = new int[N][3];
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				num [i][j] = Integer.parseInt(s[j]);
			}
		}
		findMaxMin();
		System.out.println(maxResult+" "+minResult);

	}
	public static void findMaxMin(){
		int[][] maxDp=new int[N][3];
		int[][] minDp=new int[N][3];
		
		for(int i=0;i<3;i++){
			maxDp[0][i]=num[0][i];
			minDp[0][i]=num[0][i];
		}
		
		for(int i=1;i<N;i++){
			int max01=Math.max(maxDp[i-1][0], maxDp[i-1][1]);
			int max12=Math.max(maxDp[i-1][1], maxDp[i-1][2]);
			
			maxDp[i][0]=num[i][0]+max01;
			maxDp[i][1]=num[i][1]+Math.max(max01,max12);
			maxDp[i][2]=num[i][2]+max12;

			int min01=Math.min(minDp[i-1][0], minDp[i-1][1]);
			int min12=Math.min(minDp[i-1][1], minDp[i-1][2]);

			minDp[i][0]=num[i][0]+min01;
			minDp[i][1]=num[i][1]+Math.min(min01,min12);
			minDp[i][2]=num[i][2]+min12;
		}
		
		for(int i=0;i<3;i++){
			maxResult=Math.max(maxResult, maxDp[N-1][i]);
			minResult=Math.min(minResult, minDp[N-1][i]);
		}

	}

}
