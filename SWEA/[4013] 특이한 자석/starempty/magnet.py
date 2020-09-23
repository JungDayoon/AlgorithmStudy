def check(a,b,m):
  
  left = a-1
  arr = []
  lb = b*-1
  rb = b*-1
  while left > 0:
    if m[left-1][2] != m[left][-2]:
      arr.append((left-1, lb))
    else:
      break
    left -= 1
    lb = lb*-1

  right = a-1
  while right < 3:
    if m[right][2] != m[right+1][-2]:
      arr.append((right+1, rb))
    else:
      break
    right += 1
    rb = rb*-1
  return arr
  

def rotation(a,b,m):
  tmp_a = []
  if b == 1:
    tmp = m[a][-1]
    tmp_a.append(tmp)
    for j in m[a][:-1]:
      tmp_a.append(j)
      m[a] = tmp_a
  elif b == -1:
    tmp = m[a].pop(0)
    m[a].append(tmp)
  return m

def main():
  tc = int(input())
  for test in range(1, tc+1):
    n = int(input())
    m = []
    rot = []
    answer = 0
    
    for i in range(4):
      m.append(list(map(int, input().split())))

    for i in range(n):
      a,b = map(int, input().split())
      rot = check(a,b,m)
      m = rotation(a-1,b,m)
  
      if rot:
        for j in rot:
          m = rotation(j[0], j[1], m)
         
    for i in range(len(m)):
      if m[i][0] == 1:
        answer += 2**i
      
    print("#",end="")
    print(test, end=" ")
    print(answer)

main()

