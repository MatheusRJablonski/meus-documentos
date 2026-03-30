#include <bits/stdc++.h>

using namespace std;


int main(){
    int n, t,k,p,m;
    cin >> t;
    while(t--){
       cin >> k >> p >> n >> m;
       vector<int>a(k);
       for (int i = 0;i<k;i++){
            cin >> a[i];
       }
       if(p >= n){
        int j = n;
        while(m > 0){
            m -= a[j];
        }
       }
    }
}