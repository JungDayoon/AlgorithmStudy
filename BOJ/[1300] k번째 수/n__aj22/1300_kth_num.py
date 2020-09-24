n = int(input())
k = int(input())

def check_num(num):
  check_num = 0
  for i in range(1, int(num**0.5)+1):
    if (num%i == 0) and ((num//i)<=n):#n=3
      check_num+=1
      if i!=num//i:
        check_num+=1
  return check_num

i=1
while(True):
  if(k<=0):
    break;
  k-=check_num(i)
  i+=1
print(i-1)
  
