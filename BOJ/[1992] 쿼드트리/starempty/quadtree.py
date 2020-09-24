def main():
  n = int(input())
  v = []
  for i in range(n):
    tmp = str(input())
    v.append(list(map(int, list(tmp))))
  def quadtree(x, y, length):
    start = v[y][x]
    new = int(length/2)
    for i in range(y, y+length):
      for j in range(x, x+length):
        if start != v[i][j]:
          start = -1
          break
      if start == -1:
        break
    if start == -1:
      print("(", end='')
      quadtree(x, y, new)
      quadtree(x+new, y, new)
      quadtree(x, y+new, new)
      quadtree(x+new, y+new, new)
      print(")", end='')
    else:
      print(start, end='')  
  quadtree(0, 0, n)

main()