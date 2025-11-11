#include <bits/stdc++.h>

using namespace std;
#define int long long
signed main(){
    ios::sync_with_stdio(false); 
    cin.tie(nullptr);
    int t;
    cin >>t;
    while(t--){
        int n,m;
        cin >> n >> m;
        vector<int>v;
        vector<int>resp(n);
        int soma = 0;
        for(int i = 0;i<n;i++){
            resp[i] = i;
            soma = soma | resp[i];
        }
        for(int i = 0;i< n;i++){
            if(((~resp[i]) | m) +1){
                resp[i] = m;
            }
        }
        if(soma < m)resp[n-1] = m;
        for(int i = 0;i<n;i++){
            cout << resp[i] << ' ';
        }
        cout << endl;
    }
}
