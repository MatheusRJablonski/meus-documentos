#include <bits/stdc++.h>

using namespace std;


int main(){
    int n,m;
    cin >> n >> m;
    int maior = max(n,m);
    vector<vector<char>>a(n,vector<char>(m));
    for(int i =0;i<n;i++){
        for(int j = 0;j<m;j++) cin >> a[i][j];
    }
    int test[4] = {1,1,1,1};
    int resp = 8;
        for(int i =0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(n == m){
                    if(a[(n-1)-j][i] != a[i][j] && test[1] == 1){
                        resp-=2;
                        test[1] = 0;
                    }
                    if(a[j][m-1-i] != a[i][j] && test[2] == 1){
                        resp-=2;
                        test[2] = 0;
                    }
                }else if(test[3] == 1){
                    resp-=4;
                    test[3] = 0;
                }
                if(a[n-1-i][m-1-j] != a[i][j] && test[0] == 1){
                    resp-=2;
                    test[0] = 0;
                }
            }
        }
        if(resp == 2){
            for(int i =0;i < n;i++){
                for(int j = 0;j < m;j++){
                    if((a[i][j] != a[i][m-1-j]) && a[i][j] != a[n-1-i][j]){
                        resp--;
                        break;
                    }
                }
                if(resp == 1) break;
            }
        }
        cout << resp;
        cout << endl;
}
 



