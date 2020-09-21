def main():
  n = int(input())
  m = int(input())
  left, right = 1, m

  while left <= right:
    mid = (left+right)//2

    cnt = 0
    for i in range(1, n+1):
        cnt += min(mid//i, n)

    if cnt < m:
      left = mid + 1
    else:
      right = mid -1
  print(left)
main()