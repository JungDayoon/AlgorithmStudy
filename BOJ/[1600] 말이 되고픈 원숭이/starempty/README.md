BFS

기존 BFS 코드 +

점프 횟수별 이동루트가 완전히 달라지기 때문에 방문배열을 3차원으로 구성하는게 포인트..

계속해서 메모리 초과가 났었는데 `if(0>nx||nx>=n || 0>ny||ny>=m || visit[nk+1][nx][ny] || map[nx][ny] == 1) continue;`

위와 같이 continue처리를 해주니까 성공했다.

DFS, BFS는 가지치기가 중요하다.
