#include <bits/stdc++.h>
using namespace std;
#define int long long
signed main(){
    string a,b;
    cin >> a;
    cin >> b;
    int tam = a.size();
    int resp = 0;
    for(int i = 0;i<b.size();i++){
        for(int j = 0;j<a.size();j++){
            if(b[j+i] != a[j]){
                break;
            } 
            if(j == a.size()-1){
                resp++;
            } 
        }
    }
    cout << resp ;
}