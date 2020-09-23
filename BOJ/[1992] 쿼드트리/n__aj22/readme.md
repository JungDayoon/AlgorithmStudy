# [1992] 쿼드트리

## Algorithm

분할정복

## 코드설명

1. zip_quad(start_x, start_y, width)
  > 
  
    재귀로 구현
    
    영상 압축 함수
    start_x : 시작 x 인덱스
    start_y : 시작 y 인덱스
    width : 확인하려는 영역 width 크기 
    
    종료조건 : 확인하려는 영역의 안의 숫자가 모두 같으면 그 숫자를 문자로 return 하면서 종료
    그렇지 않을 경우 : 왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽 아래 각 영역의 시작 인덱스와 현재 width 의 반인 half_width 를 파라미터로 다시 zip_quad 호출
  
2. main
  > 
    
    N과 영상 내용을 입력받고 처음에는 전체인 zip_quad(0,0,N)를 호출
    
    * 문자열로 입력받은 것을 int 형으로 바꿔 리스트로 저장하려면 : list(map(int,input()))
  
