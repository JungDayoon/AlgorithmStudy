import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

public class _5648 {
   static int[] dx = {0, 0, -1, 1}; //��:0, ��:1, ��:2, ��:3
   static int[] dy = {1, -1, 0, 0};
   static int map[][] = new int[4001][4001];
   static int result;
   static ArrayList<atom> A = new ArrayList<atom>();
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      
      for(int t=0; t<T; t++) {
    	 result = 0;
         int N = Integer.parseInt(br.readLine()); //���� ��
         
         for(int i=0; i<N; i++) {
            String[] s = br.readLine().split(" ");
            A.add(new atom(2*Integer.parseInt(s[0]), 2*Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3])));
         }
         
         colliAtom();
         
         System.out.println("#"+(t+1)+" "+result);
      }//�׽�Ʈ���̽� ��
      
   }
   
   static ArrayList<int[]> check = new ArrayList<>();
   
   static void colliAtom(){
	  int d;
	  
	  while(A.size()!=0) { 
     	 for(int i=0; i<A.size(); i++) {
     		 if(map[A.get(i).y+2000][A.get(i).x+2000] > 0)
     			 map[A.get(i).y+2000][A.get(i).x+2000]--;
     		 
     		 d = A.get(i).d;
     		 A.get(i).x += dx[d];
     		 A.get(i).y += dy[d];
     		 
     		 if(A.get(i).x<-2000 || A.get(i).x>2000 || A.get(i).y<-2000 || A.get(i).y>2000) { //���� �����
     			 A.remove(i);
     			 i--;
     		 }else { //�������� �ִٸ�
     			 map[A.get(i).y+2000][A.get(i).x+2000]++;
     			 if(map[A.get(i).y+2000][A.get(i).x+2000] >= 2) {
     				 check.add(new int[] {A.get(i).y, A.get(i).x});
     			 }
     		 }
     	 }
     	 
     	 for(int i=0; i<A.size(); i++) {
     		 int[] Aloc = new int[] {A.get(i).y, A.get(i).x};
     		 
     		 for(int[] tmp: check) {
     			 if(Arrays.equals(Aloc, tmp)) {
     				result += A.get(i).k;
     				map[A.get(i).y+2000][A.get(i).x+2000]--; //map ���� ���ְ�
     				
     				A.remove(i); // ��������
         			i--;
         			break;
     			 }
     		 }
     	 }
     	 check.clear();
      }
   }
}

class atom{
	   int x;
	   int y;
	   int d; // �̵� ���� 
	   int k; // ���� ������
	   
	   atom(int x, int y, int d, int k){
	      this.x = x;
	      this.y = y;
	      this.d = d;
	      this.k = k;
	   }
	}