import java.io.*;


public class _2688 {
    
    static long[][] digit=new long[65][65];

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        long[] dp=new long[65];
        dp[1]=10;

       
        for(int tc=0;tc<TC;tc++){
            int N=Integer.parseInt(br.readLine());
            
            if(dp[N]==0){
                long ret=findLDS(N);
                dp[N]=ret;
            }
           
            System.out.println(dp[N]); 
        }
    }
    public static long findLDS(int N){
        long result=0;
        
        for(int i=0;i<=9;i++)
            digit[1][i]=1; //자리수 1개인데 i로 끝나는 경우

        for(int d=2;d<=N;d++){
            if(digit[d][0]>0) continue;
            result=0;

            digit[d][0]=digit[d-1][0];
            result+=digit[d][0];
            for(int i=1;i<10;i++){
                digit[d][i]=digit[d][i-1]+digit[d-1][i];
                result+=digit[d][i];
            }
        }

        return result;
    }
}
