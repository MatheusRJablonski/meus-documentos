#include <bits/stdc++.h>
using namespace std;
#define int long long
signed main(){
    int a,b;
    cin >> a;
    cin >> b;
    vector<int>mom(a);
    int resp = 0;
    for(int i = 0;i<a;i++){
        cin >> mom[i];
    }    
    sort(mom.begin(),mom.end());
    reverse(mom.begin(),mom.end());

    if(a <= 2){
        if( a == 2){
            resp = mom[0];
        }
    }else{
        for(int i = 1;i<a;i++){
            if(b == 0)break;
            resp += mom[i];
            b--;
        }
    }
    cout << resp << endl;
}