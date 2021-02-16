#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
using namespace std;
int N,r,c;
int whichDistrict(int sy, int sx, int len){
    int halfrow=sy+len/2;
    int halfcol=sx+len/2;

    if(r<halfrow && c <halfcol) return 0;
    if(r<halfrow && c >= halfcol) return 1;
    if(r>=halfrow && c <halfcol) return 2;
    if(r>=halfrow && c >=halfcol) return 3;
}
void goZ(int sy, int sx, int val, int len,int depth){
    if(len==1){
        cout<<val<<'\n';
        exit(0);
    }

    int district=whichDistrict(sy,sx,len);
    int size=pow(2,(N-depth)*2);

    if(district==0){
        goZ(sy,sx,val,len/2,depth+1);
    }
    else if(district==1){
        int ny=sy;
        int nx=sx+len/2;
        goZ(ny,nx,val+size/4,len/2,depth+1);
    }
    else if(district==2){
        int ny=sy+len/2;
        int nx=sx;
        goZ(ny,nx,val+size/2,len/2,depth+1);
    }
    else if(district==3){
        int ny=sy+len/2;
        int nx=sx+len/2;
        goZ(ny,nx,val+size/4*3,len/2,depth+1);
    }
}
int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin>>N>>r>>c;
    goZ(0,0,0,pow(2,N),0);
}