import java.util.ArrayList;

class Solution {
    static int dy[]={1,-1,0,0};
    static int dx[]={0,0,1,-1};
    static ArrayList<Integer>[] adj;
    static int direction(char dir){
        if(dir=='U')    return 0;
        else if(dir=='D')    return 1;
        else if(dir=='R')    return 2;
        else return 3;
    }
    static boolean inRange(int y, int x){
        if(y>10 ||y<0 ) return false;
        if(x>10 ||x<0) return false;
        return true;
    }
    public static int solution(String dirs) {
        int answer = 0;
        int sy=5, sx=5;

        adj=new ArrayList[1111];
        for(int i=0;i<1111;i++)
            adj[i]=new ArrayList<Integer>();
        
        for(int i=0;i<dirs.length();i++){
            int dir=direction(dirs.charAt(i));

            int ny=sy+dy[dir];
            int nx=sx+dx[dir];

            if(!inRange(ny,nx)) continue;
            int a=sx*100+sy, b=ny*100+nx;

            if(!adj[a].contains(b)){
                answer++;
                adj[a].add(b);
                adj[b].add(a);
            }
            sy=ny;
            sx=nx;
        }
        return answer;
    }
    public static void main(String[] args){
        System.out.println(solution("RL"));
    }
}