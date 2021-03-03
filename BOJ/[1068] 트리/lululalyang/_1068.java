import java.io.*;
import java.util.*;

public class _1068 {
	static ArrayList<Integer>[] g;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		g = new ArrayList[N];
		for(int i=0; i<N; i++)
			g[i] = new ArrayList<>();
		
		String[] s = br.readLine().split(" ");
		int Delete = Integer.parseInt(br.readLine());
		
		int Root = -1;
		for(int i=0; i<N; i++) {
			int p = Integer.parseInt(s[i]);
			if(p == -1)
				Root = i;
			else
				g[p].add(i);
		}
		
		System.out.print(CountLeaf(N, Root, Delete));
	}
	
	private static int CountLeaf(int N, int Root, int Delete) {
		if(Root == Delete)
			return 0;
		
		int cnt = 0;
		boolean[] deleteNode = new boolean[N];
		ChkDeleteNode(Root, Delete, false, deleteNode);
		
		for(int i=0; i<N; i++) {
			if(deleteNode[i])
				g[i].clear();
		}
		
		for(int i=0; i<N; i++) {
			if(!deleteNode[i] && g[i].size()==0)
				cnt++;
		}
		
		return cnt;
	}
	
	private static void ChkDeleteNode(int now, int Dnode, boolean delete, boolean[] deleteNode) {
		Iterator<Integer> itr = g[now].iterator();
		
		while(itr.hasNext()) {
			int next = itr.next();
			
			if(!delete) { // Dnode찾기
				if(next == Dnode) {
					g[now].remove(g[now].indexOf(Dnode));
					deleteNode[next] = true;
					ChkDeleteNode(next, Dnode, true, deleteNode);
					return;
				}else {
					ChkDeleteNode(next, Dnode, delete, deleteNode);
				}
			}else { // 자식 노드 지우기 (delete == true)
				deleteNode[next] = true;
				ChkDeleteNode(next, Dnode, delete, deleteNode);
			}
		}
	}
}
