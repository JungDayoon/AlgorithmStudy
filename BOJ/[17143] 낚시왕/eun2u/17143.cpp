#include <iostream>
#include <utility>
#include <string>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;
int R,C,M;
int dy[4]={-1,1,0,0};
int dx[4]={0,0,1,-1};
struct SHARK{
    int r,c, s,d,z; //s는 속력, d는 방향, z는 크기
};
typedef struct SHARK SHARK;
vector<SHARK> shark;
SHARK board[100][100];
int result=0;

bool cmp_SHARK(SHARK a, SHARK b) { return a.z > b.z; }
void killShark(int loc){
    //사람 col에 존재하는 상어 중에 row값이 가장 작은 상어 죽임
    for(int i=0;i<R;i++){
        if(board[i][loc].z){
            //cout<<"plus"<<board[i][loc].z<<'\n';
            result+=board[i][loc].z;
            board[i][loc].d=0;
            board[i][loc].s=0;
            board[i][loc].z=0;
            break;
        }
    }
    //죽은 상어를 없애고 board에 존재하는 상어벡터 생성
    shark.clear();
    for(int i=0;i<R;i++){
        for(int j=0;j<C;j++){
            if(board[i][j].z){
                SHARK val;
                val.r=i;
                val.c=j;
                val.s=board[i][j].s;
                val.d=board[i][j].d;
                val.z=board[i][j].z;

                shark.push_back(val);
            }
        }
    } 
    //상어벡터를 크기순으로 정렬
    sort(shark.begin(),shark.end(),cmp_SHARK);
}
void moveShark(SHARK shrk){
    
    int y=shrk.r;
    int x=shrk.c;
    int dir=shrk.d;
    int remains=shrk.s;
    int diff=0;
    
    while(remains>0){
        if(dir==0){//위로
            diff=y-0;
            if(remains > diff){ //가야할 거리가 diff보다 클때
                remains-=diff;
                dir=1;
                y=0;
            }
            else{ //가야할 거리가 diff보다 작거나 같을 때
                y-=remains;
                break;
            }
        }
        else if(dir==1){//아래로
            diff=R-1-y;
            if(remains>diff){
                remains-=diff;
                dir=0;
                y=R-1;
            }
            else{
                y+=remains;
                break;
            }
        }
        else if(dir==3){//왼쪽으로
            diff=x-0;
            if(remains>diff){
                remains-=diff;
                dir=2;
                x=0;
            }
            else{
                x-=remains;
                break;
            }
        }
        else if(dir==2){//오른쪽으로
            diff=C-1-x;
            if(remains>diff){
                remains-=diff;
                dir=3;
                x=C-1;
            }
            else{
                x+=remains;
                break;
            }
        }
    }

    if(board[y][x].z) //넣으려는 곳에 상어가 이미 존재하면, 
        if(board[y][x].z > shrk.z)
            return;

    SHARK val;
    val.r=0;
    val.c=0;
    val.s=shrk.s;
    val.d=dir;
    val.z=shrk.z;
    board[y][x]=val;
}

void initBoard(){ 
    SHARK val;
    val.r=0;
    val.c=0;
    val.s=0;
    val.d=0;
    val.z=0;
    for(int i=0;i<R;i++)
        for(int j=0;j<C;j++)
            board[i][j]=val;
}
void movePerSec(){
    int person=0;
    
    while(person < C){
        killShark(person);
        initBoard();
        for(int i=0;i<shark.size();i++)
            moveShark(shark[i]);
        person++;
    }
}
int main(){

    cin>>R>>C>>M;

    int r,c,s,d,z;
    for(int i=0;i<M;i++){
        cin>>r>>c>>s>>d>>z;
        SHARK input;
        input.s=s;
        input.d=d-1;
        input.z=z;
        board[r-1][c-1]=input;
    }

    movePerSec();
    cout<<result<<'\n';
    return 0;
}