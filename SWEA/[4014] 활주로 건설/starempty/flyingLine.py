def check(arr, x):
  n = len(arr)
  prev = arr[0]
  const = []
  for i in range(n):
    const.append(0)
  flag = True
  for i in range(1, n):
    re = 0
    cur = arr[i]
    if cur == prev:
      prev = cur
    elif cur-prev == -1:
      if i+x <= n:
        for j in range(1, x):
          if cur != arr[i+j] or const[i+j-1] == 1:
            re = -1
            break
          else:
            const[i+j-1] = 1
        if re == -1:
          flag = False
          break
        else:
          const[i+x-1] = 1
          prev = cur
      else:
        flag = False
        break
    elif cur-prev == 1:
      if i-x >= 0:
        for j in range(1,x):
          if const[i-j] != 1 and const[i-j-1] != 1:
            if arr[i-j] != arr[i-j-1] or const[i-j] == 1:
              re = -1
              break
            else:
              const[i-j] = 1
          else:
              re = -1
              break
        if re == -1:
          flag = False
          break
        else:
          const[i-x] = 1
          prev = cur
      else:
        flag = False
        break
    else:
      flag = False
      break
      
  if flag:
    return 1
  else:
    return 0

def main():
  num = int(input())
  for tc in range(1, num+1):
    answer = 0
    n, x = map(int, input().split())
    array = []
    for i in range(n):
      tmp = list(map(int, input().split()))
      array.append(tmp)
    for i in range(n):
      answer += check(array[i], x)
      tmp = []
      for j in range(n):
        tmp.append(array[j][i])
      answer += check(tmp, x)


    print("#{} {}".format(tc, answer))
main()