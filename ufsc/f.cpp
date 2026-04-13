#include <bits/stdc++.h>
using namespace std;
#define int long long
signed main(){
    int a,b;
    cin >> a;
    vector<int>mom(a);
    int resp = 0;
    int resp2 = 0;
    for(int i = 0;i<a;i++){
        cin >> mom[i];
    } 
    mom.insert(0);
    for(int i = 0;i<a;i++){
        if(mom[i] > 0){
            if(mom[i-1]-mom[i+1]/-2 == mom[i]){
                resp += mom[i];
            }
        }
    }
    resp = (resp*-1)/resp2;
    cout << resp << endl;
}