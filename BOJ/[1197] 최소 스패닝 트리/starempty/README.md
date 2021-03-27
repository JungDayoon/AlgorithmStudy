MST 크루스칼

최소스패닝트리(MST)를 구현해보는 문제이다 - 인접정보는 행렬/리스트로 구현 가능

나는 Union-find를 써서 크루스칼로 구현했다. 크루스칼을 쓸때는 인접정보 자체를 저장한 배열을 만들어주면 가장 편했다. 최소비용을 찾기 때문에 인접정보리스트를 비용크기별로 정렬하면 사용하기 편리하다.
유니온파인드는 각 정점의 부모정보를 저장하는 부모배열을 만들어 사용한다.

Union find 기본 코드
```
private static boolean union(int a, int b){
    a = find(a);
    b = find(b);
    if(a == b) return false; //false 반환을 통하여 메인에서 find를 두 번 할 필요가 없다
    parents[b] = a;
    return true;
}
private static int find(int x){
    if(parents[x] == x) return x;
    return parents[x] = find(parents[x]); // path compression
}
```

다른 방법으로는 프림이 있는데, 프림은 연결된 정점과 그 정점을 거쳐서 갈 수 있는 다른 간선의 가중치를 비교하여 갱신하는 특성이 있다. 따라서 각 정점의 가중치를 저장해줄 수 있도록 배열(dist[])을 하나 선언해줘야한다. 