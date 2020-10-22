import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[][] puyoMap = new int[12][6];
	static int[][] pos = {{1,0},{-1,0}, {0,1}, {0,-1}};
	static ArrayList<Puyo> deleteList = new ArrayList<>();
	static ArrayList<Puyo> tmpList = new ArrayList<>();
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			String puyoStr = br.readLine();
			for(int j = 0; j<6; j++)
			{
				if(puyoStr.charAt(j) == '.')
					puyoMap[i][j] = 0;
				else if(puyoStr.charAt(j) == 'R')
					puyoMap[i][j] = 1;
				else if(puyoStr.charAt(j) == 'G')
					puyoMap[i][j] = 2;
				else if(puyoStr.charAt(j) == 'B')
					puyoMap[i][j] = 3;
				else if(puyoStr.charAt(j) == 'P')
					puyoMap[i][j] = 4;
				else
					puyoMap[i][j] = 5;
			}
		}
	}

	public static class Puyo{
		int y;
		int x;
		
		Puyo(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	public static boolean isIn(int y, int x) {
		if(0<=y && y<12 && 0<=x && x <6)
			return true;
		return false;
	}
	
	
	public static int bfs(int y, int x, int color, boolean[][] visited)
	{
		Queue<Puyo> queue = new LinkedList<>();
		queue.offer(new Puyo(y, x));
		visited[y][x] = true;
		int count = 0;
		
		while(!queue.isEmpty())
		{
			Puyo curr = queue.poll();
			tmpList.add(curr);
			count += 1;
			
			for(int i = 0; i<4; i++)
			{
				int ny = curr.y + pos[i][0];
				int nx = curr.x + pos[i][1];
				
				if(isIn(ny, nx) && !visited[ny][nx] && puyoMap[ny][nx] == color)
				{
					Puyo newPuyo = new Puyo(ny, nx);
					queue.offer(newPuyo);
					visited[ny][nx] = true;

				}
			}
		}
		
		return count;
	}
	
	public static void removeEmpty()
	{
		for(int j = 0; j<6; j++)
		{
			ArrayList<Integer> newRow = new ArrayList<>();
			for(int i = 0; i<12; i++)
			{
				if(puyoMap[i][j] != 0) {
					newRow.add(puyoMap[i][j]);
					puyoMap[i][j] = 0;
				}				
			}
			
			int idx = 11;
			for(int i = newRow.size()-1; i>=0; i--)
			{
				puyoMap[idx][j] = newRow.get(i);
				idx--;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		input();
		
		int resCnt = 0;
		while(true)
		{
			boolean flag = false;
			deleteList.clear();
			boolean[][] visited = new boolean[12][6];
			
			for(int i = 0; i<12; i++)
			{
				for(int j = 0; j<6; j++)
				{
					if(puyoMap[i][j] != 0 && !visited[i][j])
					{
						tmpList.clear();
						int nowCnt = bfs(i, j, puyoMap[i][j], visited);
						if(nowCnt >= 4) {
							flag = true;
							for(Puyo p: tmpList) {
								deleteList.add(new Puyo(p.y, p.x));
							}
						}
					}
				}
			}
			if(!flag)
				break;
			
			for(Puyo dP : deleteList)
			{
				puyoMap[dP.y][dP.x] = 0;
			}
			
			removeEmpty();
			

			resCnt += 1;
		}
		
		System.out.println(resCnt);
		
	}
}
