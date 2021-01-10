import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _10282 {
    static int n,d,c;
    static int comps, result;
    static final int INF=Integer.MAX_VALUE;
    static ArrayList<edge>[] adj;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc=0;tc<TC;tc++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            d=Integer.parseInt(st.nextToken());
            c=Integer.parseInt(st.nextToken());
            adj=new ArrayList[n];
            
            for(int i=0;i<n;i++){
                adj[i]=new ArrayList<edge>();
            }
            for(int i=0;i<d;i++){
                st=new StringTokenizer(br.readLine());
                int a=Integer.parseInt(st.nextToken());
                int b=Integer.parseInt(st.nextToken());
                int s=Integer.parseInt(st.nextToken());
                adj[b-1].add(new edge(a-1,s));
            }
            dijkstra(c-1);
            
            sb.append(comps).append(" ").append(result).append('\n');
        }
        System.out.print(sb);

    }
    public static void dijkstra(int src){
        int[] dist=new int[n];
        Arrays.fill(dist, INF);
        dist[src]=0;
        PriorityQueue<edge> pq= new PriorityQueue<>();
        pq.offer(new edge(src,0));

        while(!pq.isEmpty()){
            edge cur= pq.poll();

            if(dist[cur.v]<cur.c) continue;
            
            for(edge next : adj[cur.v]){
                int nextDist=cur.c+next.c;

                if(dist[next.v]>nextDist){
                    dist[next.v]=nextDist;
                    pq.offer(new edge(next.v,nextDist));
                }
            }
        }
        timetoInfect(dist);
    }
    
    public static void timetoInfect(int[] dist) {
        comps=0; result=0;
        for(int elem:dist){
            if(elem!=INF){
                result=Math.max(result,elem);
                comps++;
            }
        }
    }
        
    static class edge implements Comparable<edge>{
        int v,c;
        edge(int v, int c){
            this.v=v;
            this.c=c;
        }
        @Override
        public int compareTo(edge o){
            return Integer.compare(this.c, o.c);
        }
    }
    
}
