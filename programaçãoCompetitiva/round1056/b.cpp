#include <bits/stdc++.h>
 
using namespace std;
 
int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    long long n;
    cin >> n;
    while(n--){
        int k,v;
        cin >> k >> v;
        vector<vector<char>> ans(k,vector<char>(k));
        if (k*k - v == 1) {
            cout << "NO\n";
        }
        else if(k == 2){
            cout << "YES\n";
            if(v == 1){
                cout << "UD" << '\n';
                cout << "RL" << '\n';
            }else if(v == 0){
                cout << "DD" << '\n';
                cout << "UU" << '\n';
            }else if(v == 2){
                cout << "UU" << '\n';
                cout << "RL" << '\n';
            }else if(v >= 4){
                cout << "UU" << '\n';
                cout << "UU" << '\n';
            }
        }

         else{
            cout << "YES" << '\n';
            int dif = k - v;
            int quant;
            if(dif > 0){
                quant = k*k - v + dif; 
            }else{
                quant = k*k - v; 
            }
            for(int i = 0;i<k;i++){
                for(int j = 0;j<k;j++){
                    if(dif > 0){
                        ans[i][j] = 'D';
                        dif--;
                    }else{
                        ans[i][j] = 'U';
                    }
                }
            }
            
            int p = 0;
            while(quant > 0 && p < k){
                
                if(quant  >= k-1){
                    ans[1][p] = 'D';
                    quant -= k-1;
                    p++;
                }else if(quant > 1){
                    ans[k-quant][p] = 'D';
                    quant = 0;
                }else{
                    ans[k-1][p] = 'L';
                    break;
                }  
            }
             
            for(int i = 0;i<k;i++){
                for(int j = 0;j<k;j++){
                    cout << ans[i][j];
                }
                cout << endl;
            }
        } 
    }
}