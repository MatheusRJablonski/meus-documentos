#include <bits/stdc++.h>
using namespace std;
#define int long long

signed main(){
    int t;
    cin >> t;
    while(t--){
        int n,a,b;
        cin >> n;
        vector<int> valores(n);
        vector<int> cnt1(31, 0);
        long long soma = 0;
        
        for(int i = 0; i < n; i++){
            cin >> valores[i];
        }
        for(int x : valores)
            for(int i = 0; i < 31; i++)
                cnt1[i] += (x >> i) & 1;

        long long best = 0;
        for (int i = 30; i >= 0; i--){
            cout << cnt1[i] << " ";
        }
        
        for(int k = 0; k < n; k++){
            long long s = 0;
            for(int i = 30; i >= 0; i--){
                long long diff = ((valores[k] >> i) & 1) ? (n - cnt1[i]) : cnt1[i];
                s += diff << i;
            }
            best = max(best, s);
        }
        cout << best << "\n";
        
    }
}