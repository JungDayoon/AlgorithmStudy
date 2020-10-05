//#include <algorithm>
//#include <vector>

#include <iostream>

using namespace std;


int main(int argc, const char * argv[]) {
    
    int num;
    int tc;
    
    cin >> num;
    for(tc = 1; tc < num+1; tc++){
        int answer = 0;
        
        int n, k, m;
        cin >> n >> m >> k;
        
        int col[k], row[k], kk[k], way[k];
        for(int i = 0; i < k; i++){
            col[i] = 0;
            row[i] = 0;
            kk[i] = 0;
            way[i] = 0;
        }
        
                
        for(int i = 0; i < k; i++){
            cin >> col[i] >> row[i] >> kk[i] >> way[i];
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < k; j++){
                if(kk[j] != -1){
                    if(way[j] == 1){
                        col[j] = col[j] -1;
                        if(col[j] == 0 || row[j] == 0 || col[j] == n-1 || row[j] == n-1){
                            kk[j] /= 2;
                            way[j] = 2;
                        }
                        
                    }
                    else if(way[j] == 2){
                        col[j] = col[j] +1;
                        
                        if(col[j] == 0 || row[j] == 0|| col[j] == n-1 || row[j] == n-1){
                            kk[j] /= 2;
                            way[j] = 1;
                        }
                    }
                    else if(way[j] == 3){
                        row[j] = row[j] -1;
                        if(col[j] == 0 || row[j] == 0|| col[j] == n-1 || row[j] == n-1){
                            kk[j] /= 2;
                            way[j] = 4;
                        }
                    }
                    else if(way[j] == 4){
                        row[j] = row[j] +1;
                        
                        if(col[j] == 0 || row[j] == 0|| col[j] == n-1 || row[j] == n-1){
                            kk[j] /= 2;
                            way[j] = 3;
                        }
                    }
                }
                
            }
            //check begin
            for(int s = 0; s < k; s++){
                int col_tmp = col[s];
                int row_tmp = row[s];
                int max = kk[s];
                int sum = kk[s];
                int cway = way[s];
                for(int b = s+1; b < k; b++){
                    if(col_tmp==col[b] && row_tmp==row[b]){
                        if(max < kk[b]){
                            sum += kk[b];
                            max = kk[b];
                            kk[b] = 0;
                            cway = way[b];
                        }
                        else{
                            sum += kk[b];
                            kk[b] = 0;
                        }
                    }
                    
                    kk[s] = sum;
                    way[s] = cway;
                }
                
                
            }
            //check end

        }
        for(int j = 0; j < k; j++){
            if(kk[j] == -1){
                continue;
            }
            else{
                answer += kk[j];
            }
        }
        cout << "#"<<tc<<" "<<answer;
        cout << endl;

    }
        return 0;
}