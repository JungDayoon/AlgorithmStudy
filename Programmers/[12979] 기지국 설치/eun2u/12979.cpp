#include <iostream>
#include <vector>
using namespace std;

int makeStation(int len,int w){
    int prop=w*2+1;
    if(len%prop==0)
        return len/prop;
    else 
        return len/prop+1;
}
int solution(int n, vector<int> stations, int w)
{
    int answer = 0;
    int s=1,d,len,num;
    int prop=w*2+1;

    if(stations[0]>w+1){
        len=stations[0]-(w+1);
        answer+=makeStation(len,w);
    }
    for(int i=0;i<stations.size()-1;i++){
        if(stations[i+1]-stations[i]>prop){// 사이에 기지국 설치 해야함
            s=stations[i]+(w+1);
            d=stations[i+1]-(w+1);
            len=d-s+1;
            answer+=makeStation(len,w);
        }
    }
    int last=stations.size()-1;
    len=n-(stations[last]+w);
    if(len>0){
        answer+=makeStation(len,w);
    }

    return answer;
}