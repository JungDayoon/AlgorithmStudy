import java.io.*;
import java.util.*;


public class _17472 {
    static int INF = 987654321;
    static int N,M,num=0;
    static int[][] map;
    static ArrayList<loc>[] island=new ArrayList[6];
    static boolean[][] visited ;
    static int[] dy={0,1,0,-1};
    static int[] dx={1,0,-1,0};
    static PriorityQueue<edge> pq=new PriorityQueue<>(); 
    static int[] p;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] s=br.readLine().split(" ");

        N=Integer.parseInt(s[0]);
        M=Integer.parseInt(s[1]);
        map=new int[N][M];
        visited=new boolean[N][M]; 
        for(int i=0;i<6;i++){
            island[i]=new ArrayList<loc>();
        }

        for(int i=0;i<N;i++){
            s=br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(s[j]);
            }
        }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j]==1 && !visited[i][j]){
                    dfs(i,j,num++);
                }
            }
        }
        
        for(int i=0;i<num;i++){
            for(int j=i+1;j<num;j++){
                int w=findBridge(island[i], island[j]);
                if(w<2) continue; 

                pq.add(new edge(i,j,w)); 
            }
        }

        p = new int[num]; 
        for (int i = 0; i < num; i++) 
            p[i] = i; 
        
        int ans=0,cnt=0;
        while(!pq.isEmpty()) { 
            edge edge = pq.poll(); 
            if (findSet(p, edge.u) != findSet(p, edge.v)) { 
                ans+=edge.w; 
                if (cnt+1 == num) 
                    break; 
                cnt++;
                union(p, edge.u, edge.v); 
            } 
        }
        if(ans==0 || cnt!=num-1) ans=-1;
        System.out.println(ans);
    }
    public static int findSet(int[] p, int x) {
        if (p[x] == x) 
            return x; 
        else return p[x] = findSet(p, p[x]); 
    } 
    public static void union(int[] p, int x, int y) { 
        if (x < y) 
            p[findSet(p, y)] = findSet(p, x); 
        else p[findSet(p, x)] = findSet(p, y); 
    }
    public static int findBridge(ArrayList<loc> a, ArrayList<loc> b){
        int ret=INF;
        for(int i=0;i<a.size();i++){
            for(int j=0;j<b.size();j++){
                if(a.get(i).y == b.get(j).y ){
                    if(isGround(0, a.get(i), b.get(j))) continue;
                    int tmp=Math.abs(a.get(i).x - b.get(j).x)-1;
                    if(tmp==1) continue;
                    ret=Math.min(ret, tmp);
                }
                if(a.get(i).x == b.get(j).x){
                    if(isGround(1,a.get(i), b.get(j))) continue;
                    int tmp = Math.abs(a.get(i).y - b.get(j).y)-1;
                    if(tmp==1) continue;
                    ret=Math.min(ret, tmp);
                }
            }
        }
        return ret==INF?-1:ret;
    }
    public static boolean isGround(int axis,loc a, loc b){
        int s,l;
        if(axis==0){//y값이 같다. x값 사이 비교
            int y = a.y;
            if(a.x<b.x) {s=a.x; l=b.x;}
            else {s=b.x; l=a.x;}

            for(int i=s+1;i<l;i++)
                if(map[y][i]==1) return true;

        }
        else if(axis==1){//x값이 같다. y값 사이 비교
            int x=a.x;
            if(a.y<b.y) {s=a.y; l=b.y;}
            else {s=b.y; l=a.y;}

            for(int i=s+1;i<l;i++)
                if(map[i][x]==1) return true;
        }
        return false;
    }
    public static boolean inRange(int y, int x){
        if(y<0 || y>=N) return false;
        if(x<0 || x>=M) return false;
        return true;
    }
    public static boolean adjacencySea(int y, int x){
        for(int dir=0;dir<4;dir++){
            int ny=y+dy[dir];
            int nx=x+dx[dir];
            if(inRange(ny,nx) && map[ny][nx]==0){
                return true;
            }
        }
        return false;
    }
    public static void dfs(int y, int x,int num){
        if(adjacencySea(y,x)){ //바다와 인접한 땅은 island에 추가
            island[num].add(new loc(y,x));
        }
        visited[y][x]=true;

        for(int dir=0;dir<4;dir++){
            int ny=y+dy[dir];
            int nx=x+dx[dir];

            if(!inRange(ny,nx)) continue;

            if(map[ny][nx]==1 && !visited[ny][nx]) {
                dfs(ny,nx,num);
            }
        }   
    }
    static class edge implements Comparable<edge>{
        int u,v,w;
        edge(int u,int v, int w){
            this.u=u;
            this.v=v;
            this.w=w;
        }
        @Override
        public int compareTo(edge o){
            return Integer.compare(this.w, o.w);
        }
    }
    static class loc{
        int y,x;
        loc(int y, int x){
            this.y=y;
            this.x=x;
        }
    }   
}