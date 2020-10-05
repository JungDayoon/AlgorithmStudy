import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class _2382 {
   static int[] dx = {-1, 1, 0, 0}; //상, 하, 좌, 우
   static int[] dy = {0, 0, -1, 1}; //상, 하, 좌, 우
   
   public static void main(String[] args) throws NumberFormatException, IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      int[] result = new int[T];
      for(int t=0; t<T; t++) {
         String[] s = br.readLine().split(" ");
         int N = Integer.parseInt(s[0]); //한 변에 있는 셀의 개수
         int M = Integer.parseInt(s[1]); //격리 시간
         int K = Integer.parseInt(s[2]); //미생물 군집의 개수
         Micro[] com = new Micro[K];
         
         for(int i=0; i<K; i++) {
            String[] d = br.readLine().split(" "); //세로, 가로, 미생물 수, 이동방향
            com[i] = new Micro(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]), Integer.parseInt(d[3]));
         }
         
         int time = 0;
         while(time != M) {
            for(int i=0; i<K; i++) {
               if(com[i].x != -1) {
                  com[i].x+=dx[com[i].dir-1];
                  com[i].y+=dy[com[i].dir-1];
                  if(com[i].x==0 || com[i].x==(N-1) || com[i].y==0 || com[i].y==(N-1)) { //약품이 칠해진 곳일 때
                     if(com[i].mCnt == 1) {
                    	 com[i].x = -1;
                     }
                     else {
                        com[i].mCnt/=2;
                        
                        if(com[i].dir%2 == 1)   com[i].dir++; //dir=1,3
                        else   com[i].dir--; //dir=2,4
                     }
                  }
               }
            }
            
            for(int i=0; i<K; i++) {
            	if(com[i].x == -1)	continue;
            	
            	ArrayList<Integer> tmp = new ArrayList<Integer>();
            	int mCntSum = com[i].mCnt;
            	for(int j=0; j<K; j++) {
            		if(i!=j && com[i].x==com[j].x && com[i].y==com[j].y) { //같은 자리에 있는 미생물끼리 비교
            			mCntSum+=com[j].mCnt;
            			tmp.add(j);
            		}
            	}   
            	if(tmp.size() != 0) {
            		int maxmCnt = com[i].mCnt;
            		int maxidx = i;
            		for(int j : tmp) {
            			if(maxmCnt < com[j].mCnt) {
            				com[maxidx].x = -1;
            				maxmCnt = com[j].mCnt;
            				maxidx = j;
            			}else {
            				com[j].x = -1;
            			}
            		}
            		com[maxidx].mCnt = mCntSum;
            	}
            }
            time++;
         }
         int remain = 0;
         for(int i=0; i<K; i++) {
            if(com[i].x != -1) {
               remain+=com[i].mCnt;
            }
         }
         result[t] = remain;
      }
      
      for(int t=0; t<T; t++) {
         System.out.println("#"+(t+1)+" "+result[t]);
      }
   }
   
}

class Micro{
   int x; //세로 위치
   int y; //가로 위치
   int mCnt; //미생물 수
   int dir; //이동 방향
   
   Micro(int x, int y, int mCnt, int dir){
      this.x = x;
      this.y = y;
      this.mCnt = mCnt;
      this.dir = dir;
   }
}