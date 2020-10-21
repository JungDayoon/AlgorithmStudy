# [BOJ 1516] 게임 개발 - Java

### :computer: Algorithm

> Topological Sort



### :computer: Logic

각 건물마다 먼저 지어져야 하는 건물들이 주어지기 때문에(우선 순위 존재) -> 위상정렬로 풀이할 수 있다.

건물들의 번호로 Adjacency List를 만든다.

각 건물들의 번호로 들어오는 간선의 개수를 indegree 배열에 저장한다.

priority queue로 queue에서 어떤 노드를 먼저 꺼내서 사용할 지 정한다. -> 시간이 짧은 건물 부터 !

<자바에서 PQ 사용법>

```java
static PriorityQueue<Game> pq = new PriorityQueue<>();
	
	static class Game implements Comparable<Game>{
		int num, time;
		Game(int num, int time){
			this.num = num;
			this.time = time;
		}
		
		public int compareTo(Game o) {
			return this.time - o.time;
		}
	}
```



### :computer: Review

자바로 NHN 코테 쀼시자 !!!!!!

