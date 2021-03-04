분할정복, 재귀

r, c가 포함되지 않은 사분면은 들어갈필요없이 사분면 사이즈를 답에 더해주기만 하면 시간을 절약할 수 있다.

기존 분할정복처럼 문제를 작성하여 1~4사분면을 진입하는데 사전에 r, c가 해당 사분면에 포함되었는가를 체크하는 부분을 짠다.

```java
    s /= 2;
	if(r < x+s && c < y+s) find(s, x, y);
	else cnt += s*s;
	if(r < x+s && y+s <= c) find(s, x, y+s);
	else cnt += s*s;
	if(x+s <= r && c < y+s) find(s, x+s, y);
	else cnt += s*s;
	if(x+s <= r && y+s <= c) find(s, x+s, y+s);
	else cnt += s*s;
```