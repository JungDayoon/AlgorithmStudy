import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2112 {
   static int[] A;
   static int[] B;
   static int Flag = 0;
   static int[][] film;
   static int K;
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      int[] result = new int[T];
      for(int t=0; t<T; t++) {
         Flag = 0;
         String[] s = br.readLine().split(" ");
         int D = Integer.parseInt(s[0]); //�β�
         int W = Integer.parseInt(s[1]); //�ʺ�
         K = Integer.parseInt(s[2]); //�հݱ���
         film = new int[D][W];
         A = new int[W];
         B = new int[W];
         
         for(int i=0; i<D; i++) {
            String[] d = br.readLine().split(" ");
            for(int j=0; j<W; j++) {
               film[i][j] = Integer.parseInt(d[j]); // 0�� A, 1�� B
               A[j] = 0;
               B[j] = 1;
            }
         }
         
         if(check_byK()) { //ó������ �հݱ��� ����ϴ���
            result[t] = 0;
            continue;
         }
        
         if(K == 1) {
        	 result[t] = 0;
        	 continue;
         }
         
         int l_k;
         for(l_k=1; l_k<K; l_k++) {
            chk_medi(l_k, 0, 0);
            if(Flag == 1) 
            	break;
         }
         
         
         if(l_k == K)   result[t] = K;
         else {
            result[t] = l_k;
         }
      }//testCase ��
      
      for(int t=0; t<T; t++) {
         System.out.println("#"+(t+1)+" "+result[t]);
      }
   }
   
   static void chk_medi(int goal, int num, int prev) { // goal: ���� ��ǰó���ؾ��ϴ� �� ��, num: ó���� �� ��, prev: �������� idx 
      
	   if(goal == num) {
         if(check_byK()) {
            Flag = 1;
         }
         return;
      }
      
      for(int i = prev; i<film.length; i++) {
    	int[] arr = new int[film[0].length];
    	copyrow(film[i], arr);
    	
    	copyrow(A, film[i]);
    	chk_medi(goal, num+1, i+1);
    	if(Flag == 1) return;
    	
    	copyrow(B, film[i]);
    	chk_medi(goal, num+1, i+1);
    	if(Flag == 1) return;
    		
    	copyrow(arr, film[i]);
      }
      
   }
   
   static void copyrow(int[] now, int[] result) {
	   for(int i=0; i<now.length; i++) {
		   result[i] = now[i];
	   }
   }
   static boolean check_byK() {
      for(int i=0; i<film[0].length; i++) {
         int same = 1;
         int f2 = 0;
         for(int j=1; j<film.length; j++) {
            if((film[j][i] == film[j-1][i]))   same++;
            else   same = 1;
            
            if(same >= K) {
               f2 = 1; //���
               break;
            }
         }
         if(f2 == 0) return false;
      } 
      return true;
   }
   
}